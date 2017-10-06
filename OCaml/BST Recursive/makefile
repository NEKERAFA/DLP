# Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
# Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)

all: bst_program.out
	ocamlrun bst_program.out

bst.cmi: bst.mli
	rm -f -v bst.cmi
	ocamlc -c bst.mli

bst.cmo: bst.cmi bst.ml
	rm -f -v bst.cmo
	ocamlc -c -w -a bst.ml

bst_program.cmo: bst_program.ml
	rm -f -v bst_program.cmo
	ocamlc -c -w -a bst_program.ml

bst_program.out: bst.cmo bst_program.cmo
	rm -f -v bst_program.out
	ocamlc -o bst_program.out bst.cmo bst_program.cmo

clean:
	rm -f -v *.cmi
	rm -f -v *.cmo
	rm -f -v *.o
	rm -f -v *.out
