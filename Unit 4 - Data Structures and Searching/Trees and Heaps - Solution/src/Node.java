public class Node {
	private Node parent = null;
	private Node leftChild = null;
	private Node rightChild = null;
	private Integer content;
	
	public void setParent(Node parent){
		this.parent = parent;
	}
	public void setLeftNode(Node leftChild){
		this.leftChild = leftChild;
	}
	public void setRightNode(Node rightChild){
		this.rightChild = rightChild;
	}
	public void setContent(Integer content){
		this.content = content;
	}
	public Node getParent(){
		return parent;
	}
	public Node getLeftChild(){
		return leftChild;
	}
	public Node getRightChild(){
		return rightChild;
	}
	public Integer getContent(){
		return content;
	}
	public String toString(){
		return content + "";
	}
}
