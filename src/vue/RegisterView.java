package vue;

import controllerFX.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterView implements ViewMaker {
	private Stage stage;

	public RegisterView(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		RegisterController control = new RegisterController(stage);
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(0);
		grid.setPadding(new Insets(10));

		Text welcome = new Text("Inscription");
		welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
		grid.add(welcome, 0, 0);

		Label user = new Label("Identifiant");
		grid.add(user, 0, 1);

		TextField userField = new TextField();
		userField.setPromptText("Identifiant");
		grid.add(userField, 1, 1);

		Label pass = new Label("Mot de passe");
		grid.add(pass, 0, 2);

		PasswordField passField = new PasswordField();
		passField.setPromptText("Mot de passe");
		grid.add(passField, 1, 2);

		Button btn = new Button("Inscrire");
		grid.add(btn, 1, 3);
		root.setCenter(grid);
		
		Button backBtn = new Button("Back");
		backBtn.setOnMousePressed(e -> control.backAction(e));
		root.setBottom(backBtn);
		
		btn.setOnMousePressed(event -> control.registerAction(event,userField.getText(), passField.getText()));

		Scene scene = new Scene(root, 300, 300);
		return scene;

	}

	/*
	 * Alert alert; public RegisterView(Stage stage) { GridPane grid = new
	 * GridPane();
	 * 
	 * grid.setAlignment(Pos.CENTER); grid.setVgap(10); grid.setHgap(0);
	 * grid.setPadding(new Insets(10));
	 * 
	 * Text title = new Text("Création"); title.setFont(Font.font("Tahoma",
	 * FontWeight.LIGHT, 25)); grid.add(title, 0, 0);
	 * 
	 * Label user = new Label("Identifiant"); grid.add(user, 0, 1);
	 * 
	 * TextField userField = new TextField();
	 * userField.setPromptText("Identifiant"); grid.add(userField, 1, 1);
	 * 
	 * Label pass = new Label("Mot de passe"); grid.add(pass, 0, 2);
	 * 
	 * //passCheck = new Label("Verifier Mdp"); //grid.add(pass, 0, 3);
	 * 
	 * PasswordField passField = new PasswordField();
	 * passField.setPromptText("Mot de passe"); grid.add(passField, 1, 2);
	 * 
	 * //passFieldCheck = new PasswordField();
	 * //passFieldCheck.setPromptText("Mot de passe"); //grid.add(passField, 1, 3);
	 * 
	 * Button btn = new Button("Valider"); grid.add(btn, 1, 4);
	 * 
	 * /*alert= new Alert(AlertType.INFORMATION); alert.setTitle("Information");
	 * alert.setHeaderText(null);
	 * alert.setContentText("Le mot de passe ne correspond pas");
	 */
	/*
	 * btn.setOnAction(new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent event) {
	 * //if(passField.getText().equals(passFieldCheck.getText())) {
	 * listener.buttonEventPerformed(new LoginButtonEvent(userField.getText(),
	 * passField.getText()));
	 * 
	 * //}else { // alert.showAndWait(); // } } });
	 */
}
