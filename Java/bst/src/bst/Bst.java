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

	private void insertR(Bst tree, Integer key) {
		// Insert in the root
		if (tree.root == null) {
			tree.root = new Node();
			tree.root.setKey(key);
		// Insert in left tree child
		} else if (key < tree.root.getKey()) {
			insertR(tree.root.getLeftChild(), key);
		// Insert in right tree child
		} else if (key > tree.root.getKey()) {
			insertR(tree.root.getRightChild(), key);
		}
		// Ignore duplicates
	}
	
	private void insertI(Integer key) {
		if (this.root.getKey() == null) { // Empty tree
			this.key = key;
		} else {
			Bst newNode = new Bst();
			newNode.key = key;
			
		}
	}
}
