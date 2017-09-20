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
	
	//bracketPreorder(tree); printf("\n");
	
	free(tree);
	
	return 0;
}
