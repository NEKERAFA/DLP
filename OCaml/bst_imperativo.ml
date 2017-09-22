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

let insert_i a key =
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

let insertKey = insert_i;;

let search_i tree key =
    let node = ref !tree in
    while (!node <> Empty) && ((root node) <> key) do
        if key < (root node)
        then node := !(leftChild node)
        else node := !(rightChild node)
    done;
    node;;

let searchKey = search_i;;

let delete_i tree key =
	let rm = ref (!tree) in
	let parentRm = ref Empty in
	let notEmptyChild = ref Empty in
	let maxLeftChild = ref Empty in

	(* Search for the node to remove *)
	while ((!rm <> Empty) && ((root rm) <> key)) do
		parentRm := !rm;
		if (key < (root rm)) then
			rm := !(leftChild rm)
		else
			rm := !(rightChild rm)
	done;

	(* If the key isn't in the tree, don't do anything *)
	if (!rm <> Empty) then
		let numChildren = ref 0 in
		if (!(leftChild rm) <> Empty) then
			numChildren := (!numChildren + 1);
		if (!(rightChild rm) <> Empty) then
			numChildren := (!numChildren + 1);

		match !numChildren with
		 	0 -> if (!parentRm = Empty) then
			     	tree := Empty
			     else if ((leftChild parentRm) = rm) then
				 	(leftChild parentRm) := Empty
				 else
				 	(rightChild parentRm) := Empty;
			| 1 -> if (!(leftChild rm) = Empty) then
			     	notEmptyChild := !(rightChild rm)
				 else
				 	notEmptyChild := !(leftChild rm);

				 if (!parentRm = Empty) then
				 	tree := !notEmptyChild
				 else if ((leftChild parentRm) = rm) then (*** TODO Comprobar si la comparación tiene que ser por contenido *)
				 	(leftChild parentRm) := !notEmptyChild
				 else
				 	(rightChild parentRm) := !notEmptyChild;
			| 2 -> parentRm := !rm;
			     maxLeftChild := !(leftChild rm);
				 while (!(rightChild maxLeftChild) <> Empty) do
				 	parentRm := !maxLeftChild;
					maxLeftChild := !(rightChild maxLeftChild)
				 done;

				 rm := Node((root maxLeftChild), (leftChild rm), (rightChild rm));
				 if (!parentRm = !rm) then
				 	(leftChild parentRm) := !(leftChild maxLeftChild)
				 else
				 	(rightChild parentRm) := !(leftChild maxLeftChild);;
