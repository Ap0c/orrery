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

/**
 * Represents the region of space in which the solar system exists. Maintains
 * and controls all planet objects in said space.
 */
class Space {

	// ----- Instance Variables ----- //

	private final double spaceWidth = 700;
	private final double spaceHeight = 700;
	private final double centreX = spaceWidth / 2;
	private final double centreY = spaceHeight / 2;
	private final double sunSize = 35.0;
	private Rectangle background;
	private Group spaceGroup;
	private Map<String, Planet> planets;
	public ArrayList<String> orderedPlanets;

	// ----- Instance Methods ----- //

	/**
	 * Creates the rectangle that serves as the backdrop for the solar system.
	 */
	private void buildBackground() {

		background = new Rectangle(spaceWidth, spaceHeight, Color.BLACK);
		spaceGroup.getChildren().add(background);

	}

	/**
	 * Creates the Sun circle object that sits at the centre of the space.
	 */
	private void buildSun () {

		Circle sun = new Circle(centreX, centreY, sunSize, Color.ORANGE);
		spaceGroup.getChildren().add(sun);

	}

	/**
	 * Creates the planets from a file containing their data and adds them to
	 * the space object.
	 *
	 * @throws FileNotFoundException thrown if the data file is not found.
	 */
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

	/**
	 * Adds the planets to the space and also retrieves and adds their graphical
	 * and animation data.
	 *
	 * @throws FileNotFoundException thrown if the planet file is not found.
	 */
	private void buildPlanets () throws FileNotFoundException {

		addPlanets();

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			spaceGroup.getChildren().add(planet.getValue().getOrbitalPath());
			spaceGroup.getChildren().add(planet.getValue().getPlanet());
		}

	}

	/**
	 * Starts the movement of the planets in their orbits.
	 */
	public void startMotion () {

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			planet.getValue().getMotion().play();
		}

	}

	/**
	 * Retrieves the graphical object that represents the space and planets on
	 * screen.
	 *
	 * @return A javafx Group object.
	 */
	public Group getSpace () {
		return spaceGroup;
	}

	/**
	 * Retrieves an individual planet object in the space using its name.
	 *
	 * @param name a string containing the planet's name.
	 * @return A Planet object.
	 */
	public Planet getPlanet (String name) {
		return planets.get(name);
	}

	/**
	 * Retrieves a list of the planets by name, ordered according to their
	 * proximity to the Sun.
	 *
	 * @return A list of planet names.
	 */
	public ArrayList<String> getPlanets () {
		return orderedPlanets;
	}

	/**
	 * Gets the width of the space's graphical area.
	 *
	 * @return A width in pixels.
	 */
	public double getWidth () {
		return spaceWidth;
	}

	/**
	 * Gets the height of the space's graphical area.
	 *
	 * @return A height in pixels.
	 */
	public double getHeight () {
		return spaceHeight;
	}

	// ----- Constructor ----- //

	public Space () throws FileNotFoundException {

		this.planets = new HashMap<>();
		this.spaceGroup = new Group();
		this.orderedPlanets = new ArrayList<String>();

		buildBackground();
		buildSun();
		buildPlanets();

	}

}
