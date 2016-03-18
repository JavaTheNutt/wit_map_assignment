package ie.wit.map.version_1.model.gui;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joe on 16/03/2016.
 */
public class AreaManagement
{
	private static final List<ClickableArea> areaList = new ArrayList<>();
	private static final List<String> idList = new ArrayList<>();

	// TODO: 18/03/2016 get an area by its id
	// TODO: 17/03/2016 return a list of all assigned/unassigned areas 
	// TODO: 17/03/2016 return  a list of all area ids 
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

	public static List<String> getIdList()
	{
		return idList;
	}

	public static List<ClickableArea> getUnassignedAreas()
	{
		return areaList.stream().filter(e -> !e.isAssigned()).collect(Collectors.toList());
	}

	public static List<ClickableArea> getAssignedAreas()
	{
		return areaList.stream().filter(e -> e.isAssigned()).collect(Collectors.toList());
	}
}
