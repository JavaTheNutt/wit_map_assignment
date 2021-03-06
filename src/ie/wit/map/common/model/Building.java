package ie.wit.map.common.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This will be an instance of a building
 */
public class Building extends Place
{
	private final IntegerProperty numRooms;
	private boolean initialised;

	public Building(int id, String name, int numRooms, String type, String guiArea)
	{
		super(id, name, type, 0, guiArea);
		this.numRooms = new SimpleIntegerProperty(numRooms);
		initialised = true;
	}

	public Building(String name, int numRooms, String type, String guiArea)
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

	public final IntegerProperty numRoomsProperty()
	{
		if (initialised) {
			return numRooms;
		}
		return null;
	}


}
