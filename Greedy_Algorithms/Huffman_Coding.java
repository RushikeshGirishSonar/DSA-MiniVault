package Greedy_Algorithms;

import java.util.PriorityQueue;
import java.util.Scanner;

// Node class for Huffman Tree
class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

public class Huffman_Coding {

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

    // Print Huffman codes from the root of the tree
    static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) return;

        // It's a leaf node
        if (root.left == null && root.right == null) {
            printSlow("Character: " + root.ch + " -> Code: " + code, 30);
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    // Main method to build the Huffman Tree
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printSlow("Enter number of characters: ", 30);
        int n = sc.nextInt();
        char[] chars = new char[n];
        int[] freqs = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter character " + (i + 1) + ": ");
            chars[i] = sc.next().charAt(0);
            System.out.print("Enter frequency of '" + chars[i] + "': ");
            freqs[i] = sc.nextInt();
        }

        // Start timing
        long startTime = System.nanoTime();

        // PriorityQueue as min-heap for Huffman tree creation
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(n, (a, b) -> a.freq - b.freq);

        // Create leaf nodes for all characters
        for (int i = 0; i < n; i++) {
            pq.add(new HuffmanNode(chars[i], freqs[i]));
        }

        printSlow("\n=== Building Huffman Tree Using Greedy Method ===", 30);

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            printSlow("Picked nodes: (" + left.ch + ", " + left.freq + ") & (" + right.ch + ", " + right.freq + ")", 25);

            HuffmanNode newNode = new HuffmanNode('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;

            printSlow("-> New node freq: " + newNode.freq + " (combined)", 25);
            pq.add(newNode);
        }

        // The final node is the root of the Huffman Tree
        HuffmanNode root = pq.poll();

        printSlow("\n=== Generated Huffman Codes ===", 30);
        printHuffmanCodes(root, "");

        // End timing
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000;
        printSlow("\nTime taken: " + duration + " microseconds", 30);

        printSlow("\n=== Complexity Analysis ===", 30);
        printSlow("Best Case: O(n log n) => Inserting and combining nodes with PriorityQueue", 25);
        printSlow("Worst Case: O(n log n) => Still based on heap operations for each of n characters", 25);
    }
}
