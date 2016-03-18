package ie.wit.map.version_1.managers;

import ie.wit.map.version_1.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Joe on 13/03/2016.
 */
public class ViewLoader
{
	private final Stage window;

	public ViewLoader(Stage window)
	{
		this.window = window;
	}

	public void displayPlaceManager()
	{
		window.setTitle("Place Manager");
		VBox root;
		try {
			root = FXMLLoader.load(getClass().getResource("../views/place_manager/PlaceManager.fxml"));
			Scene scene = new Scene(root, 900, 600);
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayRoot(boolean selection)
	{
		BorderPane root = null;
		Main.setRootInSelection(selection);
		try {
			root = FXMLLoader.load(getClass().getResource("../views/root/Root.fxml"));
			window.setResizable(false);
			window.setTitle("Towers of Hanoi Version 2");
			Scene scene = new Scene(root, 1000, 600);
			Main.setScene(scene);
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
