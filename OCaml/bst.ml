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

let rec delete_r key = function
