package Random_Programs;
import java.util.*;
public class Random_Number {
	public static void main(String[] args) {
		//creating an object of Random class
		Random random = new Random();
		//Generates random integers 0 to 49
		int x = random.nextInt(50);
		//Generates random integers 0 to 999
		int y = random.nextInt(1000);
		//prints random integer values
		
		System.out.println("Randomly Generated Integers Values : ");
		System.out.println(x);
		System.out.println(y);
		
		//Generates Random doubles values
		double a = random.nextDouble();
		double b = random.nextDouble();
		
		//prints random double values
		System.out.println("Randomly Generated Double values : ");
		System.out.println(a);
		System.out.println(b);
		
		//Generate Random float values
		float f = random.nextFloat();
		float i = random.nextFloat();
		
		//Prints random float values
		System.out.println("Randomly Generated Float Values : ");
		System.out.println(f);
		System.out.println(i);
		
		//Generates Random Long values
		long p = random.nextLong();
		long q = random.nextLong();
		
		//prints random long values
		System.out.println(p);
		System.out.println(q);
		
		//Generates Random boolean values
		boolean m = random.nextBoolean();
		boolean n = random.nextBoolean();
		
		//Prints random boolean values
		System.out.println("Randomly generated boolean values : ");
		System.out.println(m);
		System.out.println(n);
	}
}