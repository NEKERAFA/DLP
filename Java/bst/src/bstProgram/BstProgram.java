/* 
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

package bstProgram;

import bst.Bst;
import bst.BstUtils;

public class BstProgram {
	
	private static void bracketPreorder(Bst a) {
		System.out.print("(");
		if (!BstUtils.isEmptyTree(a)) {
			if (!BstUtils.isEmptyTree(BstUtils.leftChild(a)) || !BstUtils.isEmptyTree(BstUtils.rightChild(a))) {
				System.out.print(" " + BstUtils.root(a) + " ");
				bracketPreorder(BstUtils.leftChild(a));
				System.out.print(" ");
				bracketPreorder(BstUtils.rightChild(a));
			} else {
				System.out.print(" " + BstUtils.root(a) + " ");
			}
		}
		System.out.print(")");
	}
	
	public static void main(String[] args) {
		Bst tree = BstUtils.emptyTree();

		BstUtils.insertKey(tree, 4);
		BstUtils.insertKey(tree, 4);
		BstUtils.insertKey(tree, 2);
		BstUtils.insertKey(tree, 6);
		BstUtils.insertKey(tree, 1);
		BstUtils.insertKey(tree, 3);
		BstUtils.insertKey(tree, 5);
		BstUtils.insertKey(tree, 7);
		
		bracketPreorder(tree); 
		System.out.println();
		
		System.out.println("Search 1... " + BstUtils.root(BstUtils.searchKey(tree, 1)));
		System.out.println("Search 2... " + BstUtils.root(BstUtils.searchKey(tree, 2)));
		System.out.println("Search 3... " + BstUtils.root(BstUtils.searchKey(tree, 3)));
		System.out.println("Search 4... " + BstUtils.root(BstUtils.searchKey(tree, 4)));
		System.out.println("Search 5... " + BstUtils.root(BstUtils.searchKey(tree, 5)));
		System.out.println("Search 6... " + BstUtils.root(BstUtils.searchKey(tree, 6)));
		System.out.println("Search 7... " + BstUtils.root(BstUtils.searchKey(tree, 7)));

		System.out.print("Delete 5...");
		BstUtils.deleteKey(tree, 5);
		bracketPreorder(tree); System.out.println();
		
		System.out.print("Delete 6...");
		BstUtils.deleteKey(tree, 6);
		bracketPreorder(tree);
		System.out.println();
		
		System.out.print("Delete 4...");
		BstUtils.deleteKey(tree, 4);
		bracketPreorder(tree);
		System.out.println();
		
		System.out.print("Delete 2...");
		BstUtils.deleteKey(tree, 2);
		bracketPreorder(tree);
		System.out.println();

	}

}
