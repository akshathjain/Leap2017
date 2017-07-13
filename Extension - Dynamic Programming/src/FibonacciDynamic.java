/*
Name: Akshath Jain
Date: 7/13/17
Purpose: fibonacci with dynamic programming
*/

import java.util.Scanner;

public class FibonacciDynamic{
	public static long storedValues[];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Num Elements: ");
		int elements = scanner.nextInt();
		
		//init the storage
		storedValues = new long[elements];
		storedValues[0] = 1;
		storedValues[1] = 1;

		System.out.println(fibonacci(elements - 1));
	}	

	public static long fibonacci(int num){
		if(storedValues[num] != 0)
			return storedValues[num];
		else
			return (storedValues[num] = fibonacci(num - 1) + fibonacci(num - 2));
	}
}