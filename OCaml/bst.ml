(*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 *)

type bst =
    Empty
    |Node of int * bst * bst;; (* Esto no debería estar aquí *)

let emptyTree = Empty;;

(* Si el arbol de entrada esta vacio, peta *)
let leftChild = function
    Node(_,left,_) -> left;;

let rightChild = function
    Node(_,_,right) -> right;;

let root = function
    Node(key,_,_) -> key;;

let isEmptyTree = function
    Empty -> true
    |_ -> false;;

(* La función devuelve el nodo a insertar cuando el árbol está vacío.
   Si no está vacío y la clave coincide, devuelve el propio nodo.
   Cuando no está vacío y la clave no coincide, devuelve el nodo
   modificado para que el subárbol correspondiente sea el resultado
   de la llamada recursiva *)
let rec insertKeyR k = function
    Empty -> Node(k,Empty,Empty)
    |Node(key,left,right) -> if k < key
                             then Node(key,insertKeyR k left, right)
                             else if k > key
                                  then Node(key,left,insertKeyR k right)
                                  else Node(key,left,right);; (* Duplicates are ignored *)

let insertKey key tree =
    insertKeyR key tree;;

let rec searchKeyR k = function
    Empty -> Empty
    |Node(key,left,right) -> if k = key
                             then Node(key,left,right)
                             else if k < key
                                then searchKeyR k left
                                else searchKeyR k right;;

let searchKey key tree =
    searchKeyR key tree;;

let rec delete_r key tree = (* El segundo argumento es el nodo padre *)
    let rec del_aux node parentNode =
        match (rightChild node) with
        (* Sabemos que node nunca va a ser vacío: en la primera llamada porque
        solo se llama a esta funcion si el nodo tiene dos hijos, y en las
        llamadas recursivas porque la llamada solo se hace si el hijo derecho no es vacio *)
            Node(key,left,right) -> let (foundK,newRight) = del_aux (rightChild node) node
                                    in foundK, Node(root parentNode,leftChild parentNode,newRight)
            |Empty -> root node, leftChild node
    in match tree with
        Empty -> Empty (* La clave no estaba en el árbol *)
        |Node(k,left,right) -> if key < k
                               then Node(k,delete_r key left,right)
                               else if key > k
                                    then Node(k,left,delete_r key right)
                                    else if left = Empty (* A partir de aquí ya hemos encontrado el nodo a eliminar (tree) *)
                                         then right
                                         else if right = Empty
                                            then left
                                            else let (foundk,newleft) = del_aux left left
                                                in Node(foundk,newleft,right);;

let deleteKey key tree =
	delete_r key tree;;
1
