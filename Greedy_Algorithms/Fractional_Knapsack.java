package Greedy_Algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Fractional_Knapsack {

    // ChatGPT-style output: slow printing
    static void printSlow(String text, int delay) {
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

    // Item class to store value, weight, and value/weight ratio
    static class Item {
        int value, weight;
        double ratio;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }

    // Fractional Knapsack implementation
    static double fractionalKnapsack(int W, Item[] items) {
        Arrays.sort(items, Comparator.comparingDouble(i -> -i.ratio));

        printSlow("\n=== Items Sorted by Value/Weight Ratio ===", 30);
        for (int i = 0; i < items.length; i++) {
            printSlow("Item " + i + ": Value = " + items[i].value + ", Weight = " + items[i].weight + ", Ratio = " + String.format("%.2f", items[i].ratio), 20);
        }

        double totalValue = 0.0;
        int currentWeight = 0;

        printSlow("\n=== Greedy Selection Begins ===", 30);
        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                printSlow("Taking full item -> Value: " + item.value + ", Weight: " + item.weight, 25);
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remain = W - currentWeight;
                double fractionValue = item.ratio * remain;
                printSlow("Taking fraction of item -> Remaining Weight: " + remain + ", Fraction Value: " + String.format("%.2f", fractionValue), 25);
                totalValue += fractionValue;
                break; // Knapsack full
            }
            printSlow("=> Current total weight: " + currentWeight + ", Total value: " + String.format("%.2f", totalValue), 25);
            System.out.println("------------------------------------");
        }

        return totalValue;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printSlow("Enter the number of items: ", 30);
        int n = sc.nextInt();

        Item[] items = new Item[n];

        printSlow("Enter the values and weights of the items:", 30);
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " - Value: ");
            int value = sc.nextInt();
            System.out.print("Item " + (i + 1) + " - Weight: ");
            int weight = sc.nextInt();
            items[i] = new Item(value, weight);
        }

        printSlow("Enter the capacity of the knapsack: ", 30);
        int W = sc.nextInt();

        long startTime = System.nanoTime();
        double maxValue = fractionalKnapsack(W, items);
        long endTime = System.nanoTime();

        printSlow("\nMaximum value in knapsack = " + String.format("%.2f", maxValue), 30);

        long duration = (endTime - startTime) / 1000;
        printSlow("Time taken: " + duration + " microseconds", 30);
    }
}
