/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

package bst;

public class Bst {
	// TODO deberíamos plantearnos poner un método
	// arbolVacio de todos modos?
	// Rollo poner a nulo el nodo y via
	// (aunque parece un desperdicio de memoria tremendo)
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
	
	// del: node to delete
	// parentNode: parent of the node that will replace del
	// TODO inicialmente parentNode == del?
	private void deleteAux(Node del, Node parentNode, Node node) {
		// Check if the right child has its own right child
		// We need the parent node instead of the node itself
		// in order to take the children of the node that
		// will replace del and put them as children of
		// parentNode (<- TODO para la memoria más que nada)
		if (node.getRightChild() != null) { 
			deleteAux(del, node, node.getRightChild());
		} else {
			del.setKey(node.getKey());
			parentNode.setRightChild(node.getLeftChild());
		}
	}
	
	// parentNode: parent of the node to be deleted. Used to reorder the children of the node
	// to be deleted.
	private void deleteR(Node node, Node parentNode, Integer key) {
		if (node != null) {
			if (key < node.getKey()) {
				deleteR(node.getLeftChild(), node, key);
			} else if (key > node.getKey()) {
				deleteR(node.getRightChild(), node, key);
			} else {
				// Delete node with at most one child
				if (node.getLeftChild() == null) {
					if (parentNode == null) {
						this.root = node.getRightChild();
					} else if (parentNode.getLeftChild() == node) {
						parentNode.setLeftChild(node.getRightChild());
					} else {
						parentNode.setRightChild(node.getRightChild());
					}
				} else if (node.getRightChild() == null) {
					if (parentNode == null) {
						this.root = node.getLeftChild();
					} if (parentNode.getLeftChild() == node) {
						parentNode.setLeftChild(node.getLeftChild());
					} else {
						parentNode.setRightChild(node.getLeftChild());
					}
				} else {
					deleteAux(node, node, node.getLeftChild());
				}
			}
		}
	}
	
	public void deleteKey(Integer key) {
		deleteR(this.root, null, key);
	}
}
