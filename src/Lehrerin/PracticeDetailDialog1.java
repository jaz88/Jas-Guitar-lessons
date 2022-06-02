package Lehrerin;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import klassen.Datenbank;

import klassen.Practice;
import klassen.PracticeFX;
/**
 * In dieser Klasse befindet sich zuerst eine ObservableList von PracticeFX, 
 * dann gestalte ich die TableView ,und setze Buttons on action 
 * @author Jasmina Marinkovic 
 *
 */
public class PracticeDetailDialog1 extends Dialog<ButtonType> {
	private ObservableList<PracticeFX> olPracticeneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * Der Konstruktor
	 * @param practiceFX ist die Practice
	 */
	public PracticeDetailDialog1(PracticeFX practiceFX) {

		TableColumn<PracticeFX, String> idCol = new TableColumn<>("PRACTICEID");
		idCol.setPrefWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<PracticeFX, String> titelCol = new TableColumn<>("PRACTICETITEL");
		titelCol.setPrefWidth(200);
		titelCol.setCellValueFactory(new PropertyValueFactory<>("titel"));

		TableColumn<PracticeFX, String> bildPCol = new TableColumn<>("PRACTICEBILDPATH");
		bildPCol.setPrefWidth(150);
		bildPCol.setCellValueFactory(new PropertyValueFactory<>("bildPath"));

		TableView<PracticeFX> tvPractice = new TableView<>(olPracticeneu);
		tvPractice.getColumns().addAll(idCol, titelCol, bildPCol);
		tvPractice.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HBox Hb = new HBox();
		// damit rufe ich die LesePractice Methode aus der DatenbankKlasse auf 
		//und gebe die Practices in der TableView aus 
		try {
			ArrayList<Practice> al = klassen.Datenbank.lesePractice(null);
			olPracticeneu.clear();
			for (Practice einP : al)
				olPracticeneu.add(new PracticeFX(einP)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
		}
		// der Neue Practice  Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man eine neue Practice erzeugt hat 
		//, diesen in der TableView sofort wieder zu geben 
		Button newN = new Button("Neue Practice");
		newN.setPrefWidth(120);
		newN.setPadding(new Insets(10));
		newN.setLineSpacing(10);
		newN.setOnAction(e -> {
		  PracticeFX ndd1= new PracticeFX(new Practice()); 
			PracticeDetailDialog2 ndd = new PracticeDetailDialog2(ndd1);
			ndd.showAndWait();
			olPracticeneu.clear();
			update01();

			
		});

		Label msg = new Label();
		 // der Practice  Bearbeiten Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man eine Practice  bearbeitet  hat 
		//, diesen in der TableView sofort wieder zu geben
		Button updateN = new Button("Practice bearbeiten");
		updateN.setPrefWidth(130);
		updateN.setPadding(new Insets(10));
		updateN.setLineSpacing(10);
		updateN.setOnAction(e -> {
			if (tvPractice.getSelectionModel().getSelectedItem() != null) {
				PracticeDetailDialog2 ndd = new PracticeDetailDialog2(tvPractice.getSelectionModel().getSelectedItem());
				ndd.showAndWait();
				olPracticeneu.clear();
				update01();

			} else {
				msg.setText(" Bitte wählen Sie eine Practice aus !");
			}
		});
		// damit löscht man eine Practice in dem man im tryBlock die 
		// PracticeEntfernen methode aus der DatenbankKlasse aufruft
		Button deleteN = new Button("Practice löschen");
		deleteN.setPrefWidth(130);
		deleteN.setPadding(new Insets(10));
		deleteN.setLineSpacing(10);
		deleteN.setOnAction(e -> {
			if (tvPractice.getSelectionModel().getSelectedItem() != null) {
				try {
					klassen.Datenbank.practiceEntfernen(tvPractice.getSelectionModel().getSelectedItem().getId());
					int i = tvPractice.getSelectionModel().getFocusedIndex();
					olPracticeneu.remove(i);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

			} else {
				msg.setText(" Bitte wählen Sie eine Practice aus !");
			}
		});

		Hb.getChildren().addAll(tvPractice);

		FlowPane fpane = new FlowPane();

		fpane.setPadding(new Insets(10));
		fpane.getChildren().addAll(Hb, newN, updateN, deleteN, msg);
		 fpane.setStyle("-fx-background-color: aliceblue;");
		this.getDialogPane().setContent(fpane);
		ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().add(beenden);

	}
/**
 * Methode um ein sofortiges Bild der Istlage nach Änderungen
		 anzuzeigen
 */
	public void update01() {

		try {
			ArrayList<Practice> al = klassen.Datenbank.lesePractice(null);
			olPracticeneu.clear();
			for (Practice einP : al)
				olPracticeneu.add(new PracticeFX(einP)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
		}
	}

}