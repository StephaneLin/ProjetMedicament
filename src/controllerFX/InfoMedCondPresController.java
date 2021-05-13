package controllerFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import controller.AdminDAO;
import fr.esigelec.jee.main;
import modele.CIS_HAS_ASMR;
import modele.CIS_HAS_SMR;
import modele.CIS_bdpm;
import modele.SceneName;
import modele.Utilisateur;
import vue.InfoMedView;
import vue.InfoUserView;
import vue.ListView;
import vue.infoAvisASMR;
import vue.infoAvisSMR;
import vue.infoCondPres;

public class InfoMedCondPresController {
	private Stage stage;
	private AdminDAO dao;
	private Alert alert;

	public InfoMedCondPresController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}

		this.stage = stage;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
	}

	public List<CIS_HAS_ASMR> getAvisasmr() {
		return dao.getAvisasmr(0);
	}

	public void backAction(MouseEvent event) {
		stage.setScene(main.getScenes().get(SceneName.SCENE5));
	}

	public void redirectcond(MouseEvent e, int code_cis) {
		stage.setScene(new infoCondPres(stage, code_cis).getScene());
		
	}
	

	

}
