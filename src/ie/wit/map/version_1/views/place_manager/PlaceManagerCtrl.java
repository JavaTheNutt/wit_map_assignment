package ie.wit.map.version_1.views.place_manager;

import ie.wit.map.version_1.Main;
import javafx.fxml.FXML;

/**
 * Created by Joe on 19/03/2016.
 */
public class PlaceManagerCtrl
{
	// TODO: 04/04/2016 not working as place manager is now a modal of the root control 
	@FXML
	private void returnClicked()
	{
		Main.viewLoader.displayRoot();
	}


}
