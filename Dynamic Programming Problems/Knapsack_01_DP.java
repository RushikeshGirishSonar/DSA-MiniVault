package Dynamic_Programming;

import java.util.Scanner;

public class Knapsack_01_DP {

    // Slow printing to simulate educational typing
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

    // Function implementing 0/1 Knapsack using Dynamic Programming
    static int[][] knapsackDP(int[] weights, int[] profits, int n, int W) {
        int[][] dp = new int[n + 1][W + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(profits[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp;
    }

    // Function to print the DP matrix for better understanding
    static void printDPMatrix(int[][] dp, int[] weights, int[] profits, int W, int delay) {
        int n = weights.length;
        slowPrint("\n DP Matrix (dp[i][w] shows max profit using first i items for capacity w):", delay);

        // Print column headers (capacities)
        System.out.print("     ");
        for (int w = 0; w <= W; w++) {
            System.out.printf("%4d", w);
        }
        System.out.println();

        // Print matrix with item row labels
        for (int i = 0; i <= n; i++) {
            if (i == 0) System.out.printf("  0 ");
            else System.out.printf(" %2d ", i);
            for (int w = 0; w <= W; w++) {
                System.out.printf("%4d", dp[i][w]);
            }
            System.out.println();
        }
    }

    // Main method to drive the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int delay = 25;

        slowPrint(" 0/1 Knapsack Problem Solver using Dynamic Programming\n", delay);
        slowPrint("Enter number of items: ", delay);
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] profits = new int[n];

        slowPrint("Enter weights of items (space-separated):", delay);
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        slowPrint("Enter profits of items (space-separated):", delay);
        for (int i = 0; i < n; i++) {
            profits[i] = sc.nextInt();
        }

        slowPrint("Enter maximum capacity of the knapsack: ", delay);
        int W = sc.nextInt();

        slowPrint("\n Solving using DP...\n", delay);
        long start = System.nanoTime();  // Start timing

        int[][] dp = knapsackDP(weights, profits, n, W);

        long end = System.nanoTime();    // End timing

        // Display the DP table
        printDPMatrix(dp, weights, profits, W, delay);

        // Print result and performance
        slowPrint("\n Maximum profit achievable: " + dp[n][W], delay);
        slowPrint("\n Time taken: " + ((end - start) / 1000) + " microseconds", delay);
        slowPrint("\n Time Complexity: O(n X W)", delay);
    }
}
