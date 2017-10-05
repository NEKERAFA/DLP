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

def insert_r(node, key):
	# Insert in empty tree
	if node == {}:
		node['key'] = key
		node['left'] = {}
		node['right'] = {}
	# Insert in left child
	elif key < node['key']:
		insert_r(node['left'], key)
	# Insert in right child
	elif key > node['key']:
		insert_r(node['right'], key)
	# Duplicates are ignored
# Insert recursive

def insert_i(tree, key):
	# Create new node
	newNode = createNodeT()
	newNode['key'] = key

	# If tree is empty, insert in
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
		# Duplicate are ignored
# Insert iterative

################################################################################

def insertKey(tree, key):
	insert_i(tree, key)
	#insert_r(tree, key)

################################################################################

def search_r(node, key):
	if node == {}:
		return None
	elif (key == node['key']):
		return node
	elif (key < node['key']):
		return search_r(node['left'], key)
	else:
		return search_r(node['right'], key)

################################################################################

def search_i(tree, key):
	node = tree

	# Search for key node
	while node != {} and node['key'] != key:
		if key < node['key']:
			node = node['left']
		else:
			node = node['right']

	return node

################################################################################

def searchKey(tree, key):
	return search_r(tree, key)
	#return search_i(tree, key)

################################################################################

def delete_r(tree, key):
	aux = {}

	def del_aux(node):
		'''
			When node to remove has two children, delete_r calls this function
			to replace it with greatest key node from left subtree.
		'''
		if node['right'] != {}:
			del_aux(node['right'])
		else:
			aux['key'] = node['key']
			# If left subtree is empty, remove all keys in subtree
			if node['left'] == {}:
				del node['key']
				del node['left']
				del node['right']
			# If left subtree isn't node, replace all keys by left subtree keys
			else:
				node['key'] = node['right']['key']
				node['key'] = node['right']['key']
				node['key'] = node['right']['key']
	# del_aux

	if tree != {}:
		if key < tree['key']:
			delete_r(tree['left'], key)
		elif key > tree['key']:
			delete_r(tree['right'], key)
		else:
			aux = tree
			# Delete node with at most one child
			if tree['left'] == {}:
				# If right child is empty, delete all parent's keys
				if tree['right'] == {}:
					del tree['key']
					del tree['left']
					del tree['right']
				# If right child isn't empty, replace all parent's keys
				else:
					tree['key'] = tree['right']['key']
					tree['left'] = tree['right']['left']
					tree['right'] = tree['right']['right']
			elif tree['right'] == {}:
				# If right child is empty, delete all parent's keys
				if tree['left'] == {}:
					del tree['key']
					del tree['left']
					del tree['right']
				# If right child isn't empty, replace all parent's keys
				else:
					tree['key'] = tree['left']['key']
					tree['right'] = tree['left']['right']
					tree['left'] = tree['left']['left']
			else:
				del_aux(tree['left'])
# recursive delete

################################################################################

def delete_i(tree, key):
	parentRm = None
	rm = tree

	# Search for the node to remove
	while rm != {} and rm['key'] != key:
		parentRm = rm

		if key < rm['key']:
			rm = rm['left']
		else:
			rm = rm['right']

	# If the key isn't in the tree, don't do anything
	if rm != {}:
		numChildren = 0
		if rm['left'] != {}:
			numChildren = numChildren+1
		if rm['right'] != {}:
			numChildren = numChildren+1

		# Remove leaf node
		if numChildren == 0:
			if parentRm is None:
			# Parent is the only node in tree
			# Remove all key in tree
				del tree['key']
				del tree['left']
				del tree['right']
			elif parentRm['left'] == rm:
				parentRm['left'] = {}
			else:
				parentRm['right'] = {}

		# Remove node with one child
		elif numChildren == 1:
			noEmptyChild = None
			# Get no empty child
			if rm['left'] == {}:
				noEmptyChild = rm['right']
			else:
				noEmptyChild = rm['left']

			if parentRm is None:
				# Parent is the only node in tree
				# Replace all key in tree
					tree['key'] = noEmptyChild['key']
					tree['left'] = noEmptyChild['left']
					tree['right']  = noEmptyChild['right']
			# Replace child
			elif parentRm['left'] == rm:
				parentRm['left']  = noEmptyChild
			else:
				parentRm['right']  = noEmptyChild

		# Remove node with two children
		elif numChildren == 2:
			parentRm = rm
			maxLeftChild = rm['left']

			# Get greatest key node from left subtree
			while maxLeftChild['right'] != {}:
				parentRm = maxLeftChild
				maxLeftChild = maxLeftChild['left']

			rm['key'] = maxLeftChild['key']
			if parentRm == rm:
				parentRm['left'] = maxLeftChild['left']
			else:
				parentRm['right'] = maxLeftChild['left']
# Iterative delete

################################################################################

def deleteKey(tree, key):
	#delete_r(tree, key)
	delete_i(tree, key)
