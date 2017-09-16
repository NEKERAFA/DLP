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
		insertI(key);
	}
	
	private Bst searchR(Node node, Integer key) {
		Bst result;
		
		if (node == null) {
			result = new Bst(); // TODO devolver vacío?
		} else if (key == node.getKey()) {
			result = new Bst();
			result.root = node;
		} else if (key < node.getKey()) {
			result = searchR(node.getLeftChild(), key);
		} else {
			result = searchR(node.getRightChild(), key);
		}
		
		return result;
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
	
	public Bst searchKey(Integer key) {
		return searchI(key);
	}
	
	private void removeI(Integer key) {
		int numChildren;
		Node rm;			// Node to remove
		Node parentRm;		// Parent of node to remove
		Node noEmptyChild;	// If node to remove has only a child, it isn't empty one
		Node maxLeftChild;	// If node to remove has two children, it is greater value than all left subtree
		
		parentRm = null;
		rm = this.root;
		
		// Search node to remove
		while ((rm != null) && (rm.getKey() != key)) {
			parentRm = rm;
			
			if (key < rm.getKey()) { // Move forward left child if key is less than the current node
				rm = rm.getLeftChild();
			} else { // Move forward right child if key is greater than the current node
				rm = rm.getRightChild();
			}
		}
		
		// If the key isn't in the tree, It do anything
		numChildren = 0;
		if (rm.getLeftChild() != null) numChildren++;
		if (rm.getRightChild() != null) numChildren++;
		
		switch (numChildren) {
			// Remove leaf node
			case 0:
				if (parentRm == null) {
					this.root = null;
				} else if (parentRm.getLeftChild() == rm) {
					parentRm.setLeftChild(null);
				} else {
					parentRm.setRightChild(null);
				}
				break;
	
			// Remove node with one child
			case 1:
				if (rm.getLeftChild() == null) {
					noEmptyChild = rm.getRightChild();
				} else {
					noEmptyChild = rm.getLeftChild();
				}
				
				if (parentRm == null) {
					this.root = noEmptyChild;
				} else if (parentRm.getLeftChild() == rm) {
					parentRm.setLeftChild(noEmptyChild);
				} else {
					parentRm.setRightChild(noEmptyChild);
				}
				break;
		
			// Remove node with two children
			case 2:
				parentRm = rm;
				maxLeftChild = rm.getLeftChild();
				
				// Search node with greater key than all left subtree
				while (maxLeftChild.getRightChild() != null) {
					parentRm = maxLeftChild;
					maxLeftChild = maxLeftChild.getRightChild();
				}
				
				// Up the key of found node
				rm.setKey(maxLeftChild.getKey());
				
				// If left subtree haven't right child
				if (parentRm == rm) {
					// Maximun left child node will be removed, so put its children to parent node
					parentRm.setLeftChild(maxLeftChild.getLeftChild());
				} else {
					// Maximun left child node will be removed, so put its children to parent node
					parentRm.setRightChild(maxLeftChild.getLeftChild());
				}
				break;
		}
	}
	
	public void removeKey(Integer key) {
		removeI(key);
	}
}
