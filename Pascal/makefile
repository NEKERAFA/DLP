all: programa_abb.out
	./programa_abb.out

programa_abb.out: abb.pas programa_abb.pas
	fpc -oprograma_abb.out programa_abb.pas

clean:
	rm -f -v *ppu
	rm -f -v *o
	rm -f -v *out
