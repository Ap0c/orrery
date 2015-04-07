// ----- Imports ----- //

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


// ----- Class ----- //

class Info {

	private final double infoWidth = 300;
	private VBox infoBox;

	private void buildInfo () {

		infoBox.setPrefWidth(infoWidth);
		infoBox.setStyle("-fx-background-color: #000000");
		Label label = new Label("The Planets");
		infoBox.getChildren().add(label);

	}

	public VBox getInfo () {
		return infoBox;
	}

	public double infoWidth () {
		return infoWidth;
	}

	public Info () {

		this.infoBox = new VBox();
		buildInfo();

	}

}

