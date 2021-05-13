package vue;

import java.io.File;
import java.io.FilenameFilter;

import controllerFX.ChoixConsulterLogController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import vue.ViewMaker;

public class ChoixConsulterLogView implements ViewMaker {

	private Stage stage;
	private static String choix;
	public ChoixConsulterLogView(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		
		//get the list of all file .txt of the repertory of log
		File fl = new File("C:/Users/steph/eclipse-workspace/Medicament2_G3/WebContent/log/");
	    File[] files = fl.listFiles(new FilenameFilter() {          
	        public boolean accept(File file, String name) {
	            return name.contains(".txt");
	        }
	    });
	    
	    final ComboBox<String> comboBox = new ComboBox<String>(); 

	    comboBox.setValue(files[0].getName());
	    choix=files[0].getName();
	    
	    for (File file : files) {
	    	comboBox.getItems().add(file.getName());
	    }
		comboBox.valueProperty().addListener(observable -> choix=comboBox.getValue());
		
	    ChoixConsulterLogController con = new ChoixConsulterLogController(stage);
	    
		Button addBtn = new Button("Afficher le fichier sélectionné");
		addBtn.setOnMousePressed(e -> con.addAction(e));
		Button BackBtn = new Button("Retour au menu principal");
		BackBtn.setOnMousePressed(e -> con.BackAction(e));
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(comboBox, addBtn,BackBtn);

		BorderPane root = new BorderPane();
		root.setCenter(vbox);
		Label label = new Label("Liste des fichiers de log existants");
		label.setFont(new Font(32));
		root.setTop(label);

		Scene scene = new Scene(root, 400,250);

		return scene;
	}
	public static String getChoix() {
		return choix;
	}
}
