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
	private boolean assigned;
	// TODO: 17/03/2016 track if an area is assigned or not

	public ClickableArea(String id, Pane[] group)
	{
		this.areaId = id;
		assigned = false;
		squares.addAll(new ArrayList<Pane>(Arrays.asList(group)));
	}

	public ClickableArea(String id, Pane square)
	{
		this.areaId = id;
		assigned = false;
		squares.add(square);
	}

	public void addSquare(Group square)
	{
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

	public boolean isAssigned()
	{
		return assigned;
	}

	public void setAssigned(boolean assigned)
	{
		this.assigned = assigned;
	}

	public boolean containsElement(Node element)
	{
		return squares.contains(element);
	}
}
