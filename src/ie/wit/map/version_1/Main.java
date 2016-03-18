package ie.wit.map.version_1;

import ie.wit.map.version_1.managers.ViewLoader;
import ie.wit.map.version_1.model.database.DataCollection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Joe on 11/03/2016.
 */
public class Main extends Application
{
	public static final DataCollection dataCollection = new DataCollection();
	public static ViewLoader viewLoader;
	private static Scene scene;
	private static boolean rootInSelection = false;

	public static void main(String[] args)
	{
		launch(args);
	}

	public static Scene getScene()
	{
		return scene;
	}

	/*private static void populateDb()
	{
		Building building01 = new Building("building01", 12, "Educational");
		Building building02 = new Building("building02", 23, "Educational");
		Building building03 = new Building("building03", 23, "Educational");
		Building building04 = new Building("building04", 23, "Educational");
		Building building05 = new Building("building05", 23, "Educational");
		Area area01 = new Area("Area01", "Car Park");
		Area area02 = new Area("Area02", "Car Park");
		Area area03 = new Area("Area03", "Car Park");
		Area area04 = new Area("Area04", "Car Park");
		Area area05 = new Area("Area05", "Car Park");
		List<Place> list = new ArrayList<>();
		list.add(building01);
		list.add(building02);
		list.add(building03);
		list.add(building04);
		list.add(building05);
		list.add(area01);
		list.add(area02);
		list.add(area03);
		list.add(area04);
		list.add(area05);
		list.forEach(place -> dataCollection.addToList(place));
	}*/

	public static void setScene(Scene scene1)
	{
		scene = scene1;
	}

	public static boolean isRootInSelection()
	{
		return rootInSelection;
	}

	public static void setRootInSelection(boolean rootInSelection)
	{
		Main.rootInSelection = rootInSelection;
	}

	// TODO: 13/03/2016 finish add building form
	// TODO: 16/03/2016 match areas on map to building/area object
	@Override
	public void start(Stage window) throws Exception
	{
		this.viewLoader = new ViewLoader(window);
		viewLoader.displayRoot(false);
	}
}
