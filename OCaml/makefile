all: bst_program.out
	./bst_program.out

bst.cmi: bst.mli
	rm -f -v bst.cmi
	ocamlc -c bst.mli

bst.cmx: bst.cmi bst.ml
	rm -f -v bst.cmx
	ocamlopt -c -w -a bst.ml

bst_program.cmx: bst_program.ml
	rm -f -v bst_program.cmx
	ocamlopt -c -w -a bst_program.ml

bst_program.out: bst.cmx bst_program.cmx
	rm -f -v bst_program.out
	ocamlopt -o bst_program.out bst.cmx bst_program.cmx

clean:
	rm -f -v *cmi
	rm -f -v *cmx
	rm -f -v *o
	rm -f -v *out
