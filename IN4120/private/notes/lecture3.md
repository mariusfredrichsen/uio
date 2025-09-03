## Dictionary data structures

### Hashtables
- fast lookup

- no easy way to find minor differences



### Trees
- binary tree, simplest
    - O-log(n), requires balancing which is expensive, perfect balancing gives O(h) where h is the height of the tree
- B-tree, most used
- Split the trees into segments where each leafnode is a word
    - the segments are words starting with a-m, n-z, etc.


### Bigram (k-gram) indexes
- overlapping sub-segments of the phrase
- april -> $a, ap, pr, ri, il, l$
- processing wild cards
    - mon* -> $m AND mo AND on
    - moon would satisfy this, so therefor check after by enumerating over the phrase


## Wildcard queries
- *
- mon* finds the words starting with the prefix "mon"
- trees has a difficult time searching for temrs fitting complex wildcard searches

### Permuterm index
- hello gives:
    - hello$,ello$h,llo$he,lo$hel,o$hell
- queries
    - X --> X$
    - X* --> $X* 
    - *X --> X$\*
    - *X\* --> X\*
    - X*Y --> Y$X\*
    - X*Y\*Z --> ??? exercise

## Spell correction

### Principles
- Correcting documents being indexed
- Correcting user queries to retrive "right" answers


### Two main flavors
- Isolated word
    - form and from
- Context-sensetiv
    - I flew **from**


### Document correction
- Documents can still be uncorrect or contain spelling mistakes


### Weighted edit distance
- use a probability model to check if the spelling mistake is actually a mistake
    - m and n has a higher probability to be wrong than m and a for example since the physical distance is much larger

### n-gram overlap


### jaccard coefficient
- |X intercept Y| / |X union Y|
- gives a number between 0 and 1
- if the number is high enough then they are similar enough


## Soundex
- class of heuristics to expand a query into phonetic equivalents
- typical use case is for names

### Typical algorithm
- turn every token to be indexed into a 4-character reduced form
- do the same for the query terms
- build and search an index on the redeced forms

- for english
    - keep the first letter in the word
    - A, E, I, O, U, H, W, Y --> 0
    - other letters --> 1
    - some others --> 2
    - remove all pairs of consecutive digits
    - remove all zeros
    - pad the result
- not so useful for information retrival