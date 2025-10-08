
package Random_Programs;

import java.util.Scanner;

public class Armstrong_Number {

    // Method to print each character with a delay
    private static void printStringWithDelay(String str, int delayMillis) {
        try {
			System.out.print("[IndraMind] : ");
            for (char c : str.toCharArray()) {
                System.out.print(c);
                Thread.sleep(delayMillis);
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("Error during delay.");
        }
    }

    // Function to check if the number is Armstrong
    private static boolean isArmstrong(int n) {
        int temp = n, digits = 0, last = 0, sum = 0;
        printStringWithDelay("Calculating Armstrong for " + n, 90);

        // Count digits
        while (temp > 0) {
            printStringWithDelay("temp(" + temp + ") > 0 is true", 90);
            temp /= 10;
            printStringWithDelay("temp /= 10 -> temp is now " + temp, 90);
            digits++;
            printStringWithDelay("digits++ -> digits is now " + digits, 90);
			System.out.println("\n");
        }
        printStringWithDelay("Number of digits: " + digits, 90);
		// System.out.println("\n");

        temp = n;
        while (temp > 0) {
            printStringWithDelay("temp(" + temp + ") > 0 is true", 90);
            last = temp % 10;
            printStringWithDelay("last = temp % 10 -> last is now " + last, 90);
            sum += Math.pow(last, digits);
            printStringWithDelay("sum += Math.pow(last, digits) -> sum is now " + sum, 90);
            temp /= 10;
            printStringWithDelay("temp /= 10 -> temp is now " + temp, 90);
			System.out.println("\n");
        }
		// System.out.println("\n");

        boolean result = n == sum;
        printStringWithDelay("n == sum -> " + n + " == " + sum + " is " + result, 90);
        printStringWithDelay("Final sum: " + sum + ", Armstrong result: " + result, 90);
		System.out.println("\n");
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		System.out.println("\n");
		printStringWithDelay("System initialized. Awaiting user input.", 80);
        printStringWithDelay("Please input the number : ", 90);
        int num = sc.nextInt();
        printStringWithDelay("You entered: " + num, 90);
        String result = isArmstrong(num) ? "Entered Number is Armstrong" : "Entered Number is Not Armstrong";
		printStringWithDelay("Operation complete. Results displayed.", 80);
        printStringWithDelay(result, 90);
    }
}
