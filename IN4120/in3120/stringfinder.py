# pylint: disable=missing-module-docstring
# pylint: disable=line-too-long
# pylint: disable=too-few-public-methods

from dataclasses import dataclass
from typing import Iterator, Any, List
from .analyzer import Analyzer
from .trie import Trie


class StringFinder:
    """
    Given a trie encoding a dictionary of strings, efficiently finds the subset of strings in the dictionary
    that are also present in a given text buffer. I.e., in a sense computes the "intersection" or "overlap"
    between the dictionary and the text buffer.

    Uses a trie-walk algorithm similar to the Aho-Corasick algorithm with some simplifications (we ignore the
    part about failure transitions) and some minor NLP extensions. The running time of this algorithm is in
    practice virtually insensitive to the size of the dictionary, and linear in the length of the buffer we
    are searching in.

    The analyzer we use when scanning the input buffer is assumed to be the same as the one that was used
    when adding strings to the trie.
    """

    @dataclass
    class State:
        """
        A currently explored state, as the scan proceeds.
        """
        node: Trie  # The current position in the trie, after having consumed zero or more characters.
        begin: int  # The index into the original buffer where the state was "born".
        match: str  # The symbols consumed so far to get to the current state.

    @dataclass
    class Result:
        """
        An individual result of the scan, as reported back to the client.
        """
        match: str        # The matching dictionary entry.
        meta: None | Any  # Optional mata data associated with the match, if present in the dictionary.
        surface: str      # The part of the input buffer that triggered the match, space-normalized.
        begin: int        # The index into the original buffer where the surface form starts.
        end: int          # The index into the original buffer where the surface form ends.

    def __init__(self, trie: Trie, analyzer: Analyzer):
        self._trie = trie          # The set of strings we want to detect in the scanned buffer.
        self._analyzer = analyzer  # The same that was used when the trie was built.

    def scan(self, buffer: str) -> Iterator[Result]:
        """
        Scans the given buffer once and finds all dictionary entries in the trie that are also present in the
        buffer. We only consider matches that begin and end on token boundaries.

        In a serious application we'd add more lookup/evaluation features, e.g., support for prefix matching,
        support for leftmost-longest matching (instead of reporting all matches), and more.
        """
        terms = list(self._analyzer.terms(buffer)) 
        results = [] 
        
        for i in range(len(terms)): 
            match_symbols = [] 
            state = self.State( 
                node = self._trie, 
                begin = terms[i][1][0], 
                match = "" 
            ) 
            
            for j in range(i, len(terms)): 
                symbol, span = terms[j] 
                match_symbols.append(symbol)

                if j + 1 < len(terms):
                    next_span = terms[j + 1][1]
                    separator = "" if span[1] == next_span[0] else " "
                else:
                    separator = " "

                normalized_string = separator.join(match_symbols) 
                node = self._trie.consume(normalized_string) 
                
                if node is None: 
                    break 
                
                state.node = node
                state.match = normalized_string
                
                if state.node.is_final(): 
                    surface = " ".join(buffer[state.begin:span[1]].split()) 
                    result = self.Result( 
                        match = state.match, 
                        meta = state.node.get_meta(), 
                        surface = surface, 
                        begin = state.begin, 
                        end = span[1] 
                    )
                    
                    results.append(result)
            
        for res in sorted(results, key = lambda r: (r.end, r.begin)): 
            yield res