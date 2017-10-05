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

struct tNodeT {
	tKey key;
	struct tNodeT *left;
	struct tNodeT *right;
};

void error(char *s) {
	printf("%s\n", s);
	exit(1);
}

void createNodeT(tPosT *p) {
	*p = malloc(sizeof(tNodeT));
	if (p == NULL)
		error(" *** bst.createNoteT: Out of memory");
}

/**********************************************************************/

void emptyTree(tBST *T) {
	*T = NULL;
}

/**********************************************************************/

tBST leftChild(tBST T) {
	return T->left;
}

/**********************************************************************/

tBST rightChild(tBST T) {
	return T->right;
}

/**********************************************************************/

tKey root(tBST T) {
	return T->key;
}

/**********************************************************************/

bool isEmptyTree(tBST T) {
	return T == NULL;
}

/**********************************************************************/

void insertR(tBST *T, tKey key) {
    if (*T == NULL) {
        createNodeT(T);
        (*T)->key = key;
        (*T)->left = NULL;
        (*T)->right = NULL;
    } else if (key < (*T)->key) {
        insertR(&(*T)->left, key);
    } else if (key > (*T)->key) {
        insertR(&(*T)->right, key);
    }
    // Duplicates are ignored
}

/**********************************************************************/

void insertI(tBST *T, tKey key) {
	tBST new, parent, child;

	createNodeT(&new);
	new->key = key;
	new->left = NULL;
	new->right = NULL;

	if (*T == NULL) // Insert in empty tree
		*T = new;
	else {
		parent = NULL;
		child = *T;
		// Search for the key's right place in the tree
		while ((child != NULL) && (child->key != key)) {
			parent = child;
			if (key < child->key)
				child = child->left;
			else
				child = child->right;
		}
		// Insert new key in its place
		if (child == NULL)
			if (key < parent->key)
				parent->left = new;
			else
				parent->right = new;
		// Duplicates are ignored
	}
}

void insertKey(tBST *T, tKey key) {
	insertR(T, key);
	//insertI(T, key);
}

/**********************************************************************/

tPosT searchI(tBST *T, tKey key) {
    tPosT node = *T;
    while ((node != NULL) && (node->key != key)) {
        if (key < node->key) {
            node = node->left;
        } else {
            node = node->right;
        }
    }
    return node;
}

/**********************************************************************/

tPosT searchR(tBST T, tKey key) {
	if (T == NULL)
		return NULL;
	else if (T->key == key)
		return T;
	else if (key < T->key)
		return searchR(T->left, key);
	else
		return searchR(T->right, key);
}

tBST searchKey(tBST T, tKey key) {
	//return searchR(T, key);
	return searchI(&T, key);
}

/**********************************************************************/

void deleteR(tBST *T, tKey key) {
    tBST aux;

    void delAux(tBST *T) {
        if ((*T)->right != NULL) {
            delAux(&(*T)->right);
        } else {
            aux->key = (*T)->key;
            aux = *T;
            (*T) = (*T)->left;
        }
    }

    if ((*T) != NULL) {
        if (key < (*T)->key) {
            deleteR(&(*T)->left, key);
        } else if (key > (*T)->key) {
            deleteR(&(*T)->right, key);
        } else {
            aux = *T;
            if ((*T)->left == NULL) {
                (*T) = (*T)->right;
            } else if ((*T)->right == NULL) {
                (*T) = (*T)->left;
            } else {
                delAux(&(*T)->left);
            }
            free(aux);
        }
    }
}

/**********************************************************************/

void deleteI(tBST *T, tKey key) {
	int numChildren;
	tBST del; 			// Node to delete
	tBST parentDel;		// Parent of the node to delete
	tBST nonEmptyChild;	// If the node to delete has only one child, the one that isn't empty
	tBST maxLeftChild;	// If the node to delete has two children, the node with the greatest key in left subtree

	// Search for the node to delete
	parentDel = NULL;
	del = *T;
	while ((del != NULL) && (del->key != key)) {
		parentDel = del;
		if (key < del->key) // Move forward left child if the key is smaller than current node
			del = del->left;
		else del = del->right; // Move forward right child if the key is greater or equal to current node
	}

	// If key not found in T, do nothing
	if (del != NULL) {
		// Count children
		numChildren = 0;
		if (del->left != NULL)
			numChildren++;
		if (del->right != NULL)
			numChildren++;

		switch (numChildren) {
			// Delete leaf node
			case 0:
				if (parentDel == NULL)
					*T = NULL; // Root was the only node in the tree
				else if (parentDel->left == del)
					parentDel->left = NULL;
				else
					parentDel->right = NULL;
				break;
			// case 0

			// Delete node with one child
			case 1:
				if (del->left == NULL)
					nonEmptyChild = del->right;
				else
					nonEmptyChild = del->left;

				if (parentDel == NULL)
					*T = nonEmptyChild; // Delete root replaced it with only not empty child
				else if (parentDel->left == del)
					parentDel->left = nonEmptyChild;
				else
					parentDel->right = nonEmptyChild;
				break;
			// case 1

			// Delete node with two children
			case 2:
				parentDel = del;
				maxLeftChild = del->left;
				while (maxLeftChild->right != NULL) { // Search for the node with greatest key in left subtree
					parentDel = maxLeftChild;
					maxLeftChild = maxLeftChild->right;
				}

				del->key = maxLeftChild->key; // Up found node
				if (parentDel == del) // If left subtree haven't right child
					parentDel->left = maxLeftChild->left; // maxLeftChild will be deleted, so we hook up its children to parentDel
				else // If left subtree have right child, it will have to hook up to right child
					parentDel->right = maxLeftChild->left; // maxLeftChild will be deleted, so we hook up its children to parentDel

				del = maxLeftChild; // Delete maxLeftChild
				break;
			// case 2
		} // switch

		free(del);
	}
}

void deleteKey(tBST *T, tKey key) {
	deleteR(T, key);
	//deleteI(T, key);
}
