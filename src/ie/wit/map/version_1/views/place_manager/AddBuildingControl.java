package ie.wit.map.version_1.views.place_manager;

import ie.wit.map.common.model.Building;
import ie.wit.map.common.model.Place;
import ie.wit.map.version_1.Main;
import ie.wit.map.version_1.model.gui.AreaManagement;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Joe on 15/03/2016.
 */
public class AddBuildingControl
{
	// TODO: 19/03/2016 match gui elements to variables 
	// TODO: 19/03/2016 bind managed properties of area selected labels to visible properties 
	private final TextField[] fields = new TextField[2];
	private final Label[] errorLabels = new Label[2];
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
	@FXML
	private Label areaNotSelectedLabel;
	@FXML
	private Label areaSelectedLabel;
	@FXML
	private ChoiceBox<String> typeChoice;

	@FXML
	private TableView<Building> table;
	@FXML
	private TableColumn<Building, String> buildingNameColumn;
	@FXML
	private TableColumn<Building, String> buildingTypeColumn;
	@FXML
	private TableColumn<Building, Integer> numRoomsColumn;

	private String selectedId;
	private boolean isBuilding = true;
	private String[] typeNames = {"Building", "Outdoor Area"};

	@FXML
	private void initialize()
	{
		/*Table*/
		buildingNameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
		buildingTypeColumn.setCellValueFactory(data -> data.getValue().typeProperty());
		numRoomsColumn.setCellValueFactory(data -> data.getValue().numRoomsProperty().asObject());

		/*Form*/

		setChoices();
		typeChoice.getItems().addAll(typeNames);
		typeChoice.setValue(typeNames[0]);
		typeChoice.setOnAction(e -> {
			isBuilding = typeChoice.getValue().equalsIgnoreCase("building");
			setChoices();
			toggleNumRooms();
		});
		/*This could have been done with a single label and changing the text and color, but I wanted to showcase the managed property*/
		areaNotSelectedLabel.managedProperty().bind(areaNotSelectedLabel.visibleProperty());
		areaSelectedLabel.managedProperty().bind(areaSelectedLabel.visibleProperty());
		fields[0] = nameField;
		fields[1] = numRoomsField;
		errorLabels[0] = nameErrorLabel;
		errorLabels[1] = numRoomsErrorLabel;
		areaSelectedLabel.setVisible(false);
		nameErrorLabel.setVisible(false);
		numRoomsErrorLabel.setVisible(false);

		/*Table*/
		table.setItems(Main.dataCollection.getAllBuildingObservable());
	}

	private void setChoices()
	{
		String[] choices = isBuilding ? Place.getBuildingTypes() : Place.getAreaTypes();
		choiceBox.getItems().clear();
		choiceBox.getItems().addAll(choices);
		choiceBox.setValue(choices[0]);
	}

	private void toggleNumRooms()
	{
		numRoomsField.setDisable(!isBuilding);
	}

	@FXML
	private void submitClicked()
	{
		boolean check = false;
		for (int i = 0; i < fields.length; i++) {
			if (validateField(fields[i], errorLabels[i])) {
				check = (i == 0) ? validateName() : validateNum() > 0;
			}
		}
		if (check && selectedId != null) {
			Building building = new Building(nameField.getText(), validateNum(), choiceBox.getValue(), selectedId);
			System.out.println(building.toString());
			Main.dataCollection.addToList(building);

			AreaManagement.getById(selectedId).setAssigned(true);
		}
	}

	private boolean validateField(TextField field, Label label)
	{
		if (field.getText().length() < 1) {
			field.getStyleClass().add("invalidEntry");
			label.setText("Required Field");
			label.setVisible(true);
			return false;
		}
		field.getStyleClass().remove("invalidEntry");
		label.setVisible(false);
		return true;
	}

	private boolean validateName()
	{
		if (nameField.getText().length() < 3) {
			nameErrorLabel.setText("Please enter a name with more than three characters");
			nameErrorLabel.setVisible(true);
			nameField.getStyleClass().add("invalidEntry");
			return false;
		}
		nameErrorLabel.setVisible(false);
		nameErrorLabel.getStyleClass().remove("invalidEntry");
		return true;
	}

	private int validateNum()
	{
		try {
			int num = Integer.parseInt(numRoomsField.getText());
			numRoomsErrorLabel.setVisible(false);
			numRoomsField.getStyleClass().remove("invalidEntry");
			return num;
		} catch (NumberFormatException e) {
			numRoomsErrorLabel.setText("Please enter a valid number");
			numRoomsErrorLabel.setVisible(true);
			numRoomsField.getStyleClass().add("invalidEntry");
			return -10;
		}
	}

	@FXML
	private void selectArea()
	{
		Main.setBuildingSelection(true);
		Main.viewLoader.displayRootSelection((selectedId) -> {
			// TODO: 04/04/2016 perform validation on returned input
			if (selectedId.contains("building")) {
				this.selectedId = selectedId;
				areaSelectedLabel.setVisible(true);
				areaNotSelectedLabel.setVisible(false);
			} else {
				areaSelectedLabel.setVisible(false);
				areaNotSelectedLabel.setText("The area selected is not a building, please select again");
				areaNotSelectedLabel.setVisible(true);
				this.selectedId = null;
			}
		});
	}


}
