package ie.wit.map.version_2.views.add_item;

import ie.wit.map.common.model.Area;
import ie.wit.map.common.model.Building;
import ie.wit.map.common.model.Place;
import ie.wit.map.version_2.managers.Session;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Joe on 19/04/2016.
 */
public class AddItemCtrl
{
	private static final String[] types = {"Building", "Outdoor"};
	int numRooms;
	@FXML
	private TextField nameField;
	@FXML
	private TextField numRoomsField;
	@FXML
	private ChoiceBox<String> typeChoice;
	@FXML
	private ChoiceBox<String> purposeChoice;
	@FXML
	private GridPane numRoomsGrid;
	@FXML
	private Label nameErrorLabel;
	@FXML
	private Label numRoomsErrorLabel;
	@FXML
	private Label selectAreaLabel;
	private String currentName;
	private String currentType;
	private String currentFunction;
	private String currentNumRooms;
	private String currentGuiId;
	private boolean isBuilding;
	private boolean formValid;

	@FXML
	private void initialize()
	{

		currentGuiId = "";
		nameErrorLabel.setVisible(false);
		numRoomsErrorLabel.setVisible(false);
		numRoomsGrid.managedProperty().bind(numRoomsGrid.visibleProperty());
		typeChoice.getItems().addAll(types);
		typeChoice.setValue(types[0]);
		setPurposeChoice();
		typeChoice.setOnAction(e -> setPurposeChoice());
		nameField.textProperty().addListener((observable, oldValue, newValue) -> {
			validateName(newValue);
			checkFormValidity();
		});
		numRoomsField.textProperty().addListener(((observable, oldValue, newValue) -> {
			validateNumRooms(newValue);
			checkFormValidity();
		}));
		purposeChoice.valueProperty().addListener(((observable, oldValue, newValue) -> currentFunction = newValue));
		if (Session.isInEdit()) {
			if (Session.isBuildingInEdit()) {
				Building building = Session.getBuildingToEdit();
				isBuilding = true;
				typeChoice.setValue(types[0]);
				nameField.setText(building.getName());
				purposeChoice.setValue(building.getType());
				numRoomsField.setText(Integer.toString(building.getNumRooms()));
				currentGuiId = building.getGuiArea();
			} else {
				Area area = Session.getAreaToEdit();
				isBuilding = false;
				typeChoice.setValue(types[1]);
				nameField.setText(area.getName());
				purposeChoice.setValue(area.getType());
				currentGuiId = area.getGuiArea();
			}
		} else {
			currentGuiId = "";
			isBuilding = true;
			typeChoice.setValue(types[0]);
		}
		checkFormValidity();
		updateSelectAreaLabel();
	}

	private void checkFormValidity()
	{
		if (!nameErrorLabel.isVisible() && currentGuiId.length() > 0) {
			if (!numRoomsGrid.isVisible()) {
				formValid = true;
				System.out.println("form valid");
				return;
			} else {
				if (!numRoomsErrorLabel.isVisible()) {
					formValid = true;
					System.out.println("form valid");
					return;
				}
			}
			formValid = false;
		}
	}

	private void updateSelectAreaLabel()
	{
		if (currentGuiId.length() > 0) {
			selectAreaLabel.getStyleClass().remove("invalidFormLabel");
			selectAreaLabel.getStyleClass().add("areaSelectedLabel");
			selectAreaLabel.setText("Area Selected: " + currentGuiId);
		} else {
			selectAreaLabel.getStyleClass().remove("areaSelectedLabel");
			selectAreaLabel.getStyleClass().add("invalidFormLabel");
			selectAreaLabel.setText("Area Not Selected");
		}
	}

	private void setController()
	{
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Session.controllers.setAddItemCtrl(this);
		});
	}

	// TODO: 19/04/2016 these should also set the values to the Session class so that they can be retrieved
	private void validateName(String value)
	{
		String msg = checkNotNull(value);
		if (msg.length() > 0) {
			nameField.getStyleClass().add("invalidEntry");
			nameErrorLabel.setText(msg);
			nameErrorLabel.setVisible(true);
		} else {
			nameField.getStyleClass().remove("invalidEntry");
			nameErrorLabel.setVisible(false);

		}
		currentName = value;
	}

	private String checkNotNull(String value)
	{
		return value.length() > 0 ? "" : "This field is required";
	}

	private void validateNumRooms(String value)
	{
		String msg = checkNotNull(value);
		if (msg.length() > 0) {
			numRoomsField.getStyleClass().add("invalidEntry");
			numRoomsErrorLabel.setText(msg);
			numRoomsErrorLabel.setVisible(true);
		} else {
			int num = validateNum(value);
			if (num < 0) {
				numRoomsField.getStyleClass().add("invalidEntry");
				numRoomsErrorLabel.setText("Please enter a valid number");
				numRoomsErrorLabel.setVisible(true);
			} else {
				numRoomsField.getStyleClass().remove("invalidEntry");
				numRoomsErrorLabel.setVisible(false);
				numRooms = num;
			}
		}
	}

	private int validateNum(String num)
	{
		try {
			return Integer.parseInt(num);
		} catch (NumberFormatException e) {
			return -10;
		}
	}

	private void setPurposeChoice()
	{
		purposeChoice.getItems().clear();
		if (typeChoice.getValue().equalsIgnoreCase("building")) {
			purposeChoice.getItems().addAll(Place.getBuildingTypes());
			purposeChoice.setValue(Place.getBuildingTypes()[0]);
			numRoomsGrid.setVisible(true);
			isBuilding = true;
		} else {
			purposeChoice.getItems().addAll(Place.getAreaTypes());
			purposeChoice.setValue(Place.getAreaTypes()[0]);
			numRoomsGrid.setVisible(false);
			isBuilding = false;
		}
		checkFormValidity();
		currentType = typeChoice.getValue();
	}

	@FXML
	private void selectAreaClicked()
	{
		Session.saveTempForm(currentName, currentType, currentFunction, currentNumRooms);
		Session.setBuildingSelection(isBuilding);
		Session.setMapInSelection(true);
		Session.viewLoader.initModalRoot();
	}

	public void setCurrentGuiId(String currentGuiId)
	{
		this.currentGuiId = currentGuiId;
		updateSelectAreaLabel();
		checkFormValidity();
	}

	@FXML
	private void submitClicked()
	{
		if (formValid) {
			boolean check;
			if (isBuilding) {
				if (Session.isInEdit()) {
					Building building = new Building(Session.getBuildingToEdit().getId(),
							nameField.getText(),
							Integer.parseInt(numRoomsField.getText()),
							purposeChoice.getValue(),
							currentGuiId);
					updateItem(building);
					return;
				} else {
					Building building = new Building(nameField.getText(),
							Integer.parseInt(numRoomsField.getText()),
							purposeChoice.getValue(),
							currentGuiId);
					check = Session.dataCollection.addToList(building);
				}
			} else {
				if (Session.isInEdit()) {
					Area area = new Area(Session.getAreaToEdit().getId(),
							nameField.getText(),
							purposeChoice.getValue(),
							currentGuiId);
					updateItem(area);
					return;
				} else {
					Area area = new Area(nameField.getText(), purposeChoice.getValue(), currentGuiId);
					check = Session.dataCollection.addToList(area);
				}
			}
			if (check) {
				Session.viewLoader.showTable();
			}
		}
	}

	private void updateItem(Place place)
	{
		if (Session.dataCollection.updatePlace(place)) {
			Session.setInEdit(false);
			Session.viewLoader.showTable();
		}
	}
}
