package ie.wit.map.version_1.views.place_manager;

import ie.wit.map.version_1.model.Building;
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
	private Label numRoomsErrorLabel;

	private final TextField[] fields = new TextField[2];
	private final Label[] errorLabels = new Label[2];

	@FXML
	private void initialize(){
		choiceBox.getItems().addAll(Place.getBuildingTypes());
		choiceBox.setValue(Place.getBuildingTypes()[0]);
		fields[0] = nameField;
		fields[1] = numRoomsField;
		errorLabels[0]= nameErrorLabel;
		errorLabels[1] = numRoomsErrorLabel;
	}

	@FXML
	private void submitClicked(){
		boolean check = false;
		for (int i = 0; i < fields.length; i++) {
			if (validateField(fields[i], errorLabels[i])) {
				/*if (i == 0){
					check = validateName();
				} else {
					check = validateNum() > 0;
				}*/
				/*if (i == 0)
					check = validateName();
				else
					check = validateNum() > 0;*/
				check = (i == 0) ? validateName() : validateNum() > 0;
			}
		}
		if(check){
			//Building building = new Building(nameField.getText(), validateNum(), choiceBox.getValue());
			//System.out.println(building.toString());
		}
	}
	private boolean validateField(TextField field, Label label){
		if (field.getText().length() < 1){
			field.getStyleClass().add("invalidEntry");
			label.setText("Required Field");
			label.setVisible(true);
			return false;
		}
		field.getStyleClass().remove("invalidEntry");
		label.setVisible(false);
		return true;
	}
	private boolean validateName(){
		if(nameField.getText().length() < 3){
			nameErrorLabel.setText("Please enter a name with more than three characters");
			nameErrorLabel.setVisible(true);
			nameField.getStyleClass().add("invalidEntry");
			return false;
		}
		nameErrorLabel.setVisible(false);
		nameErrorLabel.getStyleClass().remove("invalidEntry");
		return true;
	}
	private int validateNum(){
		try{
			return Integer.parseInt(numRoomsField.getText());
		}catch (NumberFormatException e){
			return -10;
		}
	}
}
