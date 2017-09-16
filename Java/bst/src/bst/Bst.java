/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

package bst;

public class Bst {

	private Node root = null;

	public Bst() {
		root = new Node();
	}
	
	public Integer root() {
		return this.root.getKey();
	}
	
	public Bst rightChild() {
		Bst rightChild = new Bst();
		rightChild.root = this.root.getRightChild();
		return rightChild;
	}
	
	public Bst leftChild() {
		Bst leftChild = new Bst();
		leftChild.root = this.root.getLeftChild();
		return leftChild;
	}
	
	public boolean isEmptyTree() {
		return this.root == null;
	}

	private void insertR(Node node, Integer key) {
		// Insert in empty tree
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
		// Duplicates are ignored
	}
	
	private void insertI(Integer key) {
		// Insert in empty tree
		if (this.root.getKey() == null) { 
			this.root.setKey(key);
		} else {
			Node newNode = new Node();
			newNode.setKey(key);
			Node parent = null;
			Node child = this.root;
			
			// Search for the key's right place in the tree
			while ((child != null) && (child.getKey() != key)) {
				parent = child;
				if (key < child.getKey()) {
					child = child.getLeftChild();
				} else {
					child = child.getRightChild();
				}
			}
			
			// Insert new key in its place
			if (child == null) {
				if (key < parent.getKey()) {
					parent.setLeftChild(newNode);
				} else {
					parent.setRightChild(newNode);
				}
			}
		}
	}
	
	public void insertKey(Integer key) {
		insertR(this.root, key);
	}
	
	private Bst searchI(Integer key) {
		Node node = this.root;
		
		while ((node != null) && (node.getKey() != key)) {
			if (key < node.getKey()) {
				node = node.getLeftChild();
			} else {
				node = node.getRightChild();
			}
		}
		
		Bst tree = new Bst();
		tree.root = node;
		return tree;
	}
}
