package ie.wit.map.version_1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This will be an instance of a building
 */
public class Building extends Place
{
	private final IntegerProperty numRooms;
	private boolean initialised;

	public Building(int id, String name, int numRooms, String type, int guiArea)
	{
		super(id, name, type, 0, guiArea);
		this.numRooms = new SimpleIntegerProperty(numRooms);
		initialised = true;
	}

	public Building(String name, int numRooms, String type,  int guiArea)
	{
		super(name, type, 0, guiArea);
		this.numRooms = new SimpleIntegerProperty(numRooms);
		initialised = true;
	}

	@Override
	public String toString()
	{
		if (initialised) {
			return super.toString() + "\nRooms:\t" + numRooms.get() + "\n";
		}
		return null;
	}

	public int getNumRooms()
	{
		if (initialised) {
			return numRooms.get();
		}
		return 0;
	}


}
