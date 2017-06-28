/*
Name: Akshath Jain
Date: 6/27/17
Purpose: Fibonacci series demo using recursion and iteration
*/

import java.util.Scanner;

public class Fibonacci{
	public static void main(String[] args) {
		
	}
}












































/*public class Fibonacci{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		while(true){
			System.out.print("Enter series number: ");
			int num = scanner.nextInt();
			System.out.println(recursiveFibonacci(num));	
			System.out.println(iterativeFibonacci(num));	
		}
	}

	//1,1,2,3,5,8
	public static int iterativeFibonacci(int index){
		int current = 1, previous = 1;
		for(int i = 0; i < index - 1; i++){
			int prevTemp = previous;
			previous = current;
			current += prevTemp;
		}
		return current;
	}

	public static int recursiveFibonacci(int index){
		if(index == 0 || index == 1)
			return 1;
		else
			return recursiveFibonacci(index - 1) + recursiveFibonacci(index - 2);
	}
}*/