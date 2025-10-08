package Other_Algorithms;

import java.util.Scanner;

public class TravelingSalesmanNearestNeighbor {

    // Simulated typing effect for better visualization
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

    // Solves the TSP using the Nearest Neighbor heuristic
    static void solveTSP(int[][] costMatrix, int startCity) {
        int n = costMatrix.length;
        boolean[] visited = new boolean[n];
        int[] path = new int[n + 1];
        int totalCost = 0;

        int current = startCity;
        visited[current] = true;
        path[0] = current;

        slowPrint(" Starting from city: " + current, 30);

        for (int i = 1; i < n; i++) {
            int nearest = -1;
            int minDistance = Integer.MAX_VALUE;

            slowPrint("\n Searching nearest unvisited city from city " + current, 30);
            for (int j = 0; j < n; j++) {
                if (!visited[j] && costMatrix[current][j] < minDistance) {
                    nearest = j;
                    minDistance = costMatrix[current][j];
                    slowPrint("  -> Closer city found: " + j + " with cost " + minDistance, 30);
                }
            }

            if (nearest != -1) {
                visited[nearest] = true;
                totalCost += minDistance;
                current = nearest;
                path[i] = current;
                slowPrint(" Moving to city " + current + ", accumulated cost = " + totalCost, 30);
            }
        }

        totalCost += costMatrix[current][startCity];
        path[n] = startCity;
        slowPrint("\n Returning to starting city " + startCity + ", return cost = " + costMatrix[current][startCity], 30);
        slowPrint(" Final Total Cost = " + totalCost, 30);

        // Print final path
        slowPrint("\n TSP Path (Nearest Neighbor):", 40);
        for (int i = 0; i <= n; i++) {
            System.out.print(path[i] + (i < n ? " -> " : ""));
        }
        System.out.println();

        slowPrint(" Total Path Cost: " + totalCost, 40);
    }

    // Main method to take user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int delay = 50;

        slowPrint(" Traveling Salesman Problem using Nearest Neighbor Heuristic", delay);
        slowPrint("Enter number of cities:", delay);
        int n = sc.nextInt();

        int[][] costMatrix = new int[n][n];
        slowPrint("Enter the cost adjacency matrix (use 9999 for no direct path):", delay);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                costMatrix[i][j] = sc.nextInt();

        slowPrint("Enter starting city index (0 to " + (n - 1) + "):", delay);
        int startCity = sc.nextInt();

        slowPrint("\n Solving TSP using Nearest Neighbor...\n", delay);
        solveTSP(costMatrix, startCity);
    }
}
