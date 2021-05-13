package vue;

import java.util.ArrayList;
import java.util.List;

import controller.AdminDAO;
import controllerFX.InfoMedGeneriqueController;
import controllerFX.InfoMedGeneriqueMedController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.CIS_bdpm;
import javafx.scene.text.Font;

public class InfoMedGeneriqueMedView implements ViewMaker {
	private Stage stage;
	private int name;
	private AdminDAO dao;
	private final TableView<CIS_bdpm> table;
	private InfoMedGeneriqueMedController con;

	public InfoMedGeneriqueMedView(Stage stage, int name) {
		table = new TableView<CIS_bdpm>();
		this.stage = stage;
		this.name = name;
		con = new InfoMedGeneriqueMedController(stage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Scene getScene() {

		// Create column Id, Username and Password (Data type of String).
		TableColumn<CIS_bdpm, String> IdCol = new TableColumn<CIS_bdpm, String>("Code CIS");
		TableColumn<CIS_bdpm, String> DenomCol = new TableColumn<CIS_bdpm, String>("Dénomination");
		TableColumn<CIS_bdpm, String> FormeCol = new TableColumn<CIS_bdpm, String>("Forme pharmaceutique");
		TableColumn<CIS_bdpm, String> VoieCol = new TableColumn<CIS_bdpm, String>("Voie administration");
		TableColumn<CIS_bdpm, String> StatutAMMCol = new TableColumn<CIS_bdpm, String>("Statut AMM");
		TableColumn<CIS_bdpm, String> TypeAMMCol = new TableColumn<CIS_bdpm, String>("type AMM");
		TableColumn<CIS_bdpm, String> EtatComCol = new TableColumn<CIS_bdpm, String>("Etat de commercialisation");

		// Defines how to fill data for each cell.
		// Get value from property of CIS_bdpm.
		IdCol.setCellValueFactory(new PropertyValueFactory<>("code_cis"));
		DenomCol.setCellValueFactory(new PropertyValueFactory<>("denominationMedicament"));
		FormeCol.setCellValueFactory(new PropertyValueFactory<>("formePharmaceutique"));
		VoieCol.setCellValueFactory(new PropertyValueFactory<>("voieAdministration"));
		StatutAMMCol.setCellValueFactory(new PropertyValueFactory<>("statutAdministratifAmm"));
		TypeAMMCol.setCellValueFactory(new PropertyValueFactory<>("typeProcedureAmm"));
		EtatComCol.setCellValueFactory(new PropertyValueFactory<>("etatCommercialisation"));

		// Set Sort type for Username column
		DenomCol.setSortType(TableColumn.SortType.DESCENDING);

		// Display row data
		ObservableList<CIS_bdpm> med = getMedList(name);
		table.setItems(med);

		table.getColumns().addAll(IdCol, DenomCol, FormeCol, VoieCol, StatutAMMCol, TypeAMMCol, EtatComCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);

		BorderPane b = new BorderPane();

		Text title = new Text("Liste Medicament");
		title.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
		root.setTop(title);
		
		Button backBtn = new Button("Back");
		backBtn.setOnMousePressed(e -> con.backAction(e));
		b.setBottom(backBtn);
		root.setBottom(b);

		Scene scene = new Scene(root, 700, 600);

		return scene;
	}

	private ObservableList<CIS_bdpm> getMedList(int name) {
		dao = new AdminDAO();
		String lib = dao.getGenLib(name);
		List<Integer> listCIS = dao.getGenCis(lib);
		List<CIS_bdpm> listGrMeds = new ArrayList<CIS_bdpm>();
		for (int i = 0; i < listCIS.size(); i++) {
			listGrMeds.add(dao.getGrMeds(listCIS.get(i)));
		}
		ObservableList<CIS_bdpm> list = FXCollections.observableArrayList();
		for (int i = 0; i < listGrMeds.size(); i++) {
			list.add(listGrMeds.get(i));
		}
		return list;
	}

}
