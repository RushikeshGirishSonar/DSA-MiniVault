package Random_Programs;
import java.util.*;
public class palindrome_Number extends Thread{
	public static void main(String[] args) {
		try{
		System.out.println("\nInitialize variable 'original' and 'reverse' as String.");
		Thread.sleep(750);
		System.out.println("Initialize variable 'length' and 'i' as Integer.");
		Thread.sleep(1000);
		String original, reverse = "";
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter a string/number to check if it is a palindrome : ");
		original = sc.nextLine();
		Thread.sleep(750);
		System.out.println("\n The entered string or number is now going to check it is plaindrome or not \n\n Start => ");
		int length = original.length();
		Thread.sleep(1000);
		//System.out.println("\nReverse the input by iterating from the end to the beginning, appending each character to 'reverse'.");
		System.out.println("Reverse the input by iterating from the end to the beginning,\nappending each character to 'reverse'.");
		Thread.sleep(2000);
		for(int i = length-1; i>=0; i--) {
			System.out.println("\nreverse("+reverse+") = reverse("+reverse+") + character("+original.charAt(i)+")");
			reverse = reverse + original.charAt(i);
			Thread.sleep(750);
		}

		System.out.println("\nSo if the original and reversed strings are the same, it is a palindrome.\n");
		Thread.sleep(1000);
		if(original.equals(reverse)) {
			
			System.out.println("Entered string/number "+original+" == "+reverse+" \nhence it's a palindrome.\n");
		}
		else {
			System.out.println("Entered string/number "+original+" != "+reverse+" \nhence isn't a palindrome.\n");
		}
	}catch(Exception e){
		System.out.println(e);
	}
	}
}
// 

// Enter a string/number to check if it is a palindrome: dad.

// 

// If the original and reversed strings are the same, it is a palindrome.