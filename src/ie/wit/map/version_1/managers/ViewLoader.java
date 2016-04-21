package ie.wit.map.version_1.managers;

import ie.wit.map.version_1.Main;
import ie.wit.map.version_1.model.gui.AreaManagement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by Joe on 13/03/2016.
 */
public class ViewLoader
{
	private final Stage window;
	private Stage currentWindow;

	public ViewLoader(Stage window)
	{
		this.window = window;
		currentWindow = window;
	}

	public void displayPlaceManager()
	{
		Stage window = new Stage();
		window.setTitle("Place Manager");
		currentWindow = window;
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("../views/place_manager/PlaceManager.fxml"));
			TabPane center = (TabPane) root.getCenter();
			center.getTabs()
					.add(new Tab("Buildings",
							FXMLLoader.load(getClass().getResource("../views/place_manager/Buildings.fxml"))));
			center.getTabs()
					.add(new Tab("Areas",
							FXMLLoader.load(getClass().getResource("../views/place_manager/Areas.fxml"))));
			Scene scene = new Scene(root, 900, 600);
			window.setScene(scene);
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO: 19/03/2016 create new displayRoot to display it as a modal
	public void displayRoot()
	{
		BorderPane root = null;
		Main.setRootInSelection(false); //remove boolean and default to false
		currentWindow = window;
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

	public void displayRootSelection(Consumer<String> callback)
	{
		Stage window = new Stage();
		currentWindow = window;
		Main.setRootInSelection(true);
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("../views/root/Root.fxml"));
			window.setResizable(false);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("WIT Map");
			Scene scene = new Scene(root, 1000, 600);
			Main.setScene(scene);
			window.setScene(scene);
			window.showAndWait();
			String tempId = AreaManagement.getTempAreaId();

			callback.accept(tempId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getCurrentWindow()
	{
		return currentWindow;
	}
}
