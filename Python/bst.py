'''
	Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
	Eva Suarez Garcia: eva.suarez.garcia (eva.suarez.garcia@udc.es)
'''

################################################################################
def createNodeT():
	return {'key': None, 'left': {}, 'right': {}}

################################################################################

def isEmptyTree(tree):
	return tree == {}

################################################################################

def leftChild(tree):
	return tree['left']

################################################################################

def rightChild(tree):
	return tree['right']

################################################################################

def root(tree):
	return tree['key']

################################################################################

def emptyTree():
	return {}

################################################################################

def insertR(node, key):
	# Insert in empty tree
	if node == {}:
		node['key'] = key
		node['left'] = {}
		node['right'] = {}
	# Insert in left child
	elif key < node['key']:
		insertR(node['left'], key)
	# Insert in right child
	elif key > node['key']:
		insertR(node['right'], key)
	# Duplicates are ignored

################################################################################

def insertI(tree, key):
	newNode = createNodeT()
	newNode['key'] = key

	if tree == {}:
		tree['key'] = newNode['key']
		tree['right'] = newNode['right']
		tree['left'] = newNode['left']
	else:
		parent = {}
		child = tree

		# Search for the key's right place in the tree
		while (child != {}) and (child['key'] != key):
			parent = child
			if key < child['key']:
				child = child['left']
			else:
				child = child['right']

		# Insert new key in its place
		if child == {}:
			if key < parent['key']:
				parent['left'] = newNode
			else:
				parent['right'] = newNode
		# Duplicates are ignored

################################################################################

def insertKey(tree, key):
	#insertI(tree, key)
	insertR(tree, key)

################################################################################

def searchR(node, key):
	if node == {}:
		return {}
	elif (key == node['key']):
		return node
	elif (key < node['key']):
		return searchR(node['left'], key)
	else:
		return searchR(node['right'], key)

################################################################################

def searchI(tree, key):
	node = tree

	while node != {} and node['key'] != key:
		if key < node['key']:
			node = node['left']
		else:
			node = node['right']

	return node

################################################################################

def searchKey(tree, key):
	#return searchR(tree, key)
	return searchI(tree, key)

################################################################################

def deleteR(tree, key):
	aux = {}

    # When the node to remove has two children, deleteR calls this function
    # to replace it with the node with the greatest key from its left subtree.
	def delAux(node):
		if node['right'] != {}:
			delAux(node['right'])
		else:
			aux['key'] = node['key']
			# Replace 'node' with its left child
			if node['left'] == {}:
				del node['key']
				del node['left']
				del node['right']
			else:
				node['key'] = node['left']['key']
				node['right'] = node['left']['right']
				node['left'] = node['left']['left']

	if tree != {}:
		if key < tree['key']:
			deleteR(tree['left'], key)
		elif key > tree['key']:
			deleteR(tree['right'], key)
		else:
			aux = tree
			# Delete node with at most one child replacing it
			# with a non empty child
			if tree['left'] == {}:
				if tree['right'] == {}:
					del tree['key']
					del tree['left']
					del tree['right']
				else:
					tree['key'] = tree['right']['key']
					tree['left'] = tree['right']['left']
					tree['right'] = tree['right']['right']
			elif tree['right'] == {}:
				if tree['left'] == {}:
					del tree['key']
					del tree['left']
					del tree['right']
				else:
					tree['key'] = tree['left']['key']
					tree['right'] = tree['left']['right']
					tree['left'] = tree['left']['left']
			# Delete node with two children
			else:
				delAux(tree['left'])

################################################################################

def deleteI(tree, key):
	numChildren = 0
	rm = tree 				# Node to remove
	parentRm = None			# Parent of the node to remove
	nonEmptyChild = None	# If the node to remove has only one child, the one that isn't empty
	maxLeftChild = None	# If the node to remove has two children, the node with the greatest key of the left subtree

	# Search for the node to remove
	while rm != {} and rm['key'] != key:
		parentRm = rm

		if key < rm['key']:
			rm = rm['left']
		else:
			rm = rm['right']

	# If the key isn't in the tree, don't do anything
	if rm != {}:
		if rm['left'] != {}:
			numChildren = numChildren+1
		if rm['right'] != {}:
			numChildren = numChildren+1

		# Remove leaf node
		if numChildren == 0:
			if parentRm is None:
			# The root was the only node in the tree
				del tree['key']
				del tree['left']
				del tree['right']
			elif parentRm['left'] == rm:
				parentRm['left'] = {}
			else:
				parentRm['right'] = {}

		# Remove node with one child
		elif numChildren == 1:
			if rm['left'] == {}:
				nonEmptyChild = rm['right']
			else:
				nonEmptyChild = rm['left']

			if parentRm is None:
				tree['key'] = nonEmptyChild['key']
				tree['left'] = nonEmptyChild['left']
				tree['right']  = nonEmptyChild['right']
			elif parentRm['left'] == rm:
				parentRm['left']  = nonEmptyChild
			else:
				parentRm['right']  = nonEmptyChild

		# Remove node with two children
		elif numChildren == 2:
			parentRm = rm
			maxLeftChild = rm['left']

			# Search for the node with the greatest key of the left subtree
			while maxLeftChild['right'] != {}:
				parentRm = maxLeftChild
				maxLeftChild = maxLeftChild['right']

			rm['key'] = maxLeftChild['key']
			if parentRm == rm:
				parentRm['left'] = maxLeftChild['left']
			else:
				parentRm['right'] = maxLeftChild['left']

################################################################################

def deleteKey(tree, key):
	deleteR(tree, key)
	#deleteI(tree, key)
