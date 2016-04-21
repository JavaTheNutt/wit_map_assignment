package ie.wit.map.common.model;

/**
 * Created by Joe on 21/04/2016.
 */
public class Photo
{
	private final String path;
	private final int building;

	public Photo(String path, int building)
	{
		this.path = path;
		this.building = building;
	}

	public String getPath()
	{
		return path;
	}

	public int getBuilding()
	{
		return building;
	}

}
