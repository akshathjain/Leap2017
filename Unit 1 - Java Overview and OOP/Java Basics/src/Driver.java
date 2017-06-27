/*
Name: Akshath Jain
Date: 6/27/17
Purpose: Basic Java Examples
*/

import java.util.Scanner;

public class Driver{
	public static void main(String[] args){
		/*Other o = new Other();
		System.out.println(o.getOtherString());

		System.out.println(Other.getPi());*/

		/*String donkey = new String("Eddie Murphy");
		String actor = new String("Eddie Murphy");
		actor += " voiced me";

		System.out.println(actor);

		if(5 == 5){
			System.out.println("These match!");
		}
		else{
			System.out.println("don't match");
		}*/

		Scanner scanner = new  Scanner(System.in);

		System.out.print("enter your name: ");
		String userName = scanner.next();
		System.out.print("This is your name: " + userName);
		//scanner.close();

	}
}