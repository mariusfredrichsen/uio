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
- laws to estimate how many terms we are working with and how big the collection is
- **Heaps' law**: M = kT^b
    - M, **size of vocabulary**
    - T, number of tokens in the collection
    - k, typical value between 30 and 100
    - b, roughfly 0.5, increase rate of the formula
    - Used when trying to find out how many terms there is, not what they are
        - How much should we compress? How many are we compressing?
- **Zipf's**: cf = K/i
    - cf is collection frequency, number of occurrences of the term t in the collection
    - the most frequent word occurs cf_1 times
        - the second most frequent word occurs cf_1 / 2 times
        - the third most frequent word occurs cf_1 / 3 times
        - so on...
    - power law relationship, follows a kind of law
    - log-log space, take the logarithmic value of both sides of the equal sign

### Dictionary compression
- fixed-width is wastefull
- use a long string and have a table with pointers to the string (pointer to where the word starts)
- blocking, store the pointers and the lengths for every kth item. k = 4 saves 4 bytes
- Front coding, share prefixes and signal with a unique symbol
    - 8automata8automate9automatic10automation
    - 8automat*a1→e2→ic3→ion

### Postings compression
- the postings file is much larger than the dictionary by at least a factor of 10
- where you save the most
- goal: use less than 20 bits (recommended) per docID
- two conflicting forces
    - terms that appear in very few documents vs terms that appear in every document
- gap encoding, look at the difference between the next docID, works since the list is sorted
    - average gap for a term is G
    - want to use log_2(G) bits/gap entry

#### Variable length encoding (VB-encoding)
- for a gap value G we want to store fewest bytes needed to hold log_2(G) bits
- start with one byte to store G and dedicate 1 but in it to be a continuation bit c
- every byte *b* is valued N % 128^b where b starts at 1

- different variant could be using different byte size for different words, 32, 16 and 4 bits

#### Unary code
- represent n as n 1s with a final 0
- 3 is 1110
- 40 is 40x1 + 0

#### Gamma codes
- split the number into two parts
    - unary code length of the binary representation (minus 1)
    - the binary representation with its leading 1

#### Simple9 (S9) Coding
- blocks of 32 bits
- some bits for the data, some bits for how to interperet the data formatting
- 4 controll bits, 28 data bits

#### PFOR-DELTA
- Patched Frame Of Reference
- choose a number that fits 90% of numbers
- read the array from left to right, the left side contains flags that says how many cells in the array that are good. After the flags then read from the right once then continue on the left