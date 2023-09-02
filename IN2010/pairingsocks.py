n = int(input())
mPile = input().strip().split()
aPile = []
pairs = 0
moves = 0

while True:
    if len(mPile) == 0:
        break
    if len(aPile) == 0 or mPile[-1] != aPile[-1]:
        aPile.append(mPile.pop(-1))
        moves += 1
    if len(mPile) == 0:
        break
    if mPile[-1] == aPile[-1]:
        aPile.pop(-1)
        mPile.pop(-1)
        pairs += 1
        moves += 1

if pairs == n:
    print(moves)
else:
    print("impossible")