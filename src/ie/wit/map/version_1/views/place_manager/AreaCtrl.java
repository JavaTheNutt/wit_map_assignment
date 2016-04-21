package ie.wit.map.version_1.views.place_manager;

import ie.wit.map.common.model.Place;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Created by Joe on 18/04/2016.
 */
public class AreaCtrl
{
	@FXML
	private TextField nameInput;
	@FXML
	private ChoiceBox<String> areaTypeInput;

	@FXML
	private void initialize()
	{
		areaTypeInput.getItems().addAll(Place.getAreaTypes());
	}

	@FXML
	private void selectAreaClicked()
	{

	}
}
