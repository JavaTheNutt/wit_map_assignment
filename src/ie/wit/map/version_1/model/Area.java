package ie.wit.map.version_1.model;

/**
 * Created by Joe on 11/03/2016.
 */
public class Area extends Place
{
	private boolean initialised;

	public Area(int id, String name, String type, int guiArea)
	{
		super(id, name, type, 1, guiArea);
		initialised = true;
	}

	public Area(String name, String type, int guiArea)
	{
		super(name, type, 1, guiArea);
		initialised = true;
	}

}
