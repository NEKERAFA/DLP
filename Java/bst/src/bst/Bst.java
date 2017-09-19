/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

package bst;

public class Bst {
	// TODO deberíamos plantearnos poner un método arbolVacio de todos modos?
	// Rollo poner a nulo el nodo y via (aunque parece un desperdicio de memoria tremendo)
	// Más bien poner todos los atributos del nodo a null
	private Node root = null;
	
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

	// TODO usar nodo padre para poder tener node == null
	// TODO Pasar parentNode como árbol permite insertar la raíz al tener un puntero indirecto a ella
	// TODO inicialmente, node = root, parentNode = this
	private void insertR(Node node, Bst parentNode, Integer key) {
		// Insert in empty tree
		if (node == null) {
			// Insert root of the complete tree
			if (parentNode.root == null) { 
				parentNode.root = new Node();
				parentNode.root.setKey(key);
			// Insert regular node
			} else if (parentNode.root.getLeftChild() == node) {
				parentNode.root.setLeftChild(new Node());
				parentNode.root.getLeftChild().setKey(key);
			} else {
				parentNode.root.setRightChild(new Node());
				parentNode.root.getRightChild().setKey(key);
			}
		// Insert in left tree child TODO preguntar a Fer si es correcto
		} else if (key < node.getKey()) {
			Bst aux = new Bst();
			aux.root = node;
			insertR(node.getLeftChild(), aux, key);
		// Insert in right tree child
		} else if (key > node.getKey()) {
			Bst aux = new Bst();
			aux.root = node;
			insertR(node.getRightChild(), aux, key);
		}
		// Duplicates are ignored
	}
	
	private void insertI(Integer key) {
		Node newNode = new Node();
		newNode.setKey(key);
		
		// Insert in empty tree
		if (this.root == null) { 
			this.root = newNode;
		} else {
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
			// Duplicates are ignored
		}
	}
	
	public void insertKey(Integer key) {
		insertI(key);
	}
	
	private Bst searchR(Node node, Integer key) {
		Bst result;
		
		if (node == null) {
			result = new Bst(); 
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
	// TODO la definición mal hecha del árbol vacío también hace que el resultado al no encontrar
	// una clave sea distinto entre la versión iterativa y la recursiva
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
	
	// del: node with the key to delete
	// parentNode: parent of the node that will replace del
	// This method replaces the node to remove with the node with
	// the greatest key from its left subtree.
	private void deleteAux(Node del, Node parentNode, Node node) {
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
	// TODO si se pasa a clase estática, es posible que parentNode necesite ser un Bst como en la
	// inserción recursiva
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
					// Delete root of the total tree
					if (parentNode == null) {
						this.root = node.getRightChild();
					// Check if node was a right or a left child
					} else if (parentNode.getLeftChild() == node) {
						parentNode.setLeftChild(node.getRightChild());
					} else {
						parentNode.setRightChild(node.getRightChild());
					}
				} else if (node.getRightChild() == null) {
					// Delete root of the total tree
					if (parentNode == null) {
						this.root = node.getLeftChild();
					// Check if node was a right or a left child
					} if (parentNode.getLeftChild() == node) {
						parentNode.setLeftChild(node.getLeftChild());
					} else {
						parentNode.setRightChild(node.getLeftChild());
					}
				// Delete node with two children
				} else {
					deleteAux(node, node, node.getLeftChild());
				}
			}
		}
	}
	
	private void deleteI(Integer key) {
		int numChildren;
		Node rm;			// Node to remove
		Node parentRm;		// Parent of the node to remove
		Node notEmptyChild;	// If the node to remove has only one child, the one that isn't empty
		Node maxLeftChild;	// If the node to remove has two children, the node with the greatest key of the left subtree
		// TODO nombre de notEmptyChild
		parentRm = null;
		rm = this.root;
		
		// Search for the node to remove
		while ((rm != null) && (rm.getKey() != key)) {
			parentRm = rm;
			
			if (key < rm.getKey()) { 
				rm = rm.getLeftChild();
			} else { 
				rm = rm.getRightChild();
			}
		}

		// If the key isn't in the tree, don't do anything
		if (rm != null) {
			numChildren = 0;
			if (rm.getLeftChild() != null) 
				numChildren++;
			if (rm.getRightChild() != null) 
				numChildren++;
			
			switch (numChildren) {
				// Remove leaf node
				case 0:
					if (parentRm == null) {
						this.root = null; // TODO no corresponde con la definición de árbol vacío del constructor
					} else if (parentRm.getLeftChild() == rm) {
						parentRm.setLeftChild(null);
					} else {
						parentRm.setRightChild(null);
					}
					break;
		
				// Remove node with one child
				case 1:
					if (rm.getLeftChild() == null) {
						notEmptyChild = rm.getRightChild();
					} else {
						notEmptyChild = rm.getLeftChild();
					}
					
					if (parentRm == null) {
						this.root = notEmptyChild;
					} else if (parentRm.getLeftChild() == rm) {
						parentRm.setLeftChild(notEmptyChild);
					} else {
						parentRm.setRightChild(notEmptyChild);
					}
					break;
			
				// Remove node with two children
				case 2:
					parentRm = rm;
					maxLeftChild = rm.getLeftChild();
					
					// Search for the node with the greatest key of the left subtree
					while (maxLeftChild.getRightChild() != null) {
						parentRm = maxLeftChild;
						maxLeftChild = maxLeftChild.getRightChild();
					}
					
					// Replace the key of the node to remove with the found one
					rm.setKey(maxLeftChild.getKey());
				
					// TODO Put maxLeftChild's children as its parent's
					if (parentRm == rm) {
						// If maxLeftChild doesn't have right child
						parentRm.setLeftChild(maxLeftChild.getLeftChild());
					} else {
						parentRm.setRightChild(maxLeftChild.getLeftChild());
					}
					break;
			}
		}
	}
	
	public void deleteKey(Integer key) {
		deleteR(this.root, null, key);
	}
}
