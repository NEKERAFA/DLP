(*
 * Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
 * Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)
 *)

open Bst;;

let rec bracketPreorder t =
	print_char '(';
	if not (isEmptyTree t) then begin
 		if ((not (isEmptyTree (leftChild t))) || ((not (isEmptyTree (rightChild t))))) then begin
			print_string (" " ^ (string_of_int (root t)) ^ " ");
			bracketPreorder (leftChild t);
			print_string " ";
			bracketPreorder (rightChild t)
		end
	else
 		print_string (" " ^ (string_of_int (root t)) ^ " ")
	end;
	print_char ')';;

let tree = emptyTree;;

let tree = insertKey 4 tree;;
let tree = insertKey 4 tree;;
let tree = insertKey 2 tree;;
let tree = insertKey 6 tree;;
let tree = insertKey 1 tree;;
let tree = insertKey 3 tree;;
let tree = insertKey 5 tree;;
let tree = insertKey 7 tree;;

bracketPreorder tree;; print_string "\n";;

print_string ("Search 1... " ^ (string_of_int (root (searchKey 1 tree))) ^ "\n");;
print_string ("Search 2... " ^ (string_of_int (root (searchKey 2 tree))) ^ "\n");;
print_string ("Search 3... " ^ (string_of_int (root (searchKey 3 tree))) ^ "\n");;
print_string ("Search 4... " ^ (string_of_int (root (searchKey 4 tree))) ^ "\n");;
print_string ("Search 5... " ^ (string_of_int (root (searchKey 5 tree))) ^ "\n");;
print_string ("Search 6... " ^ (string_of_int (root (searchKey 6 tree))) ^ "\n");;
print_string ("Search 7... " ^ (string_of_int (root (searchKey 7 tree))) ^ "\n");;

print_string "Delete 5...";;
let tree = deleteKey 5 tree;;
bracketPreorder tree;; print_string "\n";;

print_string "Delete 6...";;
let tree = deleteKey 6 tree;;
bracketPreorder tree;; print_string "\n";;

print_string "Delete 4...";;
let tree = deleteKey 4 tree;;
bracketPreorder tree;; print_string "\n";;

print_string "Delete 2...";;
let tree = deleteKey 2 tree;;
bracketPreorder tree;; print_string "\n";;
