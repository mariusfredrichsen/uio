import random as r
import numpy as np

base  = 3
side  = base*base

def pattern(r,c): return (base*(r%base)+r//base+c)%side

def shuffle(s): return r.sample(s,len(s)) 
rBase = range(base) 
rows  = [ g*base + r for g in shuffle(rBase) for r in shuffle(rBase) ] 
cols  = [ g*base + c for g in shuffle(rBase) for c in shuffle(rBase) ]
nums  = shuffle(range(1,base*base+1))

board = [ [nums[pattern(r,c)] for c in cols] for r in rows ]
# ^^^Henta fra stackoverflow ^^^
print(board)

for linje in range(len(board)):
    for elem in range(len(board)):
        if r.randint(0,10) <= 6:
            board[linje][elem] = 0

print(np.matrix(board))

def lovlig(brett, rute):
    #Sjekker linjene
    liste_nedover = []
    for linje in range(len(brett)):
        liste_nedover.append(brett[linje][rute[1]])
    
    if brett[rute[0]].count(brett[rute[0]][rute[1]]) > 1 or liste_nedover.count(brett[rute[0]][rute[1]]) > 1:
        return False
    
    #Sjekker store ruter
    teller = 0
    for linje in range(rute[0]//3, rute[0]//3+3):
        for elem in range(rute[1]//3, rute[1]//3+3):
            if brett[rute[0]][rute[1]] == brett[linje][elem]:
                teller += 1
    if teller > 1:
        return False
    return True

def duppeditt(brett, x, y):
    if brett[x][y] == 0:
        for i in range(1,10):
            brett[x][y] = i
            if lovlig(brett,[x,y]):
                x += 1
                if x == 9:
                    x = 0
                    y += 1
            elif not lovlig(brett,[x,y]) and i == 9:
                pass








def main():
    pass

main()