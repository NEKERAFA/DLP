/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

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
	
	public Node getRightChild() {
		return this.rightChild;
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
