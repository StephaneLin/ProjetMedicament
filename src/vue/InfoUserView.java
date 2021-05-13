package vue;

import java.util.ArrayList;

import controller.AdminDAO;
import controllerFX.InfoUserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Utilisateur;
import javafx.scene.text.Font;
import modele.ConInfo;
import java.util.Date;

public class InfoUserView implements ViewMaker {
	private Stage stage;
	private AdminDAO dao;
	private String name;

	public InfoUserView(Stage stage, String name) {
		this.stage = stage;
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Scene getScene() {

		InfoUserController con = new InfoUserController(stage);

		TableView<ConInfo> table = new TableView<ConInfo>();

		// Create column Id, Username and Password (Data type of String).
		TableColumn<ConInfo, String> userCol = new TableColumn<ConInfo, String>("Username");
		TableColumn<ConInfo, Date> dateCol = new TableColumn<ConInfo, Date>("date");
		TableColumn<ConInfo, String> adressIPCol = new TableColumn<ConInfo, String>("adressIP");
		TableColumn<ConInfo, String> statusCol = new TableColumn<ConInfo, String>("Connected");
		
		// Defines how to fill data for each cell.
		// Get value from property of Utilisateur. .
		userCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		adressIPCol.setCellValueFactory(new PropertyValueFactory<>("ip"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		// Set Sort type for Username column
		userCol.setSortType(TableColumn.SortType.DESCENDING);

		// Display row data
		ObservableList<ConInfo> info = getInfoUser();
		table.setItems(info);

		table.getColumns().addAll(userCol, dateCol, adressIPCol,statusCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);

		BorderPane b = new BorderPane();

		Text title = new Text("Liste Connexion");
		title.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
		root.setTop(title);

		Button ReBtn = new Button("Back");
		ReBtn.setOnMousePressed(e -> con.retour(e));
		root.setBottom(ReBtn);
		root.setBottom(b);

		HBox h = new HBox();
		h.setSpacing(5);
		h.getChildren().add(ReBtn);
		b.setRight(h);

		Scene scene = new Scene(root, 450, 350);

		return scene;
	}

	private ObservableList<ConInfo> getInfoUser() {
		dao = new AdminDAO();
		ArrayList<ConInfo> infoUser = dao.getConInfo(this.name);
		ObservableList<ConInfo> list = FXCollections.observableArrayList();
		for (int i = 0; i < infoUser.size(); i++) {
			list.add(infoUser.get(i));
		}
		return list;
	}

}