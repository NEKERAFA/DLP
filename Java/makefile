# Rafael Alcalde Azpiazu: rafael.alcalde.azpiazu (rafael.alcalde.azpiazu@udc.es)
# Eva Suárez García: eva.suarez.garcia (eva.suarez.garcia@udc.es)

all: BstProgram.jar
	java -jar ./BstProgram.jar

BstProgram.jar: build/bst/Bst.class build/bst/Node.class build/bst/BstUtils.class build/bstProgram/BstProgram.class
	echo 'Manifest-Version: 1.0\nMain-Class: bstProgram.BstProgram\n' > manifest.txt
	rm -f -v BstProgram.jar
	jar cvfm BstProgram.jar manifest.txt -C build/ .

build/bst/Bst.class: bst/Bst.java
	mkdir -v -p build/bst
	rm -f -v $@
	javac -d build $<

build/bst/BstUtils.class: bst/BstUtils.java
	mkdir -v -p build/bst
	rm -f -v $@
	javac -d build $<

build/bst/Node.class: bst/Node.java
	mkdir -v -p build/bst
	rm -f -v $@
	javac -d build $<

build/bstProgram/BstProgram.class: bstProgram/BstProgram.java
	rm -f -v $@
	javac -d build $<

clean:
	rm -f -v -r build
	rm -f -v manifest.txt
	rm -f -v BstProgram.jar
