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

let rec insert_r a key =
	match !a with
		Empty -> a := Node(key, ref Empty, ref Empty)
		| Node(k,left,right) -> if (key < k)
								then insert_r left key
				 				else if (key > k)
								then insert_r right key;; (* Duplicates are ignored *)

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

let insertKey = insert_r;;

let rec search_r a k =
	match !a with
		Empty -> ref Empty
		| Node(key,left,right) -> if (k = key)
								  then ref (Node(key,left,right))
								  else if (k < key)
								  	then search_r left k
								  	else search_r right k;;

let search_i tree key =
    let node = ref !tree in
    while (!node <> Empty) && ((root node) <> key) do
        if key < (root node)
        then node := !(leftChild node)
        else node := !(rightChild node)
    done;
    node;;

let searchKey = search_r;;

let rec delete_r tree key =
	let rec del_aux node parentNode =
	(* Sabemos que node nunca va a ser vacío: en la primera llamada porque
	solo se llama a esta funcion si el nodo tiene dos hijos, y en las
	llamadas recursivas porque la llamada solo se hace si el hijo derecho no es vacio *)
		match !(rightChild node) with
			Node(key,left,right) -> del_aux (rightChild node) node
			|Empty -> tree := Node(root node, leftChild tree, rightChild tree);
                      (rightChild parentNode) := !(leftChild node)
	in match !tree with
	 	Empty -> () (* La clave no estaba en el árbol *)
		|Node(k,left,right) -> if key < k
                               then delete_r left key
                               else if key > k
                                    then delete_r right key
                                    else if !left = Empty (* A partir de aquí ya hemos encontrado el nodo a eliminar (tree) *)
                                         then tree := !right
                                         else if !right = Empty
                                            then tree := !left
                                            else del_aux left left;;

let delete_i tree key =
	let rm = ref (!tree) in
	let parentRm = ref Empty in
	let notEmptyChild = ref Empty in
	let maxLeftChild = ref Empty in
    let parentMaxLeftChild = ref Empty in (* Variable auxiliar nueva *)

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
			| 2 -> parentMaxLeftChild := !rm;
			     maxLeftChild := !(leftChild rm);
				 while (!(rightChild maxLeftChild) <> Empty) do
				 	parentMaxLeftChild := !maxLeftChild;
					maxLeftChild := !(rightChild maxLeftChild)
				 done;
                 (* Cambiamos el nodo desde el padre (cambiar directamente rm no funciona) *)
                 if (!parentRm = Empty) then
                    tree := Node(root maxLeftChild, leftChild rm, rightChild rm)
                 else if ((root rm) < (root parentRm)) then
				    (leftChild parentRm) := Node(root maxLeftChild, leftChild rm, rightChild rm)
                 else if ((root rm) > (root parentRm)) then
                    (rightChild parentRm) := Node(root maxLeftChild, leftChild rm, rightChild rm);

				 if (!parentMaxLeftChild = !rm) then
				 	(leftChild parentMaxLeftChild) := !(leftChild maxLeftChild)
				 else
				 	(rightChild parentMaxLeftChild) := !(leftChild maxLeftChild);;

let deleteKey = delete_i;;
