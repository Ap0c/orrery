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

class Planet {

	private String name;
	private Circle planet;
	private Path orbitalPath;
	private PathTransition motion;
	private Map<String, String> properties;

	private void setRadius (double radius) {
		planet.setRadius(radius);
	}

	private void setColour (Color colour) {
		planet.setFill(colour);
	}

	private void graphicProperties (String[] planetData) {

		double radius = Double.parseDouble(planetData[2]);
		int red = Integer.parseInt(planetData[4]);
		int green = Integer.parseInt(planetData[5]);
		int blue = Integer.parseInt(planetData[6]);

		planet = new Circle(radius, Color.rgb(red, green, blue));

	}

	private void motionProperties (
		double centreX, double centreY, String[] planetData) {

		orbitalPath = new Path();
		motion = new PathTransition();

		double orbitalRadius = Double.parseDouble(planetData[1]);
		int period = Integer.parseInt(planetData[3]);

		setOrbitalPath(centreX, centreY, orbitalRadius);
		setMotion(period);

	}

	private void physicalProperties (String[] planetData) {

		properties = new HashMap<>();

		properties.put("Mass", planetData[7]);
		properties.put("Mean Radius", planetData[8]);
		properties.put("Semi-Major Axis", planetData[9]);
		properties.put("Orbital Period", planetData[10]);
		properties.put("Rotation Period", planetData[11]);
		properties.put("Number of Moons", planetData[12]);

	}

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

	private void setMotion (int orbitalPeriod) {

		motion.setDuration(Duration.millis(orbitalPeriod * 1000));
		motion.setPath(orbitalPath);
		motion.setNode(planet);
		motion.setOrientation(
			PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		motion.setCycleCount(Timeline.INDEFINITE);
		motion.setInterpolator(Interpolator.LINEAR);

	}

	public Circle getPlanet () {
		return planet;
	}

	public Path getOrbitalPath () {
		return orbitalPath;
	}

	public PathTransition getMotion () {
		return motion;
	}

	public String getName () {
		return name;
	}

	public Map<String, String> getProperties () {
		return properties;
	}

	Planet (double centreX, double centreY, String[] planetData) {

		this.name = planetData[0];
		
		graphicProperties(planetData);
		motionProperties(centreX, centreY, planetData);
		physicalProperties(planetData);

		for (Map.Entry<String, String> item :
			getProperties().entrySet()) {

			System.out.println(item.getKey() + " : " + item.getValue());

		}

	}

}
