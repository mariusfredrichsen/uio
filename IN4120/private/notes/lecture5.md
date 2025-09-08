# Retrival and Web Search, Index compression

### Why compression
- saves space and money
- read compressed data | decompress
    - faster than reading uncompressed data
- premise: decompression algorithms are fast

- make the dictionary small enough to keep in main memory
- make the postings lists small enough to be in main memory too
    - takes the most space
    - large search engines keeps a significant part of the postings list in memory

### Lossless vs lossy compression
- lossy, information loss when removing words from either the dictionary or postings list (position / non-positional)
- **Heaps' law**: M = kT^b
    - M, size of vocabulary
    - T, number of tokens in the collection
    - k, typical value between 30 and 100
    - b, roughfly 0.5, increase rate of the formula
    - Used when trying to find out how many terms there is, not what they are
        - How much should we compress? How many are we compressing?
- **Zipf's**: cf = K/i
    - cf is collection frequency, number of occurrences of the term t in the collection
    - the most frequent word occurs cf_1 times
        - the second most frequent word occurs cf_1/2 times
        - the third most frequent word occurs cf_1/3 times
        - so on...
    - power law relationship, follows a kind of law
    - log-log space, take the logarithmic value of both sides of the equal sign

### Dictionary compression
- fixed-width is wastefull
- use a long string and have a table with pointers to the string (pointer to where the word starts)
- se på blokker???
- Front coding, share prefix

### Postings compression
- where you save the most
- goal: use less than 20 bits (recommended) per docID
- gap encoding, look at the difference between the next docID, works since the list is sorted

#### Variable length encoding (VB-encoding)
- uses base 127
    - times 128 for each byte required
- in addition to a continuation bit, 1-done, 0-more bytes coming
- stored in a byte concatenation

#### Unary code
- represent n as n 1s with a final 0
- 3 is 1110
- 40 is 40x1 + 0

#### Gamma codes
- reduce to bit-level
- the first bit is always 1 so cut it off
- length is the length of offset
- its represented in unary


#### Simple9 (S9) Coding
- blocks of 32 bits
- some bits for the data, some bits for how to interperet the data formatting
- 4 controll bits, 28 data bits

#### PFOR-DELTA
- Patched Frame Of Reference
- choose a number that fits 90% of numbers
- read the array from left to right, the left side contains flags that says how many cells in the array that are good. After the flags then read from the right once then continue on the left