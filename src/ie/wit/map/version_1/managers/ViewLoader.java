package ie.wit.map.version_1.managers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Joe on 13/03/2016.
 */
public class ViewLoader
{
	public void displayAddBuilding(){
		Stage window = new Stage();
		window.setTitle("Add Building");
		window.initModality(Modality.APPLICATION_MODAL);
		VBox root;
		try {
			root = FXMLLoader.load(getClass().getResource("../views/add_building/AddBuilding.fxml"));
			Scene scene = new Scene(root, 400, 200);
			window.setScene(scene);
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void displayPlaceManager(){
		Stage window = new Stage();
		window.setTitle("Place Manager");
		window.initModality(Modality.APPLICATION_MODAL);
		TabPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("../views/place_manager/PlaceManager.fxml"));
			Scene scene = new Scene(root, 900, 600);
			window.setScene(scene);
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
