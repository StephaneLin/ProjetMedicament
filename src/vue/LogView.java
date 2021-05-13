package vue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controllerFX.LogController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.LigneLog;
import vue.ViewMaker;

public class LogView implements ViewMaker {

	private Stage stage;
	
	public LogView(Stage stage) {
		this.stage = stage;
	}
	@Override
	public Scene getScene() {
		LogController con = new LogController(stage);
		
		TableView<LigneLog> table = new TableView<LigneLog>();

		// Create column in order to display information of medicaments (Data type of
		// String).
		TableColumn<LigneLog, Integer> IdCol = new TableColumn<LigneLog, Integer>("code d'identification");
		TableColumn<LigneLog, String> DescrCol = new TableColumn<LigneLog, String>("Description de l'action");
		TableColumn<LigneLog, Date> HoraireCol = new TableColumn<LigneLog, Date>("Horaire de l'enregistrement");
		TableColumn<LigneLog, String> LoginCol = new TableColumn<LigneLog, String>("login associé");
		TableColumn<LigneLog, String> ipCol = new TableColumn<LigneLog, String>("adresse ip associée");

		// Defines how to fill data for each cell.
		// Get value from property of CIS_bdpm.
		IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		DescrCol.setCellValueFactory(new PropertyValueFactory<>("descr"));
		HoraireCol.setCellValueFactory(new PropertyValueFactory<>("horaireEnreg"));
		LoginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
		ipCol.setCellValueFactory(new PropertyValueFactory<>("ip"));

		// Set Sort type for Username column
		DescrCol.setSortType(TableColumn.SortType.DESCENDING);

		// Display row data
		ObservableList<LigneLog> med = getAllLogsList();
		table.setItems(med);

		table.getColumns().addAll(IdCol, DescrCol, HoraireCol, LoginCol, ipCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);

		BorderPane b = new BorderPane();

		// Partie recherche médicament Filter
		TextField name = new TextField();
		name.setPromptText("Rechercher ici !!");

		FilteredList<LigneLog> flmed = new FilteredList(med, p -> true);// Pass the data to a filtered list
		table.setItems(flmed);// Set the table's items using the filtered list
		name.setOnKeyReleased(keyEvent -> {
			flmed.setPredicate(
					p -> p.getDescr().toLowerCase().contains(name.getText().toLowerCase().trim()));// filter
																													// table
																													// by
																													// first
																													// name
		});

		Text title = new Text("Liste des lignes de logs");
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

		Scene scene = new Scene(root, 450, 350);

		return scene;
	}

	private ObservableList<LigneLog> getAllLogsList() {
		ObservableList<LigneLog> med = FXCollections.observableArrayList();
		String line;
		
		try {
			String choix = ChoixConsulterLogView.getChoix();
			BufferedReader reader = new BufferedReader(new FileReader(new File("C:/Users/steph/eclipse-workspace/Medicament2_G3/WebContent/log/"+choix+"")));

			while ((line = reader.readLine()) != null) {
				
				String parts[] = line.split(";", 5);
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				int id = Integer.parseInt(parts[0]) ;
				Date date = format.parse(parts[2]);
				
				med.add(new LigneLog(id,parts[1],date,parts[3],parts[4]));
			}
			reader.close();
			

		} catch (IOException | ParseException e  ) {
			e.printStackTrace();
		}
		
		return med;
	}

}

