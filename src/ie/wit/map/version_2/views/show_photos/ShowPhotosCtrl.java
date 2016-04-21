package ie.wit.map.version_2.views.show_photos;

import ie.wit.map.common.model.Photo;
import ie.wit.map.version_2.managers.Session;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;

import java.util.List;

/**
 * Created by Joe on 21/04/2016.
 */
public class ShowPhotosCtrl
{
	@FXML
	private StackPane imagePane;

	private String[] photoPaths;
	private Image[] images;

	@FXML
	private void initialize(){
		List<Photo> photos = Session.dataCollection.getPhotoList();
		photoPaths = new String[photos.size()];
		images = new Image[photoPaths.length];
		for (int i = 0; i < photos.size(); i++) {
			photoPaths[i] = photos.get(i).getPath();
			/*Image img = new Image(photoPaths[i]);
			images[i] = img;*/
			System.out.println(photos.get(i).getPath());
		}
		/*imagePane.setStyle("-fx-background-image: url('" + photoPaths[0] + "')");*/

	}
}
