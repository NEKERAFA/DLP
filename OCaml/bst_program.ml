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

let tree = emptyTree ();;

insertKey tree 4;;
insertKey tree 4;;
insertKey tree 2;;
insertKey tree 6;;
insertKey tree 1;;
insertKey tree 3;;
insertKey tree 5;;
insertKey tree 7;;

bracketPreorder tree;; print_string "\n";;

print_string ("Search 1... " ^ (string_of_int (root (searchKey tree 1))) ^ "\n");;
print_string ("Search 2... " ^ (string_of_int (root (searchKey tree 2))) ^ "\n");;
print_string ("Search 3... " ^ (string_of_int (root (searchKey tree 3))) ^ "\n");;
print_string ("Search 4... " ^ (string_of_int (root (searchKey tree 4))) ^ "\n");;
print_string ("Search 5... " ^ (string_of_int (root (searchKey tree 5))) ^ "\n");;
print_string ("Search 6... " ^ (string_of_int (root (searchKey tree 6))) ^ "\n");;
print_string ("Search 7... " ^ (string_of_int (root (searchKey tree 7))) ^ "\n");;


print_string "Delete 5...";
deleteKey tree 5;;
bracketPreorder tree;; print_string "\n";;

print_string "Delete 6...";
deleteKey tree 6;;
bracketPreorder tree;; print_string "\n";;

print_string "Delete 4...";
deleteKey tree 4;;
bracketPreorder tree;; print_string "\n";;

print_string "Delete 2...";
deleteKey tree 2;;
bracketPreorder tree;; print_string "\n";;
