package vue;

import controllerFX.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * Create the Login form scene in the stage
 * 
 * @author steph
 *
 */
public class LoginView implements ViewMaker {
	private Stage stage;

	public LoginView(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		LoginController control = new LoginController(stage);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(0);
		grid.setPadding(new Insets(10));
		
		Text welcome = new Text("Bienvenue");
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
		
		Button btn = new Button("Se connecter");
		grid.add(btn, 1, 3);

		btn.setOnMousePressed(event -> control.loginAction(event, userField.getText(), passField.getText()));

		Scene scene = new Scene(grid, 300, 300);
		return scene;

	}
}
