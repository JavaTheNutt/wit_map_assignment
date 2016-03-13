package ie.wit.map.version_1.model.database;

import ie.wit.map.version_1.exceptions.InvalidEntryException;
import ie.wit.map.version_1.model.Area;
import ie.wit.map.version_1.model.Building;
import ie.wit.map.version_1.model.Place;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joe on 12/03/2016.
 */
public class DataCollection
{
	private List<Place> totalList;
	private List<Integer> idList;

	public DataCollection()
	{
		DatabaseConnection.connect();
		if (DatabaseConnection.checkConnection()) {
			idList = new ArrayList<>();
			totalList = DatabaseConnection.getAllFromRecords();
			if (totalList != null || totalList.size() > 0) {
				totalList.forEach(e -> idList.add(e.getId()));
			}
		}
	}

	public List<Place> getBuildings()
	{
		return totalList.stream().filter(m -> m instanceof Building).collect(Collectors.toList());
	}

	public List<Place> getAreas()
	{
		return totalList.stream().filter(m -> m instanceof Area).collect(Collectors.toList());
	}

	public boolean addToList(Place place)
	{
		boolean check = false;
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

	public ObservableList<Place> getAllObservable()
	{
		return FXCollections.observableArrayList(totalList);
	}

	public ObservableList<Place> getAllBuildingObservable()
	{
		return FXCollections.observableArrayList(getBuildings());
	}

	public ObservableList<Place> getAllAreaObservable()
	{
		return FXCollections.observableArrayList(getAreas());
	}
}
