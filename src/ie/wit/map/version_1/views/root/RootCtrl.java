package ie.wit.map.version_1.views.root;

import ie.wit.map.version_1.Main;
import ie.wit.map.version_1.managers.ViewLoader;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;

/**
 * Created by Joe on 12/03/2016.
 */
public class RootCtrl
{
	private ViewLoader viewLoader = Main.viewLoader;
	@FXML
	private void panelEntered()
	{
		Main.getScene().setCursor(Cursor.HAND);
	}

	@FXML
	private void panelExited()
	{
		Main.getScene().setCursor(Cursor.DEFAULT);
	}
	@FXML
	private void addBuildingRequested(){
		viewLoader.displayPlaceManager();
	}
	@FXML
	private void mapClicked(Event e){
		System.out.println();
	}
}
