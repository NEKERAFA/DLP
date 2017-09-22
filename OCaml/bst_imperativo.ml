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

let insertKeyI a key =
	let newNode = ref (Node (key, (ref Empty), (ref Empty))) in
	(* Insert in empty tree *)
	if (!a = Empty) then
		a := !newNode
	else
		let parent = ref Empty in
		let child = ref (!a) in
		(* Search for the key's right place in the tree *)
		while (((!child) <> Empty) && ((root child) <> key)) do
			parent := !child;
			if (key < (root child)) then
				child := !(leftChild child)
			else
				child := !(rightChild child)
		done;

		if (!child = Empty) then
			if (key < (root parent)) then
				(leftChild parent) := !newNode
			else
				(rightChild parent) := !newNode;;
