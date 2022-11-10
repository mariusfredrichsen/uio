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
    
    def move_cells(self, direction):
        print(direction)
        if direction == "down":
            for y in range(3, -1, -1):
                for x in range(3, -1, -1):
                    if self._board[y][x] != None:
                        for temp_y in range(3, y, -1):
                            if self._board[temp_y][x] == None:
                                self._board[temp_y][x], self._board[y][x] = self._board[y][x], None
                                self.merge(x, y, direction)
        
        if direction == "up":
            for y in range(0, 4):
                for x in range(0, 4):
                    if self._board[y][x] != None:
                        for temp_y in range(0, y):
                            if self._board[temp_y][x] == None:
                                self._board[temp_y][x], self._board[y][x] = self._board[y][x], None
                                self.merge(x, y, direction)
        
        if direction == "left":
            for y in range(0, 4):
                for x in range(0, 4):
                    if self._board[y][x] != None:
                        for temp_x in range(0, x):
                            if self._board[y][temp_x] == None:
                                self._board[y][temp_x], self._board[y][x] = self._board[y][x], None
                                self.merge(x, y, direction)
        
        if direction == "right":
            for y in range(0, 4):
                for x in range(3, -1, -1):
                    if self._board[y][x] != None:
                        for temp_x in range(3, x, -1):
                            if self._board[y][temp_x] == None:
                                self._board[y][temp_x], self._board[y][x] = self._board[y][x], None
                                self.merge(x, y, direction)
    
    def merge(self, x, y, retning):
        if retning == "up":
            if y > 0:
                if self._board[y-1][x] != None:
                    if self._board[y-1][x].value() == self._board[y][x].value():
                        self._board[y-1][x]._value *= 2
                        self._board[y][x] = None

        if retning == "down":
            if y < 3:
                if self._board[y+1][x] != None:
                    if self._board[y+1][x].value() == self._board[y][x].value():
                        self._board[y+1][x]._value *= 2
                        self._board[y][x] = None

        if retning == "right":
            if x < 3:
                if self._board[y][x+1] != None:
                    if self._board[y][x+1].value() == self._board[y][x].value():
                        self._board[y][x+1]._value *= 2
                        self._board[y][x] = None
        
        if retning == "left":
            if x > 0:
                if self._board[y][x-1] != None:
                    if self._board[y][x-1].value() == self._board[y][x].value():
                        self._board[y][x-1]._value *= 2
                        self._board[y][x] = None