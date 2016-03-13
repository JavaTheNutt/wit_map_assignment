package ie.wit.map.version_1.comparators;

import ie.wit.map.version_1.model.Place;

import java.util.Comparator;

/**
 * Created by Joe on 12/03/2016.
 */
public class IdComparator implements Comparator<Place>
{
	@Override
	public int compare(Place o1, Place o2)
	{
		return o1.getId() - o2.getId();
	}
}
