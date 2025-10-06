# pylint: disable=missing-module-docstring
# pylint: disable=line-too-long

from __future__ import annotations
from typing import Iterable, Iterator, Dict, Tuple
from math import sqrt
from .sieve import Sieve



class SparseDocumentVector:
    """
    A simple representation of a sparse document vector. The vector space has one dimension
    per vocabulary term, and our representation only explicitly stores the dimensions that
    have non-zero values. The dimensions that have zero-values are implicit, i.e., they are
    not explicitly represented. For very high-dimensional spaces, such a sparse representation
    typically incurs significant savings wrt both memory usage and computational efficiency.

    For example, a sparse representation of document A might be {"foo": 0.8, "bar": 0.5, "baz": 0.5}
    and a sparse representation of document B might be {"foo": 0.1, "qux": 0.5}. In a space
    spanned by the vocabulary ["foo", "sap", "bar", "baz", "qux", "pum", "pop"], these sparse
    representations would be equivalent to the dense representations [0.8, 0, 0.5, 0.5, 0, 0, 0]
    and [0.1, 0, 0, 0, 0.5, 0, 0], respectively. Now, imagine the savings incurred for the tiny
    documents A and B if the vocabulary had contained millions of terms.

    Being able to place text buffers, be they documents or queries, in a vector space and
    thinking of them as point clouds (or, equivalently, as vectors from the origin) enables us
    to numerically assess how similar they are according to some suitable metric. Cosine
    similarity (the inner product of the vectors normalized by their lengths) is a very
    common metric.
    """

    def __init__(self, values: Dict[str, float]):
        # An alternative, effective representation would be as a
        # [(term identifier, weight)] list kept sorted by integer
        # term identifiers. Computing dot products would then be done
        # pretty much in the same way we do posting list AND-scans.
        self._values = {term: weight for term, weight in values.items() if weight != 0.0}

        # We cache the length. It might get used over and over, e.g., for cosine
        # computations. A value of None triggers lazy computation.
        self._length: None | float = None

    def __iter__(self):
        return iter(self._values.items())

    def __getitem__(self, term: str) -> float:
        return self._values.get(term, 0.0)

    def __setitem__(self, term: str, weight: float) -> None:
        if weight != 0.0:
            self._values[term] = weight
        else:
            self._values.pop(term, None)
        self._length = None

    def __contains__(self, term: str) -> bool:
        return term in self._values

    def __len__(self) -> int:
        """
        Enables use of the built-in len/1 function to count the number of non-zero
        dimensions in the vector. It is not for computing the vector's norm.
        """
        return len(self._values)

    def get_length(self) -> float:
        """
        Returns the length (L^2 norm, also called the Euclidian norm) of the vector.
        """
        self._length = sqrt(sum(map(lambda x: x**2, self._values.values())))
        return self._length
        
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def normalize(self) -> None:
        """
        Divides all weights by the length of the vector, thus rescaling it to
        have unit length.
        """
       
        n = self.get_length()
        if n:
            self._values = {term: weight/n for term, weight in self._values.items()}
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def top(self, count: int) -> Iterable[Tuple[str, float]]:
        """
        Returns the top weighted terms, i.e., the "most important" terms and their weights.
        The top terms are returned sorted (in descending order) according to their weights.
        """
        # assert count > 0
        if count == 0:
            return
        sieve = Sieve(count)
        for term, weight in self._values.items():
            sieve.sift(weight, term)
        yield from ((term, weight) for weight, term in sieve.winners())
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def truncate(self, count: int) -> None:
        """
        Truncates the vector so that it contains no more than the given number of terms,
        by removing the lowest-weighted terms.
        """
        self._values = {term: weight for term, weight in self.top(count=count)}
        #raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def scale(self, factor: float) -> None:
        """
        Multiplies every vector component by the given factor.
        """
        self._values = {term: weight*factor for term, weight in self._values.items() if weight*factor != 0.0}
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def dot(self, other: SparseDocumentVector) -> float:
        """
        Returns the dot product (inner product, scalar product) between this vector
        and the other vector.
        """
        return sum(self[term] * other[term] for term in self._values)
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")

    def cosine(self, other: SparseDocumentVector) -> float:
        """
        Returns the cosine of the angle between this vector and the other vector.
        See also https://en.wikipedia.org/wiki/Cosine_similarity.
        """
        len_self = self.get_length()
        len_other = other.get_length()
        if not len_self or not len_other:
            return 0.0 
        return self.dot(other) / (len_self * len_other)
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")
    
    @staticmethod
    def centroid(vectors: Iterator[SparseDocumentVector]) -> SparseDocumentVector:
        """
        Computes the centroid of all the vectors, i.e., the average vector.
        """
        values = dict()
        count = 0

        for vector in vectors:
            count += 1
            for term, weight in vector:
                if term in values:
                    values[term] += weight
                else:
                    values[term] = weight

        if count == 0:
            return SparseDocumentVector({})

        values = {term: weight / count for term, weight in values.items()}
        return SparseDocumentVector(values)
                    
        # raise NotImplementedError("You need to implement this as part of the obligatory assignment.")
