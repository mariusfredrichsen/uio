# Windows Makefile for Assignment1 (made by gpt)

# Classpath (use ; as separator for Windows)
CP = .;.\lib\esper-common-8.2.0.jar;.\lib\esper-runtime-8.2.0.jar;.\lib\esper-compiler-8.2.0.jar;.\lib\slf4j-api-1.7.25.jar;.\lib\slf4j-jdk14-1.7.25.jar;.\lib\antlr4-runtime-4.7.1.jar;.\lib\janino-3.0.10.jar;.\lib\commons-compiler-3.0.10.jar

# Compile all Java files
all:
	javac -classpath $(CP) WeatherTuple.java
	javac -classpath $(CP) Assignment1.java

# Run commands
run1: all
	java -classpath $(CP) Assignment1 query_1.epl

run2: all
	java -classpath $(CP) Assignment1 query_2.epl

run3: all
	java -classpath $(CP) Assignment1 query_3.epl

run4: all
	java -classpath $(CP) Assignment1 query_4.epl

run6: all
	java -classpath $(CP) Assignment1 query_6.epl

run7: all
	java -classpath $(CP) Assignment1 query_7.epl

run8: all
	java -classpath $(CP) Assignment1 query_8.epl

# Clean compiled class files
clean:
	del *.class
