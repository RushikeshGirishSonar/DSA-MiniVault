package Graph_Algorithms;

import java.util.*;

public class Breadth_First_Search {

    // Function to simulate ChatGPT-like text printing
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

    // BFS traversal from a given start node
    static void bfs(int start, boolean[] visited, List<List<Integer>> adjList, int delay) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        slowPrint("-> Start BFS from node " + start, delay);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            slowPrint("-> Visiting node: " + current, delay);

            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    slowPrint("  -> Queueing neighbor: " + neighbor, delay);
                } else {
                    slowPrint("  X Neighbor " + neighbor + " already visited", delay);
                }
            }
        }
    }

    // Perform BFS for all components
    static void runBFS(int vertices, List<List<Integer>> adjList, int delay) {
        boolean[] visited = new boolean[vertices];

        slowPrint("\n=== BFS Traversal ===\n", delay);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                slowPrint("Starting BFS component from vertex: " + i, delay);
                bfs(i, visited, adjList, delay);
            }
        }
    }

    // Driver function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        slowPrint("Enter number of vertices: ", 25);
        int vertices = sc.nextInt();

        slowPrint("Enter number of edges: ", 25);
        int edges = sc.nextInt();

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        slowPrint("Enter edges (from to):", 25);
        for (int i = 0; i < edges; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjList.get(from).add(to);
            adjList.get(to).add(from); // Since it's an undirected graph
        }

        // Time Measurement
        long startTime = System.nanoTime();

        int delay = 30; // Delay in milliseconds for animated effect
        runBFS(vertices, adjList, delay);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000;

        slowPrint("\nTime taken: " + duration + " microseconds", delay);

        // Time Complexity Explanation
        slowPrint("\n=== Time Complexity Analysis ===", delay);
        slowPrint("For V vertices and E edges:", delay);
        slowPrint("-> Time Complexity = O(V + E)", delay);
    }
}
