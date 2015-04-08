// ----- Imports ----- //

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Map;
import java.util.HashMap;


// ----- Class ----- //

class Info {

	private final double infoWidth = 300;
	private VBox infoBox;

	private void buildInfo () {

		infoBox.setPrefWidth(infoWidth);
		infoBox.setStyle("-fx-background-color: #000000");
		infoBox.setSpacing(19);
		infoBox.setPadding(new Insets(10, 50, 50, 50));

		Label title = new Label("The Planets");
		title.setTextFill(Color.WHITE);
		infoBox.getChildren().add(title);

	}

	private void addPlanets (Space space) {

		Map<String, Planet> planets = space.getPlanets();

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {

			Circle planetShape = planet.getValue().getPlanet();
			Circle infoPlanet = new Circle(
				planetShape.getRadius(), planetShape.getFill());

			Label planetName = new Label(planet.getValue().getName());
			planetName.setTextFill(Color.WHITE);

			infoBox.getChildren().add(infoPlanet);
			infoBox.getChildren().add(planetName);


		}

	}

	public VBox getInfo () {
		return infoBox;
	}

	public double infoWidth () {
		return infoWidth;
	}

	public Info (Space space) {

		this.infoBox = new VBox();
		buildInfo();
		addPlanets(space);

	}

}

