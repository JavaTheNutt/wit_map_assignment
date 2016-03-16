package ie.wit.map.version_1.views.add_building;

import ie.wit.map.version_1.Main;
import ie.wit.map.version_1.model.Building;
import ie.wit.map.version_1.model.Place;
import ie.wit.map.version_1.model.database.DataCollection;
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
	private final Label[] errorLabel = new Label[2];
	private DataCollection dataCollection;

	// TODO: 13/03/2016 improve form layout 
	@FXML
	private void initialize(){
		typeChoice.getItems().addAll(Place.getBuildingTypes());
		typeChoice.setValue(Place.getBuildingTypes()[0]);
		fields[0] = nameField;
		fields[1] = numRoomsField;
		errorLabel[0] = nameErrorLabel;
		errorLabel[1] = numRoomsErrorLabel;
		dataCollection = Main.dataCollection;
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
		boolean check = false;
		int num = -1;
		for (int i = 0; i < fields.length; i++) {
			check = validateForm(fields[i], errorLabel[i]);
			if (check && i ==0){
				check = validateName();
			}else if (check && i == 1){
				num = validateNum();
				check = num > 0;
			}
		}
		// TODO: 14/03/2016 create building object here and add it to the database
		if (check && num >= 0){
			Building building = new Building(nameField.getText(), num, typeChoice.getValue());
			System.out.println(building.toString());
			if (building.isTypeValid()){
				dataCollection.addToList(building);
			} else {
				System.out.println("incorrect");
			}
		}

	}
	private boolean validateForm(TextField field, Label label){
		if(field.getText().length() < 1){
			label.setText("This field is required");
			field.getStyleClass().add("invalidEntry");
			label.setVisible(true);
			return false;
		}
		label.setVisible(false);
		field.getStyleClass().remove("invalidEntry");
		return true;
	}
	private boolean validateName(){
		if (nameField.getText().length() <= 3){
			nameErrorLabel.setText("Please enter a name longer than 3 characters");
			nameField.getStyleClass().add("invalidEntry");
			nameErrorLabel.setVisible(true);
			return false;
		}
		nameErrorLabel.setVisible(false);
		nameField.getStyleClass().remove("invalidEntry");
		return true;
	}
	private int validateNum(){
		try{
			numRoomsErrorLabel.setVisible(false);
			numRoomsField.getStyleClass().remove("invalidEntry");
			return Integer.parseInt(numRoomsField.getText());
		}catch (NumberFormatException e){
			numRoomsErrorLabel.setText("Please enter a valid number");
			numRoomsErrorLabel.setVisible(true);
			numRoomsField.getStyleClass().add("invalidEntry");
			return -10;
		}
	}
}
