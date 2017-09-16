package bst;

public class Bst {

	private Integer key;
	private Bst leftChild;
	private Bst rightChild;
	
	public Bst getRightChild() {
		return rightChild;
	}
	
	public boolean isEmptyTree() {
		return key == null;
	}
	
	private void insertR(Bst tree, Integer key) {
		// Insert in the root
		if (this.key == null) {
			this.key = key;
		// Insert in left tree child
		} else if (key < this.key) {
			if (tree.leftChild == null) {
				tree.leftChild = new Bst();
			}
			
			insertR(tree.leftChild, key);
		// Insert in right tree child
		} else if (key > this.key) {
			if (tree.rightChild == null) {
				tree.rightChild = new Bst();
			}
			
			insertR(tree.rightChild, key);
		}
		// Ignore duplicate
	}
}
