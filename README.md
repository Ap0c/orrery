# Graphics

An orrery implemented in Java, using JavaFX for graphics.

## Build and Run

Ant is used to build and run this code, simply type `ant run` in the root directory to run it. The run command depends on compile, so will build these if they are missing or any changes are made. Also available are:

```
ant compile
ant clean
ant doc
```

to compile the code separately, delete the .class files, or generate documentation using javadoc respectively.

## File Structure

The source code is located within the `src` directory, within the `orrery` package. The docs are in the `doc` directory and the compiled code is in the `bin` directory. Also present is the `planetdata.txt` file, which contains all the data about the planets and is read in at runtime.