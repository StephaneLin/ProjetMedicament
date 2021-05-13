package controllerFX;

import java.util.HashMap;
import java.util.Map;

import fr.esigelec.jee.main;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.SceneName;
import vue.LogView;

public class ChoixConsulterLogController {

	private Stage stage;
	private static Map<SceneName, Scene> scenes = new HashMap<>();

	public ChoixConsulterLogController(Stage stage) {
		this.stage = stage;
	}

	public void addAction(MouseEvent e) {
		scenes.put(SceneName.SCENE4, new LogView(stage).getScene());
		stage.setScene(scenes.get(SceneName.SCENE4));
	}
	public void BackAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE3));
	}

}
