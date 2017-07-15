/*
Name: Akshath Jain
Date: 7/6/17
Purpose: radix sort 
*/

import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class RadixSort{

	public static void main(String[] args){
		int[] ar = getRandom(getUserInput());
		int[] compAr = ar.clone();

		//account for negative numbers
		ArrayList<Integer> neg = new ArrayList<>();
		ArrayList<Integer> pos = new ArrayList<>();
		for(int i = 0; i < ar.length; i++){
			if(ar[i] < 0)
				neg.add(-1 * ar[i]); //assume all positive
			else
				pos.add(ar[i]);
		}

		Integer[] sortedNeg = neg.toArray(new Integer[neg.size()]);
		Integer[] sortedPos = pos.toArray(new Integer[pos.size()]);
		radixSort(sortedNeg);
		radixSort(sortedPos);

		int[] sortedFinal = new int[sortedNeg.length + sortedPos.length];
		for(int i = 0; i < sortedNeg.length; i++)
			sortedFinal[i] = -1 * sortedNeg[sortedNeg.length - 1 - i];
		for(int i = sortedNeg.length; i < sortedFinal.length; i++)
			sortedFinal[i] = sortedPos[i - sortedNeg.length];

		System.out.println(Arrays.toString(sortedFinal));
	}

	//gets user input and error catches
	public static int getUserInput(){
		Integer val = null;

		do{
			Scanner scanner = new Scanner(System.in);
			try{	
				System.out.print("Num random numbers: ");
				val = scanner.nextInt();

				if(val < 1)
					throw new InputMismatchException();
			}catch(InputMismatchException e){
				System.out.println("Invalid, try again");
				val = null;
			}
		}while(val == null);

		return val;
	}

	//gets a random number from [-size/2, size/2]
	private static int[] getRandom(int size){
		int[] temp = new int[size];
		for(int i = 0; i < size; i++)
			temp[i] = (int)(Math.random() * size) - size/2;
		return temp;
	}

	//radix sort @paramenter Integer[] ar is the Integer array to be sorted
	//post condition: @parameter Integer[] ar is sorted
	private static void radixSort(Integer[] ar){;
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

	//returns the digit in the @parameter int place of @parameter int num
	private static int getNth(int num, int place){
		return (int)(num / Math.pow(10, place - 1)) % 10;
	}

	//returns the length of @parameter num
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