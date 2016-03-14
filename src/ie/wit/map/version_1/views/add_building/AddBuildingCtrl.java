package ie.wit.map.version_1.views.add_building;

import ie.wit.map.version_1.Main;
import ie.wit.map.version_1.model.Place;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Joe on 13/03/2016.
 */
public class AddBuildingCtrl
{
	@FXML
	private ChoiceBox<String> typeChoice;
	@FXML
	private TextField nameField;
	@FXML
	private TextField numRoomsField;
	@FXML
	private Label nameErrorLabel;
	@FXML
	private Label numRoomsErrorLabel;

	private final TextField[] fields = new TextField[2];

	// TODO: 13/03/2016 improve form layout 
	@FXML
	private void initialize(){
		typeChoice.getItems().addAll(Place.getBuildingTypes());
		typeChoice.setValue(Place.getBuildingTypes()[0]);
		fields[0] = nameField;
		fields[1] = numRoomsField;

	}
	@FXML
	private void clearInfo(){
		if (numRoomsField.getText().length() < 1 && nameField.getText().length() < 1){
			return;
		}
		numRoomsField.setText("");
		nameField.setText("");
	}

	// TODO: 13/03/2016 validate input and object creation 
	@FXML
	private void submitClicked(){
		/*for (TextField field : fields) {
			if (field.getText().length() < 1){
				field.getStyleClass().add("invalidEntry");
				nameErrorLabel.setText("This field is required");
				nameErrorLabel.setVisible(true);
			} else{
				field.getStyleClass().remove("invalidEntry");
			}
		}*/
		validateForm(nameField);
	}
	private void validateForm(TextField field){
		if (field.getId().equalsIgnoreCase("nameField")){
			System.out.println(true);
		}
	}

}
