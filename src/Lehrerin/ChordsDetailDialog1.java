
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
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import klassen.Chords;
import klassen.ChordsFX;
import klassen.Datenbank;
import klassen.Notation;
import klassen.Notenblatt;
import klassen.NotenblattFX;
import klassen.Practice;
import klassen.PracticeFX;
/**
 * In dieser Klasse befindet sich zuerst eine ObservableList von ChordsFX, 
 * dann gestalte ich die TableView ,und setze Buttons on action 
 * @author Jasmina Marinkovic 
 *
 */
public class ChordsDetailDialog1 extends Dialog<ButtonType> {
	private ObservableList<ChordsFX> olChordsneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * Der Konstruktor 
	 * @param chordsFX ist der Chord
	 */
	public ChordsDetailDialog1(ChordsFX chordsFX) {

		TableColumn<ChordsFX, String> idCol = new TableColumn<>("CHORDID");
		idCol.setPrefWidth(100);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<ChordsFX, String> titelCol = new TableColumn<>("CHORDTITEL");
		titelCol.setPrefWidth(150);
		
		titelCol.setCellValueFactory(new PropertyValueFactory<>("titel"));

		TableColumn<ChordsFX, Integer> notationCol = new TableColumn<>("CHORDNOTATION");
		notationCol.setPrefWidth(150);
		notationCol.setCellValueFactory(new PropertyValueFactory<>("notationS"));
		

		TableColumn<ChordsFX, String> bildPCol = new TableColumn<>("CHORDBILDPATH");
		bildPCol.setPrefWidth(150);
		bildPCol.setCellValueFactory(new PropertyValueFactory<>("bildPath"));

		TableView<ChordsFX> tvChords = new TableView<>(olChordsneu);
      
         
		tvChords.getColumns().addAll(idCol, titelCol, notationCol, bildPCol);
		tvChords.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
		HBox Hb = new HBox();
	
       // damit rufe ich die LeseChords Methode aus der DatenbankKlasse auf 
		//und gebe die Chords in der TableView aus 
		try {
			ArrayList<Chords> al = klassen.Datenbank.leseChords(null);
			olChordsneu.clear();
			for (Chords einC : al)
				olChordsneu.add(new ChordsFX(einC)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
			e.printStackTrace();
		}
		
   // der Neue Chord Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man einen neues Chord erzeugt hat 
		//, diesen in der TableView sofort wieder zu geben  
		Button newN = new Button("Neuer Chord");
		newN.setPrefWidth(120);
		newN.setPadding(new Insets(10));
		newN.setLineSpacing(10);
		newN.setOnAction(e -> {
			ChordsFX ndd1= new ChordsFX(new Chords()); 
			ChordsDetailDialog2 ndd = new ChordsDetailDialog2(ndd1);
			ndd.showAndWait();
			olChordsneu.clear();
			update01();

			
		});
			

		Label msg = new Label();
		msg.setAlignment(Pos.BOTTOM_RIGHT);
		 // der Chord  Bearbeiten Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man einen Chord bearbeitet  hat 
		//, diesen in der TableView sofort wieder zu geben  
		Button updateN = new Button("Chord bearbeiten");
		updateN.setPrefWidth(140);
		updateN.setPadding(new Insets(10));
		updateN.setLineSpacing(10);
		updateN.setOnAction(e -> {
			if (tvChords.getSelectionModel().getSelectedItem() != null) {

				ChordsDetailDialog2 ndd = new ChordsDetailDialog2(tvChords.getSelectionModel().getSelectedItem());
				ndd.showAndWait();
				olChordsneu.clear();
				update01();
			} else {

				msg.setText("Bitte wählen Sie einen Chord aus!");

			}
		});
       // damit löscht man einen Chord indem man im tryBlock die 
		// ChordsEntfernen methode aus der DatenbankKlasse aufruft 
		Button deleteN = new Button("Chord löschen");
		deleteN.setPrefWidth(135);
		deleteN.setPadding(new Insets(10));
		deleteN.setLineSpacing(10);
		deleteN.setOnAction(e -> {
			if (tvChords.getSelectionModel().getSelectedItem() != null) {

				try {
					klassen.Datenbank.ChordsEntfernen(tvChords.getSelectionModel().getSelectedItem().getId());
					int i = tvChords.getSelectionModel().getSelectedIndex();
					olChordsneu.remove(i);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			} else {
				msg.setText(" Bitte wählen Sie ein Objekt aus !");
			}
		});

		Hb.getChildren().addAll(tvChords);

		FlowPane fpane = new FlowPane();

		fpane.setPadding(new Insets(10));
	
		fpane.getChildren().addAll(Hb, newN, updateN, deleteN, msg);
        fpane.setStyle("-fx-background-color: aliceblue;");
		this.getDialogPane().setContent(fpane);
		ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll( beenden);

	}
    // Methode um ein sofortiges Bild der Istlage nach Änderungen
	// anzuzeigen 
	public void update01() {

		try {
			ArrayList<Chords> al = klassen.Datenbank.leseChords(null);
			olChordsneu.clear();
			for (Chords einC : al)
				olChordsneu.add(new ChordsFX(einC)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
			e.printStackTrace();
		}
	}
}