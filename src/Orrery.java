// ----- Imports ----- //

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

// ----- Class ----- //

public class Orrery extends Application {

	@Override
	public void start (Stage primaryStage) {

		// Creates a circle in the centre of the screen.
		Circle circle = new Circle(200, 200, 40, Color.ORANGE);

		// Sets up a group to hold the circle.
		Group root = new Group();
		root.getChildren().add(circle);

		// Adds title and scene to stage.
		primaryStage.setTitle("Solar System");
		primaryStage.setScene(new Scene(root, 400, 400, Color.BLACK));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
