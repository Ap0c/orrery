// ----- Imports ----- //

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import java.io.FileNotFoundException;


// ----- Class ----- //

public class Orrery extends Application {

	private BorderPane layout;
	private Space space;
	private Info info;

	private void buildLayout () throws FileNotFoundException {

		layout = new BorderPane();
		space = new Space();
		info = new Info(space);

		layout.setCenter(space.getSpace());
		layout.setRight(info.getInfo());

	}

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

	public static void main(String[] args) {
		launch(args);
	}

}
