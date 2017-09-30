'''
	Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
	Eva Suarez Garcia: eva.suarez.garcia (eva.suarez.garcia@udc.es)
'''

################################################################################

def isEmptyTree(T):
	return T['node'] is None

################################################################################

def leftChild(T):
	return { 'node': T['node']['left'] }

################################################################################

def rightChild(T):
	return { 'node': T['node']['right'] }

################################################################################

def root(T):
	return T['node']['key']

################################################################################

def emptyTree():
	return { 'node': None }

################################################################################

def insert_r(node, T, key):
	# Insert in empty tree
	if node is None:
		# Insert root of the complete tree
		if T['node'] is None:
			T['node'] = { 'key': key, 'left': None, 'right': None }
		# Insert regular node
		elif key < T['node']['key']:
			T['node']['left'] = { 'key': key, 'left': None, 'right': None }
		else:
			T['node']['right'] = { 'key': key, 'left': None, 'right': None }
	# Insert in left child
	elif key < node['key']:
		aux = {'node': node}
		insert_r(node['left'], aux, key)
	# Insert in right child
	elif key > node['key']:
		aux = {'node': node}
		insert_r(node['right'], aux, key)
# Insert recursive

def insert_i(T, key):
	# Create new node
	newNode = { 'key': key, 'left': None, 'right': None }

	# If tree is empty, insert in
	if T['node'] is None:
		T['node'] = newNode
	else:
		parent = None
		child = T['node']

		# Search for the key's right place in the tree
		while (child is not None) and (child['key'] != key):
			parent = child
			if key < child['key']:
				child = child['left']
			else:
				child = child['right']

		# Insert new key in its place
		if child is None:
			if key < parent['key']:
				parent['left'] = new
			else:
				parent['right'] = new
		# Duplicate are ignored
# Insert iterative

################################################################################

def insertKey(T, key):
	#insert_i(T, key)
	insert_r(T['node'], T, key)

################################################################################

def search_r(node, key):
	result = {}

	if node is None:
		result['node'] = None
	elif (key == node['key']):
		result['node'] = node
	elif (key < node['key']):
		result = search_r(node['left'], key)
	else:
		result = search_r(node['right'], key)

	return result

################################################################################

def search_i(T, key):
	node = T['node']

	# Search for key node
	while node is not None and node['key'] != key:
		if key < node['key']:
			node = node['left']
		else:
			node = node['right']

	return { 'node': node }

################################################################################

def searchKey(T, key):
	#return search_r(T['node'], key)
	return search_i(T, key)

################################################################################

def delete_r(node, T, key):
	'''
		T: parent of the node to be deleted. Used to reorder the children of
		the node to be deleted.
	'''
	def del_aux(rm, parentNode, node):
		'''
			rm: node with the key to delete
			parentNode: parent of the node that will replace del
			This method replaces the node to remove with the node with the
			greatest key from its left subtree.

			We need the parent node instead of the node itself in order to take
			the children of the node that will replace del and put them as
			children of parentNode
		'''
		if node['right'] is not None:
			del_aux(rm, node, node['right'])
		else:
			rm['key'] = node['key']
			parentNode['right'] = node['left']

	if node is not None:
		# Create auxiliar tree
		aux = { 'node': node }

		if key < node['key']:
			delete_r(node['left'], aux, key)
		elif key > node['key']:
			delete_r(node['right'], aux, key)
		else:
			# Delete node with at most one child
			if node['left'] is None:
				# Delete root of the total tree
				if T['node'] is node:
					T['root'] = node['right']
				# Check if node was a right or a left child
				elif T['node']['left'] is node:
					T['node']['left'] = node['right']
				else:
				 	T['node']['right'] = node['right']
			elif node['right'] is None:
				# Delete root of the total tree
				if T['node'] is node:
					T['root'] = node['left']
				# Check if node was a right or a left child
				elif T['node']['left'] is node:
					T['node']['left'] = node['left']
				else:
				 	T['node']['right'] = node['left']
			else:
				del_aux(node, node, node['left'])
# recursive delete

def deleteKey(T, key):
	delete_r(T['node'], T, key)
