# Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
# Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)

all: bst_program.out
	./bst_program.out

bst.o: bst.c bst.h
	rm -f -v bst.o
	gcc -c bst.c

bst_program.o: bst_program.c
	rm -f -v bst_program.o
	gcc -c bst_program.c

bst_program.out: bst.o bst_program.o
	rm -f -v bst_program.out
	gcc -g -o bst_program.out bst.o bst_program.o

clean:
	rm -f -v *.o
	rm -f -v *.out
