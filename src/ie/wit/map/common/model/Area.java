package ie.wit.map.common.model;

/**
 * Created by Joe on 11/03/2016.
 */
public class Area extends Place
{
	private boolean initialised;

	public Area(int id, String name, String type, String guiArea)
	{
		super(id, name, type, 1, guiArea);
		initialised = true;
	}

	public Area(String name, String type, String guiArea)
	{
		super(name, type, 1, guiArea);
		initialised = true;
	}

}
