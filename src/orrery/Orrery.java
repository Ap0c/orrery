package orrery;

// ----- Imports ----- //

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import java.io.FileNotFoundException;


// ----- Class ----- //

/**
 * The main class for the Orrery. Sets up the graphical environment and contains
 * the javafx start method.
 */
public class Orrery extends Application {

	// ----- Instance Variables ----- //

	private BorderPane layout;
	private Space space;
	private Info info;

	// ----- Instance Methods ----- //

	/**
	 * Sets up the window layout.
	 *
	 * @throws FileNotFoundException thrown if the data file is not found.
	 */
	private void buildLayout () throws FileNotFoundException {

		layout = new BorderPane();
		space = new Space();
		info = new Info(space);

		layout.setCenter(space.getSpace());
		layout.setRight(info.getInfo());

	}

	/**
	 * The javafx start method, the entry point for the javafx application.
	 * This is called when the application starts, and is passed the primary
	 * stage onto which the scene is set.
	 *
	 * @param primaryStage the primary stage for the application.
	 * @throws FileNotFoundException thrown if the data file is not found.
	 */
	@Override
	public void start (Stage primaryStage) throws FileNotFoundException {

		try {

			buildLayout();

			// Adds title and scene to stage.
			primaryStage.setTitle("Solar System");
			primaryStage.setScene(new Scene(layout,
				space.getWidth() + info.infoWidth(),
				space.getHeight(),
				Color.BLACK)
			);

			space.startMotion();
			primaryStage.show();

		} catch (FileNotFoundException e) {
			Platform.exit();
			System.out.println("Datafile not found.");
		}

	}

	// ----- Main ----- //

	public static void main(String[] args) {
		launch(args);
	}

}
