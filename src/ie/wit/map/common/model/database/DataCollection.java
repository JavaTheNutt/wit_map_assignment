package ie.wit.map.common.model.database;

import ie.wit.map.common.exceptions.InvalidEntryException;
import ie.wit.map.common.model.Area;
import ie.wit.map.common.model.Building;
import ie.wit.map.common.model.Photo;
import ie.wit.map.common.model.Place;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

/**
 * Created by Joe on 12/03/2016.
 */
public class DataCollection
{
	private List<Place> totalList;
	private List<Integer> idList;
	private List<String> guiList;
	private List<Photo> photoList;

	public DataCollection()
	{
		// TODO: 18/03/2016 refactor this because it is unnessecary to check the connection
		idList = new ArrayList<>();
		getData();
		guiList = new ArrayList<>();
		if (totalList != null || totalList.size() > 0) {
			totalList.forEach(e -> {
				idList.add(e.getId());
				guiList.add(e.getGuiArea());
			});
		}
	}

	private void getData()
	{
		totalList = DatabaseConnection.getAllFromRecords();
		photoList = DatabaseConnection.getAllPhotos();
	}

	public List<Building> getBuildings()
	{
		return totalList.stream()
				.filter(m -> m instanceof Building)
				.map(m -> (Building) m)
				.collect(Collectors.toList());
	}

	public List<Area> getAreas()
	{
		return totalList.stream().filter(m -> m instanceof Area).map(m -> (Area) m).collect(Collectors.toList());
	}

	public boolean addToList(Place place)
	{
		try {
			if (place instanceof Building) {
				DatabaseConnection.addBuilding((Building) place);
			} else {
				DatabaseConnection.addArea((Area) place);
			}
		} catch (InvalidEntryException e) {
			System.out.println(e.getMessage());
			return false;
		}
		totalList.add(place);
		idList.add(place.getId());
		guiList.add(place.getGuiArea());
		return true;
	}

	public boolean idExists(int id)
	{
		return idList.contains(id);
	}

	public Place getById(Integer id)
	{
		for (Place place : totalList) {
			if (place.getId() == id) {
				return place;
			}
		}
		return null;

	}

	public List<Place> getTotalList()
	{
		return totalList;
	}

	public boolean guiIdExists(String guiId)
	{
		return guiList.contains(guiId);
	}

	public ObservableList<Place> getAllObservable()
	{
		return FXCollections.observableArrayList(totalList);
	}

	public ObservableList<Building> getAllBuildingObservable()
	{
		return FXCollections.observableArrayList(getBuildings());
	}

	public ObservableList<Area> getAllAreaObservable()
	{
		return FXCollections.observableArrayList(getAreas());
	}

	public void deleteItem(Place place)
	{
		DatabaseConnection.removeFromDatabase(place.getId(), place instanceof Building);
		delete(place);
	}

	private void delete(Place place)
	{
		totalList.remove(place);
		guiList.remove(place.getGuiArea());
		idList.remove(Integer.valueOf(place.getId()));
	}

	private Place getById(int id)
	{
		for (Place place : totalList) {
			if (place.getId() == id) {
				return place;
			}
		}
		return null;
	}

	public boolean updatePlace(Place place)
	{
		boolean check;
		if (place instanceof Building) {
			System.out.println("updating");
			check = DatabaseConnection.updateBuilding((Building) place);
		} else {
			System.out.println("updating");
			check = DatabaseConnection.updateArea((Area) place);
		}
		if (check) {
			getData();
		}
		return check;
	}
	public Place getByGuiId(String guiId){
		for (Place place : totalList) {
			if(guiId.equalsIgnoreCase(place.getGuiArea())){
				return place;
			}
		}
		return null;
	}
	public void addPhoto(Photo photo){
		DatabaseConnection.addPhoto(photo);
		getData();
	}
	public Map getBuildingDetails(){
		Map map = new HashMap<>();
		for (Building building : getBuildings()) {
			map.put(building.getName(), building.getId());
		}
		return map;
	}
	public Map getAreaDetails(){
		Map map = new HashMap<>();
		for (Area area : getAreas()) {
			map.put(area.getName(), area.getId());
		}
		return map;
	}
	public List<Photo> getPhotoList(){
		return photoList;
	}
}
