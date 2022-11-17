from statistics import median
from typing import Counter
from cell import Cell
import random as r
import math

class Playboard:
    def __init__(self):
        self._board = self.create_board()
        self._score = 0

    def create_board(self):
        board = []
        for i in range(4):
            board.append([None,None,None,None])
        return board
    
    def create_cell(self):
        x, y = r.randint(0,3), r.randint(0,3)
        if self._board[y][x] == None:
            self._board[y][x] = Cell(x, y)
        else:
            self.create_cell()
    
    def draw(self):
        for i in range(2):
            print()
        for list in self._board:
            print("\n", end = "")
            for elem in list:
                if elem == None:
                    elem = 0
                print(f"{elem}", end = " ")
    
    def move(self, direction):
        