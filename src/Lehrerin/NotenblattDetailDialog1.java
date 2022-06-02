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

import klassen.Notenblatt;
import klassen.NotenblattFX;
import klassen.Practice;
import klassen.PracticeFX;
/**
 * In dieser Klasse befindet sich zuerst eine ObservableList von NotenblattFX, 
 * dann gestalte ich die TableView ,und setze Buttons on action 
 * @author Jasmina Marinkovic 
 *
 */
public class NotenblattDetailDialog1 extends Dialog<ButtonType> {
	private ObservableList<NotenblattFX> olNotenblattneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * Der Konstruktor 
	 * @param notenblattFX ist das Notenblatt
	 */
	public NotenblattDetailDialog1(NotenblattFX notenblattFX) {

		TableColumn<NotenblattFX, String> idCol = new TableColumn<>("NOTENBLATTID");
		idCol.setPrefWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<NotenblattFX, String> titelCol = new TableColumn<>("NOTENBLATTTITEL");
		titelCol.setPrefWidth(200);
		titelCol.setCellValueFactory(new PropertyValueFactory<>("titel"));

		TableColumn<NotenblattFX, String> bildPCol = new TableColumn<>("NOTENBLATTBILDPATH");
		bildPCol.setPrefWidth(200);
		bildPCol.setCellValueFactory(new PropertyValueFactory<>("bildPath"));

		TableView<NotenblattFX> tvNotenblatt = new TableView<>(olNotenblattneu);
		tvNotenblatt.getColumns().addAll(idCol, titelCol, bildPCol);
		tvNotenblatt.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HBox Hb = new HBox();
		// damit rufe ich die LeseNotenblatt Methode aus der DatenbankKlasse auf 
				//und gebe die Notenblätter in der TableView aus 
		try {
			ArrayList<Notenblatt> al = klassen.Datenbank.leseNotenblatt(null);
			olNotenblattneu.clear();
			for (Notenblatt einN : al)
				olNotenblattneu.add(new NotenblattFX(einN)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
		}
		// der Neues Notenblatt  Button führt zu einem neuen Modalen Dialog und die updateMethode
				// wird hier aufgerufen um, sobald man ein neues Notenblatt erzeugt hat 
				//, diesen in der TableView sofort wieder zu geben 
		Button newN = new Button("Neues Notenblatt");
		newN.setPrefWidth(120);
		newN.setPadding(new Insets(10));
		newN.setOnAction(e -> {
		
				  NotenblattFX ndd1= new NotenblattFX(new Notenblatt()); 
					NotenblattDetailDialog2 ndd = new NotenblattDetailDialog2(ndd1);
					ndd.showAndWait();
					olNotenblattneu.clear();
					update01();

					
				});

	

		Label msg = new Label();
		 // der Notenblatt  Bearbeiten Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man ein Notenblatt bearbeitet  hat 
		//, diesen in der TableView sofort wieder zu geben
		Button updateN = new Button("Notenblatt bearbeiten");
		updateN.setPrefWidth(135);
		updateN.setPadding(new Insets(10));
		updateN.setLineSpacing(10);
		updateN.setOnAction(e -> {
			if (tvNotenblatt.getSelectionModel().getSelectedItem() != null) {
				NotenblattDetailDialog2 ndd = new NotenblattDetailDialog2(
						tvNotenblatt.getSelectionModel().getSelectedItem());
				ndd.showAndWait();
				olNotenblattneu.clear();
				update01();

			} else {
				msg.setText(" BItte wählen Sie ein Notenblatt aus! ");
			}

		});
		// damit löscht man ein Notenblatt in dem man im tryBlock die 
				// NotenblattEntfernen methode aus der DatenbankKlasse aufruft
		Button deleteN = new Button("Notenblatt löschen");
		deleteN.setPrefWidth(140);
		deleteN.setPadding(new Insets(10));
		deleteN.setLineSpacing(10);
		deleteN.setOnAction(e -> {
			if (tvNotenblatt.getSelectionModel().getSelectedItem() != null) {
				try {
					klassen.Datenbank.notenblattEntfernen(tvNotenblatt.getSelectionModel().getSelectedItem().getId());
					int i = tvNotenblatt.getSelectionModel().getSelectedIndex();

					olNotenblattneu.remove(i);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			} else {
				msg.setText(" Bitte wählen Sie ein Notenblatt aus !");
			}
		});

		Hb.getChildren().addAll(tvNotenblatt);

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
			ArrayList<Notenblatt> al = klassen.Datenbank.leseNotenblatt(null);
			olNotenblattneu.clear();
			for (Notenblatt einN : al)
				olNotenblattneu.add(new NotenblattFX(einN)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
		}
	}

}