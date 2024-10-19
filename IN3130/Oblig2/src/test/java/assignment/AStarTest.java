package assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AStarTest {

    private AStar astar;

    @BeforeEach
    public void setUp() {
        astar = new AStar();
    }

    @Test
    public void testCase1() {
        int[][] input = {
                {1, 2, 0},
                {4, 6, 3},
                {7, 5, 8}
        };
        String solution = astar.solve(input);
        String suggestedSolution = "DLDR";
        assertEquals(suggestedSolution.length(), solution.length(), "Solution is not optimal");
        assertTrue(isSolutionValid(input, solution), "The puzzle is not solved after applying the solution moves.");
    }

    @Test
    public void testCase2() {
        int[][] input = {
                {1, 0, 5},
                {4, 3, 2},
                {7, 8, 6}
        };
        String solution = astar.solve(input);
        String suggestedSolution = "DRULDRD";
        assertEquals(suggestedSolution.length(), solution.length(), "Solution is not optimal");
        assertTrue(isSolutionValid(input, solution), "The puzzle is not solved after applying the solution moves.");
    }

    @Test
    public void testCase3() {
        int[][] input = {
                {0, 1, 7},
                {5, 2, 6},
                {4, 8, 3}
        };
        String solution = astar.solve(input);
        String suggestedSolution = "RRDLURDDLUURDLLDRR";
        assertEquals(suggestedSolution.length(), solution.length(), "Solution is not optimal");
        assertTrue(isSolutionValid(input, solution), "The puzzle is not solved after applying the solution moves.");
    }

    @Test
    public void testCase4() {
        int[][] input = {
                {0, 1, 3, 4},
                {5, 2, 6, 7},
                {9, 10, 11, 8},
                {13, 14, 15, 12}
        };
        String solution = astar.solve(input);
        String suggestedSolution = "RDRRDD";
        assertEquals(suggestedSolution.length(), solution.length(), "Solution is not optimal");
        assertTrue(isSolutionValid(input, solution), "The puzzle is not solved after applying the solution moves.");
    }

    private boolean isSolutionValid(int[][] board, String moves) {
        int[][] clonedBoard = cloneBoard(board);
        applyMoves(clonedBoard, moves);
        return isSolved(clonedBoard);
    }

    private int[][] cloneBoard(int[][] board) {
        int n = board.length;
        int[][] cloned = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, cloned[i], 0, n);
        }
        return cloned;
    }

    private void applyMoves(int[][] board, String moves) {
        int n = board.length;
        int x = 0, y = 0;
        search:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                    break search;
                }
            }
        }

        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'L' -> {
                    if (y > 0) {
                        swap(board, x, y, x, y - 1);
                        y--;
                    }
                }
                case 'R' -> {
                    if (y < n - 1) {
                        swap(board, x, y, x, y + 1);
                        y++;
                    }
                }
                case 'U' -> {
                    if (x > 0) {
                        swap(board, x, y, x - 1, y);
                        x--;
                    }
                }
                case 'D' -> {
                    if (x < n - 1) {
                        swap(board, x, y, x + 1, y);
                        x++;
                    }
                }
            }
        }
    }

    private boolean isSolved(int[][] board) {
        int n = board.length;
        int expectedValue = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    // The last cell should be 0
                    if (board[i][j] != 0) return false;
                } else {
                    if (board[i][j] != expectedValue) return false;
                    expectedValue++;
                }
            }
        }

        return true;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
}
