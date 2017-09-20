/*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "bst.h"

/** Implementation **/
/**********************************************************************/

void error(char *s) {
	printf("%s\n", s);
	exit(1);
}

void createNodeT(tPosT *p) {
	*p = malloc(sizeof(tNodeT));
	if (p == NULL) {
		error(" *** abb.createNoteT: Out of memory");
	}
}

void emptyTree(tBST *T) {
	*T = NULL;
}

void insertKey(tBST *T, tKey key) {
	printf("STUB function\n");
}

tBST leftChild(tBST T) {
	printf("STUB function\n");
	return NULL;
}

tBST rightChild(tBST T) {
	printf("STUB function\n");
	return NULL;
}

tKey root(tBST T) {
	printf("STUB function\n");
	return 0;
}

bool isEmptyTree(tBST T) {
	printf("STUB function\n");
	return false;
}

tBST searchKey(tBST T, tKey key) {
	printf("STUB function\n");
	return NULL;
}

void removeKey(tBST *T, tKey key) {
	printf("STUB function\n");
}

