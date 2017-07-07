/*
Name: Akshath Jain
Date: 7/6/17
Purpose: radix sort 
*/

import java.util.LinkedList;
import java.util.Arrays;

public class RadixSort{

	public static void main(String[] args){
		int[] ar = new int[10];
		for(int i = 0; i < ar.length; i++)
			ar[i] = (int)(Math.random() * 1000);
		
		System.out.println(Arrays.toString(radixSort(ar)));
	}

	private static int[] radixSort(int[] ar){
		int iterations = String.valueOf(ar[0]).length();
		//get max length;
		for(int i = 1; i < ar.length; i++){
			if(String.valueOf(ar[i]).length() > iterations){
				iterations = String.valueOf(ar[i]).length();
			}
		}

		for(int i = 1; i <= iterations; i++){
			LinkedList<Integer>[] bucket = new LinkedList[10];
			for(int j = 0; j < bucket.length; j++)
				bucket[j] = new LinkedList<>();

			for(int j = 0; j < ar.length; j++){
				bucket[getNth(ar[j], i)].add(ar[j]);
			}

			int adder = 0;
			for(int j = 0; j < bucket.length; j++){
				for(int k = 0; k < bucket[j].size(); k++){
					ar[adder++] = bucket[j].pop();
					System.out.println(ar[adder-1]);
				}
			}
		}
		return ar;
	}

	private static int getNth(int num, int place){
		return (int)(num / Math.pow(10, place - 1)) % 10;
	}
}