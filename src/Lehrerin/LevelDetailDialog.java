package Lehrerin;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import klassen.Datenbank;
import klassen.InstrumentalFX;
import klassen.Level;
import klassen.LevelFX;
import klassen.Notenblatt;
import klassen.NotenblattFX;
import klassen.Practice;
import klassen.PracticeFX;
/**
 * In dieser Klasse befindet sich zuerst eine ObservableList von LevelFX, 
 * dann gestalte ich die TableView ,und setze Buttons on action 
 * @author Jasmina Marinkovic 
 *
 */
public class LevelDetailDialog extends Dialog<ButtonType> {

	private ObservableList<LevelFX> olLevelneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
    /**
     * Der Konstruktor
     * @param levelFX ist der Level
     */
	public LevelDetailDialog(LevelFX levelFX) {

		TableColumn<LevelFX, String> idCol = new TableColumn<>("ID");
		idCol.setPrefWidth(150);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<LevelFX, String> bezeichCol = new TableColumn<>("BEZEICHNUNG");
		bezeichCol.setPrefWidth(150);
		bezeichCol.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));

		TableView<LevelFX> tvLevel = new TableView<>(olLevelneu);
		tvLevel.getColumns().addAll(idCol, bezeichCol);
		tvLevel.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HBox Hb = new HBox();
		 // damit rufe ich die LeseLevel Methode aus der DatenbankKlasse auf 
		//und gebe die Level in der TableView aus 
		try {
			ArrayList<Level> al = klassen.Datenbank.leseLevel(null);
			olLevelneu.clear();
			for (Level einL : al)
				olLevelneu.add(new LevelFX(einL)); // damit kopiere ich alle in die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
		}
		// der Neues Level  Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man einen neuen Level erzeugt hat 
		//, diesen in der TableView sofort wieder zu geben 
		Button newL = new Button("Neues Level");
		newL.setPrefWidth(100);
		newL.setPadding(new Insets(10));
		newL.setLineSpacing(10);
		newL.setOnAction(e -> {
			 LevelFX ndd1= new LevelFX(new Level()); 
				LevelDetailDialog2 ndd = new LevelDetailDialog2(ndd1);
				ndd.showAndWait();
				olLevelneu.clear();
				update01();

				
			});

			
		

		Label msg = new Label();
		 // der Level  Bearbeiten Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man ein Level bearbeitet  hat 
		//, diesen in der TableView sofort wieder zu geben
		Button updateL = new Button("Level bearbeiten");
		updateL.setPrefWidth(120);
		updateL.setPadding(new Insets(10));
		updateL.setLineSpacing(10);
		updateL.setOnAction(e -> {
			if (tvLevel.getSelectionModel().getSelectedItem() != null) {
				LevelDetailDialog2 ndd = new LevelDetailDialog2(tvLevel.getSelectionModel().getSelectedItem());
				ndd.showAndWait();
				olLevelneu.clear();
				update01();
			} else {
				msg.setText(" Bitte wählen Sie ein Level aus!");
			}
		});
		// damit löscht man ein Level in dem man im tryBlock die 
		// LevelEntfernen methode aus der DatenbankKlasse aufruft
		Button deleteL = new Button("Level löschen");
		deleteL.setPrefWidth(100);
		deleteL.setPadding(new Insets(10));
		deleteL.setLineSpacing(10);
		deleteL.setOnAction(e -> {
			if (tvLevel.getSelectionModel().getSelectedItem() != null) {
				try {
					klassen.Datenbank.LevelEntfernen(tvLevel.getSelectionModel().getSelectedItem().getId());
					int i = tvLevel.getSelectionModel().getSelectedIndex();
					olLevelneu.remove(i);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			} else {
				msg.setText(" Bitte wählen Sie ein Level aus ");
			}
		});

		Hb.getChildren().addAll(tvLevel);

		FlowPane fpane = new FlowPane();

		fpane.setPadding(new Insets(10));
		fpane.getChildren().addAll(Hb, newL, updateL, deleteL, msg);
		 fpane.setStyle("-fx-background-color: aliceblue;");
		this.getDialogPane().setContent(fpane);
		ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().add(beenden);

	}
/**
 *  Methode um ein sofortiges Bild der Istlage nach Änderungen
		 anzuzeigen 
 */
	public void update01() {

		try {
			ArrayList<Level> al = klassen.Datenbank.leseLevel(null);
			olLevelneu.clear();
			for (Level einL : al)
				olLevelneu.add(new LevelFX(einL)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
		}
	}
}
