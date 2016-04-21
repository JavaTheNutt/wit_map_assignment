package ie.wit.map.version_2.views.add_photo;

import ie.wit.map.common.model.Photo;
import ie.wit.map.version_2.managers.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Joe on 21/04/2016.
 */
public class AddPhotoCtrl
{
	private File photo;
	private int building;
	private Map buildingMap;
	private Map areaMap;
	private static final String[] types = {"Building", "Area"};
	private static final List<String> buildingNames = new ArrayList<>();
	private static final List<String> areaNames = new ArrayList<>();


	@FXML
	private ChoiceBox<String> choiceBox;
	@FXML
	private ChoiceBox<String> typeBox;
	@FXML
	private void initialize(){
		buildingMap = Session.dataCollection.getBuildingDetails();
		areaMap = Session.dataCollection.getAreaDetails();
		initSelectors();
	}
	private int getBuildingId(String name){
		if(typeBox.getValue().equalsIgnoreCase("building")){
			return (int) buildingMap.get(name);
		}else{
			return (int) areaMap.get(name);
		}
	}
	private void initSelectors(){
		mapData();
		typeBox.getItems().addAll(types);
		typeBox.setValue(types[0]);
		syncChoices(typeBox.getValue());
		typeBox.valueProperty().addListener((observable, oldValue, newValue) -> syncChoices(newValue));
	}
	private void syncChoices(String val){
		choiceBox.getItems().clear();
		if(val.equalsIgnoreCase("building")){
			choiceBox.getItems().addAll(buildingNames);
			choiceBox.setValue(buildingNames.get(0));
		}else{
			choiceBox.getItems().addAll(areaNames);
			choiceBox.setValue(areaNames.get(0));
		}
	}
	private void mapData(){
		Iterator iter = buildingMap.keySet().iterator();
		while (iter.hasNext()){
			buildingNames.add((String) iter.next());
		}
		iter = areaMap.keySet().iterator();
		while(iter.hasNext()){
			areaNames.add((String) iter.next());
		}
	}
	@FXML
	private void addPhotoClicked(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select photo");
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG", "*.jpeg"), new FileChooser.ExtensionFilter("JPG", "*.jpg"));
		photo = chooser.showOpenDialog(Session.viewLoader.getWindow());
		System.out.println(photo.getAbsoluteFile());
	}
	@FXML
	private void submitClicked(){
		if(photo != null){
			Photo pic = new Photo(photo.getAbsolutePath(), getBuildingId(choiceBox.getValue()));
			Session.dataCollection.addPhoto(pic);
			System.out.println(pic.getPath());
		}
	}
}
