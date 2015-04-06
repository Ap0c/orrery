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

	private final double centreX = 350;
	private final double centreY = 350;
	private Group planetGroup;
	private Map<String, Planet> planets;

	private void buildSun () {

		Circle sun = new Circle(centreX, centreY, 35.0, Color.ORANGE);
		planetGroup.getChildren().add(sun);

	}

	private void addPlanets() {

		Planet mercury = new Planet(
			"Mercury", centreX, centreY, 50, 5, 1, new int[]{180, 180, 180});
		planets.put("mercury", mercury);
		Planet venus = new Planet(
			"Venus", centreX, centreY, 90, 10, 3, new int[]{240, 200, 180});
		planets.put("venus", venus);
		Planet earth = new Planet(
			"Earth", centreX, centreY, 130, 10, 5, new int[]{40, 100, 255});
		planets.put("earth", earth);
		Planet mars = new Planet(
			"Mars", centreX, centreY, 170, 8, 10, new int[]{250, 100, 40});
		planets.put("mars", mars);
		Planet jupiter = new Planet(
			"Jupiter", centreX, centreY, 210, 20, 60, new int[]{230, 120, 40});
		planets.put("jupiter", jupiter);
		Planet saturn = new Planet(
			"Saturn", centreX, centreY, 250, 18, 150, new int[]{220, 160, 40});
		planets.put("saturn", saturn);
		Planet uranus = new Planet(
			"Uranus", centreX, centreY, 290, 13, 420, new int[]{180, 200, 240});
		planets.put("uranus", uranus);
		Planet neptune = new Planet(
			"Neptune", centreX, centreY, 330, 13, 825, new int[]{0, 50, 240});
		planets.put("neptune", neptune);

	}

	private void buildPlanets () {

		addPlanets();

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			planetGroup.getChildren().add(planet.getValue().getOrbitalPath());
			planetGroup.getChildren().add(planet.getValue().getPlanet());
		}

	}

	private void startMotion () {

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			planet.getValue().getMotion().play();
		}

	}

	@Override
	public void start (Stage primaryStage) {

		planetGroup = new Group();
		planets = new HashMap<>();

		buildSun();
		buildPlanets();

		// Adds title and scene to stage.
		primaryStage.setTitle("Solar System");
		primaryStage.setScene(new Scene(planetGroup, 700, 700, Color.BLACK));
		startMotion();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
