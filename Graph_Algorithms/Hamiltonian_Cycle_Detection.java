package Graph_Algorithms;

import java.util.*;

public class Hamiltonian_Cycle_Detection {

    static int V; // Number of vertices
    static int[][] graph; // Adjacency matrix

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

    // Utility to print the solution path
    static void printSolution(int[] path, int delay) {
        System.out.println("\n");
        slowPrint("-> Hamiltonian Cycle found:", delay);
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println(path[0]); // To show the cycle
    }

    // Check if vertex v can be added at index 'pos'
    static boolean isSafe(int v, int[] path, int pos, int delay) {
        if (graph[path[pos - 1]][v] == 0) {
            slowPrint("X Vertex " + v + " is not adjacent to " + path[pos - 1], delay);
            return false;
        }

        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                slowPrint("X Vertex " + v + " already included in path", delay);
                return false;
            }
        }

        slowPrint("Y Vertex " + v + " is safe to add at position " + pos, delay);
        return true;
    }

    // Recursive utility to solve Hamiltonian Cycle
    static boolean hamCycleUtil(int[] path, int pos, int delay) {
        if (pos == V) {
            System.out.println("\n");
            if (graph[path[pos - 1]][path[0]] == 1) {
                slowPrint("Y Last vertex " + path[pos - 1] + " connects back to start " + path[0], delay);
                return true;
            } else {
                slowPrint("X No edge from last vertex " + path[pos - 1] + " to start " + path[0], delay);
                return false;
            }
        }

        for (int v = 1; v < V; v++) {
            System.out.println("\n");
            slowPrint("-> Try vertex " + v + " at position " + pos, delay);
            if (isSafe(v, path, pos, delay)) {
                path[pos] = v;

                if (hamCycleUtil(path, pos + 1, delay)) {
                    return true;
                }

                // Backtrack
                slowPrint("<- Backtrack from vertex " + v + " at position " + pos, delay);
                path[pos] = -1;
            }
        }

        return false;
    }

    // Main function to solve Hamiltonian Cycle problem
    static boolean hamCycle(int delay) {
        int[] path = new int[V];
        Arrays.fill(path, -1);

        path[0] = 0; // Start at vertex 0

        if (!hamCycleUtil(path, 1, delay)) {
            System.out.println("\n");
            slowPrint("X No Hamiltonian Cycle exists", delay);
            return false;
        }

        printSolution(path, delay);
        return true;
    }

    // Driver Code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        slowPrint("Enter number of vertices: ", 20);
        V = sc.nextInt();
        graph = new int[V][V];

        slowPrint("Enter adjacency matrix (" + V + " x " + V + "):", 20);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Start timing
        long startTime = System.nanoTime();

        int delay = 30; // ms delay per character
        hamCycle(delay);

        // End timing
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000;

        slowPrint("\nTime taken: " + duration + " microseconds", delay);

        slowPrint("\n=== Time Complexity ===", delay);
        slowPrint("-> Time Complexity: O(N!) due to backtracking for each vertex permutation", delay);
    }
}
