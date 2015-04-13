package orrery;

// ----- Imports ----- //

import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.paint.Color;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import java.util.Map;
import java.util.HashMap;


// ----- Class ----- //

/**
 * An orbiting planet object.
 */
class Planet {

	// ----- Instance Variables ----- //

	private String name;
	private Circle planet;
	private Path orbitalPath;
	private PathTransition motion;
	private Map<String, String> properties;

	// ----- Instance Methods ----- //

	/**
	 * Creates the circle object that represents the planet.
	 * 
	 * @param planetData an array of properties describing the planet.
	 */
	private void graphicProperties (String[] planetData) {

		double radius = Double.parseDouble(planetData[2]);
		int red = Integer.parseInt(planetData[4]);
		int green = Integer.parseInt(planetData[5]);
		int blue = Integer.parseInt(planetData[6]);

		planet = new Circle(radius, Color.rgb(red, green, blue));

	}

	/**
	 * Creates the movement data for the planet, i.e. defines its orbit on
	 * screen.
	 * 
	 * @param centreX the x-coordinate of the centre of the orbit.
	 * @param centreY the y-coordinate of the centre of the orbit.
	 * @param planetData an array of properties describing the planet.
	 */
	private void motionProperties (
		double centreX, double centreY, String[] planetData) {

		orbitalPath = new Path();
		motion = new PathTransition();

		double orbitalRadius = Double.parseDouble(planetData[1]);
		int period = Integer.parseInt(planetData[3]);

		setOrbitalPath(centreX, centreY, orbitalRadius);
		setMotion(period);

	}

	/**
	 * Stores additional information about the planet not related to its
	 * graphical appearance.
	 * 
	 * @param planetData an array of properties describing the planet.
	 */
	private void physicalProperties (String[] planetData) {

		properties = new HashMap<>();

		properties.put("Mass", planetData[7]);
		properties.put("Mean Radius", planetData[8]);
		properties.put("Semi-Major Axis", planetData[9]);
		properties.put("Orbital Period", planetData[10]);
		properties.put("Rotation Period", planetData[11]);
		properties.put("Number of Moons", planetData[12]);

	}

	/**
	 * Defines the path on screen along which the planet orbits.
	 * 
	 * @param centreX the x-coordinate of the centre of the orbit.
	 * @param centreY the y-coordinate of the centre of the orbit.
	 * @param rad the radius of the orbit.
	 */
	private void setOrbitalPath (double centreX, double centreY, double rad) {

		double startY = centreY - rad;
		double midY = centreY + rad;

		orbitalPath.setStroke(Color.WHITE);
		orbitalPath.setSmooth(true);

		orbitalPath.getElements().add(new MoveTo(centreX, startY));
		orbitalPath.getElements().add(new ArcTo(
			rad, rad, 0, centreX, midY, false, false
		));
		orbitalPath.getElements().add(new ArcTo(
			rad, rad, 0, centreX, startY, false, false
		));
		orbitalPath.getElements().add(new ClosePath());

	}

	/**
	 * Defines how the planet moves along its orbital path, i.e. period, 
	 * looping the animation, etc.
	 * 
	 * @param orbitalPeriod the period of the orbit (seconds).
	 */
	private void setMotion (int orbitalPeriod) {

		motion.setDuration(Duration.millis(orbitalPeriod * 1000));
		motion.setPath(orbitalPath);
		motion.setNode(planet);
		motion.setOrientation(
			PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		motion.setCycleCount(Timeline.INDEFINITE);
		motion.setInterpolator(Interpolator.LINEAR);

	}

	/**
	 * Retrieves the circle representing the planet on screen.
	 * 
	 * @return A javafx Circle object.
	 */
	public Circle getPlanet () {
		return planet;
	}

	/**
	 * Retrieves the path describing the orbit.
	 * 
	 * @return A javafx Path object.
	 */
	public Path getOrbitalPath () {
		return orbitalPath;
	}

	/**
	 * Retrieves the orbit animation.
	 * 
	 * @return A javafx PathTransition object.
	 */
	public PathTransition getMotion () {
		return motion;
	}

	/**
	 * Retrieves the name of the planet.
	 * 
	 * @return Planet name in string format.
	 */
	public String getName () {
		return name;
	}

	/**
	 * Retrieves the list of real physical properties of the planet, i.e. Mass,
	 * Number of Moons, etc.
	 * 
	 * @return A hashmap with the names of the properties as keys.
	 */
	public Map<String, String> getProperties () {
		return properties;
	}

	// ----- Constructor ----- //

	Planet (double centreX, double centreY, String[] planetData) {

		this.name = planetData[0];
		
		graphicProperties(planetData);
		motionProperties(centreX, centreY, planetData);
		physicalProperties(planetData);

	}

}
