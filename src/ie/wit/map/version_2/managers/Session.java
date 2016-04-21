package ie.wit.map.version_2.managers;

import ie.wit.map.common.model.Area;
import ie.wit.map.common.model.Building;
import ie.wit.map.common.model.Place;
import ie.wit.map.common.model.database.DataCollection;
import ie.wit.map.version_2.views.root.RootCtrl;

/**
 * Created by Joe on 18/04/2016.
 */
public class Session
{
	public static DataCollection dataCollection;
	public static ViewLoader viewLoader;
	public static ControllerMgmt controllers;

	private static boolean mapInSelection;
	private static boolean mapInView;
	private static boolean formUsed;
	private static boolean buildingSelection;
	private static boolean inEdit;
	private static boolean buildingInEdit;
	private static boolean mapShowArea;
	private static boolean tableShowItem;

	private static RootCtrl rootCtrl;

	private static String tempName;
	private static String tempType;
	private static String tempFunction;
	private static String tempNumRooms;
	private static String areaToBeShown;

	private static Place areaToShowInTable;
	private static Building buildingToEdit;
	private static Area areaToEdit;

	public static void initSession()
	{
		dataCollection = new DataCollection();
		controllers = new ControllerMgmt();
		mapInSelection = false;
		mapInView = true;
		formUsed = false;
		viewLoader = new ViewLoader();
	}

	public static void saveTempForm(String currentName,
	                                String currentType,
	                                String currentFunction,
	                                String currentNumRooms)
	{
		tempName = currentName;
		tempType = currentType;
		tempFunction = currentFunction;
		tempNumRooms = currentNumRooms;
	}

	public static String[] getTempForm()
	{
		String[] tempForm = {tempName, tempType, tempFunction, tempNumRooms};
		return tempForm;
	}

	public static boolean isMapInSelection()
	{
		return mapInSelection;
	}

	// TODO: 19/04/2016 refactor this to set mapInView when setting map in selection 
	public static void setMapInSelection(boolean mapInSelection)
	{
		if (!mapInView) {
			mapInView = true;
		}
		Session.mapInSelection = mapInSelection;
	}

	public static boolean isBuildingSelection()
	{
		return buildingSelection;
	}

	public static void setBuildingSelection(boolean buildingSelection)
	{
		Session.buildingSelection = buildingSelection;
	}

	public static boolean isMapInView()
	{
		return mapInView;
	}

	public static void setMapInView(boolean mapInView)
	{
		Session.mapInView = mapInView;
	}

	public static RootCtrl getRootCtrl()
	{
		return rootCtrl;
	}

	public static void setRootCtrl(RootCtrl rootCtrl)
	{
		Session.rootCtrl = rootCtrl;
	}

	public static boolean isFormUsed()
	{
		return formUsed;
	}

	public static void setFormUsed(boolean formUsed)
	{
		Session.formUsed = formUsed;
	}

	public static boolean isInEdit()
	{
		return inEdit;
	}

	public static void setInEdit(boolean inEdit)
	{
		Session.inEdit = inEdit;
	}

	public static boolean isBuildingInEdit()
	{

		return inEdit && buildingInEdit;
	}

	public static void setBuildingInEdit(boolean buildingInEdit)
	{
		Session.buildingInEdit = buildingInEdit;
	}

	public static Building getBuildingToEdit()
	{
		return buildingToEdit;
	}

	public static void setBuildingToEdit(Building buildingToEdit)
	{
		Session.buildingToEdit = buildingToEdit;
	}

	public static Area getAreaToEdit()
	{
		return areaToEdit;
	}

	public static void setAreaToEdit(Area areaToEdit)
	{
		Session.areaToEdit = areaToEdit;
	}

	public static boolean isMapShowArea()
	{
		return mapShowArea;
	}

	public static void setMapShowArea(boolean mapShowArea)
	{
		Session.mapShowArea = mapShowArea;
	}

	public static String getAreaToBeShown()
	{
		return areaToBeShown;
	}

	public static void setAreaToBeShown(String areaToBeShown)
	{
		Session.areaToBeShown = areaToBeShown;
	}
	public static void storeItemFromGui(String guiId){
		areaToShowInTable  = dataCollection.getByGuiId(guiId);
	}

	public static boolean isTableShowItem()
	{
		return tableShowItem;
	}

	public static void setTableShowItem(boolean tableShowItem)
	{
		Session.tableShowItem = tableShowItem;
	}

	public static Place getAreaToShowInTable()
	{
		return areaToShowInTable;
	}
}
