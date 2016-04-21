package ie.wit.map.version_1.views.root;

import ie.wit.map.version_1.Main;
import ie.wit.map.version_1.managers.ViewLoader;
import ie.wit.map.version_1.model.gui.AreaManagement;
import ie.wit.map.version_1.model.gui.ClickableArea;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 12/03/2016.
 */
public class RootCtrl
{
	// TODO: 16/03/2016 make it possible to select an area from the add building menu
	private ViewLoader viewLoader = Main.viewLoader;
	@FXML
	private MenuBar menuBar;
	@FXML
	private StackPane returnBox;
	@FXML
	private Pane carPark0101;
	@FXML
	private Pane carPark0102;
	@FXML
	private Pane carPark0103;
	@FXML
	private Pane carPark0104;
	@FXML
	private Pane carPark0105;
	@FXML
	private Pane carPark0106;
	@FXML
	private Pane carPark0107;
	@FXML
	private Pane carPark0108;
	@FXML
	private Pane carPark0109;
	@FXML
	private Pane carPark0201;
	@FXML
	private Pane carPark0202;
	@FXML
	private Pane carPark0301;
	@FXML
	private Pane building0101;
	@FXML
	private Pane building0102;
	@FXML
	private Pane building0201;
	@FXML
	private Pane building0301;
	@FXML
	private Pane building0401;
	@FXML
	private Pane building0501;
	@FXML
	private Pane building0601;
	@FXML
	private Pane building0701;
	@FXML
	private Pane building0801;
	@FXML
	private Pane building0901;
	@FXML
	private Pane building1001;
	@FXML
	private Pane building1101;
	@FXML
	private Pane building1201;
	@FXML
	private Pane building1301;
	@FXML
	private Pane building1401;
	@FXML
	private Pane building1501;
	@FXML
	private Pane building1601;
	@FXML
	private Pane building1602;
	@FXML
	private Pane pitch01;

	private ClickableArea[] areaList = new ClickableArea[20];
	private List<Pane> allPanels = new ArrayList<>();
	private List<ClickableArea> buildingList = new ArrayList<>();
	private List<ClickableArea> nonBuildingList = new ArrayList<>();

	@FXML
	private void initialize()
	{
		// TODO: 04/04/2016 create a new style class for assigned/unassigned areas 
		assignGroups();
		System.out.println(buildingList.size());
		menuBar.managedProperty().bind(menuBar.visibleProperty()); //remove space when hidden
		returnBox.managedProperty().bind(returnBox.visibleProperty());//remove space when hidden
		initScene();

	}

	private void initScene()
	{
		menuBar.setVisible(!Main.isRootInSelection());
		returnBox.setVisible(Main.isRootInSelection());
		if (Main.isBuildingSelection()) {
			for (ClickableArea area : buildingList) {
				String styleClass = area.isAssigned() ? "nonSelectableAreaShown" : "selectableAreaShown";
				for (Node pane : area.getSquares()) {

					pane.getStyleClass().add(styleClass);
				}
			}
			for (ClickableArea area : nonBuildingList) {
				for (Node pane : area.getSquares()) {
					pane.getStyleClass().add("nonSelectableAreaShown");
				}
			}
		}
	}

	@FXML
	private void panelEntered()
	{
		Main.getScene().setCursor(Cursor.HAND);
	}

	// TODO: 18/03/2016 make assigned/unassigned areas differently colored
	private void assignGroups()
	{
		Pane[] carparkList01 = {carPark0101, carPark0102, carPark0103, carPark0104, carPark0105, carPark0106, carPark0107, carPark0108, carPark0109};
		Pane[] buildingList01 = {building0101, building0102};
		Pane[] buildingList16 = {building1601, building1602};
		Pane[] carPark02 = {carPark0201, carPark0202};
		areaList[0] = new ClickableArea("carpark01", carparkList01, false);
		nonBuildingList.add(areaList[0]);
		areaList[1] = new ClickableArea("building01", buildingList01, true);
		buildingList.add(areaList[1]);
		areaList[2] = new ClickableArea("building02", building0201, true);
		buildingList.add(areaList[2]);
		areaList[3] = new ClickableArea("building03", building0301, true);
		buildingList.add(areaList[3]);
		areaList[4] = new ClickableArea("building04", building0401, true);
		buildingList.add(areaList[4]);
		areaList[5] = new ClickableArea("building05", building0501, true);
		buildingList.add(areaList[5]);
		areaList[6] = new ClickableArea("building06", building0601, true);
		buildingList.add(areaList[6]);
		areaList[7] = new ClickableArea("building07", building0701, true);
		buildingList.add(areaList[7]);
		areaList[8] = new ClickableArea("building08", building0801, true);
		buildingList.add(areaList[8]);
		areaList[9] = new ClickableArea("building09", building0901, true);
		buildingList.add(areaList[9]);
		areaList[10] = new ClickableArea("building10", building1001, true);
		buildingList.add(areaList[10]);
		areaList[11] = new ClickableArea("building11", building1101, true);
		buildingList.add(areaList[11]);
		areaList[12] = new ClickableArea("building12", building1201, true);
		buildingList.add(areaList[12]);
		areaList[13] = new ClickableArea("building13", building1301, true);
		buildingList.add(areaList[13]);
		areaList[14] = new ClickableArea("building14", building1401, true);
		buildingList.add(areaList[14]);
		areaList[15] = new ClickableArea("building15", building1501, true);
		buildingList.add(areaList[15]);
		areaList[16] = new ClickableArea("building16", buildingList16, true);
		buildingList.add(areaList[16]);
		areaList[17] = new ClickableArea("pitch01", pitch01, false);
		nonBuildingList.add(areaList[17]);
		areaList[18] = new ClickableArea("carPark02", carPark02, false);
		nonBuildingList.add(areaList[18]);
		areaList[19] = new ClickableArea("carPark03", carPark0301, false);
		nonBuildingList.add(areaList[19]);
		AreaManagement.addToList(new ArrayList<ClickableArea>(Arrays.asList(areaList)));
		for (ClickableArea clickableArea : areaList) {
			if (Main.dataCollection.guiIdExists(clickableArea.getAreaId())) {
				clickableArea.setAssigned(true);
			}
			for (Node node : clickableArea.getSquares()) {
				allPanels.add((Pane) node);
			}
		}
	}

	@FXML
	private void panelExited()
	{
		Main.getScene().setCursor(Cursor.DEFAULT);
	}

	@FXML
	private void addBuildingRequested()
	{
		viewLoader.displayPlaceManager();
	}

	// TODO: 19/03/2016 validate the choice and close the window
	@FXML
	private void panelClicked(Event e)
	{
		if (Main.isRootInSelection()) {
			inSelectionClick(e);
		} else {
			inDisplayClick(e);
		}
		System.out.println(AreaManagement.findArea((Node) e.getSource()));
	}

	private void inSelectionClick(Event e)
	{
		System.out.println("selection mode click");
		String tempId = AreaManagement.findArea((Node) e.getSource());
		ClickableArea areaClicked = AreaManagement.getArea((Node) e.getSource());
		if (areaClicked.isBuilding()) {
			AreaManagement.setTempAreaId(tempId);
			viewLoader.getCurrentWindow().close();
		}

	}

	private void inDisplayClick(Event e)
	{
		System.out.println("display mode click");
	}
}
