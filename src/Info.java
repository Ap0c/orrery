// ----- Imports ----- //

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import java.util.Map;
import java.util.HashMap;


// ----- Class ----- //

class Info {

	private final double infoWidth = 300;
	private GridPane infoBox;
	private GridPane planetList;
	private GridPane planetData;
	private String[] titleLabels = {"Mass", "Mean Radius", "Semi-Major Axis",
		"Orbital Period", "Rotation Period", "Number of Moons"};
	private Map<String, Label> dataLabels;

	private void buildList () {

		planetList.setPrefWidth(infoWidth);
		planetList.setHgap(30);
		planetList.setVgap(20);
		planetList.setPadding(new Insets(20, 50, 30, 50));

		Label title = new Label("The Planets");
		title.setFont(new Font(20));
		title.setTextFill(Color.WHITE);
		planetList.add(title, 1, 0, 2, 1);
		GridPane.setHalignment(title, HPos.CENTER);

	}

	private void dataLabels () {

		int row = 0;

		for (String title : titleLabels) {

			Label titleLabel = new Label(title);
			titleLabel.setTextFill(Color.WHITE);
			planetData.add(titleLabel, 0, row);

			Label dataLabel = new Label("-");
			dataLabel.setTextFill(Color.WHITE);
			planetData.add(dataLabel, 1, row);
			dataLabels.put(title, dataLabel);

			row++;

		}

	}

	private void buildData () {

		ColumnConstraints titleCol = new ColumnConstraints();
		titleCol.setHalignment(HPos.RIGHT);

		planetData.getColumnConstraints().add(titleCol);
		planetData.setPadding(new Insets(20, 50, 10, 10));
		planetData.setHgap(30);
		planetData.setVgap(20);

		dataLabels();

	}

	private void buildInfo () {

		buildList();
		infoBox.add(planetList, 0, 0);
		buildData();
		infoBox.add(planetData, 0, 1);

		infoBox.setStyle("-fx-background-color: #000000");

	}

	private void updateData (Planet planet) {

		for (Map.Entry<String, String> item :
			planet.getProperties().entrySet()) {

			dataLabels.get(item.getKey()).setText(item.getValue());

		}

	}

	private EventHandler<MouseEvent> clickHandler (
		Space space, Circle planet, String name) {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle (MouseEvent event) {

				for (String planet : space.getPlanets()) {
					space.getPlanet(planet).getOrbitalPath().setStroke(
						Color.WHITE);
				}

				Planet planet = space.getPlanet(name);
				planet.getOrbitalPath().setStroke(Color.RED);
				updateData(planet);

			}
		};
	
	}

	private void setClick (Space space, Circle planet, String name, Label lbl) {

		planet.setOnMouseClicked(clickHandler(space, planet, name));
		lbl.setOnMouseClicked(clickHandler(space, planet, name));

	}

	private void addPlanets (Space space) {

		int row = 1;

		for (String name : space.getPlanets()) {

			Planet planet = space.getPlanet(name);
			Circle infoPlanet = new Circle(
				planet.getPlanet().getRadius(), planet.getPlanet().getFill());

			Label planetName = new Label(planet.getName());
			planetName.setTextFill(Color.WHITE);

			planetList.add(infoPlanet, 1, row);
			planetList.add(planetName, 2, row);

			GridPane.setHalignment(infoPlanet, HPos.CENTER);
			setClick(space, infoPlanet, name, planetName);

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
		this.planetList = new GridPane();
		this.planetData = new GridPane();
		this.dataLabels = new HashMap<>();

		buildInfo();
		addPlanets(space);

	}

}
