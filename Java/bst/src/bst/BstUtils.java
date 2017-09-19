/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

package bst;

public final class BstUtils {

	private BstUtils() {}

	// TODO deberíamos plantearnos poner un método arbolVacio de todos modos?
	// Rollo poner a nulo el nodo y via (aunque parece un desperdicio de memoria tremendo)
	// Más bien poner todos los atributos del nodo a null
	public static Bst emptyTree() {
		Bst tree = new Bst();
		tree.root = null;
		return tree;
	}
	
	public static int root(Bst tree) {
		return tree.root.key;
	}
	
	public static Bst rightChild(Bst tree) {
		Bst rightChild = new Bst();
		rightChild.root = tree.root.right;
		return rightChild;
	}
	
	public static Bst leftChild(Bst tree) {
		Bst leftChild = new Bst();
		leftChild.root = tree.root.left;
		return leftChild;
	}
	
	public static boolean isEmptyTree(Bst tree) {
		return tree.root == null;
	}

	// TODO usar nodo padre para poder tener node == null
	// TODO Pasar parentNode como árbol permite insertar la raíz al tener un puntero indirecto a ella
	// TODO inicialmente, node = root, parentNode = this
	private static void insertR(Node node, Bst tree, int key) {
		// Insert in empty tree
		if (node == null) {
			// Insert root of the complete tree
			if (tree.root == null) { 
				tree.root = new Node();
				tree.root.key = key;
			// Insert regular node
			} else if (tree.root.left == node) {
				tree.root.left = new Node();
				tree.root.left.key = key;
			} else {
				tree.root.right = new Node();
				tree.root.right.key = key;
			}
		// Insert in left tree child TODO preguntar a Fer si es correcto
		} else if (key < node.key) {
			Bst aux = new Bst();
			aux.root = node;
			insertR(node.left, aux, key);
		// Insert in right tree child
		} else if (key > node.key) {
			Bst aux = new Bst();
			aux.root = node;
			insertR(node.right, aux, key);
		}
		// Duplicates are ignored
	}
	
	private static void insertI(Bst tree, int key) {
		Node newNode = new Node();
		newNode.key = key;
		
		// Insert in empty tree
		if (tree.root == null) { 
			tree.root = newNode;
		} else {
			Node parent = null;
			Node child = tree.root;
			
			// Search for the key's right place in the tree
			while ((child != null) && (child.key != key)) {
				parent = child;
				if (key < child.key) {
					child = child.left;
				} else {
					child = child.right;
				}
			}
			
			// Insert new key in its place
			if (child == null) {
				if (key < parent.key) {
					parent.left = newNode;
				} else {
					parent.right = newNode;
				}
			}
			// Duplicates are ignored
		}
	}
	
	public static void insertKey(Bst tree, int key) {
		insertI(tree, key);
		//insertR(tree.root, tree, key);
	}

	private static Bst searchR(Node node, int key) {
		Bst result;
		
		if (node == null) {
			result = new Bst(); 
		} else if (key == node.key) {
			result = new Bst();
			result.root = node;
		} else if (key < node.key) {
			result = searchR(node.left, key);
		} else {
			result = searchR(node.right, key);
		}
		
		return result;
	}
	
	// TODO la definición mal hecha del árbol vacío también hace que el resultado al no encontrar
	// una clave sea distinto entre la versión iterativa y la recursiva
	private static Bst searchI(Bst tree, int key) {
		Node node = tree.root;
		
		while ((node != null) && (node.key != key)) {
			if (key < node.key) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		
		Bst searchTree = new Bst();
		searchTree.root = node;
		return searchTree;
	}
	
	public static Bst searchKey(Bst tree, int key) {
		return searchI(tree, key);
		//return searchR(tree.root, key);
	}

	// del: node with the key to delete
	// parentNode: parent of the node that will replace del
	// This method replaces the node to remove with the node with
	// the greatest key from its left subtree.
	private static void deleteAux(Node del, Node parentNode, Node node) {
		// We need the parent node instead of the node itself
		// in order to take the children of the node that
		// will replace del and put them as children of
		// parentNode (<- TODO para la memoria más que nada)
		if (node.right != null) { 
			deleteAux(del, node, node.right);
		} else {
			del.key = node.key;
			parentNode.right = node.left;
		}
	}

	// TODO si se pasa a clase estática, es posible que parentNode necesite ser un Bst como en la
	// inserción recursiva
	// parentNode: parent of the node to be deleted. Used to reorder the children of the node
	// to be deleted.
	private static void deleteR(Node node, Bst tree, int key) {
		if (node != null) {
			// Create auxiliar tree
			Bst aux = new Bst();
			aux.root = node;
			
			if (key < node.key) {
				deleteR(node.left, aux, key);
			} else if (key > node.key) {
				deleteR(node.right, aux, key);
			} else {
				// Delete node with at most one child
				if (node.left == null) {
					// Delete root of the total tree
					if (tree.root == null) {
						tree.root = node.right;
					// Check if node was a right or a left child
					} else if (tree.root.left == node) {
						tree.root.left = node.right;
					} else {
						tree.root.right = node.right;
					}
				} else if (node.right == null) {
					// Delete root of the total tree
					if (tree.root == null) {
						tree.root = node.left;
					// Check if node was a right or a left child
					} if (tree.root.left == node) {
						tree.root.left = node.left;
					} else {
						tree.root.right = node.left;
					}
				// Delete node with two children
				} else {
					deleteAux(node, node, node.left);
				}
			}
		}
	}

	private static void deleteI(Bst tree, int key) {
		int numChildren;
		Node rm;			// Node to remove
		Node parentRm;		// Parent of the node to remove
		Node notEmptyChild;	// If the node to remove has only one child, the one that isn't empty
		Node maxLeftChild;	// If the node to remove has two children, the node with the greatest key of the left subtree
		// TODO nombre de notEmptyChild
		parentRm = null;
		rm = tree.root;
		
		// Search for the node to remove
		while ((rm != null) && (rm.key != key)) {
			parentRm = rm;
			
			if (key < rm.key) { 
				rm = rm.left;
			} else { 
				rm = rm.right;
			}
		}

		// If the key isn't in the tree, don't do anything
		if (rm != null) {
			numChildren = 0;
			if (rm.left != null) 
				numChildren++;
			if (rm.right != null) 
				numChildren++;
			
			switch (numChildren) {
				// Remove leaf node
				case 0:
					if (parentRm == null) {
						tree.root = null; // TODO no corresponde con la definición de árbol vacío del constructor
					} else if (parentRm.left == rm) {
						parentRm.left = null;
					} else {
						parentRm.right = null;
					}
					break;
		
				// Remove node with one child
				case 1:
					if (rm.left == null) {
						notEmptyChild = rm.right;
					} else {
						notEmptyChild = rm.left;
					}
					
					if (parentRm == null) {
						tree.root = notEmptyChild;
					} else if (parentRm.left == rm) {
						parentRm.left = notEmptyChild;
					} else {
						parentRm.right = notEmptyChild;
					}
					break;
			
				// Remove node with two children
				case 2:
					parentRm = rm;
					maxLeftChild = rm.left;
					
					// Search for the node with the greatest key of the left subtree
					while (maxLeftChild.right != null) {
						parentRm = maxLeftChild;
						maxLeftChild = maxLeftChild.right;
					}
					
					// Replace the key of the node to remove with the found one
					rm.key = maxLeftChild.key;
				
					// TODO Put maxLeftChild's children as its parent's
					if (parentRm == rm) {
						// If maxLeftChild doesn't have right child
						parentRm.left = maxLeftChild.left;
					} else {
						parentRm.right = maxLeftChild.left;
					}
					break;
			}
		}
	}
	
	public static void deleteKey(Bst tree, Integer key) {
		deleteI(tree, key);
		//deleteR(tree.root, tree, key);
	}
}
