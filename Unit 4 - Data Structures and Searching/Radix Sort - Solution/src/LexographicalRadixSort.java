/*
Name: Akshath Jain
Date: 7/15/17
Purpose: Lexographical radix sort
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

public class LexographicalRadixSort{

	public static void main(String[] args) {
		String[] dictionary = getDictionary("dictionary.txt");
		String[] randomized = getRandomWords(dictionary, 100);

		sort(randomized); //sorts the array
		System.out.println(Arrays.toString(randomized));
	}

	public static String[] getDictionary(String fileName){
		ArrayList<String> words = new ArrayList<>();

		try{
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNext())
				words.add(scanner.next().toLowerCase().replaceAll("[^a-z]","")); //puts all words to lower case and removes everything that's not a letter

			return words.toArray(new String[words.size()]);
		}catch(FileNotFoundException e){
			return null;
		}
	}

	public static String[] getRandomWords(String[] wordList, int howMany){
		if(howMany > wordList.length)
			return null;

		String[] random = new String[howMany];
		for(int i = 0; i < howMany; i++)
			random[i] = wordList[(int)(Math.random() * wordList.length)];

		return random;
	}

	public static void sort(String[] ar){
		//find longest word
		int iterations = 0;
		for(int i = 0; i < ar.length; i++){
			if(ar[i].length() > iterations)
				iterations = ar[i].length();
		}

		LinkedList<String>[] bucket = new LinkedList[27]; //empty character + a - z
		for(int j = 0; j < bucket.length; j++)
			bucket[j] = new LinkedList<>();

		for(int i = 0; i < iterations; i++){
			for(int j = 0; j < ar.length; j++)
				bucket[getNth(ar[j], iterations, i) - 96].add(ar[j]);

			int adder = 0;
			for(int j = 0; j < bucket.length; j++){
				for(int k = bucket[j].size() - 1; k >= 0; k--)
					ar[adder++] = bucket[j].pop();
			}
		}

	}

	//returns the char at the @parameter place place from right to left (initial place starts at 0)
	public static char getNth(String word, int longestWordLength, int place){
		if(place < longestWordLength - word.length())
			return '`';
		return word.charAt(word.length() - place + (longestWordLength - word.length()) - 1);
	}
}	