package ie.wit.map.version_1.managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
			Scene scene = new Scene(root, 500, 700);
			window.setScene(scene);
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
