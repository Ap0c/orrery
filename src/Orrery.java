// ----- Imports ----- //

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


// ----- Class ----- //

public class Orrery extends Application {

	@Override
	public void start (Stage primaryStage) {

		BorderPane border = new BorderPane();
		Space space = new Space();
		Info info = new Info();
		border.setCenter(space.getSpace());
		border.setRight(info.getInfo());

		// Circle oldEarth = planets.get("earth").getPlanet();
		// Circle newEarth = new Circle(oldEarth.getRadius(), oldEarth.getFill());
		// controls.getChildren().add(newEarth);

		// Adds title and scene to stage.
		primaryStage.setTitle("Solar System");
		primaryStage.setScene(new Scene(border,
			space.getWidth() + info.infoWidth(),
			space.getHeight(),
			Color.BLACK)
		);

		space.startMotion();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
