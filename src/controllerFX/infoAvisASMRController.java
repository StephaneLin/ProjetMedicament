package controllerFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import controller.AdminDAO;
import controllerFX.infoAvisASMRController;
import fr.esigelec.jee.main;
import vue.InfoUserView;
import vue.infoAvisSMR;
import modele.*;


public class infoAvisASMRController {
	private Stage stage;
	private AdminDAO dao;
	private Alert alert;
	
	public infoAvisASMRController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}
		
		this.stage = stage;
		alert= new Alert(AlertType.INFORMATION); alert.setTitle("Information");
		alert.setHeaderText(null);
	}
	
	public List<CIS_HAS_SMR> getAllMedicaments(){
		return dao.getAvissmr(0);
	}
	
	public void backAction(MouseEvent event) {
		stage.setScene(main.getScenes().get(SceneName.SCENE5));
	}
	
	
	public void redirectasmr(MouseEvent event, String name) {
		stage.setScene(new InfoUserView(stage, name).getScene());
	}
	
	
	
	
}
