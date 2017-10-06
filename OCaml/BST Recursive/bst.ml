(*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 *)

type bst =
    Empty
    |Node of int * bst * bst;;

let emptyTree = Empty;;

let leftChild = function
    Node(_,left,_) -> left;;

let rightChild = function
    Node(_,_,right) -> right;;

let root = function
    Node(key,_,_) -> key;;

let isEmptyTree = function
    Empty -> true
    |_ -> false;;

(* Returns the node to insert when the tree is empty.
   If the key already existed, returns the node containing it.
   Otherwise, returns the current node with one of its children
   being the result of the recursive call *)
let rec insertKey k = function
    Empty -> Node(k,Empty,Empty)
    |Node(key,left,right) -> if k < key then
                                Node(key,insertKey k left, right)
                             else if k > key then
                                Node(key,left,insertKey k right)
                             else Node(key,left,right);;

let rec searchKey k = function
    Empty -> Empty
    |Node(key,left,right) -> if k = key then
                                Node(key,left,right)
                             else if k < key then
                                searchKey k left
                             else searchKey k right;;

let rec deleteKey key tree =
(* This function is first called only when 'node' has 2 children, and
   recursive calls only happen if this argument is going to be non empty,
   so we don't need to check if 'node' is empty before calling rightChild *)
    let rec delAux node parentNode =
        match (rightChild node) with
            Node(key,left,right) -> let (foundK,newRight) = delAux (rightChild node) node
                                    in foundK, Node(root parentNode,leftChild parentNode,newRight)
            |Empty -> root node, leftChild node
    in match tree with
        Empty -> Empty (* The key was not in the tree *)
        |Node(k,left,right) -> if key < k then
                                    Node(k,deleteKey key left,right)
                               else if key > k then
                                    Node(k,left,deleteKey key right)
                               (* From this point on we have found the node with the key to delete *)
                               else if left = Empty then
                                    right
                               else if right = Empty then
                                    left
                               else let (foundk,newleft) = delAux left left
                                    in Node(foundk,newleft,right);;
