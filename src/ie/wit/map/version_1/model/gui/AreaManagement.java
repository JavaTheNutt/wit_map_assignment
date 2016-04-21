package ie.wit.map.version_1.model.gui;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the
 */
public class AreaManagement
{
	private static final List<ClickableArea> areaList = new ArrayList<>();
	private static final List<String> idList = new ArrayList<>();
	private static final List<GuiLink> modelViewLink = new ArrayList<>();
	private static String tempAreaId;
	private static boolean areaSelected = false;

	// TODO: 18/03/2016 get an area by its id
	// TODO: 17/03/2016 return a list of all assigned/unassigned areas 
	// TODO: 17/03/2016 return  a list of all area ids 
	// TODO: 16/04/2016 get a list of all assigned ids
	public static void addToList(List<ClickableArea> group)
	{
		for (ClickableArea clickableArea : group) {
			areaList.add(clickableArea);
			idList.add(clickableArea.getAreaId());
		}
	}

	public static int getIndex(ClickableArea group)
	{
		if (areaList.contains(group)) {
			return areaList.indexOf(group);
		}
		return -10;
	}

	public static ClickableArea getByIndex(int index)
	{
		return areaList.get(index);
	}

	public static String findArea(Node node)
	{
		for (ClickableArea area : areaList) {
			if (area.containsElement(node)) {
				return area.getAreaId();
			}
		}
		return null;
	}

	public static ClickableArea getArea(Node node)
	{
		String id = findArea(node);
		ClickableArea area = getById(id);
		return area;
	}

	public static List<String> getIdList()
	{
		return idList;
	}

	public static List<ClickableArea> getUnassignedAreas()
	{
		return areaList.stream().filter(e -> !e.isAssigned()).collect(Collectors.toList());
	}

	public static void addLink(GuiLink link)
	{
		modelViewLink.add(link);
		getById(link.getGuiId()).setAssigned(true);
	}

	public static List<ClickableArea> getAssignedAreas()
	{
		return areaList.stream().filter(e -> e.isAssigned()).collect(Collectors.toList());
	}

	public static ClickableArea getById(String id)
	{
		if (idList.contains(id)) {
			for (ClickableArea clickableArea : areaList) {
				if (clickableArea.getAreaId().equalsIgnoreCase(id)) {
					return clickableArea;
				}
			}
		}
		return null;
	}

	public static String getTempAreaId()
	{
		return tempAreaId;
	}

	public static void setTempAreaId(String tempAreaId)
	{
		AreaManagement.tempAreaId = tempAreaId;
	}
}
