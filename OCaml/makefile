bst.cmi: bst.mli
	ocamlc -c bst.mli

bst.cmx: bst.cmi bst.ml
	ocamlopt -c bst.ml

bst_program.cmx: bst_program.ml
	ocamlopt -c bst_program.ml

bst_program.out: bst.cmx bst_program.cmx
	ocamlopt -o bst_program.out bst.cmx bst_program.cmx

make: bst_program.out

clear:
	rm -f -v *cmi
	rm -f -v *cmx
	rm -f -v *out
