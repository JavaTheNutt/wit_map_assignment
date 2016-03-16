package ie.wit.map.version_1.views.place_manager;

import ie.wit.map.version_1.model.Place;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Created by Joe on 15/03/2016.
 */
public class AddBuildingControl
{
	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private TextField nameField;
	@FXML
	private Label nameErrorLabel;

	@FXML
	private TextField numRoomsField;

	@FXML
	private void initialize(){
		choiceBox.getItems().addAll(Place.getBuildingTypes());
		choiceBox.setValue(Place.getBuildingTypes()[0]);
	}

	@FXML
	private void submitClicked(){
		nameField.getStyleClass().add("InvalidEntry");
		nameErrorLabel.setText("Required Field");
		nameErrorLabel.setVisible(true);
	}
}
