# Information Retrieval
- is finding material, usually documents, of an unstructured nature, usually text, that satisifies an information need from within large collections, usually stored on computers.


### Collection
- A set of documents

### The quality of search
- **Precision:** Fraction of retrieved docs that are relevant to the user's information need
- **Recall:** Fraction of relevant docs in collection that are retrieved

### Term-document incidence matrices
- a matrix where the rows are the terms and the document is the columns
- a cell is 1 if the document contains the term, or 0 if it doesn't
- takes too much space
    
### Inverted Index
- each document has their own docID
- **Posting List:** list of docIDs connected to a term in the **Dictionary**
    - term -> docID -> docID -> docID -> etc...
    - can be a fixed sized array connected to a term or a linked list
    - docIDs are sorted from smallest to biggest 
- **Construction:**
    - send a buffer of text (the document contents) through a **tokenizer**
    - send the tokens from the tokenizer to a **normalizer**
    - add the normalized tokens to the dictionary connected to the inverted index

### Tokenization
- cut character sequence into word tokens

### Normalization
- map text and query term to same form
- U.S.A = USA

### Stemming
- matching of different forms of a root
- authorize, authorization

### Stop words
- may omit very common words (or not)
- the, a, to, of

### Boolean queries: Exact match
- queries consisting of AND, OR and NOT
- is precise and finds documents containing the terms

### Phrase queries
- a little more advanced, search with phrases instead of exact matches
- **Biword indexes:** split the phrase into pairs of tokens and search for a chain of term AND term
    - friends romans, romans countrymen
    - can contain false positives
    - index blowup
- **Positional indexes:** store the spans of each term in a document
    - increases the posting storage
    - still used since it is very powerful
- **Proximity queries:** within k words of < word >

### Structured data
- databases

### Unstructured data
- free text

### Semi-structured data
- no data is not fully unstructured
- title, bullet points, sections, etc









