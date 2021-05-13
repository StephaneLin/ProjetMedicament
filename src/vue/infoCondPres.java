package vue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import controller.*;
import controllerFX.ListMedController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.*;

import javafx.scene.text.Font;

public class infoCondPres implements ViewMaker {
	private Stage stage;
	private AdminDAO dao;
	private int code_cis;

	public infoCondPres(Stage stage, int cis) {
		this.stage = stage;
		this.code_cis = cis;
		
	}




	@SuppressWarnings("unchecked")
	@Override
	public Scene getScene() {

		ListMedController con = new ListMedController(stage);

		TableView<CIS_CPD_bdpm> table = new TableView<CIS_CPD_bdpm>();

		// Create column in order to display information of medicaments (Data type of
		// String).
		TableColumn<CIS_CPD_bdpm, String> libelle = new TableColumn<CIS_CPD_bdpm, String>("Conditions");
		
		
		// Defines how to fill data for each cell.
		// Get value from property of CIS_bdpm.
		// Attention inversion entre les attributs code_cis affiche les conditions et inversement 
		libelle.setCellValueFactory(new PropertyValueFactory<>("code_cis"));
		
		
		
		
		

		// Set Sort type for Username column
		libelle.setSortType(TableColumn.SortType.DESCENDING);

		// Display row data
		ObservableList<CIS_CPD_bdpm> med = getListavis(this.code_cis);
		table.setItems(med);

		table.getColumns().addAll(libelle);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);

		BorderPane b = new BorderPane();

		/* Partie recherche médicament Filter
		TextField name = new TextField();
		name.setPromptText("Rechercher ici !!");

		FilteredList<CIS_CPD_bdpm> flmed = new FilteredList(med, p -> true);// Pass the data to a filtered list
		table.setItems(flmed);// Set the table's items using the filtered list
		name.setOnKeyReleased(keyEvent -> {
			flmed.setPredicate(
					p -> p.getValeurSMR().toLowerCase().contains(name.getText().toLowerCase().trim()));// filter
																													// table
																													// by
																													// first
																													// name
		});*/

		Text title = new Text("Liste des conditions de prescription et de délivrance");
		title.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
		root.setTop(title);

		Button backBtn = new Button("Back");
		Button showBtn = new Button("voir plus");
		backBtn.setOnMousePressed(e -> con.backAction(e));
		root.setBottom(backBtn);
		root.setBottom(b);

		HBox h = new HBox();
		h.setSpacing(5);
		h.getChildren().add(backBtn);
	
		b.setRight(h);

		root.setBottom(b);

		Scene scene = new Scene(root, 700, 600);

		return scene;
	}

	private ObservableList<CIS_CPD_bdpm> getListavis(int id) {
		dao = new AdminDAO();
		List<CIS_CPD_bdpm> medicamentList = dao.getListCondition(id);
		ObservableList<CIS_CPD_bdpm> med = FXCollections.observableArrayList();
		for (int i = 0; i < medicamentList.size(); i++) {
			med.add(medicamentList.get(i));
		}

		return med;
	}
	
	
	
	
}
