# pylint: disable=missing-module-docstring
# pylint: disable=missing-class-docstring
# pylint: disable=missing-function-docstring
# pylint: disable=line-too-long

import unittest
from typing import Optional
from context import in3120


class TestCorpusLoader(unittest.TestCase):

    def test_load_from_file(self):
        expected = {
            "../data/mesh.txt": 25588,
            "../data/cran.xml": 1400,
            "../data/docs.json": 13,
            "../data/imdb.csv": 1000,
            "../data/pantheon.tsv": 11341, 
        }
        for filename, size in expected.items():
            corpus = in3120.CorpusLoader.from_files(in3120.InMemoryCorpus(), [filename])
            self.assertEqual(corpus.size(), size)

    def test_load_from_file_and_annotate(self):
        expected = {
            "../data/mesh.txt": 25588,
            "../data/cran.xml": 1400,
            "../data/docs.json": 13,
            "../data/imdb.csv": 1000,
            "../data/pantheon.tsv": 11341, 
        }
        for filename, size in expected.items():
            corpus = in3120.CorpusLoader.from_files(in3120.InMemoryCorpus(), [filename], {"bob": "fee"})
            self.assertEqual(corpus.size(), size)
            self.assertTrue("bob" in corpus[7].get_field_names())
            self.assertEqual("fee", corpus[7]["bob"])

    def test_load_from_file_and_annotate_invalid(self):
        corpus = in3120.InMemoryCorpus()
        with self.assertRaises(AssertionError):
            in3120.CorpusLoader.from_files(corpus, "../data/cran.xml", [{"foo": "bar"}])
        with self.assertRaises(AssertionError):
            in3120.CorpusLoader.from_files(corpus, None, {"foo": "bar"})

    def test_load_from_multiple_files(self):
        expected = {
            "../data/mesh.txt": 25588,
            "../data/cran.xml": 1400,
            "../data/docs.json": 13,
            "../data/imdb.csv": 1000,
            "../data/pantheon.tsv": 11341, 
        }
        corpus = in3120.CorpusLoader.from_files(in3120.InMemoryCorpus(), expected.keys())
        self.assertEqual(corpus.size(), sum(expected.values()))

    def test_load_from_multiple_files_and_annotate(self):
        expected = {
            "../data/mesh.txt": 25588,
            "../data/cran.xml": 1400,
            "../data/docs.json": 13,
            "../data/imdb.csv": 1000,
            "../data/pantheon.tsv": 11341, 
        }
        filenames = [filename for filename, _ in expected.items()]
        annotations = [{"src": filename} for filename, _ in expected.items()]
        corpus = in3120.CorpusLoader.from_files(in3120.InMemoryCorpus(), filenames, annotations)
        self.assertEqual(corpus.size(), sum(expected.values()))
        document_id = 0
        for filename, size in expected.items():
            self.assertTrue("src" in corpus[document_id].get_field_names())
            self.assertEqual(filename, corpus[document_id]["src"])
            document_id += size

    def test_load_from_multiple_files_and_annotate_invalid(self):
        corpus = in3120.InMemoryCorpus()
        with self.assertRaises(ValueError):
            in3120.CorpusLoader.from_files(corpus, ["../data/cran.xml", "../data/docs.json"], ["foo", "bar"])
        with self.assertRaises(AssertionError):
            in3120.CorpusLoader.from_files(corpus, ["../data/cran.xml", "../data/docs.json"], [{"foo": "bar"}])
        with self.assertRaises(AssertionError):
            in3120.CorpusLoader.from_files(corpus, None, [{"foo": "bar"}])

    def _drop_document_if_it_contains_the_in_body(self, document: in3120.Document) -> Optional[in3120.Document]:
        return None if "the" in document.get_field("body", "") else document

    def test_load_from_file_but_drop_documents_that_contain_the_in_body(self):
        expected = {
            "../data/mesh.txt": 25017,
            "../data/cran.xml": 8,
            "../data/docs.json": 0,
            "../data/imdb.csv": 1000,
            "../data/pantheon.tsv": 11341, 
        }
        pipeline = in3120.DocumentPipeline([self._drop_document_if_it_contains_the_in_body])
        for filename, size in expected.items():
            corpus = in3120.CorpusLoader.from_files(in3120.InMemoryCorpus(), [filename], None, pipeline)
            self.assertEqual(corpus.size(), size)


if __name__ == '__main__':
    unittest.main(verbosity=2)
