package Greedy_Algorithms;

import java.util.Scanner;

public class Optimal_BST {

    // ChatGPT-style output: slow printing
    static void printSlow(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // OBST algorithm
    static void optimalBST(int[] keys, int[] freq, int n) {
        int[][] cost = new int[n + 1][n + 1];

        printSlow("\n=== Optimal BST Dry Run ===\n", 30);

        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
            printSlow("Base Case: cost[" + i + "][" + i + "] = freq[" + i + "] = " + freq[i], 20);
        }

        for (int L = 2; L <= n; L++) {
            printSlow("\n\nLength of subarray: " + L, 25);
            for (int i = 0; i <= n - L; i++) {
                int j = i + L - 1;
                cost[i][j] = Integer.MAX_VALUE;

                int sumFreq = sum(freq, i, j);
                System.out.println("\n");
                printSlow("  Subarray keys[" + i + " to " + j + "], sumFreq = " + sumFreq, 25);
                System.out.println("\n");

                for (int r = i; r <= j; r++) {
                    int c = ((r > i) ? cost[i][r - 1] : 0) +
                            ((r < j) ? cost[r + 1][j] : 0) + sumFreq;

                    printSlow("    Trying root = key[" + r + "]: cost = " + c, 25);

                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                        printSlow("    -> Minimum cost updated: cost[" + i + "][" + j + "] = " + c, 25);
                    }
                }
            }
        }

        printSlow("\nMinimum cost to construct Optimal BST = " + cost[0][n - 1], 30);
        printSlow("\n=== Complexity ===", 30);
        printSlow("Best Case: O(n^3) -> Nested loops for each subproblem length and root", 30);
    }

    // Helper method to get sum of frequencies
    static int sum(int[] freq, int i, int j) {
        int total = 0;
        for (int k = i; k <= j; k++) {
            total += freq[k];
        }
        return total;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printSlow("Enter number of keys: ", 30);
        int n = sc.nextInt();

        int[] keys = new int[n];
        int[] freq = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter key " + (i + 1) + ": ");
            keys[i] = sc.nextInt();
            System.out.print("Enter frequency of key " + keys[i] + ": ");
            freq[i] = sc.nextInt();
        }

        long startTime = System.nanoTime();

        optimalBST(keys, freq, n);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000;
        printSlow("\nTime taken: " + duration + " microseconds", 30);
    }
}
