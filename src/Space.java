// ----- Imports ----- //

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


// ----- Class ----- //

class Space {

	private final double spaceWidth = 700;
	private final double spaceHeight = 700;
	private final double centreX = spaceWidth / 2;
	private final double centreY = spaceHeight / 2;
	private final double sunSize = 35.0;
	private Rectangle background;
	private Group spaceGroup;
	private Map<String, Planet> planets;
	public ArrayList<String> orderedPlanets;

	private void buildBackground() {

		background = new Rectangle(spaceWidth, spaceHeight, Color.BLACK);
		spaceGroup.getChildren().add(background);

	}

	private void buildSun () {

		Circle sun = new Circle(centreX, centreY, sunSize, Color.ORANGE);
		spaceGroup.getChildren().add(sun);

	}

	private void addPlanets () throws FileNotFoundException {

		Scanner planetsData = new Scanner(new File("src/planetdata.txt"));

		while (planetsData.hasNext()) {

			String dataLine = planetsData.nextLine();
			String[] planetData = dataLine.split(",");
			Planet planet = new Planet(centreX, centreY, planetData);

			planets.put(planetData[0], planet);
			orderedPlanets.add(planetData[0]);

		}

	}

	private void buildPlanets () throws FileNotFoundException {

		addPlanets();

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			spaceGroup.getChildren().add(planet.getValue().getOrbitalPath());
			spaceGroup.getChildren().add(planet.getValue().getPlanet());
		}

	}

	public void startMotion () {

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			planet.getValue().getMotion().play();
		}

	}

	public Group getSpace () {
		return spaceGroup;
	}

	public Planet getPlanet (String name) {
		return planets.get(name);
	}

	public ArrayList<String> getPlanets () {
		return orderedPlanets;
	}

	public double getWidth () {
		return spaceWidth;
	}

	public double getHeight () {
		return spaceHeight;
	}

	public Space () throws FileNotFoundException {

		this.planets = new HashMap<>();
		this.spaceGroup = new Group();
		this.orderedPlanets = new ArrayList<String>();

		buildBackground();
		buildSun();
		buildPlanets();

	}

}
