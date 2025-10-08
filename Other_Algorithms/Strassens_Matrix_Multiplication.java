package Other_Algorithms;

import java.util.Scanner;

public class Strassens_Matrix_Multiplication { 

    // Slow-print utility for visualization
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

    // Matrix addition
    static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        slowPrint("+ Adding two matrices of size " + n + "x" + n, 30);
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    // Matrix subtraction
    static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        slowPrint("- Subtracting two matrices of size " + n + "x" + n, 30);
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];
        return result;
    }

    // Strassen's Matrix Multiplication Algorithm
    static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        if (n == 1) {
            slowPrint(" Base case: Multiplying single elements", 30);
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }

        int newSize = n / 2;
        slowPrint(" Dividing matrices into 4 submatrices of size " + newSize + "x" + newSize, 30);

        // Create submatrices
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + newSize];
                A21[i][j] = A[i + newSize][j];
                A22[i][j] = A[i + newSize][j + newSize];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + newSize];
                B21[i][j] = B[i + newSize][j];
                B22[i][j] = B[i + newSize][j + newSize];
            }
        }

        // 7 recursive multiplications as per Strassen’s formula
        slowPrint(" Calculating 7 intermediate matrices (M1 to M7)...", 30);
        int[][] M1 = strassenMultiply(add(A11, A22), add(B11, B22));
        int[][] M2 = strassenMultiply(add(A21, A22), B11);
        int[][] M3 = strassenMultiply(A11, subtract(B12, B22));
        int[][] M4 = strassenMultiply(A22, subtract(B21, B11));
        int[][] M5 = strassenMultiply(add(A11, A12), B22);
        int[][] M6 = strassenMultiply(subtract(A21, A11), add(B11, B12));
        int[][] M7 = strassenMultiply(subtract(A12, A22), add(B21, B22));

        // Compute the resulting quadrants
        slowPrint("\n Combining submatrices to form result", 30);
        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);

        // Join quadrants into one result matrix
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                result[i][j] = C11[i][j];
                result[i][j + newSize] = C12[i][j];
                result[i + newSize][j] = C21[i][j];
                result[i + newSize][j + newSize] = C22[i][j];
            }
        }

        return result;
    }

    // Print matrix in formatted form
    static void printMatrix(int[][] matrix) {
        slowPrint("\n Resultant Matrix:", 30);
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        slowPrint(" Welcome to Strassen’s Matrix Multiplication Algorithm", 50);
        slowPrint("Enter size of the square matrices (must be power of 2):", 50);
        int n = sc.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        slowPrint(" Enter elements for Matrix A:", 50);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = sc.nextInt();

        slowPrint(" Enter elements for Matrix B:", 50);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                B[i][j] = sc.nextInt();

        slowPrint("=> Performing Strassen’s Multiplication...\n", 50);
        long start = System.nanoTime();
        int[][] C = strassenMultiply(A, B);
        long end = System.nanoTime();

        printMatrix(C);
        slowPrint("\n Multiplication Complete!", 50);
        slowPrint("\n Time taken: " + ((end - start) / 1000) + " microseconds", 50);
    }
}
