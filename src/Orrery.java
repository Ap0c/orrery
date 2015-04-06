// ----- Imports ----- //

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.paint.Color;
import java.util.Map;
import java.util.HashMap;


// ----- Class ----- //

public class Orrery extends Application {

	private final double centreX = 200;
	private final double centreY = 200;
	private Group root;
	private Map<String, Planet> planets;

	private void buildSun () {

		Circle sun = new Circle(centreX, centreY, 40.0, Color.ORANGE);
		root.getChildren().add(sun);

	}

	private void addPlanets() {

		Planet earth = new Planet(
			"Earth", centreX, centreY, 100, 10, 5, new int[]{40, 40, 255});
		planets.put("earth", earth);
		Planet mars = new Planet(
			"Mars", centreX, centreY, 150, 8, 7, new int[]{250, 100, 40});
		planets.put("mars", mars);

	}

	private void buildPlanets () {

		addPlanets();

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			root.getChildren().add(planet.getValue().getOrbitalPath());
			root.getChildren().add(planet.getValue().getPlanet());
		}

	}

	private void startMotion () {

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			planet.getValue().getMotion().play();
		}

	}

	@Override
	public void start (Stage primaryStage) {

		root = new Group();
		planets = new HashMap<>();

		buildSun();
		buildPlanets();

		// Adds title and scene to stage.
		primaryStage.setTitle("Solar System");
		primaryStage.setScene(new Scene(root, 400, 400, Color.BLACK));
		startMotion();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
