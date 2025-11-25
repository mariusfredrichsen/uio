### Index construction
- how can we construct an index with limited main memory?


### Scaling index construction
- cannot fit everything into memory
- BSBI: Blocked Sort-Based Indexing
    - 

### Distributed indexing
- maintain a master machine directing the indexing job
- break up indexing into sets of parallel tasks
    - parsing og inverting
    - parsers, gets a split from the master, reads documents and emits term and doc pairs into partitions
    - inverters, collects pairs from one partition, sorts and writes to posting lists
- master machine assigns each task to an idle machine from a pool

### MapReduce
- (master, parsing and inverting)
- index construction is one phase
- another phase is transofmring a term-partitioned index into a document-partiotioned index
    - term-partitioned, one machine handles a subrange of terms
    - document-partitioned, one machine handles a subrange of documents
- most search engines use document-partionioned indexes since its better for load balancing

#### Schema of map and reduce functions
- map: input --> list(k,v)
- reduce: (k, list(v)) --> output

#### instantiation of the schema for index construction
- map: collection --> list(termID, docID)
- reduce: (< termID1, list(docID) >, < termID2, list(docID) >) --> (postings list1, postings list2, ...)

### Dynamic indexing
- changing an inverted index as we go
- maintain a big main index
- new docs go into small auxiliary index
- search across both, merge results
- deletions, invalidation bit-vector for deleted docs
- filter focs output on a search result by this invalidation bit-vector
- periodically, re-index into one main index
- issues, poor performance during merge
- build up from scratch and delete the old one periodically

#### Logarithmic merge
- maintain a series of indexes, each twice as large as the previous one
- keep the smallest in memory and the larger ones on disk, I_0, I_1, I_n
- if the smallest gets to big (bigger than I_n), write it to the disk as I_0