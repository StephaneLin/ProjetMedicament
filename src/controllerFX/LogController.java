package controllerFX;

import fr.esigelec.jee.main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.SceneName;

public class LogController {
	private Stage stage;
	private Alert alert;
	
	public LogController(Stage stage){
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}
		
		this.stage = stage;
		alert= new Alert(AlertType.INFORMATION); alert.setTitle("Information");
		alert.setHeaderText(null);
	}
	
	public void backAction(MouseEvent event) {
		stage.setScene(main.getScenes().get(SceneName.SCENE6));
	}

}
