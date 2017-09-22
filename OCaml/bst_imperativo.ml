(*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 *)

type bst = Empty | Node of int * bst ref * bst ref;; (* Esto no debería estar aquí *)

let emptyTree () = ref Empty;;

(* Si el arbol de entrada esta vacio, peta *)
let leftChild a = match !a with
	Node(_,left,_) -> left;;

let rightChild a = match !a with
    Node(_,_,right) -> right;;

let root a = match !a with
    Node(key,_,_) -> key;;

let isEmptyTree a = match !a with
 	Empty -> true
	| _ -> false;;
