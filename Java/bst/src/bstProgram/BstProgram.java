/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

package bstProgram;

import bst.Bst;

public class BstProgram {

	private static Bst tree;
	
	private static void bracketPreorder(Bst a) {
		System.out.print("(");
		if (!a.isEmptyTree()) {
			if (!a.leftChild().isEmptyTree() || !a.rightChild().isEmptyTree()) {
				System.out.print(" " + a.root() + " ");
				bracketPreorder(a.leftChild());
				System.out.print(" ");
				bracketPreorder(a.rightChild());
			} else {
				System.out.print(" " + a.root() + " ");
			}
		}
		System.out.print(")");
	}
	
	public static void main(String[] args) {
		tree = new Bst();

		tree.insertKey(4);
		tree.insertKey(4);
		tree.insertKey(2);
		tree.insertKey(6);
		tree.insertKey(1);
		tree.insertKey(3);
		tree.insertKey(5);
		tree.insertKey(7);
		
		bracketPreorder(tree); System.out.println();
		
		System.out.println("Search 1... " + (tree.searchKey(1)).root());
		System.out.println("Search 2... " + (tree.searchKey(2)).root());
		System.out.println("Search 3... " + (tree.searchKey(3)).root());
		System.out.println("Search 4... " + (tree.searchKey(4)).root());
		System.out.println("Search 5... " + (tree.searchKey(5)).root());
		System.out.println("Search 6... " + (tree.searchKey(6)).root());
		System.out.println("Search 7... " + (tree.searchKey(7)).root());

	}

}
