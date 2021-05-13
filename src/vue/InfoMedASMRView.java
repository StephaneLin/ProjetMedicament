package vue;

import java.util.ArrayList;
import java.util.List;

import controller.*;
import controllerFX.InfoMedASMRController;
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

public class InfoMedASMRView implements ViewMaker {
	private Stage stage;
	private AdminDAO dao;
	private final TableView<CIS_bdpm> table;
	private InfoMedASMRController con;
	

	public InfoMedASMRView(Stage stage) {
		this.stage = stage;
		table = new TableView<CIS_bdpm>();
		con= new InfoMedASMRController(stage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Scene getScene() {

		ListMedController con = new ListMedController(stage);

		

		// Create column in order to display information of medicaments (Data type of
		// String).
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
		ObservableList<CIS_bdpm> med = getAllMedicamentsList();
		table.setItems(med);

		table.getColumns().addAll(IdCol, DenomCol, FormeCol, VoieCol, StatutAMMCol, TypeAMMCol, EtatComCol);
		addButtonToTable();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);

		BorderPane b = new BorderPane();

		// Partie recherche médicament Filter
		TextField name = new TextField();
		name.setPromptText("Rechercher ici !!");

		FilteredList<CIS_bdpm> flmed = new FilteredList(med, p -> true);// Pass the data to a filtered list
		table.setItems(flmed);// Set the table's items using the filtered list
		name.setOnKeyReleased(keyEvent -> {
			flmed.setPredicate(
					p -> p.getDenominationMedicament().toLowerCase().contains(name.getText().toLowerCase().trim()));// filter
																													// table
																													// by
																													// first
																													// name
		});

		Text title = new Text("Liste des avis ASMR de la HAS ");
		title.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
		root.setTop(title);

		Button backBtn = new Button("Back");
		
		backBtn.setOnMousePressed(e -> con.backAction(e));
		
		
		root.setBottom(backBtn);
		root.setBottom(b);

		HBox h = new HBox();
		h.setSpacing(5);
		h.getChildren().add(backBtn);
		h.getChildren().add(name);
		b.setRight(h);

		root.setBottom(b);

		Scene scene = new Scene(root, 700, 600);

		return scene;
	}

	private ObservableList<CIS_bdpm> getAllMedicamentsList() {
		dao = new AdminDAO();
		ArrayList<CIS_bdpm> medicamentList = dao.getAllMedicaments();
		ObservableList<CIS_bdpm> med = FXCollections.observableArrayList();
		for (int i = 0; i < medicamentList.size(); i++) {
			med.add(medicamentList.get(i));
		}

		return med;
	}
	
	
	private void addButtonToTable() {
        TableColumn<CIS_bdpm, Void> colBtn = new TableColumn("Informations");

        Callback<TableColumn<CIS_bdpm, Void>, TableCell<CIS_bdpm, Void>> cellFactory = new Callback<TableColumn<CIS_bdpm, Void>, TableCell<CIS_bdpm, Void>>() {
            @Override
            public TableCell<CIS_bdpm, Void> call(final TableColumn<CIS_bdpm, Void> param) {
                final TableCell<CIS_bdpm, Void> cell = new TableCell<CIS_bdpm, Void>() {

                    private final Button conBtn = new Button("Afficher information");

                    {
                    	conBtn.setOnMousePressed(e -> con.redirectasmr(e,  getTableView().getItems().get(getIndex()).getCode_cis()));
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(conBtn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }
}
