package controllerFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;

import controller.AdminDAO;
import fr.esigelec.jee.main;
import modele.SceneName;
import modele.Utilisateur;
import vue.InfoUserView;
import vue.ListView;

public class ListController {
	private Stage stage;
	private AdminDAO dao;
	private Alert alert;

	public ListController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}

		this.stage = stage;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
	}

	public ArrayList<Utilisateur> getUserList() {
		return dao.getUserList();
	}

	public void backAction(MouseEvent event) {
		stage.setScene(main.getScenes().get(SceneName.SCENE3));
	}

	public void delAction(MouseEvent event, String name) {
		dao = new AdminDAO();
		if (dao.delete(name)) {
			main.getMap().put(SceneName.SCENE2, new ListView(stage).getScene());
			alert.setContentText("Utilisateur supprimé");
			alert.showAndWait();
			stage.setScene(main.getScenes().get(SceneName.SCENE3));
		} else {
			alert.setContentText("Le compte n'a pas pu être supprimé");
			alert.showAndWait();
		}
	}

	public void redirect(MouseEvent event, String name) {
		stage.setScene(new InfoUserView(stage, name).getScene());
	}

}
