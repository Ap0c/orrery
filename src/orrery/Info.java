package orrery;

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

/**
 * A panel on the right of the screen that holds information about the planets.
 */
class Info {

	// ----- Instance Variables ----- //

	private final double infoWidth = 300;
	private GridPane infoBox;
	private GridPane planetList;
	private GridPane planetData;
	private String[] titleLabels = {"Mass", "Mean Radius", "Semi-Major Axis",
		"Orbital Period", "Rotation Period", "Number of Moons"};
	private Map<String, Label> dataLabels;

	// ----- Instance Methods ----- //

	/**
	 * Adds a set of coloured circles to the info panel to represent planets.
	 * 
	 * @param space a space object containing the planets.
	 */
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

	/**
	 * Creates a list of planets and their names for display in the info panel.
	 * 
	 * @param space a space object containing the planets.
	 */
	private void buildList (Space space) {

		planetList.setPrefWidth(infoWidth);
		planetList.setHgap(30);
		planetList.setVgap(20);
		planetList.setPadding(new Insets(20, 50, 30, 50));

		Label title = new Label("The Planets");
		title.setFont(new Font(20));
		title.setTextFill(Color.WHITE);
		planetList.add(title, 1, 0, 2, 1);
		GridPane.setHalignment(title, HPos.CENTER);

		addPlanets(space);

	}

	/**
	 * Creates a set of labels to display information about a chosen planet.
	 */
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

	/**
	 * Sets up a section of the info panel to display information about a chosen
	 * planet.
	 */
	private void buildData () {

		ColumnConstraints titleCol = new ColumnConstraints();
		titleCol.setHalignment(HPos.RIGHT);

		planetData.getColumnConstraints().add(titleCol);
		planetData.setPadding(new Insets(20, 50, 10, 10));
		planetData.setHgap(30);
		planetData.setVgap(20);

		dataLabels();

	}

	/**
	 * Creates the information panel, adding a section for a list of planets
	 * and one for displaying information about a specific planet.
	 *
	 * @param space a space object containing the planets.
	 */
	private void buildInfo (Space space) {

		buildList(space);
		infoBox.add(planetList, 0, 0);
		buildData();
		infoBox.add(planetData, 0, 1);

		infoBox.setStyle("-fx-background-color: #000000");

	}

	/**
	 * Updates the data section to display information about a specific planet
	 * when it is selected.
	 *
	 * @param planet the planet object from which the data is to be retrieved.
	 */
	private void updateData (Planet planet) {

		for (Map.Entry<String, String> item :
			planet.getProperties().entrySet()) {

			dataLabels.get(item.getKey()).setText(item.getValue());

		}

	}

	/**
	 * Creates a handler for the event that occurs when a planet in the list is
	 * clicked on. The planets orbital path is coloured red, and information
	 * about it is displayed in the data section.
	 *
	 * @param space the space object containing the planet information.
	 * @param planet the circle representing the planet in the list, this is the
	 * thing that will be clicked on.
	 * @param name the name of the planet that has been clicked on.
	 *
	 * @return An event handler for a mouse click event.
	 */
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

	/**
	 * Assigns handlers for mouse clicks on the planets and planet names in
	 * the list.
	 *
	 * @param space the space object containing the planet information.
	 * @param planet the circle representing the planet in the list, this is the
	 * thing that will be clicked on.
	 * @param name the name of the planet that has been clicked on.
	 * @param lbl the label containing the name of the planet.
	 *
	 * @return An event handler for a mouse click event.
	 */
	private void setClick (Space space, Circle planet, String name, Label lbl) {

		planet.setOnMouseClicked(clickHandler(space, planet, name));
		lbl.setOnMouseClicked(clickHandler(space, planet, name));

	}

	/**
	 * Retrieves the graphical object that represents the info panel on screen.
	 *
	 * @return A javafx GridPane object.
	 */
	public GridPane getInfo () {
		return infoBox;
	}

	/**
	 * Retrieves the width of the info panel on screen.
	 *
	 * @return A width in pixels.
	 */
	public double infoWidth () {
		return infoWidth;
	}

	// ----- Constructor ----- //

	public Info (Space space) {

		this.infoBox = new GridPane();
		this.planetList = new GridPane();
		this.planetData = new GridPane();
		this.dataLabels = new HashMap<>();

		buildInfo(space);

	}

}
