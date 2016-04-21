package ie.wit.map.version_2.views.modal_root;

import ie.wit.map.version_2.managers.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * Created by Joe on 19/04/2016.
 */
public class ModalRootCtrl
{
	@FXML
	private Label idLabel;
	private StringProperty id;

	@FXML
	private void initialize()
	{
		id = new SimpleStringProperty("");
		idLabel.textProperty().bind(id);
	}

	public void mapAreaClicked(String id)
	{
		this.id.set(id);
	}

	@FXML
	private void returnToFormClicked()
	{
		Session.controllers.getAddItemCtrl().setCurrentGuiId(id.get());
		Session.viewLoader.closeModal();
	}
}
