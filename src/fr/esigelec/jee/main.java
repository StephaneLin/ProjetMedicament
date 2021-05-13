package fr.esigelec.jee;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.SceneName;
import vue.ChoixConsulterLogView;
import vue.InfoMedASMRView;
import vue.InfoMedCOMView;
import vue.InfoMedCondPresView;
import vue.InfoMedGeneriqueView;
import vue.InfoMedSMRView;
import vue.InfoMedView;
import vue.InfoNonComView;
import vue.ListView;
import vue.LoginView;
import vue.OptionInfoView;
import vue.OptionView;
import vue.RegisterView;

/**
 * Builds all scenes and display the L
 * 
 * @author steph
 */
public class main extends Application {

	/** Holds the various scenes to switch between */
	private static Map<SceneName, Scene> scenes = new HashMap<>();

	public static Map<SceneName, Scene> getMap() {
		return scenes;
	}

	@Override
	public void start(Stage stage) {

		// Create and store all scenes up front
		scenes.put(SceneName.MAIN, new LoginView(stage).getScene());
		scenes.put(SceneName.SCENE1, new RegisterView(stage).getScene());
		scenes.put(SceneName.SCENE2, new ListView(stage).getScene());
		scenes.put(SceneName.SCENE3, new OptionView(stage).getScene());
		scenes.put(SceneName.SCENE4, new InfoMedSMRView(stage).getScene());
		scenes.put(SceneName.SCENE6, new ChoixConsulterLogView(stage).getScene());
		scenes.put(SceneName.SCENE5, new OptionInfoView(stage).getScene());
		scenes.put(SceneName.SCENE7, new InfoMedASMRView(stage).getScene());
		scenes.put(SceneName.SCENE8, new InfoMedCondPresView(stage).getScene());
		scenes.put(SceneName.SCENE9, new InfoMedCOMView(stage).getScene());
		scenes.put(SceneName.SCENE10, new InfoMedGeneriqueView(stage).getScene());
		scenes.put(SceneName.SCENE11, new InfoNonComView(stage).getScene());
		
		// Start with the Login scene
		stage.setScene(scenes.get(SceneName.MAIN));
		stage.setTitle("Administrateur");
		stage.show();
	}

	/** Returns a Map of the scenes by {@link SceneName} */
	public static Map<SceneName, Scene> getScenes() {
		return scenes;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
