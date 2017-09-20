type bst =
	 Empty
	|Node of int * bst * bst;;

val emptyTree: bst
val insertKey: int -> bst -> bst
val leftChild: bst -> bst
val rightChild: bst -> bst
val root: bst -> int
val isEmptyTree: bst -> boolean
val searchKey: int -> bst -> bst
val deleteKey: bst -> int -> bst
