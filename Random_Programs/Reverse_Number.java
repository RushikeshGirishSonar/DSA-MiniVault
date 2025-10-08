package Random_Programs;

import java.util.Scanner;

public class Reverse_Number {
    // Method for printing string with a delay
    public static void printStringWithDelay(String str, int delayMillis) {
        try {
            for (char c : str.toCharArray()) {
                System.out.print(c); // Print each character
                Thread.sleep(delayMillis); // Pause for specified milliseconds
            }
            System.out.println(); // Move to the next line after the string is printed
        } catch (InterruptedException e) {
            System.out.println("Error: Interrupted exception occurred.");
            e.printStackTrace();
        }
    }

    // Method for reversing a number and returning the result as a String
    public static String reverseNumber(int number) {
        StringBuilder reversed = new StringBuilder();
        try {
			System.out.print("\n[NEXUS]:");
            printStringWithDelay(" Initiating reverseNumber function. Current input: " + number, 80);

            if (number < 10) {
                reversed.append(number);
                System.out.print("[NEXUS]:");
            	printStringWithDelay(" Single-digit identified. Outputting digit.", 80);
            } else {
                // % operator extracts the last digit of the number
                int lastDigit = number % 10;
                // printStringWithDelay("" + lastDigit, 80);
                reversed.append(lastDigit);
                System.out.print("[NEXUS]:");
            	printStringWithDelay(" Extracted last digit using '%' operator. Calculation: " + number + " % 10 = " + lastDigit, 80);

                // / operator removes the last digit from the number
                int remainingNumber = number / 10;
                System.out.print("[NEXUS]:");
            	printStringWithDelay(" Remaining number calculated using '/' operator. Calculation: " + number + " / 10 = " + remainingNumber, 80);

                System.out.print("[NEXUS]:");
            	printStringWithDelay(" Recursively calling reverseNumber with remaining number: " + remainingNumber, 80);
                reversed.append(reverseNumber(remainingNumber)); // Recursively accumulate result
            }
        } catch (Exception e) {
            System.out.print("[NEXUS]:");
            System.out.println(" Error during sleep operation.");
            e.printStackTrace();
        }
        return reversed.toString(); // Return the accumulated reversed number as a String
    }

    public static void main(String[] args) {
        try {
            System.out.print("\n[NEXUS]:");
            printStringWithDelay(" System initialized. Awaiting user input.", 80);
            System.out.print("[NEXUS]:");
            printStringWithDelay(" Please input the number to reverse: ", 80);
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            System.out.print("\n[NEXUS]:");
            printStringWithDelay(" Input received. Commencing number reversal operation.", 80);
            String reversedNumber = reverseNumber(num);
            System.out.print("\n[NEXUS]:");
            printStringWithDelay(" Reversed number is: " + reversedNumber, 80);
            System.out.print("\n[NEXUS]:");
            printStringWithDelay(" Operation complete. Results displayed.", 80);
        } catch (Exception e) {
            System.out.print("\n[NEXUS]:");
            System.out.println(" Error during sleep operation.");
            e.printStackTrace();
        }
    }
}
