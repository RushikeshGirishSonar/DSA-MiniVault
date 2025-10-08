package Backtracking_Branch_Bound;

import java.util.Scanner;

public class N_Queens_Problem {

    // Function to simulate slow typing like ChatGPT
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

    // Function to display the board
    static void printBoard(int[][] board, int n, int delay) {
        slowPrint("\n One valid arrangement of queens (1 = queen, 0 = empty):", delay);
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(board[i][j]).append(" ");
            }
            slowPrint(row.toString(), delay);
        }
    }

    // Check if it's safe to place a queen at board[row][col]
    static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check vertically above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                slowPrint(" Conflict at column " + col + " (vertical)", 20);
                return false;
            }
        }

        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                slowPrint(" Conflict at (" + i + "," + j + ") (left diagonal)", 20);
                return false;
            }
        }

        // Check upper right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                slowPrint(" Conflict at (" + i + "," + j + ") (right diagonal)", 20);
                return false;
            }
        }

        return true;
    }

    // Recursive backtracking function
    static boolean solveNQueens(int[][] board, int row, int n, int delay) {
        if (row == n) {
            printBoard(board, n, delay);
            return true;  // Return true if you want only one solution
        }

        boolean result = false;
        for (int col = 0; col < n; col++) {
            slowPrint("-> Trying to place queen at row " + row + ", col " + col, delay);

            if (isSafe(board, row, col, n)) {
                board[row][col] = 1;
                slowPrint(" Placed queen at (" + row + ", " + col + ")", delay);

                // Recurse to next row
                result = solveNQueens(board, row + 1, n, delay) || result;

                // Backtrack
                board[row][col] = 0;
                slowPrint(" Backtracking from (" + row + ", " + col + ")", delay);
            } else {
                slowPrint("\n Cannot place queen at (" + row + ", " + col + ")\n", delay);
            }
        }

        return result;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int delay = 50;

        slowPrint("Enter the number of queens (n): ", delay);
        int n = sc.nextInt();

        int[][] board = new int[n][n];

        boolean found = solveNQueens(board, 0, n, delay);

        if (!found) {
            slowPrint("\n No solution exists for n = " + n, delay);
        } else {
            slowPrint("\n N-Queens solution completed.", delay);
        }
    }
}
