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
        - w_t,d = log(1+tf_t,d) x log_10(N/df_t)
    - Score(q,d) = SUM_t_in_q_intersected_d(tf.idf_t,d)