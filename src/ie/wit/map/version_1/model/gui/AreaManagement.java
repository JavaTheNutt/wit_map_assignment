package ie.wit.map.version_1.model.gui;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 16/03/2016.
 */
public class AreaManagement
{
	private static final List<Group> areaList = new ArrayList<>();

	public static void addToList(List<Group> group){
		areaList.addAll(group);
	}
	public static int getIndex(Group group){
		if (areaList.contains(group)){
			return areaList.indexOf(group);
		}
		return -10;
	}
	public static Group getByIndex(int index){
		return areaList.get(index);
	}

}
