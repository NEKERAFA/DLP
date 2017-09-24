all: bst_program.out
	./bst_program.out

bst.o: bst.c bst.h
	gcc -c bst.c

bst_program.o: bst_program.c
	gcc -c bst_program.c

bst_program.out: bst.o bst_program.o
	gcc -g -o bst_program.out bst.o bst_program.o

clean:
	rm -f -v *o
	rm -f -v *out
