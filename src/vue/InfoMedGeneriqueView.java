package vue;

import java.util.ArrayList;

import controller.AdminDAO;
import controllerFX.InfoMedGeneriqueController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.CIS_GENER_bdpm;
import modele.CIS_bdpm;
import javafx.scene.text.Font;

public class InfoMedGeneriqueView implements ViewMaker {
	private Stage stage;
	private AdminDAO dao;
	private final TableView<CIS_GENER_bdpm> table;
	private InfoMedGeneriqueController con;

	public InfoMedGeneriqueView(Stage stage) {
		table = new TableView<CIS_GENER_bdpm>();
		this.stage = stage;
		con = new InfoMedGeneriqueController(stage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Scene getScene() {

		// Create column Id, Username and Password (Data type of String).
		TableColumn<CIS_GENER_bdpm, Integer> cisCol = new TableColumn<CIS_GENER_bdpm, Integer>("CIS");
		TableColumn<CIS_GENER_bdpm, String> libCol = new TableColumn<CIS_GENER_bdpm, String>("Libelle");

		// Defines how to fill data for each cell.
		// Get value from property of CIS_GENER_bdpm. .
		libCol.setCellValueFactory(new PropertyValueFactory<>("code_cis"));
		libCol.setCellValueFactory(new PropertyValueFactory<>("libelleGroupeGenerique"));

		// Set Sort type for Username column
		libCol.setSortType(TableColumn.SortType.DESCENDING);

		// Display row data
		ObservableList<CIS_GENER_bdpm> list = getGenerList();
		table.setItems(list);

		table.getColumns().addAll(libCol);

		addButtonToTable();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);

		BorderPane b = new BorderPane();

		// Partie recherche médicament Filter
		TextField name = new TextField();
		name.setPromptText("Rechercher ici !!");

		FilteredList<CIS_GENER_bdpm> flmed = new FilteredList(list, p -> true);// Pass the data to a filtered list
		table.setItems(flmed);// Set the table's items using the filtered list
		name.setOnKeyReleased(keyEvent -> {
			flmed.setPredicate(
					p -> p.getLibelleGroupeGenerique().toLowerCase().contains(name.getText().toLowerCase().trim()));
		});
		Text title = new Text("Liste CIS_GENER_bdpm");
		title.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
		root.setTop(title);

		Button backBtn = new Button("Back");
		backBtn.setOnMousePressed(e -> con.backAction(e));
		root.setBottom(backBtn);
		root.setBottom(b);

		Scene scene = new Scene(root, 700, 600);

		return scene;
	}

	private ObservableList<CIS_GENER_bdpm> getGenerList() {
		dao = new AdminDAO();
		ArrayList<CIS_GENER_bdpm> listUser = dao.getGenerList();
		ObservableList<CIS_GENER_bdpm> list = FXCollections.observableArrayList();
		for (int i = 0; i < listUser.size(); i++) {
			list.add(listUser.get(i));
		}
		return list;
	}

	private void addButtonToTable() {
		TableColumn<CIS_GENER_bdpm, Void> colBtn = new TableColumn("Voir medicament");

		Callback<TableColumn<CIS_GENER_bdpm, Void>, TableCell<CIS_GENER_bdpm, Void>> cellFactory = new Callback<TableColumn<CIS_GENER_bdpm, Void>, TableCell<CIS_GENER_bdpm, Void>>() {
			@Override
			public TableCell<CIS_GENER_bdpm, Void> call(final TableColumn<CIS_GENER_bdpm, Void> param) {
				final TableCell<CIS_GENER_bdpm, Void> cell = new TableCell<CIS_GENER_bdpm, Void>() {

					private final Button conBtn = new Button("Afficher medicament");

					{
						conBtn.setOnMousePressed(e -> con.redirect(e,
								getTableView().getItems().get(getIndex()).getCode_cis()));
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
