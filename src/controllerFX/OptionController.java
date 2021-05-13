package controllerFX;

import fr.esigelec.jee.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.SceneName;
import vue.ChoixConsulterLogView;
import vue.ListView;

public class OptionController {

	private Stage stage;

	public OptionController(Stage stage) {
		this.stage = stage;
	}

	public void addAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE1));
	}

	public void listAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE2));
	}

	public void logAction(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE4));
	}

	public void logAction2(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE6));
	}

	public void listAction1(MouseEvent e) {
		stage.setScene(main.getScenes().get(SceneName.SCENE5));
	}

	public void newFile(MouseEvent e) {
		CreerFichier log = new CreerFichier();
		log.creerFichier();
		main.getMap().put(SceneName.SCENE6, new ChoixConsulterLogView(stage).getScene());
	}
}
