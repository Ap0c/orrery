# Graphics

An orrery implemented in Java, using JavaFX for graphics. For more information read the [Overview](http://ap0c.github.io/orrery), or the documentation found in the `docs` directory (start with `index.html`).

## Build and Run

Apache Ant is used to build and run this code, simply type `ant run` in the root directory to run it.

The run command depends on compile, so will build these if they are missing or any changes are made, or can alternatively be run via `ant compile`.

Also available are `ant clean` to delete the build files, and `ant doc` to generate documentation using javadoc.

## File Structure

The source code is located within the `src` directory, within the `orrery` package. The docs are in the `doc` directory and the compiled code is in the `bin` directory. Also present is the `planetdata.txt` file, which contains all the data about the planets and is read in at runtime.