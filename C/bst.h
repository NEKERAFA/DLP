/*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 */

#include <stdbool.h>

#ifndef _H_BST_
#define _H_BST_

/** Interface **/
/**********************************************************************/

typedef int tKey;

struct tNodeT {
	tKey key;
	struct tNodeT *left;
	struct tNodeT *right;
};

typedef struct tNodeT tNodeT;

typedef tNodeT *tPosT;

typedef tPosT tBST;

/**********************************************************************/

void emptyTree(tBST *T);

tBST leftChild(tBST T);

tBST rightChild(tBST T);

tKey root(tBST T);

bool isEmptyTree(tBST T);

void insertKey(tBST *T, tKey key);

tBST searchKey(tBST T, tKey key);

void deleteKey(tBST *T, tKey key);

#endif // _H_BST_
