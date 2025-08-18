# pylint: disable=missing-module-docstring
# pylint: disable=missing-class-docstring
# pylint: disable=missing-function-docstring
# pylint: disable=too-few-public-methods
# pylint: disable=line-too-long
# pylint: disable=superfluous-parens
# pylint: disable=invalid-name
# pylint: disable=too-many-locals

import ast
from typing import List, Dict
from .booleansearchengine import BooleanSearchEngine
from .corpus import Corpus
from .invertedindex import InvertedIndex
from .wildcardexpander import WildcardExpander
from .normalizer import SoundexNormalizer
from .analyzer import DummyAnalyzer
from .editsearchengine import EditSearchEngine
from .trie import Trie, SimpleTrie


class ExtendedBooleanSearchEngine(BooleanSearchEngine):
    """
    Similar to BooleanSearchEngine, but adds some new operators. Relies on that
    each of the new operators can be rewritten into an OR-query.
    """

    def __init__(self, corpus: Corpus, inverted_index: InvertedIndex, synonyms: Trie | None):

        # Deal with stuff above.
        super().__init__(corpus, inverted_index)

        # Our ability to do some forms of index term expansion that makes sense to the user
        # depends on how the inverted index was created. We just know the terms, not the surface
        # form tokens that gave rise to the terms (and which the user relates to.) The terms we
        # would be expanding might in principle just be small fragments of the original words
        # (if a custom tokenizer is used), or normalized to codes or otherwise heavily mangled
        # (if a custom normalizer is used.) Check with a handful of canary test words as a
        # crude best-effort verification.
        canaries = ["Aleksander", "tables", "operationally", "PIZZA"]
        terms1 = {canary: list(inverted_index.get_terms(canary)) for canary in canaries}
        assert all(len(terms1[canary]) == 1 for canary in canaries), "Unsupported tokenization detected."
        assert all(len(canary) == len(terms1[canary][0]) for canary in canaries), "Unsupported normalization detected."
        terms2 = {canary: list(inverted_index.get_terms(terms1[canary][0])) for canary in canaries}
        assert all(terms1[canary] == terms2[canary] for canary in canaries), "Unsupported normalization detected."

        # Initialize operator-specific helpers.
        raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def _unhandled(self, tree: ast.Call) -> None:
        """
        Invoked during initial tree validation from the base class. Overloaded from the base
        class to add support for new operators.

        Rewrites the operator into an OR-expression as a side-effect.
        """
        # The extended linguistic operators we offer clients to use in their queries,
        # in addition to the Boolean operators offered by the base class. They are all
        # rewritten into OR expressions through term expansion.
        expanders = {
            "WILDCARD":   self._wildcard,
            "SYNONYM":    self._synonym,
            "LOOKSLIKE":  self._lookslike,
            "SOUNDSLIKE": self._soundslike,
        }

        # The superclass' tree validation logic decorates the argument node with terms, if
        # the argument node is a literal. As such, we can use the presence of terms as a way
        # to deduce if the argument node is a literal. However, literals that represent wildcard
        # patterns tokenize into multiple terms due to the meta-character '*', so deal with this
        # as a special case.
        fixers = {
            "WILDCARD":   lambda a: [a.value],
            "SYNONYM":    lambda a: a.terms,
            "LOOKSLIKE":  lambda a: a.terms,
            "SOUNDSLIKE": lambda a: a.terms,
        }

        # At this point we actually know the structure except for details about the operator name,
        # due to how this method is invoked from the base class during initial AST validation.
        match tree:

            # One of the extension operators. They're all unary and they all rewrite to a logical OR expression,
            # so the processing details are largely shared. We do not properly normalize the individual pieces
            # of the wildcard pattern.
            case ast.Call(func=ast.Name(id=("WILDCARD" | "SYNONYM" | "LOOKSLIKE" | "SOUNDSLIKE") as operator)):
                if len(tree.args) != 1:
                    raise ValueError(f"Operator {operator} expects exactly one argument.")
                argument = tree.args[0]
                self._validate(argument)
                if not hasattr(argument, "terms"):
                    raise ValueError(f"Operator {operator} has an argument of an unexpected type.")
                argument.terms = fixers[operator](argument)
                if len(argument.terms) != 1:
                    raise ValueError(f"Operator {operator} expects a single-term argument, got {argument.terms}.")
                if operator == "WILDCARD" and " " in argument.terms[0]:
                    raise ValueError(f"Operator {operator} expects a single-term argument, got {argument.terms[0].split()}.")
                expansions = expanders[operator](argument.terms[0])
                constant = ast.Constant(value="<expanded>")
                constant.terms = expansions  # type: ignore[attr-defined]
                tree.func = ast.Name(id="OR")
                tree.args = [constant]

            # Not implemented here, either.
            case _:
                super()._unhandled(tree)

    def _wildcard(self, pattern) -> List[str]:
        """
        Given a wildcard query pattern, returns the list of index terms that the
        pattern gets expanded into.
        """
        raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def _synonym(self, term) -> List[str]:
        """
        Given a query term, returns the list of its synonym index terms.
        Note that we consider synonymity to be a reflexive relation for
        convenience, unless the synonym dictionary contains a mapping for
        the given term. In that case, it is left up to the provider of the
        dictionary what the mapping is. This means that the mapping can be
        non-reflexive and non-symmetrical and non-transitive, in principle,
        and thus not necessarily an equivalence relation. We could check for
        and enforce an equivalence relation criterion during construction of
        the synonym dictionary, if needed.
        """
        raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def _lookslike(self, term) -> List[str]:
        """
        Given a query term, returns the index term(s) that are syntactically close enough
        to it as measured by edit distance. Currently, the edit distance threshold and the
        other lookup parameters are fixed.
        """
        raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def _soundslike(self, term) -> List[str]:
        """
        Given a query term, returns the index term(s) that are phonetically close enough
        to it as measured by the Soundex algorithm.
        """
        raise NotImplementedError("You need to implement this as part of the obligatory assignment.")
