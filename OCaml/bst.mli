(*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 *)

type bst =
	 Empty
	|Node of int * bst * bst

val emptyTree: bst
val insertKey: int -> bst -> bst
val leftChild: bst -> bst
val rightChild: bst -> bst
val root: bst -> int
val isEmptyTree: bst -> bool
val searchKey: int -> bst -> bst
val deleteKey: int -> bst -> bst
