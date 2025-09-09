# pylint: disable=missing-module-docstring
# pylint: disable=line-too-long
# pylint: disable=fixme
# pylint: disable=too-few-public-methods
# pylint: disable=too-many-locals
# pylint: disable=too-many-arguments

import math
from dataclasses import dataclass
from typing import Iterator, Any, Callable
from .edittable import EditTable
from .analyzer import Analyzer
from .sieve import Sieve
from .trie import Trie


class EditSearchEngine:
    """
    Realizes a simple edit distance lookup engine, that, given a larger set of strings encoded
    in a trie, finds all strings in the trie that are close to a given query string in terms of edit
    distance.
    
    See the paper "Tries for Approximate String Matching" by Shang and Merrett for details. This
    implementation assumes that we set an upper bound on the allowed edit distance (treating anything
    above this bound as infinity and non-retrievable), and that this upper bound is relatively small.
    Imposing a small upper bound allows us to prune the search space and make the search reasonably
    efficient.
    """

    @dataclass
    class Options:
        """
        Query-time options. Controls lookup behavior.
        """
        upper_bound: int = 1          # The maximum allowed edit distance between the query and a match.
        candidate_count: int = 10000  # The maximum number of candidate matches we score.
        hit_count: int = 10           # The maximum number of scored matches we will emit.
        first_n: int = 0              # Assume that the first N query characters are correct, to reduce the search space.
        scoring: str = "normalized"   # The scoring function to apply to candidate matches.

    @dataclass
    class Result:
        """
        An individual lookup result, as reported back to the client.
        """
        match: str        # The matching dictionary entry.
        meta: Any | None  # Optional meta data associated with the match, if present in the dictionary.
        score: float      # The score associated with the match, per the chosen scoring function.
        distance: int     # The edit distance between the query and the match.

    def __init__(self, trie: Trie, analyzer: Analyzer):
        self._trie = trie
        self._analyzer = analyzer  # The same as was used for trie building.

    def evaluate(self, query: str, options: Options | None = None) -> Iterator[Result]:
        """
        Locates all strings in the trie that are no more than a given number of edit errors away
        from the query string.

        The matching strings, if any, are scored and only the highest-scoring matches are yielded
        back to the client.
        """
        assert options.scoring in ["normalized", "negated", "lopresti"]
        if not options:
            options = EditSearchEngine.Options()
        
        def calculate_score(scoring: str, distance: int, candidate: str):
            match scoring:
                case "normalized":
                    return 1.0 - (distance / max(len(candidate), 1))
                case "negated":
                    return distance * -1
                case "lopresti":
                    length = 0
                    for i in range(min(len(query), len(candidate))):
                        if query[i] == candidate[i]:
                            length += 1
                            continue
                        break
                    return (2 * length) / (len(query) + len(candidate))
                case _:
                    return 1.0 - (distance / max(len(candidate), 1))
        
        candidates = Sieve(max(1, options.hit_count))
        def callback(distance: int, candidate: str, meta: Any) -> bool:
            if distance <= options.upper_bound and len(candidates) < options.candidate_count:
                score = calculate_score(options.scoring, distance, candidate)
                candidates.sift(
                    score = score, 
                    item = (candidates.sifted(), EditSearchEngine.Result(
                        match = candidate,
                        meta = meta,
                        score = score,
                        distance = distance,
                    ))
                )
            return len(candidates) < options.hit_count
        
        terms = self._analyzer.terms(query)
        for term, _ in terms:
            first_n = max(0, options.first_n)
            new_term = term[:first_n]
            
            start_node = self._trie.consume(new_term) if first_n > 0 else self._trie
            if start_node is None:
                continue
            
            edit_table = EditTable(term, new_term)
            self._dfs(
                node = start_node,
                level = first_n,
                table = edit_table,
                upper_bound = options.upper_bound,
                callback = callback
            )

        # map to results
        return map(lambda item: item[1][1], candidates.winners())
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def _dfs(self, node: Trie, level: int, table: EditTable, upper_bound: int, callback: Callable[[int, str, Any], bool]) -> bool:
        """
        Does a recursive depth-first search in the trie, pruning away paths that cannot lead
        to matches with a sufficiently low edit cost. See paper by Shang and Merrett for a
        detailed discussion.

        Returns True unless the supplied callback tells us to abort the search.

        As this implementation is recursive, the call stack might blow up if we go really
        many levels deep into the trie. That should not be an issue as the primary use case
        for this search is to consult a simple spellchecking dictionary of strings all having
        reasonable lengths, but could merit a second look if we look to apply this to other
        use cases.
        """
        
        if node.is_final():
            distance = table.distance(level)
            if distance <= upper_bound:
                if not callback(distance, table.prefix(level), node.get_meta()):
                    return False

        for transition in node.transitions():
            min_value = table.update2(level + 1, transition)
            if min_value <= upper_bound: 
                if not self._dfs(
                    node = node[transition],
                    level = level + 1,
                    table = table,
                    upper_bound = upper_bound,
                    callback = callback
                ):
                    return False
        
        return True
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")
