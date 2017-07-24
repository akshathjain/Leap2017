import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;


public class Driver {
	private static Tree myTree;
	private static Vector<Integer> input;
	
	public static void main(String[] args){
		mainMenu();
	}
	private static void mainMenu(){
		int choice = 1;
		Scanner s = new Scanner(System.in);
		
		System.out.println("What would you like to do?"
			+ "\n(1)Create New Binary Tree"
			+ "\n(2)Add Element To Existing Binary Tree"
			+ "\n(3)Delete Element From Existing Binary Tree"
			+ "\n(4)Search For Term in Binary Tree"
			+ "\n(5)Convert Binary Tree To Heap"
			+ "\n(6)Check If Binary Tree Is a Heap"
			+ "\n(7)Sort Binary Tree"
			+ "\n(8)Sort Heap"
			+ "\n(9)Print Binary Tree"
			+ "\n(10)Exit");
		
		try{
			choice = s.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Enter choice: 1-7\n");
			mainMenu();
		}
		
		switch(choice){
			case 1:
			getInput();
			break;
			case 2:
			addElement();
			break;
			case 3:
			deleteElement();
			break;
			case 4:
			searchForElement();
			break;
			case 5:
			convertTreeToHeap();
			break;
			case 6:
			checkIfHeap();
			break;
			case 7:
			sortTree();
			break;
			case 8:
			sortHeap();
			break;
			case 9:
			print();
			break;
			case 10:
			System.out.println("\nHave a nice day");
			return;
			default:
			System.out.println("Enter choice: 1-7\n");
			mainMenu();
		}
		System.out.println("\n----------------------------");
		mainMenu();
	}
	private static void getInput(){
		Scanner s = new Scanner(System.in);
		int[] otherValues;
		input = new Vector<Integer>();
		
		System.out.println("Enter all values (seperate with space): ");
		String arrayInput = s.nextLine();
		
		int startPos = 0;
		for(int i = 0; i < arrayInput.length(); i++){
			if(Character.isSpaceChar(arrayInput.charAt(i))){
				input.addElement((Integer.parseInt(arrayInput.substring(startPos, i))));
				startPos = i+1;
			}else if(i == arrayInput.length()-1){
				input.addElement((Integer.parseInt(arrayInput.substring(startPos, i+1))));
			}
		}
		
		otherValues = new int[input.size()];
		for(int i = 0; i < input.size(); i++){
			otherValues[i] = input.elementAt(i);
		}
		
		myTree = new Tree(otherValues);
	}
	private static void addElement(){
		Scanner s = new Scanner(System.in);
		if(checkIfTreeExists()){
			System.out.println("Element to add: ");
			myTree.add(s.nextInt());
			System.out.println("\n");
		}else{
			System.out.println("Create a tree first");
		}
	}
	private static void deleteElement(){
		Scanner s = new Scanner(System.in);
		if(checkIfTreeExists()){
			System.out.println("Element to delete: ");
			myTree.remove(s.nextInt());
			System.out.println("\n");
		}else{
			System.out.println("Create a tree first");
		}
	}
	private static void searchForElement(){
		Scanner s = new Scanner(System.in);
		if(checkIfTreeExists()){
			System.out.println("Element to search: ");
			System.out.println("Located at depth " + myTree.search(s.nextInt()));
			System.out.println("\n");
		}else{
			System.out.println("Create a tree first");
		}
	}
	private static void convertTreeToHeap(){
		myTree.convertTreeToHeap();
		System.out.println("Converted to heap");
	}
	private static void checkIfHeap(){
		if(myTree.checkIfHeap()){
			System.out.println("Your tree is a heap");
		}else{
			System.out.println("Your tree is not a heap");
		}
	}
	private static void sortTree(){
		myTree.sortBinaryTree();
	}
	private static void sortHeap(){
		myTree.sortHeap();
	}
	private static void print(){
		if(checkIfTreeExists()){
			myTree.print();
		}else{
			System.out.println("Create a tree first");
		}
	}
	private static boolean checkIfTreeExists(){
		return myTree != null;
	}
}