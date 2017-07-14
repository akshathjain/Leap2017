/*
Name: Akshath Jain
Date: 7/6/17
Purpose: radix sort 
*/

import java.util.LinkedList;
import java.util.Arrays;

public class RadixSort{

	public static void main(String[] args){
		int[] ar = getRandom(1000);
		int[] compAr = ar.clone();

		radixSort(ar);
		System.out.println(Arrays.toString(ar));
	}

	private static int[] getRandom(int size){
		int[] temp = new int[size];
		for(int i = 0; i < size; i++)
			temp[i] = -1 * (int)(Math.random() * size);
		return temp;
	}

	private static void radixSort(int[] ar){;
		//get max length;
		int iterations = getDigitLength(ar[0]);
		for(int i = 1; i < ar.length; i++){
			if(getDigitLength(ar[i]) > iterations){
				iterations = getDigitLength(ar[i]);
			}
		}

		LinkedList<Integer>[] bucket = new LinkedList[10];
		for(int j = 0; j < bucket.length; j++)
			bucket[j] = new LinkedList<>();

		for(int i = 1; i <= iterations; i++){
			for(int j = 0; j < ar.length; j++){
				bucket[getNth(ar[j], i)].add(ar[j]);
			}

			int adder = 0;
			for(int j = 0; j < bucket.length; j++){
				for(int k = bucket[j].size() - 1; k >= 0; k--){
					ar[adder++] = bucket[j].pop();
				}
			}
		}
	}

	private static int getNth(int num, int place){
		return (int)(num / Math.pow(10, place - 1)) % 10;
	}

	private static int getDigitLength(int num){
		if(num == 0)
			return 1;
		int dig = 0;
		while(num != 0){
			num /= 10;
			dig++;
		}
		return dig;
	}
}