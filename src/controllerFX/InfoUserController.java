package controllerFX;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vue.ListView;
import vue.InfoUserView;

public class InfoUserController {

	private Stage stage;

	public InfoUserController(Stage stage) {
		this.stage = stage;
	}

	public void retour(MouseEvent event) {
		stage.setScene(new ListView(stage).getScene());
	}
	
	public void redirect(MouseEvent event, String name) {
		stage.setScene(new InfoUserView(stage, name).getScene());
	}

}
