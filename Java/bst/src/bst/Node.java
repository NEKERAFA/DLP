package bst;

public class Node {

	private Integer key;
	private Node leftChild;
	private Node rightChild;
	
	public Integer getKey() {
		return this.key;
	}
	
	public Node getLeftChild() {
		return this.leftChild;
	}
	
	void setKey(Integer key) {
		this.key = key;
	}
	
	void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	
	void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
}
