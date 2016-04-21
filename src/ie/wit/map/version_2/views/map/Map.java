package ie.wit.map.version_2.views.map;

import ie.wit.map.common.model.gui.ClickableArea;
import ie.wit.map.version_2.managers.Session;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 18/04/2016.
 */
public class Map
{
	private static final List<ClickableArea> allList = new ArrayList<>();
	private static final List<ClickableArea> buildingList = new ArrayList<>();
	private static final List<ClickableArea> areaList = new ArrayList<>();
	@FXML
	private Pane building01Pane01;
	@FXML
	private Pane building02Pane01;
	@FXML
	private Pane building02Pane02;
	@FXML
	private Pane building03Pane01;
	@FXML
	private Pane building04Pane01;
	@FXML
	private Pane building05Pane01;
	@FXML
	private Pane building06Pane01;
	@FXML
	private Pane building07Pane01;
	@FXML
	private Pane building08Pane01;
	@FXML
	private Pane building09Pane01;
	@FXML
	private Pane building10Pane01;
	@FXML
	private Pane building11Pane01;
	@FXML
	private Pane building12Pane01;
	@FXML
	private Pane building13Pane01;
	@FXML
	private Pane building14Pane01;
	@FXML
	private Pane building15Pane01;
	@FXML
	private Pane area01Pane01;
	@FXML
	private Pane area02Pane01;
	@FXML
	private Pane area03Pane01;
	@FXML
	private Pane area03Pane02;
	@FXML
	private Pane area04Pane01;
	@FXML
	private Pane area04Pane02;
	@FXML
	private Pane area04Pane03;
	private Scene scene;
	private boolean buildingSelection;

	@FXML
	private void initialize()
	{
		assignGroups();
		if (Session.isMapInSelection()) {
			selectionMode();
		} else if(Session.isMapShowArea()) {
			showAreaMode();
		} else{
			standardMode();
		}
	}
	private void showAreaMode(){
		for (ClickableArea clickableArea : allList) {
			if(clickableArea.getAreaId().equalsIgnoreCase(Session.getAreaToBeShown())){
				for (Node node : clickableArea.getSquares()) {
					node.getStyleClass().add("clickablePanelShown");
				}
			}
		}
	}


	private void getScene()
	{
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			scene = Session.isMapInSelection() ? Session.viewLoader.getModalScene() : Session.viewLoader.getCurrentScene();
		});
	}

	private void standardMode()
	{

	}

	private void selectionMode()
	{
		buildingSelection = Session.isBuildingSelection();
		initialStyleBlocks(Session.isBuildingSelection());
	}

	private void assignGroups()
	{
		Pane[] building02List = {building02Pane01, building02Pane02};
		Pane[] area03List = {area03Pane01, area03Pane02};
		Pane[] area04List = {area04Pane01, area04Pane02, area04Pane03};
		ClickableArea building01 = new ClickableArea("building01", building01Pane01, true);
		allList.add(building01);
		buildingList.add(building01);
		ClickableArea building02 = new ClickableArea("building02", building02List, true);
		allList.add(building02);
		buildingList.add(building02);
		ClickableArea building03 = new ClickableArea("building03", building03Pane01, true);
		allList.add(building03);
		buildingList.add(building03);
		ClickableArea building04 = new ClickableArea("building04", building04Pane01, true);
		allList.add(building04);
		buildingList.add(building04);
		ClickableArea building05 = new ClickableArea("building05", building05Pane01, true);
		allList.add(building05);
		buildingList.add(building05);
		ClickableArea building06 = new ClickableArea("building06", building06Pane01, true);
		allList.add(building06);
		buildingList.add(building06);
		ClickableArea building07 = new ClickableArea("building07", building07Pane01, true);
		allList.add(building07);
		buildingList.add(building07);
		ClickableArea building08 = new ClickableArea("building08", building08Pane01, true);
		allList.add(building08);
		buildingList.add(building08);
		ClickableArea building09 = new ClickableArea("building09", building09Pane01, true);
		allList.add(building09);
		buildingList.add(building09);
		ClickableArea building10 = new ClickableArea("building10", building10Pane01, true);
		allList.add(building10);
		buildingList.add(building10);
		ClickableArea building11 = new ClickableArea("building11", building11Pane01, true);
		allList.add(building11);
		buildingList.add(building11);
		ClickableArea building12 = new ClickableArea("building12", building12Pane01, true);
		allList.add(building12);
		buildingList.add(building12);
		ClickableArea building13 = new ClickableArea("building13", building13Pane01, true);
		allList.add(building13);
		buildingList.add(building13);
		ClickableArea building14 = new ClickableArea("building14", building14Pane01, true);
		allList.add(building14);
		buildingList.add(building14);
		ClickableArea building15 = new ClickableArea("building15", building15Pane01, true);
		allList.add(building15);
		buildingList.add(building15);

		ClickableArea area01 = new ClickableArea("area01", area01Pane01, false);
		allList.add(area01);
		areaList.add(area01);
		ClickableArea area02 = new ClickableArea("area02", area02Pane01, false);
		allList.add(area02);
		areaList.add(area02);
		ClickableArea area03 = new ClickableArea("area03", area03List, false);
		allList.add(area03);
		areaList.add(area03);
		ClickableArea area04 = new ClickableArea("area04", area04List, false);
		allList.add(area04);
		areaList.add(area04);
		for (ClickableArea clickableArea : allList) {
			checkAssigned(clickableArea);
		}
	}

	private void initialStyleBlocks(boolean isBuilding)
	{
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			styleBlocks(isBuilding);
		});
		t.start();
	}

	private void checkAssigned(ClickableArea area)
	{
		if (Session.dataCollection.guiIdExists(area.getAreaId())) {
			area.setAssigned(true);
		}
	}

	private void styleBlocks(boolean isBuilding)
	{
		List<ClickableArea> validList = isBuilding ? buildingList : areaList;
		List<ClickableArea> invalidList = !isBuilding ? buildingList : areaList;
		for (ClickableArea clickableArea : validList) {
			boolean assigned = clickableArea.isAssigned();
			for (Node node : clickableArea.getSquares()) {
				if (!assigned) {
					node.getStyleClass().add("selectableAreaShown");
				} else {
					node.getStyleClass().add("nonSelectableAreaShown");
				}
			}
		}
		for (ClickableArea clickableArea : invalidList) {
			for (Node node : clickableArea.getSquares()) {
				node.getStyleClass().add("nonSelectableAreaShown");
			}
		}

	}

	@FXML
	private void panelExited(Event e)
	{
		scene.setCursor(Cursor.DEFAULT);
	}

	@FXML
	private void panelEntered(Event e)
	{
		scene.setCursor(Cursor.HAND);
	}

	@FXML
	private void panelClicked(Event e)
	{
		String areaId = getId((Node) e.getSource());
		if (Session.isMapInSelection()) {

			if (buildingSelection) {
				if (areaId.length() > 0 && areaId.contains("building")) {
					Session.controllers.getModalRootCtrl().mapAreaClicked(areaId);
				}
			} else {
				if (areaId.length() > 0 && !areaId.contains("building")) {
					Session.controllers.getModalRootCtrl().mapAreaClicked(areaId);
				}
			}
		}else{
			Session.storeItemFromGui(areaId);
			Session.setTableShowItem(true);
			Session.viewLoader.showTable();
		}
	}

	private String getId(Node node)
	{
		for (ClickableArea clickableArea : allList) {
			if (clickableArea.containsElement(node)) {
				return clickableArea.getAreaId();
			}
		}
		return null;
	}

	public void setScene(Scene scene)
	{
		this.scene = scene;
	}
}
