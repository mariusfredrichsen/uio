import pytest
import numpy as np

from AStar import solve


def is_solution_valid(board, moves):
    cloned_board = np.copy(board)
    apply_moves(cloned_board, moves)
    return is_solved(cloned_board)


def apply_moves(board, moves):
    n = board.shape[0]
    x, y = np.argwhere(board == 0)[0]
    for move in moves:
        if move == 'L' and y > 0:
            board[x, y], board[x, y - 1] = board[x, y - 1], board[x, y]
            y -= 1
        elif move == 'R' and y < n - 1:
            board[x, y], board[x, y + 1] = board[x, y + 1], board[x, y]
            y += 1
        elif move == 'U' and x > 0:
            board[x, y], board[x - 1, y] = board[x - 1, y], board[x, y]
            x -= 1
        elif move == 'D' and x < n - 1:
            board[x, y], board[x + 1, y] = board[x + 1, y], board[x, y]
            x += 1


def is_solved(board):
    n = board.shape[0]
    expected_value = 1
    for i in range(n):
        for j in range(n):
            if i == n - 1 and j == n - 1:
                if board[i, j] != 0:
                    return False
            else:
                if board[i, j] != expected_value:
                    return False
                expected_value += 1
    return True


class TestAStar:
    def test_case1(self):
        input_board = np.array([
            [1, 2, 0],
            [4, 6, 3],
            [7, 5, 8]
        ])
        solution = solve(input_board)
        suggested_solution = "DLDR"
        assert len(suggested_solution) == len(solution), "Solution is not optimal"
        assert is_solution_valid(input_board, solution), "The puzzle is not solved after applying the solution moves."

    def test_case2(self):
        input_board = np.array([
            [1, 0, 5],
            [4, 3, 2],
            [7, 8, 6]
        ])
        solution = solve(input_board)
        suggested_solution = "DRULDRD"
        assert len(suggested_solution) == len(solution), "Solution is not optimal"
        assert is_solution_valid(input_board, solution), "The puzzle is not solved after applying the solution moves."

    def test_case3(self):
        input_board = np.array([
            [0, 1, 7],
            [5, 2, 6],
            [4, 8, 3]]
        )
        solution = solve(input_board)
        suggested_solution = "RRDLURDDLUURDLLDRR"
        assert len(suggested_solution) == len(solution), "Solution is not optimal"
        assert is_solution_valid(input_board, solution), "The puzzle is not solved after applying the solution moves."

    def test_case4(self):
        input_board = np.array([
            [0, 1, 3, 4],
            [5, 2, 6, 7],
            [9, 10, 11, 8],
            [13, 14, 15, 12]
        ])
        solution = solve(input_board)
        suggested_solution = "RDRRDD"
        assert len(suggested_solution) == len(solution), "Solution is not optimal"
        assert is_solution_valid(input_board, solution), "The puzzle is not solved after applying the solution moves."
