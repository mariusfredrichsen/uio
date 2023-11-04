


def calc_height(height: int) -> int:
    n = 1
    for h in range(1, height+1):
        n += (2**h)
    return n
        
def solve():
    inn = input().strip().split()
    if len(inn) == 2:
        height, path = int(inn[0]), inn[1]
    else:
        height = int(inn[0])
        path = ""
    
    root = calc_height(height)
    i = 0
    for p in path:
        if p == "R":
            i = (i*2+2)
        elif p == "L":
            i = (i*2+1)
    print(root-i)

solve()