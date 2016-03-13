package ie.wit.map.version_1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This will be an instance of a building
 */
public class Building extends Place
{
	private IntegerProperty numRooms;

	public Building(int id, String name, int numRooms, String type)
	{
		super(id, name, type, 0);
		this.numRooms = new SimpleIntegerProperty(numRooms);
	}

	public Building(String name, int numRooms, String type)
	{
		super(name, type, 0);
		this.numRooms = new SimpleIntegerProperty(numRooms);
	}

	@Override
	public String toString()
	{
		return super.toString() + "\nRooms:\t" + numRooms + "\n";
	}

	public int getNumRooms()
	{
		return numRooms.get();
	}

	public void setNumRooms(int numRooms)
	{
		this.numRooms = new SimpleIntegerProperty(numRooms);
	}

}
