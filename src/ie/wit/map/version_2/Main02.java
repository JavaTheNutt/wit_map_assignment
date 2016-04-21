package ie.wit.map.version_2;

import ie.wit.map.version_2.managers.Session;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Joe on 18/04/2016.
 */
public class Main02 extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Session.initSession();
	}
}
