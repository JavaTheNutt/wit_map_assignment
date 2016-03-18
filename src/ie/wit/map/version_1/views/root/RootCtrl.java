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

	@FXML
	private void initialize()
	{
		assignGroups();
		menuBar.managedProperty().bind(menuBar.visibleProperty());
		returnBox.managedProperty().bind(returnBox.visibleProperty());
		if (Main.isRootInSelection()) {
			menuBar.setVisible(false);
			returnBox.setVisible(true);
			for (Pane panel : allPanels) {
				panel.getStyleClass().add("clickablePanelShown");
				panel.getStyleClass().remove("clickablePanelHidden");
			}
		} else {
			menuBar.setVisible(true);
			returnBox.setVisible(false);
			for (Pane panel : allPanels) {
				panel.getStyleClass().remove("clickablePanelShown");
				panel.getStyleClass().add("clickablePanelHidden");
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
		areaList[0] = new ClickableArea("carpark01", carparkList01);
		areaList[1] = new ClickableArea("building01", buildingList01);
		areaList[2] = new ClickableArea("building02", building0201);
		areaList[3] = new ClickableArea("building03", building0301);
		areaList[4] = new ClickableArea("building04", building0401);
		areaList[5] = new ClickableArea("building05", building0501);
		areaList[6] = new ClickableArea("building06", building0601);
		areaList[7] = new ClickableArea("building07", building0701);
		areaList[8] = new ClickableArea("building08", building0801);
		areaList[9] = new ClickableArea("building09", building0901);
		areaList[10] = new ClickableArea("building10", building1001);
		areaList[11] = new ClickableArea("building11", building1101);
		areaList[12] = new ClickableArea("building12", building1201);
		areaList[13] = new ClickableArea("building13", building1301);
		areaList[14] = new ClickableArea("building14", building1401);
		areaList[15] = new ClickableArea("building15", building1501);
		areaList[16] = new ClickableArea("building16", buildingList16);
		areaList[17] = new ClickableArea("pitch01", pitch01);
		areaList[18] = new ClickableArea("carPark02", carPark02);
		areaList[19] = new ClickableArea("carPark03", carPark0301);
		AreaManagement.addToList(new ArrayList<ClickableArea>(Arrays.asList(areaList)));
		for (ClickableArea clickableArea : areaList) {
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

	@FXML
	private void panelClicked(Event e)
	{
		System.out.println(AreaManagement.findArea((Node) e.getSource()));
	}
}
