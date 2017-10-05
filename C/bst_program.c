/*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

#include <stdio.h>
#include <stdlib.h>
#include "bst.h"

int main() {
	tBST tree;

	void bracketPreorder(tBST T) {
		printf("(");
		if (!isEmptyTree(T)) {
			if (!isEmptyTree(leftChild(T)) || !isEmptyTree(rightChild(T))) {
				printf(" %d ", root(T));
				bracketPreorder(leftChild(T));
				printf(" ");
				bracketPreorder(rightChild(T));
			} else {
				printf(" %d ", root(T));
			}
		}
		printf(")");
	}

	emptyTree(&tree);

    //-----------------------------
    printf("Empty tree: %d\n", isEmptyTree(tree));
    //-----------------------------

	insertKey(&tree, 4);
    //-----------------------------
    printf("Empty tree: %d\n", isEmptyTree(tree));
    //-----------------------------
	insertKey(&tree, 4);
	insertKey(&tree, 2);
	insertKey(&tree, 6);
	insertKey(&tree, 1);
	insertKey(&tree, 3);
	insertKey(&tree, 5);
	insertKey(&tree, 7);

	bracketPreorder(tree); printf("\n");

	printf("Search 1... %d\n", root(searchKey(tree, 1)));
	printf("Search 2... %d\n", root(searchKey(tree, 2)));
	printf("Search 3... %d\n", root(searchKey(tree, 3)));
	printf("Search 4... %d\n", root(searchKey(tree, 4)));
	printf("Search 5... %d\n", root(searchKey(tree, 5)));
	printf("Search 6... %d\n", root(searchKey(tree, 6)));
	printf("Search 7... %d\n", root(searchKey(tree, 7)));

    /*
	printf("Delete 5...");
	deleteKey(&tree, 5);
	bracketPreorder(tree); printf("\n");

	printf("Delete 6...");
	deleteKey(&tree, 6);
	bracketPreorder(tree); printf("\n");

	printf("Delete 4...");
	deleteKey(&tree, 4);
	bracketPreorder(tree); printf("\n");

	printf("Delete 2...");
	deleteKey(&tree, 2);
	bracketPreorder(tree); printf("\n");*/

    //-----------------------------
    printf("Delete 5...");
	deleteKey(&tree, 5);
	bracketPreorder(tree); printf("\n");

    printf("Delete 5 again...");
	deleteKey(&tree, 5);
	bracketPreorder(tree); printf("\n");

    printf("Delete 6...");
    deleteKey(&tree, 6);
    bracketPreorder(tree); printf("\n");

    printf("Delete 2...");
	deleteKey(&tree, 2);
	bracketPreorder(tree); printf("\n");

    printf("Delete 4...");
	deleteKey(&tree, 4);
	bracketPreorder(tree); printf("\n");

    printf("Delete 3...");
	deleteKey(&tree, 3);
	bracketPreorder(tree); printf("\n");

    printf("Delete 1...");
	deleteKey(&tree, 1);
	bracketPreorder(tree); printf("\n");

    printf("Delete 7...");
	deleteKey(&tree, 7);
	bracketPreorder(tree); printf("\n");

    printf("Empty tree: %d\n", isEmptyTree(tree));
    //-----------------------------

	return 0;
}
