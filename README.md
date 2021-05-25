# junit
My junit code following a book called Junit in Action 2nd Edition

To compile java source files from command line 
>javac -classpath b -sourcepath b b/Other.java

Then run the compiled code with
>java -cp b Calculator 

Compile multiple files
>javac b/*.java

Compile to a particular destination that already exists
>javac b/*.java -d b/out

Specify multiple classpaths including junit
>javac -cp /opt:. CalculatorTest.java

Generating instrumented classes
>javac -cp /opt:. *.java -d uninstrumented
>cobertura-instrument --destination instrumented uninstrumented/Calculator.class

Running instrumented classes
>java -cp /opt/junit4.jar:/opt/slf4j-simple.jar:/opt/slf4j-api-2.jar:/opt/cobertura.jar:/opt/hamcrest.jar:instrumented:uninstrumented:-Dnet.sourceforge.cobertura.datafile=cobertura.ser org.junit.runner.JUnitCore CalculatorTest

Generating cobertura reports 
> cobertura-report --format html --datafile cobertura.ser --destination reports src


# Advice
That illustrates a key concept: Require objects, don’t search for objects, and ask only for objects
that your application requires. Page 74
