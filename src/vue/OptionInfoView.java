package vue;

import controllerFX.OptionController;
import controllerFX.OptionInfoController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import vue.ViewMaker;

public class OptionInfoView implements ViewMaker {

	private Stage stage;
	

	public OptionInfoView(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {

		OptionInfoController con = new OptionInfoController(stage);

		Button addBtn = new Button("Consulter les avis SMR");
		addBtn.setOnMousePressed(e -> con.smrAction(e));
		
		Button listBtn = new Button("Consulter les avis ASMR");
		listBtn.setOnMousePressed(e -> con.asmrAction(e));
		
		Button listBtn1 = new Button("Consulter la liste des groupes génériques");
		listBtn1.setOnMousePressed(e -> con.genericAction(e));
		
		Button logBtn = new Button("Consulter les conditions de prescription et de delivrance assossié à un medicament");
		logBtn.setOnMousePressed(e -> con.conditionAction(e));
		
		Button logBtn3 = new Button("Consulter la liste des médicaments commercialisés");
		logBtn3.setOnMousePressed(e -> con.arretAction(e));
		
		Button logBtn4 = new Button("Consulter la liste des médicaments en arrêt de commercialisation");
		logBtn4.setOnMousePressed(e -> con.commercialisationAction(e));
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(addBtn, listBtn, listBtn1, logBtn, logBtn3, logBtn4);

		BorderPane root = new BorderPane();
		root.setCenter(vbox);
		Label label = new Label("Menu des informations");
		label.setFont(new Font(32));
		root.setTop(label);
   
		Scene scene = new Scene(root, 500, 350);

		return scene;
	}
}
