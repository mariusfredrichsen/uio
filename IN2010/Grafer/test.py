from collections import defaultdict
from sys import stdin

N, Q = [int(x) for x in next(stdin).strip().split()]

bank = defaultdict(lambda: 0)

for i in range(Q):
    command = next(stdin).strip().split()
    
    if command[0] == "SET":
        bank[command[1]] = command[2]
    elif command[0] == "PRINT":
        print(bank[command[1]])
    elif command[0] == "RESTART":
        asd = command[1]
        bank = defaultdict(lambda: asd)
