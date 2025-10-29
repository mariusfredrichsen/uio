📘 Lecture 1 – Introduction to Information Retrieval

What is Information Retrieval (IR)?;Finding material, usually text documents, of an unstructured nature that satisfies an information need from large collections.
Give examples of IR beyond web search.;Email search, desktop search, corporate knowledge bases, and legal information retrieval.
What is the basic assumption in IR?;We work with a static collection of documents and aim to retrieve those relevant to a user’s information need.
What is an information need?;The underlying goal or task that leads a user to issue a query.
What is the difference between structured and unstructured data?;Structured data fits a database schema (tables, fields), while unstructured data is free text such as documents.
What are the main components of the classic search model?;Collection, user task, information need, query formulation, retrieval, and results evaluation.
Define precision.;Fraction of retrieved documents that are relevant to the user’s information need.
Define recall.;Fraction of relevant documents in the entire collection that are retrieved.
What is the trade-off between precision and recall?;Higher recall often lowers precision and vice-versa; systems aim to balance both.
What is a term-document incidence matrix?;A binary matrix with rows as terms and columns as documents indicating term presence (1) or absence (0).
Why is a term-document matrix inefficient?;It is very large and sparse, containing mostly zeros, wasting memory.
What is an incidence vector?;A 0/1 vector for a term showing which documents contain that term.
How can Boolean queries be answered with incidence vectors?;By performing bitwise AND/OR/NOT operations across term vectors.
What is the inverted index?;A dictionary of terms, each pointing to a postings list of document IDs where the term occurs.
What is a postings list?;An ordered list of document IDs (and optionally term frequencies or positions) for a given term.
Why must postings lists be sorted by docID?;To allow efficient intersection (merge) operations during query processing.
What data structure do we use for large postings lists?;Variable-length arrays or linked lists depending on disk vs memory trade-offs.
What are the main stages of text processing before indexing?;Tokenization, normalization, stemming or lemmatization, and stop-word removal.
What is tokenization?;Splitting a character sequence into word tokens.
What is normalization?;Mapping text to a consistent form, e.g., lowercasing or unifying “U.S.A.” and “USA.”
What is stemming?;Reducing words to their morphological root, e.g., “authorize” → “author.”
What is lemmatization?;Using vocabulary and morphology analysis to map words to their dictionary form (lemma).
What are stop words?;Common words such as “the,” “a,” “of,” that are often removed to save space.
What is the Boolean retrieval model?;A model where documents are viewed as sets of words and queries use logical operators (AND, OR, NOT).
What are examples of Boolean operators?;AND for intersection, OR for union, NOT for complement.
Give an example of a Boolean query.;(Brutus AND Caesar) AND NOT Calpurnia.
Why is Boolean retrieval an “exact match” model?;A document either satisfies the Boolean condition or it doesn’t—no ranking.
What is query refinement?;Modifying the query (adding/removing terms) to better express the information need.
What is the time complexity of merging two postings lists?;O(x + y), where x and y are the lengths of the two lists.
What is the role of skip pointers?;They allow faster traversal of long postings lists during merging.
What is a biword index?;An index of consecutive word pairs used for exact phrase or proximity searching.



Lecture 2: Indexing Pipeline, Dictionary, and Postings

Indexing Pipeline;The process of turning documents into an inverted index, starting with a Tokenizer, followed by Linguistic Modules, and finally an Indexer Tokenizer;The component in the indexing pipeline that takes a stream of characters (documents) and converts it into a stream of tokens Token;An instance of a sequence of characters in the text that is a candidate for an index entry after further processing Term;The normalized or modified token that is actually an entry in the inverted index dictionary Linguistic Modules;The stage in the indexing pipeline that performs functions like stemming, case-folding, or stop word removal on the token stream to produce modified tokens Indexer;The component in the indexing pipeline that takes the stream of modified tokens and their document/position information and creates the inverted index (dictionary and postings lists) Parsing a Document;The initial task of determining a document's format (pdf/word/excel/html), language, and character set, which is a classification problem Document Grain Size;The decision on what constitutes a unit document for indexing, such as a file, an email, or a group of files Tokenization Issue (Hyphenation);The problem of deciding whether a hyphenated sequence like "state-of-the-art" should be broken up or treated as a single token Tokenization Issue (Numbers);The problem of deciding how to treat sequences that look like numbers, dates, or product codes, such as "3/20/91" or "B-52" Stop Word;A common word (e.g., "a", "the") that is deemed to have little value in helping select documents and is often discarded during indexing Stop Word List (Small);A hand-written list of 30-300 most common words that are removed from the index to save space Stop Word List (Large);A list created using collection statistics to find terms that occur with a high document frequency, such as "document" or "computer" Dynamic Stop List;A strategy where the set of stop words is determined dynamically for each query by ignoring terms with very high document frequency (e.g., df>40%) Stemming;A process that reduces a term to its "root" form, aiming to map related words (e.g., automate, automatic) to the same term (e.g., automat) Porter Stemmer;The most common algorithm for stemming English, which consists of a set of about 60 conventions for suffix stripping Equivalence Class;The set of distinct words that are mapped to the same term by normalization and stemming Postings List;The list, associated with a term in the dictionary, that records the documents and optionally the positions where the term occurs Postings;An entry within a postings list, typically consisting of a document ID and optional position/frequency information Inverted Index;The fundamental data structure in Information Retrieval, consisting of a dictionary (vocabulary) and the associated postings lists Merge Operation (Boolean);The operation used to process a Boolean query (e.g., "BRUTUS AND CAESAR") by simultaneously stepping through the postings lists of the query terms and finding common document IDs Skip Pointer (Skip List);An enhancement to postings lists that adds extra pointers to allow the merge operation to jump over intermediate postings and speed up conjunction queries Skip Pointer Placement Tradeoff;The balancing act between having more skips (shorter skip spans, more comparisons) and fewer skips (fewer comparisons, but longer skip spans) Positional Index;A type of index where the postings list for a term stores not just the document ID, but also the word positions within that document Phrase Query;A query that searches for a sequence of terms appearing next to each other in a document, requiring a positional index for exact matching Biword Index;A method to handle phrase queries by indexing all pairs of consecutive terms (e.g., "to be") as single terms in the index Biword Index Problem;The issue that this index's size can be very large (up to M2 terms) and it cannot handle phrases longer than two words K-Gram Index;A structure used to handle wildcard queries by indexing every sequence of k characters (k-gram) occurring in the dictionary terms Wildcard Query;A query containing the wildcard character (), which typically stands for any sequence of characters (e.g., "comput" matches "computer") Permuterm Index;An alternative for wildcard queries where every term is indexed under all its possible rotations with a special boundary symbol ($) Blocked Sort-Based Indexing (BSI);An index construction method where the collection is segmented into blocks, a mini-index is created for each block, and these are then merged using an external merge sort Single-Pass In-Memory Indexing (SPIMI);An index construction method that avoids a global dictionary and sorting postings by generating separate, self-contained indexes for large blocks



📗 Lecture 3 – Tolerant Retrieval

What are the main topics in tolerant retrieval?;Dictionary data structures, wildcard queries, spelling correction, and phonetic matching (Soundex).
What are the two main dictionary data structures?;Hashtables and trees (especially B-trees).
What are pros of hashtables for dictionaries?;Very fast lookup O(1).
What are cons of hashtables?;No support for prefix or range searches and need for expensive rehashing if vocabulary grows.
What are pros of tree-based dictionaries?;They maintain lexicographic order and support prefix and range queries.
What are cons of trees?;Slower lookup O(log M) and rebalancing overhead (mitigated by B-trees).
What is a B-tree?;A balanced multiway search tree where each internal node has between a and b children.
What are wildcard queries?;Queries using special characters (* or ?) to match variable parts of words, e.g., “mon*” or “tion.”
How are right-truncated wildcards (e.g., mon) handled?;Using lexicographic range search in a B-tree: retrieve all words with mon ≤ w < moo.
How can left-truncated wildcards (*mon) be supported?;By maintaining a separate B-tree with reversed terms.
What problem do wildcards in the middle of a word pose?;They require intersecting large term sets, which is computationally expensive.
What is the solution for middle wildcards?;Use a Permuterm index to transform queries so * appears at the end.
What is a Permuterm index?;An index of all rotations of each term plus a special symbol $ to allow efficient wildcard matching.
Give an example of rotations for “hello.”;hello$, ello$h, llo$he, lo$hel, o$hell.
How are Permuterm queries processed?;Rotate the query so that * is at the end and then perform a range search in the index.
What is the main drawback of Permuterm indexing?;It increases the dictionary size roughly fourfold.
What is a k-gram (n-gram) index?;An index mapping all sequences of k characters (e.g., bigrams) to the dictionary terms containing them.
Why use k-gram indexes?;To support wildcard queries and spelling correction by finding terms sharing character sequences.
Give examples of bigrams for “April.”;$a, ap, pr, ri, il, l$.
How do we process wildcard queries with k-gram indexes?;Convert query into AND of all k-grams, find matching terms, then post-filter by actual pattern.
Why do we post-filter wildcard results?;Because some terms share k-grams but do not exactly match the wildcard pattern.
What are the two main uses of spelling correction?;Correcting text during indexing and correcting user queries.
What are the two main types of spelling correction?;Isolated-word correction and context-sensitive correction.
What is isolated-word correction?;Checking each word independently against a lexicon for misspellings.
What is context-sensitive correction?;Considering neighboring words to detect real-word errors (e.g., “I flew form Heathrow”).
What is the typical lexicon for spelling correction?;A standard dictionary, domain-specific list, or the vocabulary of the indexed corpus.
What is edit distance?;The minimum number of insertions, deletions, or substitutions needed to transform one string into another.
What is the Levenshtein distance?;A common edit-distance measure assigning cost = 1 per insertion, deletion, or substitution.
How is edit distance used for spelling correction?;To find lexicon terms within a threshold distance from the misspelled query term.
What are common sources of misspellings?;Keyboard proximity errors, transpositions, OCR mistakes.
What is the Soundex algorithm?;A phonetic encoding that maps similar-sounding names to the same code for matching.
How does Soundex work?;Keep the first letter, remove vowels, assign digits to consonants, and truncate or pad to four characters.
What is an example of Soundex output?;“Robert” → R163 and “Rupert” → R163 (same code).
When is Soundex mainly used?;For matching personal names or other phonetic search tasks.



📙 Lecture 4 – Index Construction

Why is index construction hardware-dependent?;Because disk I/O, memory size, and seek times dominate performance.
Why is disk I/O slower than memory?;Disk seeks require mechanical movement and data transfer occurs in blocks.
What are typical disk block sizes?;8 KB – 256 KB.
Why is it faster to transfer one large block than many small ones?;Each seek adds latency; larger transfers amortize the cost.
How large is main memory versus disk on typical IR servers?;Several GB of RAM and > 1 TB of disk.
Why is fault tolerance usually not built into one machine?;It’s cheaper to use multiple regular servers rather than one highly fault-tolerant system.
What dataset is commonly used to demonstrate scalable indexing?;The Reuters RCV1 newswire collection (≈ 800 K docs, 400 K terms).
What is the main challenge in scalable index construction?;Sorting huge numbers of term-doc records that don’t fit into memory.
What is sort-based index construction?;Parsing documents into (term, docID, freq) triples, sorting them, and merging to form an inverted index.
Why can’t we just sort everything in memory?;Large collections exceed RAM; disk-based sorting is required.
Why is naïve disk sorting too slow?;It causes too many random disk seeks.
What is external sorting?;A disk-based sorting method that minimizes random I/O by processing large sequential chunks.
What is the BSBI algorithm?;Blocked Sort-Based Indexing — sorts manageable in-memory blocks and merges them to form the final index.
What is a block in BSBI?;A subset of term-doc records (e.g., 10 M) that fits into memory for sorting.
Describe BSBI steps.;(1) Parse documents into term-doc pairs. (2) Accumulate into blocks. (3) Sort each block. (4) Write sorted blocks to disk. (5) Merge sorted runs.
Why does BSBI reduce disk I/O?;It uses sequential writes and reads instead of many random seeks.
What is multi-way merging?;Simultaneously reading chunks from multiple sorted runs and merging them efficiently.
Why is multi-way merging better than binary merging?;It requires fewer merge passes and fewer total disk reads/writes.
What is SPIMI?;Single-Pass In-Memory Indexing — builds postings for each term in memory until full, then writes blocks to disk.
How does SPIMI differ from BSBI?;SPIMI creates postings lists directly (no global sorting of term-doc pairs) and uses hash tables for terms.
What happens after all SPIMI blocks are written?;They are merged into a single inverted index on disk.
What is dynamic indexing?;Techniques that allow adding or deleting documents from an existing index without rebuilding from scratch.
What is a log-structured merge index?;A hierarchical structure that merges small in-memory indexes into larger on-disk indexes over time.
What is the main performance bottleneck in index construction?;Sorting and writing postings to disk efficiently.
What is compression’s role during index construction?;Usually applied after building the index to reduce disk space and improve query speed.
Why is index merging crucial for web-scale search engines?;Because data is distributed across machines and must be combined into global indexes.






