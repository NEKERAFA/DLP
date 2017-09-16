/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

package bst;

public class Bst {

	private Node root;

	public Bst getRightChild() {
		Bst rightChild = new Bst();
		rightChild.root = this.root.getRightChild();
		return rightChild;
	}
	
	public boolean isEmptyTree() {
		return root == null;
	}

	private void insertR(Node node, Integer key) {
		// Insert in the root
		if (node.getKey() == null) {
			node.setKey(key);
		// Insert in left tree child
		} else if (key < node.getKey()) {
			if (node.getLeftChild() == null) {
				node.setLeftChild(new Node());
			}
			
			insertR(node.getLeftChild(), key);
		// Insert in right tree child
		} else if (key > node.getKey()) {
			if (node.getRightChild() == null) {
				node.setRightChild(new Node());
			}
			
			insertR(node.getRightChild(), key);
		}
		// Ignore duplicates
	}
	
	public Integer root() {
		return this.root.getKey();
	}
	
	public Bst leftChild() {
		Bst child = new Bst();
		child.root = this.root.getLeftChild();
		return child;
	}
	
	private void insertI(Integer key) {
		if (this.root.getKey() == null) { // Empty tree
			this.root.setKey(key);
		} else {
			Node newNode = new Node();
			newNode.setKey(key);
			Node parent = null;
			Node child = this.root;
			
			while ((child != null) && (child.getKey() != key)) {
				parent = child;
				if (key < child.getKey()) {
					child = child.getLeftChild();
				} else {
					child = child.getRightChild();
				}
			}
			
			if (child == null) {
				if (key < parent.getKey()) {
					parent.setLeftChild(newNode);
				} else {
					parent.setRightChild(newNode);
				}
			}
		}
	}
}
