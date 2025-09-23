### Computing the K largest scorings
- selection vs sorting
- use heap for selecting top K
- index elimination
    - remove every index/postinglist that doesnt contain a query term
    - maybe consider only the ones that contains many query terms instead of just atleast one
    - maybe consider high-idf query terms
- champion list
    - have a top list, a champion list that is r long, in the precomputing of the dictionary
    - at query time, compute the champion list and pick the K-top

- static quality ranking
    - give the documents a bigger score based on a authority
    - given by a function g(d), d is document

    - net score
        - g(d) + cos(d)
    - use g(d) to order the postinglists (they are just sorted by doc.id)
    - use the same sorting on every postinglists to keep the consistency
    - can be combined with champion lists

- high and low lists
    - tiered lists, high is champion list and if we need more we take from the low lists

- impact-ordered postings
    - compute scores if the wf_t,d is high enough
    
    - early termination
        - after a fixed number of computing postings
        - wf_t,d drops below some threshold
    
    - idf-ordered terms
        - 

- cluster pruning
    - pick root(N) documents and call them leaders
    - process the other coduments and give them their leaders (the non-leaders are followers)
    - pick out the leader based on the query and just get the followers
    - maybe a strategy to picking the leaders? what if the leaders are very close