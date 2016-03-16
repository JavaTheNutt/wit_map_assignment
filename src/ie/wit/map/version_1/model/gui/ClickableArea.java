package ie.wit.map.version_1.model.gui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 15/03/2016.
 */
public class ClickableArea
{
	private final String areaId;
	private final List<Node> squares = new ArrayList<>();

	public ClickableArea(String id){
		this.areaId = id;
	}
	public void addSquare(Group square){
		squares.addAll(square.getChildren());
	}

	public List<Node> getSquares()
	{
		return squares;
	}

	public String getAreaId()
	{
		return areaId;
	}
}
