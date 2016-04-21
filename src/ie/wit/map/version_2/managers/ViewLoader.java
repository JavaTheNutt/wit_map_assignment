package ie.wit.map.version_2.managers;

import ie.wit.map.version_2.views.add_item.AddItemCtrl;
import ie.wit.map.version_2.views.modal_root.ModalRootCtrl;
import ie.wit.map.version_2.views.root.RootCtrl;
import ie.wit.map.version_2.views.show_items.ShowItemCtrl;
import ie.wit.map.version_2.views.map.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Joe on 18/04/2016.
 */
public class ViewLoader
{
	private static final String ROOT_URL = "../views/root/Root.fxml";
	private static final String MAP_URL = "../views/map/map.fxml";
	private static final String ADD_ITEM_URL = "../views/add_item/Add_Item.fxml";
	private static final String MODAL_ROOT_URL = "../views/modal_root/ModalRoot.fxml";
	private static final String SHOW_ITEMS_URL = "../views/show_items/ShowItems.fxml";
	private static final String ADD_PHOTO_URL = "../views/add_photo/AddPhoto.fxml";
	private static final String SHOW_PHOTOS_URL = "../views/show_photos/ShowPhotos.fxml";
	private final Stage window;
	private final BorderPane root;
	private final Scene currentScene;
	private Stage modalWindow;
	private Scene modalScene;
	private Map mapCtrl;


	public ViewLoader()
	{
		window = new Stage();
		window.setTitle("WIT Map");
		root = initRoot();
		showMap(false);
		currentScene = new Scene(root, 900, 600);
		window.setScene(currentScene);
		mapCtrl.setScene(currentScene);
		window.setResizable(false);
		window.show();
	}

	private BorderPane initRoot()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ROOT_URL));
			BorderPane pane = loader.load();
			RootCtrl rc = loader.getController();
			Session.controllers.setRootCtrl(rc);
			Session.setRootCtrl(rc);
			return pane;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void showAddItem()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ADD_ITEM_URL));
		try {
			Node node = loader.load();
			AddItemCtrl addItemCtrl = loader.getController();
			Session.controllers.setAddItemCtrl(addItemCtrl);
			Session.setMapInView(false);
			Session.getRootCtrl().toggleButton();
			root.setCenter(node);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showMap(boolean inSelection)
	{
		Session.setMapInView(true);
		Session.setMapInSelection(inSelection);
		Session.getRootCtrl().toggleButton();
		root.setCenter(loadSelectionMap());
	}

	private Node loadSnippet(String path)
	{
		try {
			return FXMLLoader.load(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Node loadSelectionMap()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(MAP_URL));
		try {
			Node mapRoot = loader.load();
			mapCtrl = (Map) loader.getController();
			Session.controllers.setMapCtrl(mapCtrl);
			return mapRoot;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setMap()
	{

	}

	public Scene getCurrentScene()
	{
		return currentScene;
	}

	public void initModalRoot()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(MODAL_ROOT_URL));
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select Area");
		try {
			BorderPane root = loader.load();
			Session.controllers.setModalRootCtrl((ModalRootCtrl) loader.getController());
			Node map = loadSelectionMap();
			root.setCenter(map);
			modalScene = new Scene(root, 900, 600);
			window.setScene(modalScene);
			modalWindow = window;
			mapCtrl.setScene(modalScene);
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Scene getModalScene()
	{
		return modalScene;
	}

	public void setModalScene(Scene modalScene)
	{
		this.modalScene = modalScene;
	}

	public void closeModal()
	{
		modalWindow.close();
	}

	public void showTable()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(SHOW_ITEMS_URL));
		try {
			HBox tp = loader.load();
			ShowItemCtrl ctrl = loader.getController();
			Session.controllers.setShowItemCtrl(ctrl);
			root.setCenter(tp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getWindow()
	{
		return window;
	}
	public void showAddPhoto(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ADD_PHOTO_URL));
		try{
			StackPane pane = loader.load();
			Session.controllers.setAddPhotoCtrl(loader.getController());
			root.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showPhotos(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(SHOW_PHOTOS_URL));
		try {
			Node node = loader.load();
			Session.controllers.setShowPhotosCtrl(loader.getController());
			root.setCenter(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
