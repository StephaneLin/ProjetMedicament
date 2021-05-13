package controllerFX;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vue.InfoMedGeneriqueMedView;
import vue.InfoUserView;
import vue.ListView;
import vue.OptionInfoView;

public class InfoMedGeneriqueController {
	private Stage stage;

	public InfoMedGeneriqueController(Stage stage) {
		this.stage = stage;
	}

	public void backAction(MouseEvent event) {
		stage.setScene(new OptionInfoView(stage).getScene());
	}
	
	public void redirect(MouseEvent event, int name) {
		stage.setScene(new InfoMedGeneriqueMedView(stage, name).getScene());
	}
}
