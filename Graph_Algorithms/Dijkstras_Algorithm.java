package Graph_Algorithms;

import java.util.*;

public class Dijkstras_Algorithm {

    static final int INF = Integer.MAX_VALUE;

    // Utility method to simulate ChatGPT output
    static void printSlow(String message, int delay) {
        for (String word : message.split(" ")) {
            System.out.print(word + " ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Find vertex with minimum distance value from the set of vertices not yet processed
    static int minDistance(int[] dist, boolean[] visited, int V) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Dijkstra's Algorithm
    static void dijkstra(int[][] graph, int src, int V) {
        int[] dist = new int[V];        // Output array
        boolean[] visited = new boolean[V]; // Visited vertices

        // Initialize distances
        Arrays.fill(dist, INF);
        dist[src] = 0;

        printSlow("\n=== Dijkstra's Algorithm Dry Run ===\n", 25);

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited, V);
            visited[u] = true;

            printSlow("Select vertex " + u + " with minimum tentative distance: " + dist[u], 20);

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF
                        && dist[u] + graph[u][v] < dist[v]) {
                    printSlow("-> Update distance of vertex " + v + ": " + dist[v] + " -> " + (dist[u] + graph[u][v]), 20);
                    dist[v] = dist[u] + graph[u][v];
                }
            }

            printSlow("----------------------------------------", 10);
        }

        printSlow("\n=== Final Shortest Distances from Source Vertex " + src + " ===", 25);
        for (int i = 0; i < V; i++) {
            printSlow("Vertex " + i + ": " + dist[i], 20);
        }
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printSlow("Enter number of vertices: ", 25);
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        printSlow("Enter adjacency matrix (use 0 if no direct edge):", 25);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        printSlow("Enter source vertex: ", 25);
        int src = sc.nextInt();

        long startTime = System.nanoTime();
        dijkstra(graph, src, V);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000;
        printSlow("\nTime taken: " + duration + " microseconds", 30);
    }
}
