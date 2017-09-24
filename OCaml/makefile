all: bst_program.out
	./bst_program.out

bst.cmi: bst.mli
	ocamlc -c bst.mli

bst.cmx: bst.cmi bst.ml
	ocamlopt -c -w -a bst.ml

bst_program.cmx: bst_program.ml
	ocamlopt -c -w -a bst_program.ml

bst_program.out: bst.cmx bst_program.cmx
	ocamlopt -o bst_program.out bst.cmx bst_program.cmx

clean:
	rm -f -v *cmi
	rm -f -v *cmx
	rm -f -v *o
	rm -f -v *out
