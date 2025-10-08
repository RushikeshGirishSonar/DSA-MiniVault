package Graph_Algorithms;

import java.util.*;

public class TopologicalSort_DAG {

    // Utility method to print like ChatGPT (slow printing)
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

    // DFS Utility for Topological Sort
    static void dfs(int current, boolean[] visited, List<List<Integer>> adjList, Stack<Integer> stack, int delay) {
        slowPrint("-> Visiting vertex " + current, delay);
        visited[current] = true;

        for (int neighbor : adjList.get(current)) {
            if (!visited[neighbor]) {
                slowPrint("  Exploring neighbor: " + neighbor, delay);
                dfs(neighbor, visited, adjList, stack, delay);
            } else {
                slowPrint("  Neighbor " + neighbor + " already visited", delay);
            }
        }

        stack.push(current);
        slowPrint("<- Push " + current + " to stack (All neighbors visited)", delay);
    }

    // Topological Sort using DFS
    static void topologicalSort(int vertices, List<List<Integer>> adjList, int delay) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        slowPrint("\n=== Topological Sort Dry Run ===\n", delay);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                System.out.println("\n");
                slowPrint("Start DFS from vertex " + i, delay);
                dfs(i, visited, adjList, stack, delay);
            }
        }

        slowPrint("\n=== Topological Order ===", delay);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
            try {
                Thread.sleep(delay * 5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Main method
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

        slowPrint("Enter edges in the format: from to", 20);
        for (int i = 0; i < edges; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjList.get(from).add(to);
        }

        // Timing start
        long startTime = System.nanoTime();

        // Run Topological Sort with animated print
        int delay = 30; // milliseconds per character
        topologicalSort(vertices, adjList, delay);

        // Timing end
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000;

        slowPrint("Time taken: " + duration + " microseconds", delay);
    }
}
