package vue;

import java.util.ArrayList;

import controller.AdminDAO;
import controllerFX.ListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import modele.Utilisateur;
import javafx.scene.text.Font;

public class ListView implements ViewMaker {
	private Stage stage;
	private AdminDAO dao;
	private final TableView<Utilisateur> table;
	private ListController con; 
	
	public ListView(Stage stage) {
		table = new TableView<Utilisateur>();
		this.stage = stage;
		con= new ListController(stage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Scene getScene() {

		ListController con = new ListController(stage);

		// Create column Id, Username and Password (Data type of String).
		TableColumn<Utilisateur, Integer> idCol = new TableColumn<Utilisateur, Integer>("Id");
		TableColumn<Utilisateur, String> userCol = new TableColumn<Utilisateur, String>("Username");
		TableColumn<Utilisateur, String> passCol = new TableColumn<Utilisateur, String>("Password");

		// Defines how to fill data for each cell.
		// Get value from property of Utilisateur. .
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
		userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
		passCol.setCellValueFactory(new PropertyValueFactory<>("mdp"));

		// Set Sort type for Username column
		userCol.setSortType(TableColumn.SortType.DESCENDING);

		// Display row data
		ObservableList<Utilisateur> list = getUserList();
		table.setItems(list);

		table.getColumns().addAll(idCol, userCol, passCol);
		
		addButtonToTable();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		root.setCenter(table);
		
		BorderPane b = new BorderPane();
		
		TextField name = new TextField();
		name.setPromptText("Nom d'utilisateur");
		Button delBtn = new Button("Supprimer");
		delBtn.setOnMousePressed(e -> con.delAction(e, name.getText()));
		
		Text title = new Text("Liste Utilisateur");
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
		h.getChildren().add(delBtn);
		b.setRight(h);

		root.setBottom(b);
		
		Scene scene = new Scene(root, 450, 350);

		return scene;
	}

	private ObservableList<Utilisateur> getUserList() {
		dao = new AdminDAO();
		ArrayList<Utilisateur> listUser = dao.getUserList();
		ObservableList<Utilisateur> list = FXCollections.observableArrayList();
		for (int i = 0; i < listUser.size(); i++) {
			list.add(listUser.get(i));
		}
		return list;
	}
	
	private void addButtonToTable() {
        TableColumn<Utilisateur, Void> colBtn = new TableColumn("Voir connexion");

        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> cellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {
            @Override
            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {
                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {

                    private final Button conBtn = new Button("Afficher connexion");

                    {
                    	conBtn.setOnMousePressed(e -> con.redirect(e, getTableView().getItems().get(getIndex()).getUser()));
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