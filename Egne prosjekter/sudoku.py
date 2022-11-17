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

brett = [ [nums[pattern(r,c)] for c in cols] for r in rows ]
brett_kopi = brett
# ^^^Henta fra stackoverflow ^^^

for linje in range(len(brett)):
    for elem in range(len(brett)):
        if r.randint(0,10) <= 6:
            brett[linje][elem] = 0

def skriv_ut(brett):
    for i in range(3):
        print()
    for linje in brett:
        for elem in linje:
            print(elem, end=" ")
        print()

def lovlig(y, x, n):
    global brett

    for i in range(9):
        if brett[y][i] == n:
            return False
        
    for i in range(9):
        if brett[i][x] == n:
            return False
    
    x0 = (x//3)*3
    y0 = (y//3)*3
    for i in range(3):
        for j in range(3):
            if brett[y0+i][x0+j] == n:
                return False
    
    return True

skriv_ut(brett)

sjekket_liste = []

index = 0
y = 0
x = 0

while True:
    print(index)
    print(sjekket_liste)
    if index < x+y*8:
        sjekket_liste[-1] = [1,2,3,4,5,6,7,8,9]
    else:
        sjekket_liste.append([1,2,3,4,5,6,7,8,9])
    if brett[y][x] == 0:
        for i in range(len(sjekket_liste[index])):
            if lovlig(y,x,sjekket_liste[index][0]):
                brett[y][x] = sjekket_liste[index][0]
                if x == 8:
                    x = 0
                    y += 1
                    index += 1
                    break
                else:
                    x += 1
                    index += 1
                    break
            else:
                sjekket_liste[index].pop(0)
    else:
        if x == 8:
            x = 0
            y += 1
            index += 1
        else:
            x += 1
            index += 1
    skriv_ut(brett)