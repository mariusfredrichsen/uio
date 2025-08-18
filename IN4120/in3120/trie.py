# pylint: disable=missing-module-docstring
# pylint: disable=line-too-long
# pylint: disable=protected-access

from __future__ import annotations
from abc import ABC, abstractmethod
from itertools import repeat
from typing import Dict, Any, Tuple, Iterable, Iterator
from .analyzer import Analyzer


class Trie(ABC):
    """
    Simple abstract base class for tries. A node in the trie is also itself a trie.

    We can, optionally, associate a meta data value of some kind with each final/terminal state
    the trie/automaton. Some applications might benefit from this, if we need to keep rich meta data
    associated with each string. In such cases, these associated values could be other strings, or
    values that can be used as lookup keys into, e.g., some external database. If a string to add
    has no meta data associated with it, we associate the value None.
    """

    def __contains__(self, string: str) -> bool:
        descendant = self.consume(string)
        return descendant is not None and descendant.is_final()

    def __iter__(self) -> Iterator[str]:
        return self.strings()

    def __getitem__(self, prefix: str) -> Trie | None:
        return self.consume(prefix)

    def consume(self, prefix: str) -> Trie | None:
        """
        Consumes the given prefix verbatim and returns the resulting descendant node,
        if any. I.e., if strings that have this prefix have been added to the trie, then
        the trie node corresponding to traversing the prefix is returned. Otherwise, None
        is returned.

        Assumes that the prefix is already normalized.
        """
        node = self
        for symbol in prefix:
            node = node.child(symbol)  # type: ignore[assignment]
            if node is None:
                return None
        return node

    @abstractmethod
    def child(self, transition: str) -> Trie | None:
        """
        Returns the immediate child node, given a transition symbol. Returns None if the transition
        symbol is invalid. Functionally equivalent to consume(transition), but simpler and for the
        special case of a single transition symbol and not a longer string.

        Assumes that the transition symbol is already normalized.
        """
        raise NotImplementedError()

    def strings(self) -> Iterator[str]:
        """
        Yields all strings that are found in or below this node. For simple testing and debugging purposes.
        The returned strings are emitted back in lexicographical order.
        """
        stack = [(self, "")]
        while stack:
            node, prefix = stack.pop()
            if node.is_final():
                yield prefix
            for symbol in reversed(list(node.transitions(True))):
                stack.append((node.child(symbol), prefix + symbol))  # type: ignore[arg-type]

    @abstractmethod
    def transitions(self, sort: bool = True) -> Iterator[str]:
        """
        Returns the set of symbols that are valid outgoing transitions, i.e., the set of symbols that
        when consumed by this node would lead to a valid child node. The returned transitions are
        emitted back in lexicographical order, if specified.
        """
        raise NotImplementedError()

    @abstractmethod
    def is_final(self) -> bool:
        """
        Returns True iff the current node is a final/terminal state in the trie/automaton, i.e.,
        if a string has been added to the trie where the end of the string ends up in this node.
        """
        raise NotImplementedError()

    def has_meta(self) -> bool:
        """
        Returns True iff the current node is a final/terminal state that has meta data associated
        with it.
        """
        return self.get_meta() is not None

    @abstractmethod
    def get_meta(self) -> Any | None:
        """
        Returns the meta data associated with the final/terminal state, or None if no such meta
        data exists.
        """
        raise NotImplementedError()


class SimpleTrie(Trie):
    """
    A very simple and straightforward implementation of a trie for demonstration purposes
    and tiny dictionaries.

    A serious real-world implementation of a trie or an automaton would not be implemented
    this way. The trie/automaton would then instead be encoded into a single contiguous buffer
    and there'd be significant attention on memory consumption and scalability with respect to
    dictionary size.

    Some plausible open source alternatives include, e.g.:

    - Marisa (https://github.com/pytries/marisa-trie)
    - DAWG (https://dawg.readthedocs.io/en/latest/)
    - datrie (https://pypi.org/project/datrie/)
    - hat-trie (https://github.com/pytries/hat-trie)
    """

    def __init__(self):
        self._children: Dict[str, None | SimpleTrie] = {}

    @staticmethod
    def from_strings(strings: Iterable[str], analyzer: Analyzer) -> SimpleTrie:
        """
        Constructor-like convenience method. Creates and returns a new trie containing
        all the given strings.
        """
        return SimpleTrie.from_strings2(zip(strings, repeat(None)), analyzer)

    @staticmethod
    def from_strings2(strings: Iterable[Tuple[str, None | Any]], analyzer: Analyzer) -> SimpleTrie:
        """
        Constructor-like convenience method. Creates and returns a new trie containing
        all the given (string, meta) pairs.
        """
        root = SimpleTrie()
        root.add2(strings, analyzer)
        return root

    def _add(self, string: str, meta: None | Any) -> None:
        """
        Internal helper method, adds the given non-empty string and its optional
        associated meta data to the trie with this node as the root. The string is
        assumed already properly normalized at this point.

        The special transition symbol "" is used as a marker to indicate that a node
        is final/terminal. The meta data, if any, is associated with this special
        transition symbol.
        """
        trie = self
        for symbol in string:
            if symbol not in trie._children:
                trie._children[symbol] = SimpleTrie()
            trie = trie._children[symbol]  # type: ignore[assignment]
        if "" in trie._children:
            assert trie._children[""] == meta
        else:
            trie._children[""] = meta

    def add(self, strings: Iterable[str], analyzer: Analyzer) -> None:
        """
        Adds all the strings to the trie, after normalizing them. The tokenizer is used so
        that we're robust to nuances in whitespace and punctuation.

        Adding the same string more than once is benign and idempotent. Note that "same" here
        means after normalization.
        """
        self.add2(zip(strings, repeat(None)), analyzer)

    def add2(self, strings: Iterable[Tuple[str, None | Any]], analyzer: Analyzer) -> None:
        """
        Adds all the strings and their associated meta data values to the trie,
        after normalizing them. The tokenizer is used so that we're robust to nuances
        in whitespace and punctuation.

        If a string has no meta data associated with it, None is assumed passed as the
        meta data value.

        Adding the same string more than once is benign and idempotent, as long as their
        associated meta data values do not differ. Note that "same" here means after
        normalization.
        """
        for string, meta in strings:
            assert string is not None
            self._add(analyzer.join(string), meta)

    def child(self, transition: str) -> None | Trie:
        return self._children.get(transition, None)

    def transitions(self, sort: bool = True) -> Iterator[str]:
        children = (s for s in self._children if s)
        yield from sorted(children) if sort else children

    def is_final(self) -> bool:
        return "" in self._children

    def get_meta(self) -> None | Any:
        return self._children[""] if self.is_final() else None
