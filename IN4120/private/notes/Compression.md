# Compression
- compression usaually happens in chunks

## Inverted lists
- They contain docIDs, frequencies, positions and maybe context, which are integer values (so far)
- Most of the lists are short, but the bigger ones dominate in index size

### Compression methods
- compute differences in docIDs, also called **gaps**


## Var-byte compression

### Method
- the compression works by dividing the numbers up in one single byte array
- the first bit in the bytes works as a indicator, if 1 then the byte sequence is not over, if 0 then we add the value from the 7 other bits to the sum of the sequence
- if the indicator is 1 then the values of the 7 other bits are multiplied with 128

## Rice coding
- simple to implement
- better compression than var-byte, but slightly slower

### Method
- Find the gaps and calculate the average gap size and use that to make a variable b which is the avg gap rounded down to the closest 2^k. if < 128 then 6 since 2⁶ = 64
- take the gap number and divide it by b and round it down to the closest integer and store it as a unary, 1 = 10, 0 = 0, 5 = 111110
- after that add the rest (gap number % b) and add it directly as a binary number
- 34, x-1 => 33, 33 // 64 = 0 => 0, 33 % 64 = 33 => 100001
- 161, x-1 => 160, 160 // 64 = 2 => 110, 160 & 64 = 33 => 100001

## Golomb coding
- optimal for random gaps
- very similar to rice coding

### Method
- very confusing, but calculate the gaps and the avarage gaps
- find a number M, usually deducted from the average
- calculate the number b by rounding up the log_2 of M
- the number of bits used for the compression depends on the rest r from the number x (gap size) and b






