// ----- Imports ----- //

import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Map;
import java.util.HashMap;


// ----- Class ----- //

class Info {

	private final double infoWidth = 300;
	private GridPane infoBox;

	private void buildInfo () {

		infoBox.setPrefWidth(infoWidth);
		infoBox.setStyle("-fx-background-color: #000000");
		infoBox.setHgap(5);
		infoBox.setVgap(20);
		infoBox.setPadding(new Insets(10, 50, 50, 50));

		Label title = new Label("The Planets");
		title.setTextFill(Color.WHITE);
		infoBox.add(title, 1, 0);

	}

	private void addPlanets (Space space) {

		Map<String, Planet> planets = space.getPlanets();

		int row = 1;

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {

			Circle planetShape = planet.getValue().getPlanet();
			Circle infoPlanet = new Circle(
				planetShape.getRadius(), planetShape.getFill());

			Label planetName = new Label(planet.getValue().getName());
			planetName.setTextFill(Color.WHITE);

			infoBox.add(infoPlanet, 1, row);
			infoBox.add(planetName, 2, row);

			row++;

		}

	}

	public GridPane getInfo () {
		return infoBox;
	}

	public double infoWidth () {
		return infoWidth;
	}

	public Info (Space space) {

		this.infoBox = new GridPane();
		buildInfo();
		addPlanets(space);

	}

}

