package controllerFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;

import controller.AdminDAO;
import fr.esigelec.jee.main;
import modele.SceneName;
import modele.CIS_bdpm;


public class ListMedController {
	private Stage stage;
	private AdminDAO dao;
	private Alert alert;
	
	public ListMedController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}
		
		this.stage = stage;
		alert= new Alert(AlertType.INFORMATION); alert.setTitle("Information");
		alert.setHeaderText(null);
	}
	
	public ArrayList<CIS_bdpm> getAllMedicaments(){
		return dao.getAllMedicaments();
	}
	
	public void backAction(MouseEvent event) {
		stage.setScene(main.getScenes().get(SceneName.SCENE3));
	}
	
	public void infoAction(MouseEvent event) {
		stage.setScene(main.getScenes().get(SceneName.SCENE4));
	}

	
	
	
	
}
