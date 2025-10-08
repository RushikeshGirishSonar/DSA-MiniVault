package Graph_Algorithms;

import java.util.Scanner;

public class Prims_Algorithm {

    // ChatGPT-style slow print
    static void printSlow(String message, int delay) {
        for (String word : message.split(" ")) {
            System.out.print(word + " ");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Find vertex with minimum key value not included in MST
    static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Print constructed MST
    static void printMST(int[] parent, int[][] graph, int V) {
        printSlow("\n=== Minimum Cost Spanning Tree using Prim's Algorithm ===", 30);
        int totalCost = 0;
        for (int i = 1; i < V; i++) {
            printSlow("Edge: " + parent[i] + " - " + i + " | Weight: " + graph[i][parent[i]], 25);
            totalCost += graph[i][parent[i]];
        }
        printSlow("Total Cost of MST: " + totalCost, 30);
    }

    // Prim's algorithm
    static void primMST(int[][] graph, int V) {
        int[] parent = new int[V]; // Stores MST
        int[] key = new int[V];    // Key values
        boolean[] mstSet = new boolean[V]; // Included in MST

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        printSlow("\n=== Dry Run of Prim's Algorithm ===\n", 30);

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;

            printSlow("Pick vertex " + u + " with min key = " + key[u], 25);

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                    printSlow("  -> Update key[" + v + "] = " + graph[u][v] + " and parent[" + v + "] = " + u, 25);
                }
            }

            printSlow("--------------------------------------------", 20);
        }

        printMST(parent, graph, V);
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printSlow("Enter number of vertices: ", 25);
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        printSlow("Enter adjacency matrix (0 if no edge):", 25);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        long startTime = System.nanoTime();
        primMST(graph, V);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000;
        printSlow("\nTime taken: " + duration + " microseconds", 30);
    }
}
