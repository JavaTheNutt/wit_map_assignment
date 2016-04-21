package ie.wit.map.version_1.model.gui;

/**
 * Created by Joe on 16/04/2016.
 */
public class GuiLink
{
	private final int itemId;
	private final String guiId;

	public GuiLink(int itemId, String guiId)
	{
		this.itemId = itemId;
		this.guiId = guiId;
	}

	public int getItemId()
	{
		return itemId;
	}

	public String getGuiId()
	{
		return guiId;
	}
}
