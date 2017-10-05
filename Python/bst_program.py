'''
	Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
	Eva Suarez Garcia: eva.suarez.garcia (eva.suarez.garcia@udc.es)
'''

import bst

def bracketPreorder(T):
	print("(", end = "")
	if not bst.isEmptyTree(T):
		if not bst.isEmptyTree(bst.leftChild(T)) or not bst.isEmptyTree(bst.rightChild(T)):
			print(" " + str(bst.root(T)) + " ", end = "")
			bracketPreorder(bst.leftChild(T))
			print(" ", end = "")
			bracketPreorder(bst.rightChild(T))
		else:
			print(" " + str(bst.root(T)) + " ", end = "")
	print(")", end = "")

tree = bst.emptyTree()

#################################
print("Empty tree: " + str(bst.isEmptyTree(tree)))
#################################

bst.insertKey(tree, 4)
#################################
print("Empty tree: " + str(bst.isEmptyTree(tree)))
#################################
bst.insertKey(tree, 4)
bst.insertKey(tree, 2)
bst.insertKey(tree, 6)
bst.insertKey(tree, 1)
bst.insertKey(tree, 3)
bst.insertKey(tree, 5)
bst.insertKey(tree, 7)

bracketPreorder(tree); print()
'''
print("Search 1... " + str(bst.root(bst.searchKey(tree, 1))))
print("Search 2... " + str(bst.root(bst.searchKey(tree, 2))))
print("Search 3... " + str(bst.root(bst.searchKey(tree, 3))))
print("Search 4... " + str(bst.root(bst.searchKey(tree, 4))))
print("Search 5... " + str(bst.root(bst.searchKey(tree, 5))))
print("Search 6... " + str(bst.root(bst.searchKey(tree, 6))))
print("Search 7... " + str(bst.root(bst.searchKey(tree, 7))))
'''
'''
print('Delete 5...', end = " ") por que end = " "?
bst.deleteKey(tree, 5)
bracketPreorder(tree); print()

print('Delete 6...', end = " ")
bst.deleteKey(tree, 6)
bracketPreorder(tree); print()

print('Delete 4...', end = " ")
bst.deleteKey(tree, 4)
bracketPreorder(tree); print()

print('Delete 2...', end = " ")
bst.deleteKey(tree, 2)
bracketPreorder(tree); print()
'''
###################################
'''
print('Delete 5...', end = " ")
bst.deleteKey(tree, 5)
bracketPreorder(tree); print()

print('Delete 5 again...', end = " ")
bst.deleteKey(tree, 5)
bracketPreorder(tree); print()

print('Delete 6...', end = " ")
bst.deleteKey(tree, 6)
bracketPreorder(tree); print()

print('Delete 2...', end = " ")
bst.deleteKey(tree, 2)
bracketPreorder(tree); print()

print('Delete 4...', end = " ")
bst.deleteKey(tree, 4)
bracketPreorder(tree); print()

print('Delete 3...', end = " ")
bst.deleteKey(tree, 3)
bracketPreorder(tree); print()

print('Delete 1...', end = " ")
bst.deleteKey(tree, 1)
bracketPreorder(tree); print()

print('Delete 7...', end = " ")
bst.deleteKey(tree, 7)
bracketPreorder(tree); print()

print("Empty tree: " + str(bst.isEmptyTree(tree)))
'''
###################################
