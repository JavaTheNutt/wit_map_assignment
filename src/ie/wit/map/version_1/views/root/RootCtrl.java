package ie.wit.map.version_1.views.root;

import ie.wit.map.version_1.Main;
import ie.wit.map.version_1.managers.ViewLoader;
import ie.wit.map.version_1.model.gui.AreaManagement;
import ie.wit.map.version_1.model.gui.ClickableArea;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Joe on 12/03/2016.
 */
public class RootCtrl
{
	// TODO: 16/03/2016 make it possible to select an area from the add building menu
	private ViewLoader viewLoader = Main.viewLoader;
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

	private Group[] areaList = new Group[25];

	@FXML
	private void initialize(){
		assignGroups();
	}
	@FXML
	private void panelEntered()
	{
		Main.getScene().setCursor(Cursor.HAND);
	}

	private void assignGroups(){
		areaList[0] = new Group(carPark0101, carPark0102, carPark0103, carPark0104, carPark0105, carPark0106, carPark0107);
		areaList[1] = new Group(building0101, building0102);
		areaList[2] =  new Group(building0201);
		areaList[3] = new Group(building0301);
		areaList[4] = new Group(building0401);
		areaList[5] = new Group(building0501);
		areaList[6] = new Group(building0601);
		areaList[7] = new Group(building0701);
		areaList[8] = new Group(building0801);
		areaList[9] = new Group(building0901);
		areaList[10] = new Group(building1001);
		areaList[11] = new Group(building1101);
		areaList[12] = new Group(building1201);
		areaList[13] = new Group(building1301);
		areaList[14] = new Group(building1401);
		areaList[15] = new Group(building1501);
		areaList[16] = new Group(building1601, building1602);
		AreaManagement.addToList(new ArrayList<Group>(Arrays.asList(areaList)));

	}

	@FXML
	private void panelExited()
	{
		Main.getScene().setCursor(Cursor.DEFAULT);
	}
	@FXML
	private void addBuildingRequested(){
		viewLoader.displayPlaceManager();
	}
	@FXML
	private void mapClicked(Event e){
		System.out.println();
	}
}
