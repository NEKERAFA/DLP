'''
	Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
	Eva Suarez Garcia: eva.suarez.garcia (eva.suarez.garcia@udc.es)
'''

################################################################################

def createNodeT(): # TODO originalmente createNode solo asignaba memoria, y aqui no puede hacerse asi que no parece fiel a Pascal
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
        # TODO para que tienes createNode si luego aqui lo haces a mano
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
# Insert recursive TODO no se si tiene mucho sentido poner comentarios de cierre

def insertI(tree, key):
	# Create new node TODO lo dicho en la definicion de la funcion
	newNode = createNodeT()
	newNode['key'] = key

	# TODO no se puede hacer la asignacion tal cual?
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
# Insert iterative

################################################################################

def insertKey(tree, key):
	insertI(tree, key)
	#insertR(tree, key)

################################################################################

def searchR(node, key):
	if node == {}:
		return None
	elif (key == node['key']):
		return node
	elif (key < node['key']):
		return searchR(node['left'], key)
	else:
		return searchR(node['right'], key)

################################################################################

def search_i(tree, key):
	node = tree

	while node != {} and node['key'] != key:
		if key < node['key']:
			node = node['left']
		else:
			node = node['right']

	return node

################################################################################

def searchKey(tree, key):
	return searchR(tree, key)
	#return search_i(tree, key)

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
			# If left subtree is empty, remove all keys in subtree
            # TODO no entiendo para que esta comprobacion
			if node['left'] == {}:
				del node['key']
				del node['left']
				del node['right']
			# If left subtree isn't node, replace all keys by left subtree keys
			else: # TODO wtf Rafa si llegamos aqui es porque node['right'] == {}
                  # que pinta esta asignacion aqui entonces?
				node['key'] = node['right']['key']
				node['key'] = node['right']['key']
				node['key'] = node['right']['key']
	# delAux

	if tree != {}:
		if key < tree['key']:
			deleteR(tree['left'], key)
		elif key > tree['key']:
			deleteR(tree['right'], key)
		else:
			aux = tree
			# Delete node with at most one child
			if tree['left'] == {}:
				# If right child is empty, delete all parent's keys
                # TODO por que esta asignacion si puedes hacer tree = tree['right']?
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
                # TODO mismo de arriba
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
				delAux(tree['left'])
# recursive delete

################################################################################

def deleteI(tree, key):
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
		numChildren = 0 # TODO por que no declararlo arriba como en Pascal?
		if rm['left'] != {}:
			numChildren = numChildren+1
		if rm['right'] != {}:
			numChildren = numChildren+1

		# Remove leaf node
		if numChildren == 0:
			if parentRm is None:
			# Remove all key in tree
            # TODO por que no tree = {}?
				del tree['key']
				del tree['left']
				del tree['right']
			elif parentRm['left'] == rm:
				parentRm['left'] = {}
			else:
				parentRm['right'] = {}

		# Remove node with one child
		elif numChildren == 1:
			nonEmptyChild = None # TODO por que no lo defines arriba como en Pascal

			if rm['left'] == {}:
				nonEmptyChild = rm['right']
			else:
				nonEmptyChild = rm['left']

			if parentRm is None:
				# TODO por que no tree = nonEmptyChild?
					tree['key'] = nonEmptyChild['key'] # TODO no esta mal indentado?
					tree['left'] = nonEmptyChild['left']
					tree['right']  = nonEmptyChild['right']
			elif parentRm['left'] == rm:
				parentRm['left']  = nonEmptyChild
			else:
				parentRm['right']  = nonEmptyChild

		# Remove node with two children
		elif numChildren == 2:
			parentRm = rm
			maxLeftChild = rm['left'] # TODO mismo que nonEmptyChild en declaracion

			# Search for the node with the greatest key of the left subtree
			while maxLeftChild['right'] != {}:
				parentRm = maxLeftChild
				maxLeftChild = maxLeftChild['left'] # TODO esto esta mal, es el derecho .-.

			rm['key'] = maxLeftChild['key']
			if parentRm == rm:
				parentRm['left'] = maxLeftChild['left']
			else:
				parentRm['right'] = maxLeftChild['left']
# Iterative delete

################################################################################

def deleteKey(tree, key):
	#deleteR(tree, key)
	deleteI(tree, key)
