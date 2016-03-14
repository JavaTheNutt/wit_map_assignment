package ie.wit.map.version_1.model;

/**
 * Created by Joe on 11/03/2016.
 */
public class Area extends Place
{
	private boolean initialised;

	public Area(int id, String name, String type)
	{
		super(id, name, type, 1);
		initialised = true;
	}

	public Area(String name, String type)
	{
		super(name, type, 1);
		initialised = true;
	}

}
