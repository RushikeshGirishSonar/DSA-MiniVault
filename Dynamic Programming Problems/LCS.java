package Dynamic_Programming;

import java.util.Scanner;

public class LCS {

    // Simulates ChatGPT-style slow printing for educational effect
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

    // Compute LCS using bottom-up DP and return the filled matrix
    static int[][] computeLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill the matrix with the logic of LCS
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;  // Match
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);  // Skip one character
                }
            }
        }
        return dp;
    }

    // Display the DP matrix with row/column headers
    static void printDPMatrix(int[][] dp, String s1, String s2, int delay) {
        int m = s1.length();
        int n = s2.length();

        slowPrint("\n DP Matrix (dp[i][j] = LCS length of s1[0..i-1] and s2[0..j-1]):", delay);
        
        // Header row (characters of s2)
        System.out.print("     ");
        System.out.print(" - ");
        for (int j = 0; j < n; j++) {
            System.out.printf("  %c ", s2.charAt(j));
        }
        System.out.println();

        // DP table rows
        for (int i = 0; i <= m; i++) {
            if (i == 0) System.out.print(" - ");
            else System.out.printf(" %c ", s1.charAt(i - 1));
            for (int j = 0; j <= n; j++) {
                System.out.printf("  %d ", dp[i][j]);
            }
            System.out.println();
        }
    }

    // Main method to drive the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int delay = 30;

        slowPrint(" Longest Common Subsequence (LCS) using Dynamic Programming\n", delay);
        
        slowPrint("Enter the first string: ", delay);
        String s1 = sc.nextLine();

        slowPrint("Enter the second string: ", delay);
        String s2 = sc.nextLine();

        slowPrint("\n Computing the DP matrix...\n", delay);
        long startTime = System.nanoTime();

        int[][] dp = computeLCS(s1, s2);

        long endTime = System.nanoTime();

        // Display the DP table
        printDPMatrix(dp, s1, s2, delay);

        int lcsLength = dp[s1.length()][s2.length()];
        long duration = (endTime - startTime) / 1000;

        slowPrint("\n Length of Longest Common Subsequence: " + lcsLength, delay);
        slowPrint("\n Time taken: " + duration + " microseconds", delay);
        slowPrint("\n Time Complexity: O(m × n)", delay);
        slowPrint("\n Where m = length of first string, n = length of second string.", delay);
    }
}
