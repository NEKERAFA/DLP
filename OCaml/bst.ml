(*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 *)

type bst = Empty | Node of int * bst ref * bst ref;;

let emptyTree () = ref Empty;;

let leftChild tree = match !tree with
    Node(_,left,_) -> left;;

let rightChild tree = match !tree with
    Node(_,_,right) -> right;;

let root tree = match !tree with
    Node(key,_,_) -> key;;

let isEmptyTree tree = match !tree with
 	Empty -> true
	| _ -> false;;

let rec insertR tree key =
	match !tree with
		Empty -> tree := Node(key, ref Empty, ref Empty)
		| Node(k,left,right) -> if (key < k) then
                                    insertR left key
				 				else if (key > k) then
                                    insertR right key;;
                                (* Duplicates are ignored *)

let insertI tree key =
	let newNode = ref (Node (key, (ref Empty), (ref Empty))) in
	(* Insert in empty tree *)
	if (!tree = Empty) then
		tree := !newNode
	else
		let parent = ref Empty in
		let child = ref (!tree) in
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

let insertKey =
	insertR;;
	(*insertI;;*)

let rec searchR tree k =
	match !tree with
		Empty -> ref Empty
		| Node(key,left,right) -> if (k = key) then
                                    ref (Node(key,left,right))
								  else if (k < key) then
                                    searchR left k
								  else
                                    searchR right k;;

let searchI tree key =
    let node = ref !tree in
    while (!node <> Empty) && ((root node) <> key) do
        if key < (root node) then
            node := !(leftChild node)
        else
            node := !(rightChild node)
    done;
    node;;

let searchKey =
	searchR;;
	(*searchI;;*)

let rec deleteR tree key =
    (* This function is first called only when 'node' has 2 children, and
       recursive calls only happen if this argument is going to be non empty,
       so we don't need to check if 'node' is empty before calling rightChild *)
    let rec delAux node parentNode =
		match !(rightChild node) with
			Node(key,left,right) -> delAux (rightChild node) node
			|Empty -> tree := Node(root node, leftChild tree, rightChild tree);
					  (* Check if the left subtree didn't have a right child *)
					  (*TODO comentar a Rafa si hace falta que !parentNode = !node no funciona*)
					  if (root parentNode) = (root node) then
					  	(leftChild parentNode) := !(leftChild node)
					  else
                      	(rightChild parentNode) := !(leftChild node)
	in match !tree with
	 	Empty -> () (* The key was not in the tree *)
		|Node(k,left,right) -> if key < k then
                                    deleteR left key
                               else if key > k then
                                    deleteR right key
                               (* From this point on we have found the node with the key to delete *)
                               else if !left = Empty then
                                    tree := !right
                               else if !right = Empty then
                                    tree := !left
                               else delAux left tree;; (*TODO cambiar en la memoria, antes se llamaba con left left*)

let deleteI tree key =
	let rm = ref (!tree) in
	let parentRm = ref Empty in
	let nonEmptyChild = ref Empty in
	let maxLeftChild = ref Empty in
    let parentMaxLeftChild = ref Empty in

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
			     	nonEmptyChild := !(rightChild rm)
				 else
				 	nonEmptyChild := !(leftChild rm);

				 if (!parentRm = Empty) then
				 	tree := !nonEmptyChild
				 else if (!(leftChild parentRm) = !rm) then
				 	(leftChild parentRm) := !nonEmptyChild
				 else
				 	(rightChild parentRm) := !nonEmptyChild;
			| 2 -> parentMaxLeftChild := !rm;
			     maxLeftChild := !(leftChild rm);
				 while (!(rightChild maxLeftChild) <> Empty) do
				 	parentMaxLeftChild := !maxLeftChild;
					maxLeftChild := !(rightChild maxLeftChild)
				 done;
                 (* The node is changed through its parent *)
                 if (!parentRm = Empty) then (* Change the root's key *)
                    tree := Node(root maxLeftChild, leftChild rm, rightChild rm)
                 (* Change a regular node's key *)
                 else if ((root rm) < (root parentRm)) then
				    (leftChild parentRm) := Node(root maxLeftChild, leftChild rm, rightChild rm)
                 else if ((root rm) > (root parentRm)) then
                    (rightChild parentRm) := Node(root maxLeftChild, leftChild rm, rightChild rm);

				 if (!parentMaxLeftChild = !rm) then
				 	(leftChild parentMaxLeftChild) := !(leftChild maxLeftChild)
				 else
				 	(rightChild parentMaxLeftChild) := !(leftChild maxLeftChild);;

let deleteKey =
	deleteR;;
	(*deleteI;;*)
