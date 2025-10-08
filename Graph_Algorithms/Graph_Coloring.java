package Graph_Algorithms;

import java.util.*;

public class Graph_Coloring {

    static int V; // Number of vertices
    static int[][] graph; // Adjacency matrix
    static int m; // Number of colors

    // To simulate ChatGPT-style typing delay
    static void slowPrint(String message, int delay) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Utility to print the color assignment
    static void printSolution(int[] color, int delay) {
        slowPrint("\n-> Solution found:", delay);
        for (int i = 0; i < V; i++) {
            slowPrint("Vertex " + i + " ---> Color " + color[i], delay);
        }
    }

    // Check if the current color can be assigned to vertex v
    static boolean isSafe(int v, int c, int[] color, int delay) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c) {
                slowPrint("X Color " + c + " is not safe for vertex " + v + " (adjacent to vertex " + i + ")", delay);
                return false;
            }
        }
        slowPrint("Y Color " + c + " is safe for vertex " + v, delay);
        return true;
    }

    // Recursive utility to solve m-coloring problem
    static boolean graphColoringUtil(int[] color, int v, int delay) {
        if (v == V) {
            return true; // All vertices are assigned a color
        }

        for (int c = 1; c <= m; c++) {
            slowPrint("\n-> Try color " + c + " for vertex " + v, delay);

            if (isSafe(v, c, color, delay)) {
                color[v] = c;

                if (graphColoringUtil(color, v + 1, delay)) {
                    return true;
                }

                // Backtrack
                slowPrint("<- Backtrack from color " + c + " at vertex " + v, delay);
                color[v] = 0;
            }
        }

        return false; // No color can be assigned to this vertex
    }

    // Main function to solve the coloring problem
    static boolean graphColoring(int delay) {
        int[] color = new int[V]; // Color assignment array, initialized to 0

        if (!graphColoringUtil(color, 0, delay)) {
            slowPrint("\nX No solution exists using " + m + " colors", delay);
            return false;
        }

        printSolution(color, delay);
        return true;
    }

    // Driver Code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        slowPrint("Enter number of vertices: ", 20);
        V = sc.nextInt();
        graph = new int[V][V];

        slowPrint("Enter number of colors (m): ", 20);
        m = sc.nextInt();

        slowPrint("Enter adjacency matrix (" + V + " x " + V + "):", 20);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        long startTime = System.nanoTime();

        int delay = 30;
        graphColoring(delay);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000;

        slowPrint("\nTime taken: " + duration + " microseconds", delay);

        slowPrint("\n=== Time Complexity ===", delay);
        slowPrint("-> Time Complexity: O(m^V) as we try m colors for each of V vertices", delay);
    }
}
