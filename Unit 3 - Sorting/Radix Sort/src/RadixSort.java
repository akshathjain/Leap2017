/*
Name: Akshath Jain
Date: 7/6/17
Purpose: radix sort 
*/

public class RadixSort{

	public static void main(String[] args){
		int[] ar = new int[100000];
	}

	private static int[] radixSort(int[] ar){
		int iterations = String.valueOf(ar[0]).length();
		//get max length;
		for(int i = 1; i < ar.length; i++)
			if(String.valueOf(ar[i]).length() > iterations)
				iterations = String.valueOf(ar[i]).length();

			for(int i = 1; i <= iterations; i++){
				for(int j = 0; j < ar.length; j++){

				}
			}
		}
	}
}