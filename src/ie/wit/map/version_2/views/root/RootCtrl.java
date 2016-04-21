package ie.wit.map.version_2.views.root;

import ie.wit.map.version_2.managers.Session;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

/**
 * Created by Joe on 18/04/2016.
 */
public class RootCtrl
{
	@FXML
	private MenuItem addItemButton;
	@FXML
	private StackPane returnPane;

	@FXML
	private void initialize()
	{
		returnPane.managedProperty().bind(returnPane.visibleProperty());
	}


	@FXML
	private void showAddItem()
	{
		Session.viewLoader.showAddItem();
	}

	@FXML
	private void showItems()
	{
		Session.viewLoader.showTable();
	}
	@FXML
	private void showAddPhoto(){Session.viewLoader.showAddPhoto();}

	@FXML
	private void returnToMap()
	{
		Session.viewLoader.showMap(false);
		Session.controllers.getMapCtrl().setScene(Session.viewLoader.getCurrentScene());
	}

	public void toggleButton()
	{
		returnPane.setVisible(!Session.isMapInView());
	}
	@FXML
	private void showPhotos(){
		Session.viewLoader.showPhotos();
	}
}
