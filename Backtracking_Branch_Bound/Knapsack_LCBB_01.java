package Backtracking_Branch_Bound;

import java.util.*;

public class Knapsack_LCBB_01 {

    // Simulated slow print for explanation output
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

    // Item class: holds weight, value, and ratio
    static class Item {
        int weight, value;
        double ratio;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    // Node class for each branch in the tree
    static class Node {
        int level, profit, weight;
        double bound;
        List<Integer> itemsIncluded = new ArrayList<>();

        Node(int level, int profit, int weight) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
        }
    }

    // Bound function gives optimistic max profit from a node
    static double bound(Node u, int capacity, Item[] items, int n) {
        if (u.weight >= capacity) return 0;

        double profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        while (j < n && totalWeight + items[j].weight <= capacity) {
            totalWeight += items[j].weight;
            profitBound += items[j].value;
            j++;
        }

        if (j < n) {
            profitBound += (capacity - totalWeight) * items[j].ratio;
        }

        return profitBound;
    }

    // LCBB Knapsack solver
    static int knapsackLCBB(int capacity, Item[] items, int delay) {
        int n = items.length;

        System.out.println("\n");
        slowPrint("->Sorting items by value-to-weight ratio (descending)...", delay);
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        System.out.println("\n");
        slowPrint("->Initializing priority queue (max-heap based on bound)...", delay);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.bound, a.bound));

        Node u, v;
        v = new Node(-1, 0, 0);
        v.bound = bound(v, capacity, items, n);
        pq.offer(v);

        int maxProfit = 0;
        List<Integer> bestCombination = new ArrayList<>();

        System.out.println("\n");
        slowPrint("->Starting Branch and Bound search...", delay);

        while (!pq.isEmpty()) {
            v = pq.poll();
            if (v.bound > maxProfit) {
                int nextLevel = v.level + 1;

                if (nextLevel < n) {
                    // Include item[nextLevel]
                    u = new Node(nextLevel,
                            v.profit + items[nextLevel].value,
                            v.weight + items[nextLevel].weight);
                    u.itemsIncluded = new ArrayList<>(v.itemsIncluded);
                    u.itemsIncluded.add(nextLevel);

                    if (u.weight <= capacity && u.profit > maxProfit) {
                        maxProfit = u.profit;
                        bestCombination = new ArrayList<>(u.itemsIncluded);
                        System.out.println("\n");
                        slowPrint(" New best profit found: " + maxProfit + " by including item " + nextLevel, delay);
                    }

                    u.bound = bound(u, capacity, items, n);
                    if (u.bound > maxProfit) {
                        pq.offer(u);
                        slowPrint("->Enqueued node including item " + nextLevel + " with bound = " + u.bound, delay);
                    }

                    // Exclude item[nextLevel]
                    u = new Node(nextLevel, v.profit, v.weight);
                    u.itemsIncluded = new ArrayList<>(v.itemsIncluded);
                    u.bound = bound(u, capacity, items, n);

                    if (u.bound > maxProfit) {
                        pq.offer(u);
                        slowPrint("->Enqueued node excluding item " + nextLevel + " with bound = " + u.bound, delay);
                    }
                }
            }
        }

        // Show selected items
        slowPrint("\nItems selected (0-based index):", delay);
        for (int idx : bestCombination) {
            slowPrint("Item " + idx + " (weight=" + items[idx].weight + ", value=" + items[idx].value + ")", delay);
        }

        return maxProfit;
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int delay = 30;

        slowPrint("Enter number of items:", delay);
        int n = sc.nextInt();

        Item[] items = new Item[n];
        slowPrint("Enter weights and values of the items:", delay);
        for (int i = 0; i < n; i++) {
            int w = sc.nextInt();
            int v = sc.nextInt();
            items[i] = new Item(w, v);
        }

        slowPrint("Enter capacity of the knapsack:", delay);
        int capacity = sc.nextInt();

        long start = System.nanoTime();
        int maxProfit = knapsackLCBB(capacity, items, delay);
        long end = System.nanoTime();

        System.out.println("\n");
        slowPrint("\nMaximum Profit using Branch and Bound: " + maxProfit, delay);
        System.out.println("\n");
        slowPrint("Execution Time: " + ((end - start) / 1000) + " microseconds", delay);
    }
}
