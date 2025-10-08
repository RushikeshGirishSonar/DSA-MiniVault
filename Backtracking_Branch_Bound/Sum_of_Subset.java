package Backtracking_Branch_Bound;

import java.util.*;

public class Sum_of_Subset {

    // Slow print function to simulate gradual output
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

    // Recursive function to find all subsets with a given sum
    static void findSubsets(int[] set, int index, List<Integer> current, int currentSum, int targetSum, List<List<Integer>> result) {
        if (currentSum == targetSum) {
            result.add(new ArrayList<>(current));
            slowPrint("\n Subset found: " + current+"\n", 100);
            return;
        }

        // If no more elements or sum exceeded
        if (index >= set.length || currentSum > targetSum) {
            return;
        }

        // INCLUDE current element
        current.add(set[index]);
        slowPrint("+ Include: " + set[index] + " → Subset: " + current + " → Sum: " + (currentSum + set[index]), 100);
        findSubsets(set, index + 1, current, currentSum + set[index], targetSum, result);

        // BACKTRACK - EXCLUDE the last element
        slowPrint("<- Backtrack: Remove " + current.get(current.size() - 1), 100);
        current.remove(current.size() - 1);
        findSubsets(set, index + 1, current, currentSum, targetSum, result);
    }

    // Main method to drive the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        slowPrint(" Enter the number of elements in the set:", 100);
        int n = sc.nextInt();
        int[] set = new int[n];

        slowPrint(" Enter the elements of the set:", 100);
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextInt();
        }

        slowPrint(" Enter the target sum:", 100);
        int targetSum = sc.nextInt();

        List<List<Integer>> result = new ArrayList<>();
        slowPrint("\n Starting backtracking to find subsets...\n", 100);
        findSubsets(set, 0, new ArrayList<>(), 0, targetSum, result);

        if (result.isEmpty()) {
            slowPrint("\n X No subset found with the given sum.", 100);
        } else {
            slowPrint("\n Total subsets found: " + result.size(), 100);
        }
    }
}
