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

class Planet {

	private String name;
	private Circle planet;
	private Path orbitalPath;
	private PathTransition motion;

	private void setRadius (double radius) {
		planet.setRadius(radius);
	}

	private void setColour (Color colour) {
		planet.setFill(colour);
	}

	public void setOrbitalPath (double centreX, double centreY, double radius) {

		double startY = centreY - radius;
		double midY = centreY + radius;

		orbitalPath.setStroke(Color.WHITE);
		orbitalPath.setSmooth(true);

		orbitalPath.getElements().add(new MoveTo(centreX, startY));
		orbitalPath.getElements().add(new ArcTo(
			radius, radius, 0, centreX, midY, false, false
		));
		orbitalPath.getElements().add(new ArcTo(
			radius, radius, 0, centreX, startY, false, false
		));
		orbitalPath.getElements().add(new ClosePath());

	}

	public void setMotion (int orbitalPeriod) {

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

	Planet (String name, double centreX, double centreY, double orbitalRadius,
		double radius, int period, int[] color) {

		this.name = name;
		this.planet = new Circle(
			radius, Color.rgb(color[0], color[1], color[2]));
		this.orbitalPath = new Path();
		this.motion = new PathTransition();
		setOrbitalPath(centreX, centreY, orbitalRadius);
		setMotion(period);

	}

}
