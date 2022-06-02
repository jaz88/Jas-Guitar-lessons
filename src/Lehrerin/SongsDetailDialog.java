package Lehrerin;import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.util.Callback;
import klassen.Datenbank;
import klassen.Level;
import klassen.Notation;
import klassen.Notenblatt;
import klassen.NotenblattFX;
import klassen.Practice;
import klassen.PracticeFX;
import klassen.Songs;
import klassen.SongsFX;
/**
 * In dieser Klasse befindet sich zuerst eine ObservableList von SongsFX und der Klasse Level, 
 * dann gestalte ich die TableView ,und setze Buttons on action 
 * @author Jasmina Marinkovic 
 *
 */
public class SongsDetailDialog extends Dialog<ButtonType> {

	private ObservableList<SongsFX> olSongneu = FXCollections.observableArrayList();
	private ObservableList<Level> olLevelneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * der Konstruktor
	 * @param songsFX ist der Song 
	 */
	public SongsDetailDialog(SongsFX songsFX) {

		TableColumn<SongsFX, String> idCol = new TableColumn<>("SONGID");
		idCol.setPrefWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<SongsFX, String> titelCol = new TableColumn<>("SONGTITEL");
		titelCol.setPrefWidth(200);
		titelCol.setCellValueFactory(new PropertyValueFactory<>("titel"));

		TableColumn<SongsFX, String> interCol = new TableColumn<>("SONGINTERPRET");
		interCol.setPrefWidth(200);
		interCol.setCellValueFactory(new PropertyValueFactory<>("interpret"));

		TableColumn<SongsFX, Integer> notationCol = new TableColumn<>("SONGNOTATION");
		notationCol.setPrefWidth(200);
		notationCol.setCellValueFactory(new PropertyValueFactory<>("notationS"));

		TableColumn<SongsFX, Level> levelCol = new TableColumn<>("SONGLEVEL");
		levelCol.setPrefWidth(200);
		levelCol.setCellValueFactory(new PropertyValueFactory<>("levelS"));

		TableColumn<SongsFX, String> bildPCol = new TableColumn<>("SONGBILDPATH");
		bildPCol.setPrefWidth(100);
		bildPCol.setCellValueFactory(new PropertyValueFactory<>("bildPath"));

		TableView<SongsFX> tvSong = new TableView<>(olSongneu);
		tvSong.getColumns().addAll(idCol, titelCol, interCol, notationCol, levelCol, bildPCol);
		tvSong.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HBox Hb = new HBox();
		// damit rufe ich die LeseSongs Methode aus der DatenbankKlasse auf 
				//und gebe die Songs in der TableView aus 
		try {
			ArrayList<Songs> al = klassen.Datenbank.leseSongs(null);
			olSongneu.clear();
			for (Songs einS : al)
				olSongneu.add(new SongsFX(einS)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
			e.printStackTrace();
		}
		// der Neuer Song Button führt zu einem neuen Modalen Dialog und die updateMethode
				// wird hier aufgerufen um, sobald man ein neuer Song  erzeugt hat 
				//, diesen in der TableView sofort wieder zu geben
		Button newN = new Button("Neuer Song");
		newN.setPrefWidth(110);
		newN.setPadding(new Insets(10));
		newN.setLineSpacing(10);
		newN.setOnAction(e -> {
			 SongsFX ndd1= new SongsFX(new Songs()); 
				SongsDetailDialog2 ndd = new SongsDetailDialog2(ndd1);
				ndd.showAndWait();
				olSongneu.clear();
				update01();

				
			});

			

		Label msg = new Label();
		// der Song  Bearbeiten Button führt zu einem neuen Modalen Dialog und die updateMethode
				// wird hier aufgerufen um, sobald man einen Song  bearbeitet  hat 
				//, diesen in der TableView sofort wieder zu geben
		Button updateN = new Button("Song bearbeiten");
		updateN.setPrefWidth(140);
		updateN.setPadding(new Insets(10));
		updateN.setLineSpacing(10);
		updateN.setOnAction(e -> {
			if (tvSong.getSelectionModel().getSelectedItem() != null) {
				SongsDetailDialog2 ndd = new SongsDetailDialog2(tvSong.getSelectionModel().getSelectedItem());
				ndd.showAndWait();
				olSongneu.clear();
				update01();
			} else {
				msg.setText(" Bitte wählen Sie einen Song aus!");
			}
		});
		// damit löscht man einen Song in dem man im tryBlock die 
				// SongEntfernen methode aus der DatenbankKlasse aufruft
		Button deleteN = new Button("Song löschen");
		deleteN.setPrefWidth(130);
		deleteN.setPadding(new Insets(10));
		deleteN.setLineSpacing(10);
		deleteN.setOnAction(e -> {
			if (tvSong.getSelectionModel().getSelectedItem() != null) {

				try {
					klassen.Datenbank.SongsEntfernen(tvSong.getSelectionModel().getSelectedItem().getId());
					int i = tvSong.getSelectionModel().getSelectedIndex();
					olSongneu.remove(i);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			} else {
				msg.setText("Bitte wählen Sie einen Song aus !");

			}
		});

		Hb.getChildren().addAll(tvSong);

		FlowPane fpane = new FlowPane();
        fpane.setStyle("-fx-background-color: aliceblue;");
		fpane.setPadding(new Insets(10));
		fpane.getChildren().addAll(Hb, newN, updateN, deleteN, msg);
      
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
			ArrayList<Songs> al = klassen.Datenbank.leseSongs(null);
			olSongneu.clear();
			for (Songs einS : al)
				olSongneu.add(new SongsFX(einS)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
			e.printStackTrace();
		}
	}
}
