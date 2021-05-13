package controllerFX;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vue.OptionInfoView;

public class InfoNonComController {
	private Stage stage;

	public InfoNonComController(Stage stage) {
		this.stage = stage;
	}

	public void backAction(MouseEvent event) {
		stage.setScene(new OptionInfoView(stage).getScene());
	}
}
