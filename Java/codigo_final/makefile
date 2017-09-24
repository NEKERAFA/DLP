# Java files
JAVAFILES = \
	bst/Bst.java \
	bst/BstUtils.java \
	bst/Node.java \
	bstProgram/BstProgram.java

# Classes
CLASSES = \
	build/bst/Bst.class \
	build/bst/Node.class \
	build/bst/BstUtils.class \
	build/bstProgram/BstProgram.class

all: $(CLASSES)
	jar cvfm BstProgram.jar manifest -C build/ .
	java -jar ./BstProgram.jar

$(CLASSES): $(JAVAFILES)
	javac -d build $(JAVAFILES)

clean:
	rm -vfr build
	rm -vfr BstProgram.jar

create_dir:
	mkdir build
