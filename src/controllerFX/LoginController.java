package controllerFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.SceneName;
import controller.AdminDAO;
import fr.esigelec.jee.main;

public class LoginController {
	private Stage stage;
	private AdminDAO dao;
	private Alert alert;

	/** Inject the stage from {@link main} */
	public LoginController(Stage stage) {
		this.stage = stage;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Le mot de passe/status ne correspond pas");
	}

	/** Display the first scene */
	public void loginAction(MouseEvent e, String user, String mdp) {
		dao = new AdminDAO();
		if (dao.authentify(user, mdp)) {
			if (dao.getStatus(user)) {
				stage.setScene(main.getScenes().get(SceneName.SCENE3));
			} else {
				alert.setContentText("Vous n'êtes pas administrateur");
				alert.showAndWait();
			}
		}else{
			alert.setContentText("Le nom de compte/mot de passe ne correspond pas");
			alert.showAndWait();
		}
	}
}
