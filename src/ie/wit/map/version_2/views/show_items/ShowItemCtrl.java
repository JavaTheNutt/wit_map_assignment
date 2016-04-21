package ie.wit.map.version_2.views.show_items;

import ie.wit.map.common.model.Area;
import ie.wit.map.common.model.Building;
import ie.wit.map.common.model.Place;
import ie.wit.map.version_2.managers.Session;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

/**
 * Created by Joe on 20/04/2016.
 */
public class ShowItemCtrl
{
	@FXML
	private TableView<Place> allTable;
	@FXML
	private TableView<Building> buildingTable;
	@FXML
	private TableView<Area> areaTable;

	@FXML
	private TableColumn<Place, String> nameColumn;
	@FXML
	private TableColumn<Place, String> functionColumn;
	@FXML
	private TableColumn<Building, String> buildingNameColumn;
	@FXML
	private TableColumn<Building, String> buildingFunctionColumn;
	@FXML
	private TableColumn<Building, Integer> buildingNumRoomsColumn;
	@FXML
	private TableColumn<Area, String> areaNameColumn;
	@FXML
	private TableColumn<Area, String> areaFunctionColumn;
	@FXML
	private Button deleteButton;
	@FXML
	private Button editButton;
	@FXML
	private Button showOnMapButton;
	@FXML
	private StackPane errorLabelPane;

	private Place selectedPlace;
	private BooleanProperty placeSelected;

	@FXML
	private void initialize()
	{
		errorLabelPane.managedProperty().bind(errorLabelPane.visibleProperty());
		placeSelected = new SimpleBooleanProperty();
		placeSelected.addListener((observable, oldValue, newValue) -> {
			deleteButton.setDisable(!newValue);
			editButton.setDisable(!newValue);
			showOnMapButton.setDisable(!newValue);
		});
		placeSelected.set(false);
		nameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
		functionColumn.setCellValueFactory(data -> data.getValue().getTypeProperty());
		buildingNameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
		buildingFunctionColumn.setCellValueFactory(data -> data.getValue().getTypeProperty());
		buildingNumRoomsColumn.setCellValueFactory(data -> data.getValue().numRoomsProperty().asObject());
		areaNameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
		areaFunctionColumn.setCellValueFactory(data -> data.getValue().getTypeProperty());
		allTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			selectedPlace = newValue;
			placeSelected.setValue(true);
		});
		buildingTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			selectedPlace = newValue;
			placeSelected.setValue(true);
		});
		areaTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			selectedPlace = newValue;
			placeSelected.setValue(true);
		});
		refreshTables();
		if(Session.isTableShowItem()){
			Place area = Session.getAreaToShowInTable();
			if(area != null){
				allTable.getSelectionModel().select(area);
			} else{
				errorLabelPane.setVisible(true);
			}
		}
	}

	@FXML
	private void editClicked()
	{
		Session.setInEdit(true);
		if (selectedPlace instanceof Building) {
			Session.setBuildingInEdit(true);
			Session.setBuildingToEdit((Building) selectedPlace);
		} else {
			Session.setBuildingInEdit(false);
			Session.setAreaToEdit((Area) selectedPlace);
		}
		Session.viewLoader.showAddItem();
	}

	@FXML
	private void deleteClicked()
	{
		Session.dataCollection.deleteItem(selectedPlace);
		refreshTables();
	}

	private void refreshTables()
	{
		allTable.getItems().clear();
		allTable.setItems(Session.dataCollection.getAllObservable());
		buildingTable.getItems().clear();
		buildingTable.setItems(Session.dataCollection.getAllBuildingObservable());
		areaTable.getItems().clear();
		areaTable.setItems(Session.dataCollection.getAllAreaObservable());
	}

	@FXML
	private void showOnMapClicked()
	{
		Session.setMapShowArea(true);
		Session.setAreaToBeShown(selectedPlace.getGuiArea());
		Session.viewLoader.showMap(false);
		Session.controllers.getMapCtrl().setScene(Session.viewLoader.getCurrentScene());
	}
}
