package Graph_Algorithms;

import java.util.*;

public class Deapth_First_Search {

    // Slow print like ChatGPT style
    static void slowPrint(String text, int delay) {
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

    // Perform DFS using recursion
    static void dfs(int node, boolean[] visited, List<List<Integer>> adjList, int delay) {
        visited[node] = true;
        slowPrint("-> Visiting node: " + node, delay);

        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                slowPrint("  -> Exploring neighbor: " + neighbor, delay);
                dfs(neighbor, visited, adjList, delay);
            } else {
                slowPrint("  X Neighbor " + neighbor + " already visited", delay);
            }
        }
    }

    // Main method to run DFS
    static void runDFS(int vertices, List<List<Integer>> adjList, int delay) {
        boolean[] visited = new boolean[vertices];

        slowPrint("\n=== DFS Traversal ===\n", delay);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                slowPrint("Starting DFS from vertex: " + i, delay);
                dfs(i, visited, adjList, delay);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        slowPrint("Enter number of vertices: ", 20);
        int vertices = sc.nextInt();

        slowPrint("Enter number of edges: ", 20);
        int edges = sc.nextInt();

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        slowPrint("Enter edges (from to):", 20);
        for (int i = 0; i < edges; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjList.get(from).add(to);
            adjList.get(to).add(from); // For undirected graph
        }

        // Start timing
        long startTime = System.nanoTime();

        int delay = 30; // Delay for animation
        runDFS(vertices, adjList, delay);

        // End timing
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000;

        slowPrint("\nTime taken: " + duration + " microseconds", delay);

        // Time Complexity info
        slowPrint("\n=== Time Complexity Analysis ===", delay);
        slowPrint("For V vertices and E edges:", delay);
        slowPrint("-> Time Complexity = O(V + E)", delay);
    }
}
