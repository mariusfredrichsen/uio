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
board_copy = board
# ^^^Henta fra stackoverflow ^^^

for linje in range(len(board)):
    for elem in range(len(board)):
        if r.randint(0,10) <= 6:
            board[linje][elem] = 0

def skriv_ut(board):
    for i in range(3):
        print()
    for linje in board:
        for elem in linje:
            print(elem, end=" ")
        print()

def lovlig(y, x, n):
    global board

    for i in range(9):
        if board[y][i] == n:
            return False
        
    for i in range(9):
        if board[i][x] == n:
            return False
    
    x0 = (x//3)*3
    y0 = (y//3)*3
    for i in range(3):
        for j in range(3):
            if board[y0+i][x0+j] == n:
                return False
    
    return True

<<<<<<< HEAD
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
=======
skriv_ut(board)
>>>>>>> 3da9d68a235d3608506a18f665ca837936944c75

print(lovlig(4, 4, 5))

<<<<<<< HEAD






def main():
    pass

main()
=======
def loos():
    global board
    for y in range(9):
        for x in range(9):
            if board[y][x] == 0:
                for i in range(1,10):
                    if lovlig(y,x,i):
                        board[y][x] = i
                        loos()
                
                        board[y][x] = 0
                return
loos()
skriv_ut(board)
>>>>>>> 3da9d68a235d3608506a18f665ca837936944c75
