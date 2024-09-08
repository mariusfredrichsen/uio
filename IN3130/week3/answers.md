# Exercise 1
## a) Show that n * 3, n + 3 both are O(n).


## b) Show that 2n log n is O(n2).
## c) Is 2^(n + 1) = O(2n) ?
## d) Is (10n + 16n^3) / 2 = O(n^2) ?

# Exercise 2
## a) What do we know about the running time of an algorithm if it is O(n!) ?
## b) What do we know about the running time of an algorithm if it is Ω(n) ?
## c) What do we know about the running time of an algorithm if it is Θ (2n) ?
## d) What do we know about the running time of an algorithm if it is O(n2) ?
## e) The statement “This algorithm has a running time of at least O(n2).” may seem odd. Does it make sense?

# Exercise 3
> Spend some time repeating/discussing why/how the different shift strategies of Knuth-Morris-Pratt and simplified Boyer-Moore (Horspool) work. Pay attention to what parts of the pattern P and T overlap with what, and why that is necessary for a match to be possible.

# Exercise 4
> Find the overlapping prefixes and suffixes (as defined in the Knuth-Morris-Pratt-algorithm) for the strings “ababc”, “ababba”, “010” and “00100”. You may find the prefixes/suffixes in an intuitive way (by looking and counting).

# Exercise 5 (Knuth-Morris-Pratt, Exercise 20.3 in Berman & Paul)
> Simulate CreateNext pages 637-8 in Berman & Paul – calculate the Next[]-array for the pattern “abracadabra”.

# Exercise 5 (Horspool)
> Simulate CreateShift page 639 in Berman & Paul – calculate the array Shift[a:z] for the patterns P1 = "announce", and P2 = "honolulu".

# Exercise 6
> Draw uncompressed suffix trees for the strings "BABBAGE" and "BAGLADY". And check if "BAG" is a common substring. Can you make do with only one tree?