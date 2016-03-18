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
	protected final IntegerProperty id;
	protected final StringProperty name;
	protected final StringProperty type;
	protected final StringProperty guiArea;
	/*this will ensure that the Place fits the ENUM constraints of the database. Probably not used
	* as users will most likely select types from a dropdown menu*/
	private boolean typeValid;
	private boolean initialised = false;


	public Place(String name, String type, int objectType, String guiArea)
	{
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleStringProperty(type);
		this.guiArea = new SimpleStringProperty(guiArea);
		int tempId;
		initialised = true;
		do {
			tempId = generateUniqueId();
		} while (Main.dataCollection.idExists(tempId));
		id = new SimpleIntegerProperty(tempId);
		typeValid = checkTypeValidity(type, objectType);
	}

	public Place(int id, String name, String type, int objectType, String guiArea)
	{
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleStringProperty(type);
		this.guiArea = new SimpleStringProperty(guiArea);
		typeValid = true; // defaults to true as any object with an id predefined has been read from a database
		initialised = true;
	}

	public static String[] getAreaTypes()
	{
		return areaTypes;
	}

	public static String[] getBuildingTypes()
	{
		return buildingTypes;
	}

	@Override
	public String toString()
	{
		if (initialised) {
			return "ID:\t" + id.get() + "\nName:\t" + name.get() + "\nType:\t" + type.get() + "\nValid:\t" + isTypeValid();
		}
		return null;
	}

	public int getId()
	{
		if (initialised) {
			return id.get();
		}
		return 0;
	}

	public String getName()
	{
		if (initialised) {
			return name.get();
		}
		return null;
	}

	private int generateUniqueId()
	{
		if (initialised) {
			Random random = new Random();
			return 100000 + random.nextInt(99999);
		}
		return 0;
	}

	@Override
	public final int compareTo(Place o)
	{
		return this.getId() - o.getId();
	}

	private boolean checkTypeValidity(String s, int o)
	{
		if (initialised) {
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
		}
		return false;
	}

	public final String getType()
	{
		if (initialised) {
			return type.get();
		}
		return null;
	}

	public final StringProperty typeProperty()
	{
		if (initialised) {
			return type;
		}
		return null;
	}

	public final boolean isTypeValid()
	{
		return initialised && typeValid;

	}

	public String getGuiArea()
	{
		return guiArea.get();
	}

	public StringProperty guiAreaProperty()
	{
		return guiArea;
	}
}
