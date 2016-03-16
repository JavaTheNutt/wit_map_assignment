package ie.wit.map.version_1.model.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 15/03/2016.
 */
public class ClickableArea
{
	private String areaId;
	private final List<ClickableSquare> squares = new ArrayList<>();

	public ClickableArea(){

	}
	public void addSquare(ClickableSquare square){
		squares.add(square);
	}


}
