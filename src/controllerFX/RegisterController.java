package controllerFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import controller.AdminDAO;
import fr.esigelec.jee.main;
import modele.SceneName;
import vue.ListView;

public class RegisterController {
	private Stage stage;
	private AdminDAO dao;
	private Alert alert;

	public RegisterController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}

		this.stage = stage;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
	}

	/** Display OptionView scene when the "back" button is clicked */
	public void backAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE3));
	}

	public void registerAction(MouseEvent e, String user, String mdp) {
		dao = new AdminDAO();
		if (dao.register(user, mdp)) {
			main.getMap().put(SceneName.SCENE2, new ListView(stage).getScene());
			alert.setContentText("L'utilisateur a été ajouté");
			stage.setScene(main.getScenes().get(SceneName.SCENE3));
			alert.showAndWait();
		} else {
			alert.setContentText("Le compte n'a pas pu être ajouté");
			alert.showAndWait();
		}
	}

}