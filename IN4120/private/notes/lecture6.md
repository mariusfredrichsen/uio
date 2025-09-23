# Ranking

### Ranked retrival
- boolean search can give either too many hits or none
- need a form for ranking to have at least one suggestion to what the user is searching for
- en funksjon som tar in query og document_i som returner en score_i
- query-document score
    - how many times the terms occurs in the document
- jaccard coefficient
    - use union
    - doesnt say how many times the term occurs
- bag of words model
    - no form for position, just the frequency
    - **term frequency** (tf) says how many times the term occurs in a document
    - document with tf 1 for a term is not 10 times less relevant than a document with tf 10 for the same term
    - **document frequency**: how many documents the term occurs in
    - rare terms are more informative than frequent terms
        - words occuring in a document and nowhere else in others is much more relevant
    - **collection frequency**: how many times the term occurs in general (??)
    - idf_t = log_10(N/df_t)
        - weights the documents containing a more seldom word than other words
    - tf-idf weighting
        - w_t,d = 1 + log(tf_t,d) x log_10(N/df_t)
    - Score(q,d) = SUM_t_in_q_intersected_d(tf.idf_t,d)

    - Log-frequency weighting
        - w_t,d = 1 + log_10 (tf_t,d) else 0
        - score_d = sum of frequency weight for each term in the query in the document

    - dampen the effect of the frequency if the term exists in every document, like "the"

    - BM25 - variant of a scoring

    - each document is represented by a real-value matrix (weight matrix)
        - multidimensional representation of the data where each document is a vector where its values are the weights of the unique words
        - includes a lot of zeros since a document doesn't contain every single word in our global dictionary
    - queries as vectors
        - distance or alikeness of the document and query vectors
    - distance
        - euclidean distance
            - bad idea since it gives a large distance for vectors with different lengths
        - distance between two points
        - distance is not sufficient
    - start thinking about angles instead of distance
        - cosine distance
            -normalizing the vectors first so that every length is 1
        - query vector X document vector = inner product of the two vectors
        
