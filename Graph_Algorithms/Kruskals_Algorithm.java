package Graph_Algorithms;

import java.util.*;

public class Kruskals_Algorithm {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    // Subset for union-find
    static class Subset {
        int parent, rank;
    }

    // ChatGPT-style word-by-word print
    static void printSlow(String message, int delay) {
        for (String word : message.split(" ")) {
            System.out.print(word + " ");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Find root of a set
    static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // Union of two sets
    static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Kruskal’s Algorithm
    static void kruskalMST(int V, List<Edge> edges) {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges); // Step 1: Sort edges

        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        printSlow("\n=== Kruskal's Algorithm Dry Run ===\n", 25);
        int e = 0, i = 0;

        while (e < V - 1 && i < edges.size()) {
            Edge next = edges.get(i++);
            printSlow("Consider edge: " + next.src + " - " + next.dest + " | weight = " + next.weight, 20);
            System.out.println("\n");
            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);

            if (x != y) {
                printSlow("No cycle formed -> include this edge", 20);
                result.add(next);
                union(subsets, x, y);
                e++;
            } else {
                printSlow("Cycle detected -> skip this edge", 20);
            }

            printSlow("----------------------------------------", 10);
        }

        int totalCost = 0;
        System.out.println("\n");
        printSlow("\n=== Minimum Cost Spanning Tree ===", 25);
        for (Edge edge : result) {
            printSlow("Edge: " + edge.src + " - " + edge.dest + " | Weight: " + edge.weight, 20);
            totalCost += edge.weight;
        }
        System.out.println("\n");
        printSlow("Total Cost of MST: " + totalCost, 25);
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printSlow("Enter number of vertices: ", 25);
        int V = sc.nextInt();

        printSlow("Enter number of edges: ", 25);
        int E = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        printSlow("Enter each edge in format: src dest weight", 20);
        for (int i = 0; i < E; i++) {
            Edge edge = new Edge();
            edge.src = sc.nextInt();
            edge.dest = sc.nextInt();
            edge.weight = sc.nextInt();
            edges.add(edge);
        }

        long startTime = System.nanoTime();
        kruskalMST(V, edges);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000;
        System.out.println("\n");
        printSlow("\nTime taken: " + duration + " microseconds", 30);
    }
}
