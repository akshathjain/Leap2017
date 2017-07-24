import java.util.ArrayList;


public class Tree {
	private Node root = new Node();
	private int[] userInput;
	private ArrayList<Node> allOtherNodes;
	
	public Tree(int[] allOtherValues){
		this.userInput = allOtherValues;
		convertArrayToArrayList();
		iterateThroughArrayList();
	}
	public void add(int nodeToAdd){
		Node tempNode = new Node();
		tempNode.setContent(nodeToAdd);
		if(search(nodeToAdd) == -1){
			convertArrayToArrayList();
			allOtherNodes.add(tempNode);
			iterateThroughArrayList();
		}else
			System.out.print("Node already exists");
	}
	public void remove(int numToRemove){
		convertArrayToArrayList();
		if(root.getContent() == numToRemove){
			setRoot();
		}
		for(int i = 0; i < allOtherNodes.size(); i++){
			if(allOtherNodes.get(i).getContent() == numToRemove){
				allOtherNodes.remove(i);
				break;
			}
		}
		iterateThroughArrayList();
	}
	public int search(int lookForNode){
		if(root.getContent() == lookForNode)
			return 0;
		for(int i = 0; i < allOtherNodes.size(); i++){
			if(allOtherNodes.get(i).getContent() == lookForNode){
				return findDepth(allOtherNodes.get(i), 0);
			}
		}
		return -1;
	}
	public boolean checkIfHeap(){
		return checkIfHeap(root);
	}
	public void print(){
		System.out.print("{");
		print(root);
		System.out.print("}");
		System.out.print("\n");
	}
	public void convertTreeToHeap(){
		convertArrayToArrayList();
		System.out.println(allOtherNodes);
		for(int i = 0; i < allOtherNodes.size(); i++)
			convertTreeToHeap(root, allOtherNodes.get(i));
	}
	public void sortBinaryTree(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result = sortBinaryTree(root);
		for(int i = 0; i < result.size(); i++)
			System.out.print(result.get(i) + " ");
	}
	public void sortHeap(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < userInput.length; i++){
			result.add(root.getContent());
			root.setContent(null);
			swapDown(root);
		}
		for(int i = 0; i < result.size(); i++)
			System.out.print(result.get(i) + " ");
	}
	
	private void convertTreeToHeap(Node comparing, Node currentNode){
		if(comparing.getLeftChild() == null){ //assigns node
			comparing.setLeftNode(currentNode);
		}else if(comparing.getRightChild() == null){
			comparing.setRightNode(currentNode);
		}else
			convertTreeToHeap(comparing.getLeftChild(), currentNode);
		swapUp(currentNode, comparing);
	}
	private void swapUp(Node a, Node b){
		if(a.getContent() > b.getContent()){ //swaps nodes if need be
			swap(a, b);
			if(b.getParent() != null && b.getParent().getContent() < b.getContent())
				swapUp(b.getParent(), b);
		}
	}
	private void swapDown(Node a){
		if(a.getLeftChild() != null && a.getRightChild() != null){
			if(a.getLeftChild().getContent() == null && a.getRightChild().getContent() == null)
				return;
			else if(a.getLeftChild().getContent() == null && a.getRightChild().getContent() != null){
				swap(a, a.getRightChild());
				swapDown(a.getRightChild());
			}else if(a.getLeftChild().getContent() != null && a.getRightChild().getContent() == null){
				swap(a, a.getLeftChild());
				swapDown(a.getLeftChild());
			}else if(a.getLeftChild().getContent() > a.getRightChild().getContent()){
				swap(a, a.getLeftChild());
				swapDown(a.getLeftChild());
			}else if(a.getLeftChild().getContent() <= a.getRightChild().getContent()){
				swap(a, a.getRightChild());
				swapDown(a.getRightChild());
			}
		}else if(a.getLeftChild() == null && a.getRightChild() != null){
			swap(a, a.getRightChild());
			swapDown(a.getRightChild());
		}else if(a.getLeftChild() != null && a.getRightChild() == null){
			swap(a, a.getLeftChild());
			swapDown(a.getLeftChild());
		}
	}
	private void swap(Node a, Node b){
		Node temp = new Node();
		temp.setContent(a.getContent());
		a.setContent(b.getContent());
		b.setContent(temp.getContent());
	}
	private ArrayList<Integer> sortBinaryTree(Node currentNode){
		ArrayList<Integer> greaterThan = new ArrayList<Integer>();
		ArrayList<Integer> lessThan = new ArrayList<Integer>();
		
		if(currentNode.getLeftChild() != null)
			lessThan = sortBinaryTree(currentNode.getLeftChild());
		if(currentNode.getRightChild() != null)
			greaterThan = sortBinaryTree(currentNode.getRightChild());
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < lessThan.size(); i++)
			result.add(lessThan.get(i));
		result.add(currentNode.getContent());
		for(int i = 0; i < greaterThan.size(); i++)
			result.add(greaterThan.get(i));
				
		return result;
	}
	private boolean checkIfHeap(Node currentNode){
		if(currentNode.getRightChild() != null){
			return false;
		}
		if(currentNode.getLeftChild() != null){
			return checkIfHeap(currentNode.getLeftChild());
		}else{
			return true;
		}
	}
	private void print(Node currentNode){
		System.out.print(currentNode.getContent());
		if(currentNode.getLeftChild() != null && currentNode.getRightChild() != null){
			System.out.print("{");
			print(currentNode.getLeftChild());
			System.out.print(",");
			print(currentNode.getRightChild());
			System.out.print("}");
		}else if(currentNode.getLeftChild() == null && currentNode.getRightChild() != null){
			System.out.print("{");
			print(currentNode.getRightChild());
			System.out.print("}");
		}else if(currentNode.getLeftChild() != null && currentNode.getRightChild() == null){
			System.out.print("{");
			print(currentNode.getLeftChild());
			System.out.print("}");
		}
	}
	private int findDepth(Node currentNode, int depth){
		if(currentNode.getParent() == null)
			return depth;
		else
			return findDepth(currentNode.getParent(), depth + 1);
	}
	private void convertArrayToArrayList(){
		allOtherNodes = new ArrayList<Node>();
		for(int i = 0; i < userInput.length; i++){
			Node temp = new Node();
			temp.setContent(userInput[i]);
			allOtherNodes.add(temp);
		}
		setRoot();
	}
	private void setRoot(){
		root = allOtherNodes.get(0);
		allOtherNodes.remove(0);
	}
	private void iterateThroughArrayList(){
		for(int i = 0; i < allOtherNodes.size(); i++){
			findPlaceForNode(root, allOtherNodes.get(i));
		}
	}
	private void findPlaceForNode(Node comparing, Node current){
		if(current.getContent() < comparing.getContent()){
			if(comparing.getLeftChild() == null){
				current.setParent(comparing);
				comparing.setLeftNode(current);
			}else{
				findPlaceForNode(comparing.getLeftChild(), current);
			}
		}else{
			if(comparing.getRightChild() == null){
				current.setParent(comparing);
				comparing.setRightNode(current);
			}else{
				findPlaceForNode(comparing.getRightChild(), current);
			}
		}
	}	
}