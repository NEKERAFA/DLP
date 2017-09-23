(*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 *)

type bst
val emptyTree : unit -> bst ref
val leftChild : bst ref -> bst ref
val rightChild : bst ref -> bst ref
val root : bst ref -> int
val isEmptyTree : bst ref -> bool
val insertKey : bst ref -> int -> unit
val searchKey : bst ref -> int -> bst ref
val deleteKey : bst ref -> int -> unit
