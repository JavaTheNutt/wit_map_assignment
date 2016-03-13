package ie.wit.map.version_1.model;

import ie.wit.map.version_1.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

/**
 * This is the abstract class that the other classes will inherit from
 */
public abstract class Place implements Comparable<Place>
{
	private static final String[] areaTypes = {"Car Park", "Sports Field", "Bike Shed"};
	private static final String[] buildingTypes = {"Recreational", "Educational", "Residential", "Other"};
	protected IntegerProperty id;
	protected StringProperty name;
	protected StringProperty type;
	/*this will ensure that the Place fits the ENUM constraints of the database. Probably not used
	* as users will most likely select types from a dropdown menu*/
	private boolean typeValid;


	public Place(String name, String type, int objectType)
	{
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleStringProperty(type);
		int tempId;
		do {
			tempId = generateUniqueId();
		} while (Main.dataCollection.idExists(tempId));
		id = new SimpleIntegerProperty(tempId);
		typeValid = checkTypeValidity(type, objectType);
	}

	public Place(int id, String name, String type, int objectType)
	{
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleStringProperty(type);
		typeValid = true; // defaults to true as any object with an id predefined has been read from a database
	}

	@Override
	public String toString()
	{
		return "ID:\t" + id.get() + "\nName:\t" + name.get() + "\nType:\t" + type.get();
	}

	public int getId()
	{
		return id.get();
	}


	public String getName()
	{
		return name.get();
	}

	public void setName(String name)
	{
		this.name = new SimpleStringProperty(name);
	}

	private int generateUniqueId()
	{
		Random random = new Random();
		return 100000 + random.nextInt(99999);
	}

	@Override
	public int compareTo(Place o)
	{
		return this.getId() - o.getId();
	}

	private boolean checkTypeValidity(String s, int o)
	{
		if (o == 0) {
			for (String buildingType : buildingTypes) {
				if (buildingType.equalsIgnoreCase(s)) {
					return true;
				}
			}
		} else {
			for (String areaType : areaTypes) {
				if (areaType.equalsIgnoreCase(s)) {
					return true;
				}
			}
		}
		return false;
	}

	public String getType()
	{
		return type.get();
	}

	public StringProperty typeProperty()
	{
		return type;
	}

	public boolean isTypeValid()
	{
		return typeValid;
	}
}
