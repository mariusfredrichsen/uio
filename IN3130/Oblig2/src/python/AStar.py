import numpy as np


def solve(table: np.ndarray) -> str:

    print(find_empty(table))

    """
    :param table: initial state of the board as a NumPy array
    :return: returns the string for the solution
    """
    return ""

def find_empty(table: np.ndarray):
    for y, row in enumerate(table):
        for x, col in enumerate(row):
            if col == 0: # the element in row
                return (y, x)

class Node:
    def __init__(self, board):
        self.board = board



def compare(board1, board2) -> int:
    score = 0
    for y, row in enumerate(board1):
        for x, cell in enumerate(row):
            if cell == board2[y][x]:
                score += 1
    return score
        


input_board = np.array([
    [1, 2, 0], 
    [4, 6, 3], 
    [7, 5, 8]])

input_board1 = np.array([
    [1, 0, 2], 
    [4, 6, 3], 
    [7, 5, 8]])

solve(input_board)

print(compare(input_board, input_board1))