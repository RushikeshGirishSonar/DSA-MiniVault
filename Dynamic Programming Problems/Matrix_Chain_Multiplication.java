package Dynamic_Programming;

import java.util.Scanner;

public class Matrix_Chain_Multiplication {

    // Simulates slow typing for better visualization
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

    // Matrix Chain Order DP computation
    static int[][] matrixChainOrder(int[] dims, int n) {
        int[][] dp = new int[n][n];

        // Fill the DP table for increasing chain lengths
        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dims[i - 1] * dims[k] * dims[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        return dp;
    }

    // Prints the matrix in a formatted way
    static void printDPMatrix(int[][] dp, int n, int delay) {
        slowPrint("\n DP Cost Matrix (dp[i][j] = min multiplications from matrix i to j):", delay);

        // Column headers
        System.out.print("     ");
        for (int j = 0; j < n; j++) {
            System.out.printf(" %5d", j);
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%5d", i);
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    System.out.print("      ");
                } else {
                    String val = (dp[i][j] == Integer.MAX_VALUE) ? " ∞" : String.valueOf(dp[i][j]);
                    System.out.printf(" %5s", val);
                }
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int delay = 30;

        slowPrint("=> Matrix Chain Multiplication using Dynamic Programming", delay);

        slowPrint("\nEnter the number of matrices: ", delay);
        int n = sc.nextInt();  // Number of matrices

        int[] dims = new int[n + 1];
        slowPrint("Enter the dimension array (length " + (n + 1) + "):", delay);
        for (int i = 0; i <= n; i++) {
            dims[i] = sc.nextInt();
        }

        long start = System.nanoTime();
        int[][] dp = matrixChainOrder(dims, n + 1);
        long end = System.nanoTime();

        printDPMatrix(dp, n + 1, delay);

        slowPrint("\n\n Minimum number of scalar multiplications: " + dp[1][n], delay);
        slowPrint("\n Time taken: " + ((end - start) / 1000) + " microseconds", delay);
        slowPrint("\n Time Complexity: O(n³)", delay);
    }
}
