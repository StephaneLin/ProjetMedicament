package vue;

import java.util.List;
import controller.*;
import controllerFX.ListMedController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.*;

import javafx.scene.text.Font;

public class infoAvisASMR implements ViewMaker {
	private Stage stage;
	private AdminDAO dao;
	private int code_cis;

	public infoAvisASMR(Stage stage, int cis) {
		this.stage = stage;
		this.code_cis = cis;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Scene getScene() {

		ListMedController con = new ListMedController(stage);

		TableView<CIS_HAS_ASMR> table = new TableView<CIS_HAS_ASMR>();

		// Create column in order to display information of medicaments (Data type of
		// String).
		TableColumn<CIS_HAS_ASMR, String> codecis = new TableColumn<CIS_HAS_ASMR, String>("CIS");
		TableColumn<CIS_HAS_ASMR, String> motif = new TableColumn<CIS_HAS_ASMR, String>("Motif");
		TableColumn<CIS_HAS_ASMR, String> datesmr = new TableColumn<CIS_HAS_ASMR, String>(" Date SMR");
		TableColumn<CIS_HAS_ASMR, String> val = new TableColumn<CIS_HAS_ASMR, String>("Valeur");
		TableColumn<CIS_HAS_ASMR, String> libelle = new TableColumn<CIS_HAS_ASMR, String>("Libellé");

		// Defines how to fill data for each cell.
		// Get value from property of CIS_bdpm.
		codecis.setCellValueFactory(new PropertyValueFactory<>("code_cis"));
		motif.setCellValueFactory(new PropertyValueFactory<>("motifEvaluation"));
		datesmr.setCellValueFactory(new PropertyValueFactory<>("dateAvisCommission"));
		val.setCellValueFactory(new PropertyValueFactory<>("valeurASMR"));
		libelle.setCellValueFactory(new PropertyValueFactory<>("libelleASMR"));

		// Set Sort type for Username column
		libelle.setSortType(TableColumn.SortType.DESCENDING);

		// Display row data
		ObservableList<CIS_HAS_ASMR> med = getListavis(this.code_cis);
		table.setItems(med);

		table.getColumns().addAll(codecis, motif, datesmr, val, libelle);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);

		BorderPane b = new BorderPane();

		/*
		 * Partie recherche médicament Filter TextField name = new TextField();
		 * name.setPromptText("Rechercher ici !!");
		 * 
		 * FilteredList<CIS_HAS_ASMR> flmed = new FilteredList(med, p -> true);// Pass
		 * the data to a filtered list table.setItems(flmed);// Set the table's items
		 * using the filtered list name.setOnKeyReleased(keyEvent -> {
		 * flmed.setPredicate( p ->
		 * p.getValeurSMR().toLowerCase().contains(name.getText().toLowerCase().trim()))
		 * ;// filter // table // by // first // name });
		 */

		Text title = new Text("Liste des avis ASMR de la HAS");
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

	private ObservableList<CIS_HAS_ASMR> getListavis(int id) {
		dao = new AdminDAO();
		List<CIS_HAS_ASMR> medicamentList = dao.getAvisasmr(id);
		ObservableList<CIS_HAS_ASMR> med = FXCollections.observableArrayList();
		for (int i = 0; i < medicamentList.size(); i++) {
			med.add(medicamentList.get(i));
		}

		return med;
	}

}
