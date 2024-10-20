import numpy as np
import heapq


def solve(table: np.ndarray) -> str:
    """
    :param table: initial state of the board as a NumPy array
    :return: returns the string for the solution
    """
    rows = len(table)
    cols = len(table[0]) # assuming all rows are the same length
    final_board = [[(i * cols + j + 1) % (rows * cols) for j in range(cols)] for i in range(rows)]
    
    empty_spot = find_empty(table) # (y, x)
    s = Node(table, "", empty_spot, 0)
    nodes = {"": s}
    
    pqueue = [(0, "")]
    visited = set()

    while pqueue:
        _, dir = heapq.heappop(pqueue)
        v = nodes[dir]
        y, x = v.empty_spot
                
        if dir in visited:
            continue
        
        visited.add(v.prev_move)

        # select moves that are worth going     
        for direction in get_next_moves(dir):
            if direction == "U" and y - 1 >= 0:
                u = create_swapped_board(v, y, x, direction)
                nodes[u.prev_move] = u
                
                h = compare_boards(u.board, final_board)
                if h == 0:
                    return u.prev_move
                
                heapq.heappush(pqueue, (u.moves + h, u.prev_move)) # add weight based on how alike the boards are
                
            if direction == "R" and x + 1 < cols:
                u = create_swapped_board(v, y, x, direction)
                nodes[u.prev_move] = u
                
                h = compare_boards(u.board, final_board)
                if h == 0:
                    return u.prev_move
                
                heapq.heappush(pqueue, (u.moves + h, u.prev_move))
                
            if direction == "D" and y + 1 < rows:
                u = create_swapped_board(v, y, x, direction)
                nodes[u.prev_move] = u
                
                h = compare_boards(u.board, final_board)
                if h == 0:
                    return u.prev_move
                
                heapq.heappush(pqueue, (u.moves + h, u.prev_move))
                
            if direction == "L" and x - 1 >= 0:
                u = create_swapped_board(v, y, x, direction)
                nodes[u.prev_move] = u
                
                h = compare_boards(u.board, final_board)
                if h == 0:
                    return u.prev_move
                
                heapq.heappush(pqueue, (u.moves + h, u.prev_move))
                



    return ""

class Node:
    def __init__(self, board, prev_move, empty_spot, moves):
        self.board = board
        self.prev_move = prev_move
        self.empty_spot = empty_spot
        self.moves = moves


def find_empty(table: np.ndarray):
    for y, row in enumerate(table):
        for x, col in enumerate(row):
            if col == 0: # the element in row
                return (y, x)

def create_swapped_board(node: Node, y0: int, x0: int, direction: str):
    board = node.board.copy()
    
    # find new index for directions
    if direction == "U":
        y1, x1 = y0-1, x0
    elif direction == "R":
        y1, x1 = y0, x0+1
    elif direction == "D":
        y1, x1 = y0+1, x0
    else:
        y1, x1 = y0, x0-1
        
    board[y0][x0], board[y1][x1] = board[y1][x1], board[y0][x0] # swap items at the indexes
    return Node(board, node.prev_move + direction, (y1, x1), node.moves + 1)

def get_next_moves(prev_moves: str) -> str:
    if prev_moves == "":
        return "URDL"
    last_move = prev_moves[-1]
    if last_move == "U":
        return "URL"
    elif last_move == "R":
        return "URD"
    elif last_move == "D":
        return "RDL"
    else:
        return "UDL"


def compare_boards(board1, board2) -> int:
    penalty = 0
    for y, row in enumerate(board1):
        for x, cell in enumerate(row):
            if cell != board2[y][x]:
                penalty += 1
    return penalty