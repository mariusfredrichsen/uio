# pylint: disable=missing-module-docstring
# pylint: disable=missing-class-docstring
# pylint: disable=missing-function-docstring
# pylint: disable=line-too-long

import unittest
from context import in3120


class TestRocchioClassifier(unittest.TestCase):

    def setUp(self):
        analyzer = in3120.SimpleAnalyzer()
        movies = in3120.CorpusLoader.from_files(in3120.InMemoryCorpus(), ["../data/imdb.csv"])
        fields = ["title", "description"]
        inverted_index = in3120.DummyInMemoryInvertedIndex(movies, fields, analyzer)
        training_set = movies.split("genre", lambda v: v.split(","))
        stopwords0 = in3120.CorpusLoader.from_files(in3120.InMemoryCorpus(), ["../data/stopwords-en.txt"])
        stopwords = in3120.SimpleTrie.from_strings((d["body"] for d in stopwords0), analyzer)
        vectorizer = in3120.Vectorizer(movies, inverted_index, stopwords)
        self._classifier = in3120.RocchioClassifier(training_set, fields, vectorizer)

    def test_predict_movie_genre(self):
        results = list(self._classifier.classify("It was a dark and stormy night"))
        self.assertEqual("Horror", results[0].category)

    def test_scores_are_sorted_descending(self):
        results = list(self._classifier.classify("It was a dark and stormy night"))
        scores = [result.score for result in results]
        self.assertEqual(13, len(scores))
        self.assertListEqual(scores, sorted(scores, reverse=True))


if __name__ == '__main__':
    unittest.main(verbosity=2)
