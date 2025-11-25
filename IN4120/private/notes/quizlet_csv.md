1. structured data has some type of hiarchy to it while unstructured data has a free form

2. it saves space since inverted index only saves the "true" cases in a incidence matrix

3. AND gives good precision since it narrows it down to what you only need while OR gives good recall since you fetch more

4. you find all documentIDs for all documents that contain both Brutus and Calpurnia. This is done by iterating from smallest docId to biggest docId. When the docIds are the same in both posting lists then we add it to a another posting list and return that list

5. terms with bigger document frequency should be merged first
    - start with smallest first, smaller list means faster merges

6. it turns the document into a vector by compressing it. The more two vectors are similar the more alike they are
    - one dimension per term in a document-vector
    - each query is also a vector, I guess turn the query into a vector?
    - cosine similarity to determines how clone the query is to the document?

7. it is efficient? can you explain the whole vector space model and how its used and why?
    - measures the angle between two vectors
    - cosine 0 = 1, perfect match
    - cosine 90 = 0, unrelated
    - cosine(query, document) = (query * document) / ( ||query|| ||document|| )

8. TF-IDF weighting is to make ranking more fair by normalizing the frequencies
    - frequent terms in one document = high TF
    - rare terms overall = high IDF
    - prevents common terms like "the" and "to" from dominating the ranking

9. im not sure, can you explain binary independence model and what it is used for?
    - each term is a binary variable (1 is present in document, 0 is absent in document)
    - documents are ranked by probability of relevance
    - each term has
        - a probability p_i = P(t_i | R = 1), probability term occurs in relevant documents
        - a prbability r_i = P(t_i | R = 0), probability term occurs in non-relevant documents
    - explain more
    - formula 
        - score(document) = sum(log((p_1-r_i) / (r_i(1-p_i))))
    - 

10. im not sure, can you explain BM25?
    - improved probabilistic ranking model
    - term frequency saturation, extra occurences of a term give diminishing returns
    - document length normilization, longer documents arent unfairly favored
    - formula
        - score(document, query) = sum_t(IDF_t * ((k_1 + 1) * term_frequency) / (k_1 * ((1-b) + b * |document| / avg_document_length)+ term_frequency))
        - b is ish 0.75 and k_1 is ish 1.2-2.0

11. im not sure, can you explain Rocchio's relevance feedback formula and the parameters?
    - q_m = alpha_weight * q_0 = (beta_weight / D_r) * sum_d_r(d_r - (gamma_weight / |D_nr|) * sum_d_nr(d_nr))
    - recenters the query vector toward relevant documents and away from non-relevent ones
        - more towards D_r and from D_nr
        - keep some of the score from alpha * q_0 to prevent drifting
    - explain more
    - based on feedback


12. im not sure, can you explain pseudo-relevance feedback, and what its main risk is?
    - instead of asking the user, assume top k documents from the first retriaval are relevent and change it based on the feedback
    - might risk query drift

13. im not sure, can you explain query drift and provide me an example?
    - occurs when automatic feedback moves the query away from the user's real intent

14. im not sure, can you explain global and local query expansion methods?
    - global, uses pre-built thesauri or corpus wide statistics (synonyms or wordnet)
    - local, uses the top-ranked documents for specific query (relevance feedback, pseudo feedback)

15. im not sure, can you explain HITS algorithm, hub and authority?
    - an authority is a page pointed to by many good hubs
    - a hub is a page that links to many good authorities
    - HITS reinforces these, good hubs link to good authorities and vice versa
    - explain more
    - algorithm
        - start of with a search and collect pages linking from and to
        - every pages authority and hub score is set to 1
        - update the scores
            - a = sum of hub scores of pages linking to it
            - h = sum of authority score of linked pages
        - normalize after each iteration until convergence

16. im not sure, can you explain the random surfer model in PageRank and what the damping facor represents?
    - Random Surfer Model
        - when crawling the web via links it follows a link with probability d
        - with probability 1-d jumpts to a random page
    - explain more
    - Pagerank
        - equation
            - PR(page_i) = ((1 - d) / N) + d * sum_pj_in_M(p_i)((PR(p_j) / L(p_j)))
            - p_j number of outlinks on page j
            - M(p_i) is the set of pages linkting to page_i
            - d is the damping factor, 0.85
            - N is the number of pages
    - Damping Factor
        - how much we trust the links
        - high damping factor means we depend monstly on the links
        - low damping factor means we use more randomness

17. link farms tampers with the "actual" links pointed to a website. Even though the website is irrelevant there could be many artificial websites pointing to the main website to make it look more relevant for link analysis

18. classification in information retrieval is to look at the information and give it labels while clustering is about finding close neighbours and other websites with similar information

19. im not sure, can you explain what linear SVM is and how it solves the optimization problem
    - finds the hyperplane seperating classes with the maximum margin
    - explain more
    - linear classifier
        - f(x) = w^T * x + b


20. im not sure, can you explain support vectors?
    - data points that lie closest to the dicision boundary
    - explain more
    - just helps the dicision boundary stay in the most optimal place

21. im not sure, can you explain the kernal trick and what SVMs are?
    - explain more
    - the problem is that real world data is rarely linearly seperable
    - solution, increase the space dimension
        - expensive to compute
    - matrix multiplication?

22. im not sure, can you explain what RBF kernelsare and why they are less effective for text classification compared to linear kernels?
    - explain more
    - data points closer to eachother get more points
    - pointless since documents are often linearly seperable beacuse of huge dimensionality, each term is a feature (thousands)
    - 

23. probabilisticc models are more complex and can handle more complex tasks than vector space models
    - probebalistic models estimates the likelood of relevance while vector space models measures geometric similarity

24. im not sure, can you explain what BM25 is?
    - explained earlier

25. im not sure, can you explain what anchor text is?
    - just links that can transfer you to other sites

26. im not sure, can you explain how anchor text both help and hurt retrieval accuracy?
    - improves recall by adding descriptibe terms not present on the page itself
    - the anchor text can be mislabeled with "evil corpiration" pointing to a neutral website 

27. im not sure, can you explain what the main assumptions behind relevance feedback effectiveness?
    - assumes the initial query retrieves at least some relevant document
    - relevant documents form a coherent cluster in vectore space

28. skip pointers improves the efficiency by potantially skip a lot of the posting list if the skip pointer points to a posting with a lower docID than the other posting list its being compared to

29. stop words are more and more relevant and we want more accurate searches and can handle the storage since hardware is getting better and better

30. stemming is about turning a word into its root form while lemmatization im not sure, can you explaim lemmatizations?
    - stemming removes endings
    - lemmiatization turns the word to its root


1. we want to check how relevant a document is

2. long documents has a higher chance to repeat a term in the query even though it still isn't fully relevant for the user

3. precision is about how how many of the returned documents are relevant while recall is about how many relevant documents was returned. If you increase precision you tend to lower recall and vice versa

4. F1 score calculates the quality of the search. I dont remember how it was calculated
    - the balance between precision and recall
    - formula
        - F1 = 2 * ((precision * recall) / (precision + recall))


5. not sure
    - Mean Average Precision (MAP) measures ranking quality across queries
    - each query computes a average precision, average of precision values at each rank position where a relevant document is retreived
    - then take the avarage of all APs

6. not sure, but I think it can help improve the search by letting us know what to modify
    - ish correct?
    - maps out the precision score and recall score

7. it assumes that the first search has some relevant information for the user and there should be another assumption. query drift can happen when the user doesnt get anything relevant and the search engine keeps giving non-relevant information

8. global analysis is about using general statistics while local is more user based

9. not sure
    - explicit is when the user directly indicates that the search was good
    - implicit is when we analyse that the user "liked" the search

10. not sure
    - uses pre-defined synonym lists
    - improves recall
    - may lower precision since every term is more broader

11. it changes it in the direction of the more relevant documents and away from non relevant documents

12. the advantages are that the feedback is user directed while the disadvantage is that it requires a little luck since the first query has to have some sort relevant information the user can pinpoint the search engine in

13. not sure what it assumes
    - assumptions in BIM
        - each term is independant of others
        - each term is binary (prent or absent)
        - relevance is bernoulli random variable (relevant / non-relevant)

14. 1.0 is when it rely on the links while 0.0 we ignore the links on the pages

15. note sure
    - PageRank vs HITS
        - global - local
        - query independent - query dependent
        - computes authority and hub scores - every page gets a single importance score

16. not sure
    - Convergence of HITS
        - expressed as matrix multiplications
        - principal eigenvectors

17. it manipulates it by trying to increase its link scores. It can be counteracted by dampening the effect

18. not sure
    - purpose of soft-margin in SVM
        - some misclassifications for noisy, non seperable data


19. not sure
    - C parameter in SVM
        - large value penelizes, smaller margin, possible overfitting
        - small value allows more violations, larger margin, better generalization

20. because the dimensions are already so big, many features since we have a feature for each unique term

21. it enables for faster computing
    - explain more

22. not sure, i think its because they are already linearly seperable

23. support vectors sets the decision boundry in its place. if they are removed the decision boundary moves

24. to retrieve documents that are most likely to contain the terms in the search query

25. not sure
    - BM25 over TF-IDF
        - term frequency saturation
        - length normilzation

26. it punishes the length of a document
    - wrong answer, anchor text
        - can help the linked to place

27. not sure, connectivity server
    - stores web graph link structure
        - for each url lists its in links and out links
        - enables effecient link based scoring and crawling
        - compressed links

28. it can search through semi-structured data more efficiently and find more relevant data by looking at the XML structure
    - retreives and ranks fragments of the XML document

29. not sure, struecuted ciument retreival principle
    - return the most specific XML fragment that fully satisfies the query, not too large, not too small
    - if one < section > matches return that instead of the entire article

30. not sure, generative vs discrimative models
    - generative, need generations and modifications
    - discriminative, directly model or decision boundaries



1. Doc B since Doc A is 5 times as big as Doc B but only contains 3 times more the relevant information

2. the bigger the document frequency the more punished it is

3. distance between vectores are pointless since the dimensions are so big. the angle is better to calculate

4. not sure

5. margin with is smaller and the risk of overfitting is increasing

6. not sure

7. common words like "the" and "to" appears almost garanteed in every document. logarithm is used to reduce to number to smaller numbers

8. System A: precision-0.1, recall-1.0. System B: precision-1.0, recall-0.5

9. the precision for every document compared to a query and averaged. its different from precision at k since its the average of every document instead of the whole scope
    - AP, mean of precisions at each rank where a relevant doc appears
    - P@k, precision for the top k results only
    - AP, early relevant documents gets rewarded

10. the original query vector prevents the feedback lood from drifting

11. query drifting happens when there is no relevant infromation given to the user or the user does not give good feedback. can be mitigated by keeping the original query
    - dont drift away from the original intent completely
    - done by having a big weight multiplied with original query

12. both good hubs and good authoroties get good scores

13. the damping makes the algorithm not rely so much on links and has a change to visit other links

14. anchor texts contain content that is related to the destination page

15. skip pointers are pointers in posting lists that might skip many postings when iterating through two at the same time. not sure how many you should have

16. not sure
    - Fragment Granularity in XML IR
        - how big the section of the XML document is returned, how fine it is
        - too coarse --> irrelevant text is returned
        - too fine --> missing context

17. generative classifier is a SVM that works in generations. iterations or generations are needed to get a good result. the advantage is that i allows for complex data to be digested, disadvantage is that it is more expensive to compute

18. naive is because it assumes that each data point is not related to others

19. not sure

20. the document frequencies might be bigger for every term

21. tokinization and normilazation and then add every term in every document into posting lists

22. not sure, it saves a lot of space and might be all calculated in main memory

23. not sure, maybe it is about what type of compression it is
    - static vs dynamic indexing
        - static, built once for a fixed collection
        - dynamic, supports additions/deletions incrementally

24. stemming makes each term more broader or overlap, more documents needs to be considered

25. supervised, classifying text requires classes and labels

26. the more the user looks interested in the data retreived the more we know that what we retreived is relevant for them

27. clicks alone might mislead us, the clicks might be only curious or searching and that the information retreived might be wrong but the user thinks its correct and is searching themselves

28. not sure, margin meaning in SVM
    - margin = confidence region
    - the gurther a point is from the boundary the higher P(y|x)

29. not sure, Macro vs Micro F1
    - Macro-F1, average F1 per class, all classes are equal
    - Micro-F1, aggreate counts before computing (weights large classes more)

30. because of damping?



1. positional indexes makes it easier to do phrase searches

2. by using positional indexes and comparing the indexes of the different postings/terms

3. skip pointers increases the speed in indexing of posting lists, but the more skip pointers you have the more you increase the space

4. by increasing the effiency in space usage

5. im not sure, explain block-level vs variable-byte encoding
    - block-level compresses blocks of integers, reserves 4 bits for each gap number
        - faster but uses more space
    - variable-byte encoding compresses each integer individually 
        - simple but slower to decode

6. they are useful to make better / accurate searches. it is also possible since we have more memory

7. im not sure, explain
    - limitations of boolean retrieval
        - a document is either relevant or non-relevant, cant set a document as semi-relevant
        - fixed by vector space and probabilistic models with ranking documents

8. im not sure, explain

9. im not sure, explain

10. im not sure, explain

11. a language model helps interperet the contents of a document

12. im not sure, explain

13. im not sure, explain

14. cross-entropy tells us how we should train the model to do classifications more accuratly

15. im not sure, explain

16. im not sure, explain
    - term independence assumption
        - probabilistic models like BIM assume terms occur independently given relevance, simplifies joint probabilities

17. it helps us connect the terms and know the context of the document
    - terms that frequently occur together, machine + learning, fruits + apple

18. it helps us find grouped up documents and relevant documents to the query by letting us do cos-similarity
    - embeddings map words to vectores where semantic similarity is ish equal to cosine similarity
        - car and automobile means almost the same and has a high cosine similarity

19. im not sure, explain
    - out of vocabulary words (OOV words)
        - words not seen during indexing or training
        - handles by    
            - character/subword models
            - fallback to approximate matching
            - indexing on morphological roots

20. stemming helps us shorten words and make words that are alike grouped up. stemming might make a lot of overlapping since words that doesnt mean the same could be according to the search be the same

21. im not sure, explain
    - signature files
        - old indexing technique
        - each document has a "bit signature" representing its terms (hash bits)
        - used for search, compute query signature and compare
        - disadvantage, high false positive rate and poor scalebaility

22. im not sure, explain
    - forward index
        - maps document to a list of terms (opposite of inverted index)
        - parse docs --> forward index
        - sort by term --> build inverted index (term --> docIDs)

23. it enables for more effective parralellism and indexes are so big that we cannot run them on a single machine

24. im not sure, explain
    - replica indexes
        - stores replicas on multiple servers
        - load balancing and fault tolerance

25. im not sure, explain
    - real-time indexing
        - used for social medisa where new data needs to update the index in real-time
        - maintaining low-latency updates while staying consistent and compressed

26. im not sure, explain
    - delta indexing
        - has two indexes, base and latest index
        - updates the latest index first and later adds it to the base
        - better to update the base every now and then instead of frequently since its expensive to rebuild

27. recall oriented is focused on getting ALL relevant documents while precision oriented is focused on getting ONLY relevant documents. using more AND for precision and more OR for recall

28. because the user is unpredictible
    - recall and precision is not reflecting enough
    - users behaviour based on the results we give is much more valueble

29. they implecitly give us a feedback by showing if a user is interested in the docuemnts that were given

30. im not sure, explain
    - A/B testing
        - run two versions, A = control, B = new system, on live traffic
        - measure user engagtement metrics
        - if B performs better statistically then its deployed



1. token normalization step is meant to make words that mean the same but is written differently is using the same standard. it saves space and makes it easier to search. remove symbols like dot or '. Another rule is using the root term of a word

2. im not sure
    - dictionaries are small and fits in RAM, maps term and pointers to postings
    - postings, large list of docIDs on disk

3. biwords indexes stores two and two terms together while positional indexes stores terms with their positions, both indexes uses more storage but biwords are faster but can have false positives while positional indexes dont

4. they have already splitted up terms so you can just combine them easily and find variants fast with fast lookups

5. im not sure
    - permuterm index
        - stores all permutations of each index, blows up the storage

6. edit distance is much more effective and saves space
    - edit distance gives simularity while k-grams gives candidates

7. edit distance tables increases and takes up a lot of space. it takes much longer to compute the bigger the table

8. the more balanced a tree is the faster the lookup is since we dont get edge cases where a leaf node resembles the whole tree and have to iterate N times

9. im not sure
    - BSBI, blocked sort-based indexing
        - breaks data into blocks
        - builds a partial index
        - sorts then write to disk and then merge all partial inderxes

10. im not sure
    - SPIMI vs BSBI
        - Single pass In-Memory Indexing
            - does not sort but rather uses hashes
            - faster for very large blocks

11. when merging shorter lists first we can avoid a lot of computations

12. im not sure
    - tiered indexes
        - tier 1 is the most trusted documents
        - tier 2 is rest of the webs

13. because compressing posting lists lets you compute it in memory which is faster than computing it when its in disk

14. im not sure
    - 

15. because recall is about retrieving ALL relevant documents while precision is about only relevant documents. you cant have both because recall would retrieve other ireelevant documents most likely and precision would retrieve ONLY relevent documents but would most likely miss some

16. search application would like high recall when it comes to medical research while high precision would be more nice in easy answers for a small problem

17. im not sure

18. im not sure

19. im not sure

20. im not sure

21. query drift happen when the results or the feedbacks enter a sink hole and dont provide any more relevant results

22. im not sure

23. because its easier to compute

24. it punishes documents that are long. the documents can be long and contain more of a relevant term but that is only because the document is long and maybe not because it is relevant

25. inverse document frequencies is N / df and says something of how rare a word is in the collection. it is logged to dampen the effects of it being rare

26. one is working with vectors and bases off of angles of the vectors while the other is about statistics and probabilities

27. hubs point to good authorities and authorities are pointed by good hubs

28. pagerank can score indepedently of links while HITS are very dependent on the links

29. im not sure

30. link spam is when websites create artificial websites with links to a website they want to boost. im not sure how they are countered

31. im not sure

32. it is to make searching with links much faster

33. delta indexing contains two inversed indexes and the newest changes are done to the fresher index while the main index remain unchanged unless its a deletion. deletion is done with marking a document with a vector-bit of some sort

34. we retrieve det same information multiple times

35. best element doesnt always contain the context of its contents

36. you can retrieve less infromation and more accurate infromation

37. im not sure

38. im not sure

39. im not sure

40. im not sure



1. grepping is very slow and needs to red through the whole data every time while inverted index is much more faster

2. it represents the different terms each documents contains. it does only say if the term is absent or present in the document

3. there are a lot of unusual words and many documents. if there is a document with a unusual term in a collection of one million documents then about one of those one million will actually have that term present while every other document has it assigned as absent. this uses a lot of space

4. information need is the information a user needs to get their task done while a query is about retrieving about the inputted data into the search

5. im not sure, what is ad hoc retrieval?
    - must retrieve relevant documents on the fly

6. boolean retrieval is very binary. Either a document is relevant or non-relevant. While other models tends to rank the documents on a score

7. it can retrieve more documents that might be relevant but just gives the user a sorted list on the most relevant, but the user still has the option to check the other documents

8. boolean model only checks if a word is present or not

9. im not sure, what is westlaw?
    - large commercial legal search engine
    - used by lawyers. heavy boolean + proximity queries

10. proximity operators are important because they can also find semi-relevant documents
    - check if terms are in proximity of each other. "apple" /w "fruit"

11. if we have two postings lists we want to merge we have two pointers, one at each start node and iterate based on the values. If a posting list has a docID that is smaller than the other then that postlist get iterated to the next iteration. This can be done since the posting lists are sorted by docID. If the docID matches in the iterations then that is added to the merged postings lsit

12. for each posting in x we have to check if the docID is not in y
    - NOT y = ALL docs except those with y

13. they refer to documents not present in x
    - NOT y = all documents that do not contain term y

14. OR has fewer rules to follow when merging while AND is more strict

15. depends on A, B and C. If both A and B together is smaller than C then I would evaluate A OR B first then AND it together with C, but if C is smaller than A OR B then I would AND it with A then B

16. because the smaller the postings lists the less we have to compute. If we start with the big number we can potentially find out that we have to remove a lot of the items because it was not supposed to be in the query anyways

17. im not sure, give me an example please

18. high recall requires that we retrieve a lot of docuements to make sure we retrieve every relevant docuemnt while high precision requires that we ONLY retrieve relevant docuements which means we might miss out on relevant documents since precision is more strict

19. that would require a one million times five hundrer thousand matrix which would take up too much space on the memory

20. because we use postings lists which is made of postings of documents that contains the term in the postings list. There is a lot of 0s so if we store only the 1s (which is also the only relevent information we need) then we save a lot of space

21. this makes merging ang searching much faster

22. because the data structure lets you do look ups in linear time

23. if we compute the smaller posting lists we can avoid computing the bigger ones and later finding out we dont need to compute them fully 

24. im not sure, explain the proximity operator
    - requires positional index

25. because they had a lot of data that made boolean retrieval good and sometimes we need a precise model

26. im not sure, what is legal search?
    - searching legal documents

27. because its very binary

28. precision is about how many of the retrieved documents are relevant. if 10 documents are retrieved and 5 are relevant then the precision is 0.5, but if 5 documents are retrieved and 5 are relevant then the precision is 1

29. recall is about how many of the relevant documents are retrieved. if we retrieve 9 out of 10 relevant documents then we have a recall of 0.9

30. it says about the quality of the search model



1. to have a quick lookup of terms when searching

2. to have a structure where we can check which documents contains the term in the dictionary

3. documentIDs that contain the term the list is representing
    - term frequencies
    - positions

4. the difference is that one contains the docID raw while the other just stores the gap between the docIDs. gaps are used to compress the data and store smalle numbers

5. to quickly intersect the postinglists with a smarter way to iterate
    - effecient merging
    - delta gap compression
    - skip pointers

6. to skip past a lot of documents that might not contain the term

7. more space vs faster iteration

8. for big indexes the posting lists are so big that we cannot store them in memory and rly on the disk

9. proximity queries and phrase queries

10. because we only need to iterate through each item in both lists n + m times since they are sorted

11. because we can iterate them one step at a time instead of finding them by searching the whole posting lists

12. im not sure, what is a volumentric bottleneck?
    - the data is too big to have in memory, uses disk and disk is slow and dominates in the time usage

13. because the amount of data is so big that the main memory cannot store it

14. split the data into blocks then create the index and sort them. later store them in the disk and then merge the blocks we made earlier

15. im not sure, what is a partial index?
    - just a index of a bigger index that hasnt been merged

16. Single Pass In Memory Index is not relying on the docIDs being sorted and is instead creating a hashtable for the docIDs
    - use hash dictionary
    - build postings lists incremenetally
    - when memory fills, write block to disk
        - contain a index on the memoery until

17. because using the hashtable is much faster

18. because we want to include every postings in the search and not just the blocks

19. im not sure, why do we have to do the index construction offline for static document collections?
    - because we dont want to update the index live
    - can compress faster by being agressive, batch processing

20. im not sure, why does it take longer to build the index vs just reading the documents raw?
    - parsing text
    - tokenization
    - normalization
    - disk writes
    - sorting
    - compression

21. im not sure, what is the difference between hash table vs B-tree?
    - hash tables has faster lookups, no ordering, but not disk friendly
    - b-trees has slower lookups (log(N)), supports ordered traversal, optimized for disk blocks, used when dictionary doesn't fit in the RAM

22. im not sure, why use B-tree for disk?
    - B-trees minimize disk seeks bu grouping children in same block

23. because hash tables has a lookup of O(1) since hashing is more mathimatical

24. sorted order is a list of elements that is going in a ascending order while hash order is just a collection or bag of hash values pointing to its real value

25. we can do binary search on the dictionary if it is sorted

26. im not sure, what is vocabulary file and lexicon file and what is the difference?
    - lexicon file, stores dictionary entries (term, pointers, stats)
    - vocaublary file, stores raw text of the terms, often compressed

27. front-coding uses the common prefix of the words to compress the words but keep the information

28. the less we need to store the more computing we can do on the main memory

29. to store more on the main memory to do faster computing

30. variable-byte encoding is about using a fixed sized byte array to store numbers in a more effecient way. instead of giving every number a fixed size byte array for storing the value we can use a dynamic size so we dont waste space

31. smaller numbers dont use the full assigned space while bigger numbers need it. instead just give them a small array

32. gamma and delta are good at compressing small numbers which big collection contains

33. because we are working more on the main memoery which computes faster

34. its faster to compute since it only requires a quick if check and can compute bigger chunks at a time

35. because we have to store more data 

36. sometimes it is not relevant for the search engine or task to know the position but only if the documents contains the terms in the query

37. because the collection is so massive we have to distribute the storage to multiple machines. this lets us also do parallalization to speed up the computation

38. document partioning is about searching on a document level, see if a document contains the information we need. term partioning is about seeing if the index contains the term

39. im not sure, why document partioniong is simpler
    - each node indexes its own documents fully
    - no need to merge postings
    - 

40. we have to manage the nodes with a master node and make sure the system is consistent



1. wildcard query is when letting something fill in a gap with any values if they exist. ex*t can evaluate to exit, exist, extinct, etc.

2. they have many more options to search through

3. permutation index is a index that stores any permuations of the terms in the dictionary. it lets you easily find wildcard variants of a term

4. hello, elloh, llohe, lohel, ohell
    - hello$, ello$h, etc.

5. the permutations are sorted in lexographic order and when querying h*o we can do binary search to find a potential result
    - need to rotate the search query from h*o to o$h\*

6. the drawback is that it uses a lot of space

7. it takes to much space

8. k-gram gives segments of a query where each segment is k long. hello with k=3 is hel, ell, llo

9. we can turn the query to a k-gram and compare it with other terms in k-gram form. easier to find overlap of the words and check if there is a term that fulfilles the wildcard query

10. im not sure, how would a k-gram index process the wildcard query: mi*ion?
    - find all terms beginning with $mi and terms ending with ion$ and intersect the results

11. the order might not be in the order the wildcard wanted it

12. we store the positions for the segments in the k-gram

13. smaller k-grams gives more accurate searches while bigger k-grams saves space

14. to mark the end and start of a phrase

15. spelling corrections helps the search engine handle the queries more accuratly

16. im not sure, please explain isolated-word correction and context-sensistive correction
    - isolated, fix each word individually
    - context, use surrounding words to correct

17. because the search queries might be a sentence with a bigger meaning

18. because we cannot be sure what the user wanted to spell

19. im not sure, why do we use dictionary instead of raw text when doing spelling correction?
    - raw text might have typos, inflections, rare words or random strings

20. im not sure, what is candidate generation?
    - producing all dictionary words within a small edit distance of the misspelled word

21. edit distance is the number of edits you have to do to a string to get the other string you are comparing with

22. deletion, substitution, addition, (maybe swap)

23. 2 since you have to remove either a or u and then replace it with the counterpart
    - 1, substitute once

24. im not sure, what is the edit distance between intetion and execution?
    - 5

25. because the table can get quite big and uses too much space in the memory. when doing it with the whole dictionary we have to recreate the table a lot

26. im not sure, why is edit distance often combined with k-grams?
    - k-grams shrinks the candidates to a small list
    - edit table finalizes the correction

27. im not sure, why does edit distance treat insertions and deletions symmetrically?
    - deleting in one string is the same as inserting in the other string, cost wise

28. because we have to for every char m compare it with every char n

29. im not sure, what is jaccard coefficient?
    - jaccard = | A intersect B | / | A union B |

30. im not sure

31. im not sure

32. im not sure

33. im not sure

34. im not sure❌ 22. Edit distance operations

Missing one.

Correct operations:

35. im not sure

36. im not sure

37. soundex is about extracting the sound of a word when being spelled out load. it solves the problem with finding similarity between words

38. the user does not always know how to spell something so if they can write the sound of something we can find a good candidate through sounds

39. sounds and spelling doesnt often match so good

40. benefit is that its more understandable for the users if they dont know how to spell a word or name while edit distance is for correcting spelling mistakes



1. we want to compress them to reduce the size of the index so we can store as much in memory and compute it. disk I/O is the biggest time consumer so we want as little disk writing and reading as possible

2. less disk I/O since we can store more information on the memory

3. storage saving is when we store as much information on as little storage as possible while speed saving is saving on the decoding and encoding speed

4. because its expensive to read and write on disk compared to memory

5. RAM is much closer to the CPU so computing is much faster since the data need to travel a lot less physically. therefor we need to have a much on the memory as possible

6. dictionary compression is when we try to save as much space on the similarity in the words

7. im not sure, what is blocked dictionary compression?
    - for every k terms store the full word
    - the 7 others store only differences

8. im not sure, what is the main drawback of blocke dictionary compression?
    - slower lookup, need to decompress block to get middle terms

9. front-coding is when the terms share the common prefix, they keep the prefix which still makes it easy to sort lexographically

10. 5autom*5obile3ate4tion

11. because they share the same root word

12. posting lists are much more bigger and takes more space than dictionary

13. gaps are generally much smaller numbers

14. variable-byte encoding is using a dynamic amount of bytes to represent a number. smaller numbers use less bytes and bigger numbers use bigger bytes

15. if the bit is 0 then we continue reading for the same number while 1 says that we are done reading for the number on this byte and the next byte is a new number

16. 200 % 128 = 72. var byte: 10000001 01001000

17. because we save space on small numbers since we only use 1 byte to store the numbers instead of fixed 4 byte integers for example

18. im not sure, why is variable-byte encoding faster than gamma coding on modern hardware?
    - gamma coding uses bit-level operations
    - CB uses byte operations which is CPU-optimized, vectorizable

19. it takes time to encode and decode
    - not optimal compression ratio

20. the first part of the code is the length of the binary in unary and the second part is the binary without the first 1

21. The integer 1 for example is noted as a simple 0 in gamma code since the length is 1 but remove 1 since every gap is at least 1 between document so we can remove the 1s from both length and binary

22. there is a lot of computing to do and assumptions to calculate when reverting, must scan unary prefix bit by bit, unpredictable branching

23. delta coding does gamma coding on the unary part of the gamma coding

24. 10 = 1010 in bits, length = 4 which is 11110 in unary this means the gamma coding for 10 is 1110 010

25. because they take time to compute compared to other coding types

26. im not sure, what is zipf's law?
    - term freqnecy is inversly proportional to rank
    - freq <>< 1 / rank

27. im not sure, How does Zipf’s law justify heavy-tail compression?
    - most terms are rare
    - lots of small numbers
    - highly compressible

28. im not sure, Why does Zipf’s law imply that gap lists are dominated by small values?
    - rare terms appear in very fuc documents
    - gaps between docIDs are small
    - ideal for gamma/VB coding

29. because rare items tends to contain smaller numbers and appear a lot so it would be a waste to use big byte arrays to store small numbers

30. compression for bigger numbers takes more time to encode and decode since they require more space

31. we can store more on the main memory and compute there more and avoid many disk I/O

32. im not sure, computing is faster maybe?
    - less data to read from disk
    - compressed lists often fit in CPU cache

33. because we still want to keep the structure we had to still merge in linear time

34. im not sure, Why is storing term frequencies (tf) more expensive when using positional indexing?
    - positions require storing multiple positions per document 

35. block-level compression is faster because the decoding is faster and more predictable

36. we have to decode and then add then encode the value again since its hard to know how to increase a encoded value

37. it is harder to adjust and is vulnarable to changes

38. im not sure, Why is random access difficult in bit-coded postings (gamma/delta)?
    - need to decode everything else before the value you are searching for

39. im not sure, Why do large engines often store two versions of an index: compressed and partially uncompressed?
    - one compressed, effecient storage
    - one partially uncompressed, fast access for frequent queries

40. im not sure, What is the trade-off between compression ratio and decompression speed?
    - higher compression ration, slower decompression
    - faster decompression, worse compression ratio



1. im not sure

2. because AND often results in dropping a lot of docIDs

3. when focusing on smaller posting lists we avoid computing the bigger ones and wasting computations

4. one pointer to each posting list and then iterate the smallest one. if the point pointers point to a docID with the same value then we add it to the merged posting list

5. if we work with smaller posting lists we can avoid doing unecessasary computations

6. if we group the smaller ones first we remove a lot of docIDs we dont need to compute

7. we pick the smaller ones always during a computation of a query

8. we store the term document frequency in the dictionary

9. NOT implies that we search through all the docIDs that does not exist inside of the term being "NOT"ed

10. depends on the frequencies, often should we compute AND first to filter out the docIDs we are 100% not going to compute. if A OR B is smaller than C we should compute them first else we should do C AND A or C AND B

11. when iterating posting lists we have to iterate through the whole list, but sometimes we dont need to. skip pointers let us check if we can skip ahead in the iteration and save time

12. sometimes we cant skip and the pointers take much more space which means we need to store more on the disk

13. im not sure, What heuristic determines how many skip pointers to place in a postings list?

14. AND is about finding matches while OR needs to add everything anyways

15. if we have the docID 1 to 100 and have a skip pointer from 1 to 100 and merge with a posting lists that starts on docID 140 then we can imiditly skip everything instead of iterating 100 times

16. its better to save space and just iterate as normal

17. because we work in increments and the lists are sorted in ascending order

18. that defeats the purpose of skip pointers since they know they can skip the documents inbetween the pointers. if they are not sorted we cannot confidently skip

19. im not sure, what is DAAT query processing?
    - traverse documents in parallel

20. im not sure, what is TAAT query processing?
    - traverse one postinglist per term at a time

21. im not sure, Why is DAAT usually faster for Boolean AND queries?
    - allows skipping documents that dont appear in all lists

22. im not sure, Why is TAAT more natural for computing ranked retrieval scores like tf-idf?
    - i dont know as

23. im not sure, Which model (DAAT vs TAAT) aligns better with skipping and early-termination techniques?
    - daat can skip since we are looking at documents while taat is looking at terms, there are no skipping there

24. im not sure, Which model is more compatible with WAND and MaxScore algorithms?
    - WAND and MaxScore requires DAAT

25. im not sure, Why does TAAT require materializing partial scores for many documents?

26. because we are looking for how many words in range another word is to check if its relevant or not

27. "new" /w1 "york". checks if the word "york" is 1 position in range of "new"

28. im not sure

29. because we are also storing the positions of the terms

30. because we are looking for the positions and not the frequency

31. to save space and increase recall

32. because we want to start with the smaller ones

33. because OR includes every postings

34. because we want to avoid using super common words since almost all documents include them anyways

35. im not sure

36. because its easier to use natural language

37. because we want to be precise in the search of legal, medical or patent documents

38. because its relevant for lawyers to find specific information

39. because boolean is maybe to precise and sometime might not include the documents the user wants to find

40. im not sure



1. vector space model lets us rank the different documents in a more scalar way instead of a yes or no

2. the idea behind representing documents as a vector is that its easier to compare the documents with mathimatics

3. the vectors consists of dimesions for every unique term in the document. long documents may have many unique terms

4. it represents the frequency of the terms in the document

5. they contain the same type of words

6. because long documents would dominate even though its not relevant but repeats the relevant term only because the document is longer

7. euclidean distance relies too much on the length of the vector even though a term vector and a document vector has very similar words, the document might be very long but its classified as less relevant by the eucleadian distance. cosine similarity uses the angle which is more correct since it compares based on the similiarity in the terms in the query and document

8. term frequency is how many times a term is present in a document. if you have document "a b c a", term a will have tf 2 and b 1 and c 1

9. when normalizing term frequency we make sure that long document that contain common terms doesn't win too much on being long

10. im not sure, what is diminishing returns

11. document frequency is how many documents a term is present in while term frequency is about how many times a term is present in a single document

12. because they are common and probably not relevant for the search

13. inverse document frequency is the inverse of document frequency, log(df / N). when df decreses we get a smaller number and when logged it gets bigger

14. tf-idf weighting prioritizes the more rare terms and punishses common terms like stop words

15. log(tf) * log(df/N)

16. because words that are more rare has smaller df which makes the total value bigger since we are logging it. we want to find documents that are niche

17. because bigger numbers when logged are not that much bigger than small numbers when they are also logged. we dont want to focus on common words since they most likely will appear in every document and probably is not relevant

18. im not sure?

19. because longer documents might give terms with high tf value only a high value since the document is longer and not because its more relevant

20. when turning the vectors to vector units its easier to compare them since they are more equal and removes the fact that the documents are longer

21. L2 norm is when every dimension of the vector is divided by the length of the total value of the vector

22. it makes it shorter and easier to compare to other vectores that are also divided with the L2 norm

23. because we punish longer documents by dividing it on itself so everything has a max length of 1

24. im not sure

25. because they might contain a lot of information collected in one page which makes it more relevant in the sense that it has a lot of infromation and not because its repeating the words too often

26. (V1 dot V2) / (|V1| * |V2|)

27. the length of the vector might be wrong to compare since documents are longer or shorter but still equally relevant

28. im not sure, what is orthilogical

29. because we are dividing it on the total length of the vector

30. we can check how the angle of the documents are similar by using cosine. when a document uses the same angle then they are very alike

31. because the similar the documents are to the query the better, when adding more terms to the query then we might get more hits or similarities to the documents since they are sharing more dimensions

32. im not sure

33. because we want to compare the similarity in two different documents, if we make the query a "document" then its easier to compare

34. its often very short

35. they are handled by converting them into tf-idf values

36. because the more dimensions two documents share the more they are similar. when querying with rare terms then the query will share more dimensions with some documents over other documents

37. im not sure

38. document term matrix is a matrix where we list documents in the columns or row and the terms in the row or column. cells are marked as 1 if the term exist in the document else 0

39. because it stores all unique terms in the collection and most documents contain very few of the terms in the dictionary so therefore there is a lot of cells being marked as 0 

40. because each row in a document-term matrix is a sort of vector and represents a document

41. because we are computing larger vectors since we considering the 0s as well

42. because we only consider the 1s in the document-term matrix so when creating a vector out of it then the vectors are shorter and more digestable

43. because if they dont contain the terms in the query they are most likely not relevant. better to throw them away if they dont mention a single term in the query and save time on not computing them for scores

44. im not sure. maybe because we have to sort the scores which can be many?

45. im not sure

46. because we need to check the term fully if we are going to compute partial scores and not the full scores for a document

47. because its to expensive to compute

48. the less we need to compute the better

49. because we usually compute the whole posting lists to compute the score

50. because if a term is very very common then we know its in every document and can be rendered as non relevant for the retrieval

51. im not sure

52. because we cannot take us of the co occurences of words which can be helpful to more confidently determin a document relevant

53. bag of words doesnt mean anything if you want to do phrase queries or proxmity queries

54. im not sure

55. im not sure

56. the words might not have that much meaning as of information but more because they are basic common words that almost every sentence should have

57. im not sure, is it because its normalized and might lose its meaning?

58. by making each vector contain the bm25 values instead of pure frquency values

59. im not sure

60. because we can do a lot more optimilisations in addition to the vsm

