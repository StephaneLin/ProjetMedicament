package controllerFX;

import fr.esigelec.jee.main;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.SceneName;

public class OptionInfoController {

	private Stage stage;
	public OptionInfoController(Stage stage) {
		this.stage = stage;
	}

	public void smrAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE4));
	}

	public void asmrAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE7));
	}

	public void conditionAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE8));
	}

	public void commercialisationAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE9));
	}
	
	public void arretAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE11));
	}
	
	public void genericAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE10));
	}
}
