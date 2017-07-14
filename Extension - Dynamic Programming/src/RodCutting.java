/*
Name: Akshath Jain
Date: 7/13/17
Purpose: Rod cutting solution
*/

import java.util.Scanner;
import java.util.Arrays;

public class RodCutting{

	private static int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8};
	private static int[] optimalPrice;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Rod Length: ");
		int rodLength = scanner.nextInt();
		optimalPrice = new int[rodLength + 1];
		Arrays.fill(optimalPrice, -1);
		optimalPrice[0] = 0;
		for(int i = 0; i < values.length; i++)
			optimalPrice[i] = i;

		System.out.println(getOptimalPrice(rodLength));
		System.out.println(Arrays.toString(optimalPrice));
	}

	public static int getOptimalPrice(int rodLength){
		if(optimalPrice[rodLength] != -1){
			System.out.println("found: " + rodLength + ' ' + Arrays.toString(optimalPrice));
			return optimalPrice[rodLength];
		}
		else{
			int maxPrice = -1;
			int min = 0;
			int max = rodLength;
			while(min <= max){
				int price = 0;
				if(max == rodLength && max < values.length){
					price += values[rodLength];
					max--;
				}else{
					if(rodLength >= values.length){
						max--;
						price += getOptimalPrice(max);
					}else{
						price += getOptimalPrice(max--);
					}
				}
				price += getOptimalPrice(min++); 
				System.out.println((min - 1)+ " " + (max + 1)+ " $:" + price);
				if(price > maxPrice)
					maxPrice = price;
			}
			optimalPrice[rodLength] = maxPrice;
			return maxPrice;
		}

	}
}