package Backtracking_Branch_Bound;

import java.util.Scanner;

public class Queens_Display_4 {

    // Slow print function for step-by-step output
    static void slowPrint(String text, int delay) {
        for (char ch : text.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    static final int N = 4; // Fixed size for 4-Queens
    static int delay = 50;  // delay in ms for slowPrint

    // Check if placing a queen at board[row][col] is safe
    static boolean isSafe(int[][] board, int row, int col) {
        // Check column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }

    // Recursive function to solve the 4-Queens problem
    static boolean solveNQueens(int[][] board, int row) {
        if (row == N) {
            printBoard(board);
            slowPrint("Found one valid solution!\n", delay);
            return true;  // Return true to find only one solution
        }

        boolean foundSolution = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                slowPrint("Placed queen at row " + row + ", col " + col, delay);
                if (solveNQueens(board, row + 1)) {
                    foundSolution = true;
                }
                board[row][col] = 0; // Backtrack
                slowPrint("Backtracking from row " + row + ", col " + col, delay);
            }
        }
        return foundSolution;
    }

    // Print the chessboard
    static void printBoard(int[][] board) {
        slowPrint("\nBoard configuration (1 = Queen, 0 = empty):", delay);
        for (int i = 0; i < N; i++) {
            StringBuilder rowStr = new StringBuilder();
            for (int j = 0; j < N; j++) {
                rowStr.append(board[i][j]).append(" ");
            }
            slowPrint(rowStr.toString(), delay);
        }
        slowPrint("", delay);
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];

        slowPrint("Solving the 4-Queens problem using backtracking...\n", delay);

        if (!solveNQueens(board, 0)) {
            slowPrint("No solution found!", delay);
        }
    }
}
