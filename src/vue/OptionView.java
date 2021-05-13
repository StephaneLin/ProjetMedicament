package vue;

import controllerFX.OptionController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import vue.ViewMaker;

public class OptionView implements ViewMaker {

	private Stage stage;

	public OptionView(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {

		OptionController con = new OptionController(stage);

		Button addBtn = new Button("Ajouter utilisateur");
		addBtn.setOnMousePressed(e -> con.addAction(e));
		Button listBtn = new Button("Liste D'utilisateur");
		listBtn.setOnMousePressed(e -> con.listAction(e));
		Button listBtn1 = new Button("Informations Medicament");
		listBtn1.setOnMousePressed(e -> con.listAction1(e));
		Button logBtn1 = new Button("Choisir un fichier de log à afficher");
		logBtn1.setOnMousePressed(e -> con.logAction2(e));
		Button fileBtn = new Button("Créer fichier log");
		fileBtn.setOnMousePressed(e -> con.newFile(e));

		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(addBtn, listBtn, fileBtn, logBtn1, listBtn1);

		BorderPane root = new BorderPane();
		root.setCenter(vbox);
		Label label = new Label("Menu principal");
		label.setFont(new Font(32));
		root.setTop(label);

		Scene scene = new Scene(root, 450, 250);

		return scene;
	}
}
