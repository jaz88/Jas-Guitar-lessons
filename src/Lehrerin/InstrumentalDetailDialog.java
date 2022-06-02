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
import klassen.Instrumental;
import klassen.InstrumentalFX;
import klassen.Level;
import klassen.Notenblatt;
import klassen.NotenblattFX;
import klassen.Practice;
import klassen.PracticeFX;
import klassen.SongsFX;
/**
 * In dieser Klasse befindet sich zuerst eine ObservableList von InstrumentalFX
 * und der Klasse Level, 
 * dann gestalte ich die TableView ,und setze Buttons on action 
 * @author Jasmina Marinkovic 
 *
 */
public class InstrumentalDetailDialog extends Dialog<ButtonType> {
	private ObservableList<InstrumentalFX> olInstrumentalneu = FXCollections.observableArrayList();

	private ObservableList<Level> olLevelneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * der Konstruktor 
	 * @param instrumentalFX ist ein Instrumental 
	 */
	public InstrumentalDetailDialog(InstrumentalFX instrumentalFX) {

		TableColumn<InstrumentalFX, String> idCol = new TableColumn<>("INSTRUMENTALID");
		idCol.setPrefWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<InstrumentalFX, String> titelCol = new TableColumn<>("INSTRUMENTALTITEL");
		titelCol.setPrefWidth(150);
		titelCol.setCellValueFactory(new PropertyValueFactory<>("titel"));

		TableColumn<InstrumentalFX, String> interCol = new TableColumn<>("INSTRUMENTALINTERPRET");
		interCol.setPrefWidth(200);
		interCol.setCellValueFactory(new PropertyValueFactory<>("interpret"));

		TableColumn<InstrumentalFX, Level> levelCol = new TableColumn<>("INSTRUMENTALLEVEL");
		levelCol.setPrefWidth(150);
		levelCol.setCellValueFactory(new PropertyValueFactory<>("levelS"));

		TableColumn<InstrumentalFX, String> bildPCol = new TableColumn<>("INSTRUMENTALBILDPATH");
		bildPCol.setPrefWidth(150);
		bildPCol.setCellValueFactory(new PropertyValueFactory<>("bildPath"));

		TableView<InstrumentalFX> tvInstrumental = new TableView<>(olInstrumentalneu);
		tvInstrumental.getColumns().addAll(idCol, titelCol, interCol, levelCol, bildPCol);
		tvInstrumental.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HBox Hb = new HBox();
		 // damit rufe ich die LeseInstrumental Methode aus der DatenbankKlasse auf 
		//und gebe die Instrumentals in der TableView aus 
		try {
			ArrayList<Instrumental> al = klassen.Datenbank.leseInstrumental(null);
			olInstrumentalneu.clear();
			for (Instrumental einI : al)
				olInstrumentalneu.add(new InstrumentalFX(einI)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
			e.printStackTrace();
		}
		// der Neues Instrumental  Button führt zu einem neuen Modalen Dialog und die updateMethode
				// wird hier aufgerufen um, sobald man einen neues Instrumental erzeugt hat 
				//, diesen in der TableView sofort wieder zu geben 
		Button newN = new Button("Neues Instrumental");
		newN.setPrefWidth(120);
		newN.setPadding(new Insets(10));
		newN.setLineSpacing(10);
		newN.setOnAction(e -> {
			
			  InstrumentalFX ndd1= new InstrumentalFX(new Instrumental()); 
				INstrumentalDetailDialog2 ndd = new INstrumentalDetailDialog2(ndd1);
				ndd.showAndWait();
				olInstrumentalneu.clear();
				update01();

				
			});

			

		Label msg = new Label();
		 // der Instrumental  Bearbeiten Button führt zu einem neuen Modalen Dialog und die updateMethode
		// wird hier aufgerufen um, sobald man ein Instrumental bearbeitet  hat 
		//, diesen in der TableView sofort wieder zu geben  
		Button updateN = new Button("Instrumental bearbeiten");
		updateN.setPrefWidth(150);
		updateN.setPadding(new Insets(10));
		updateN.setLineSpacing(10);
		updateN.setOnAction(e -> {

			if (tvInstrumental.getSelectionModel().getSelectedItem() != null) {
				INstrumentalDetailDialog2 ndd = new INstrumentalDetailDialog2(
						tvInstrumental.getSelectionModel().getSelectedItem());
				ndd.showAndWait();
				olInstrumentalneu.clear();
				update01();

			} else {
				msg.setText(" Bitte wählen Sie ein Instrumental aus !");
			}
		}

		);
		// damit löscht man ein Instrumental indem man im tryBlock die 
				// InstrumentalEntfernen methode aus der DatenbankKlasse aufruft 
		Button deleteN = new Button("Instrumental löschen");
		deleteN.setPrefWidth(160);
		deleteN.setPadding(new Insets(10));
		deleteN.setLineSpacing(10);
		deleteN.setOnAction(e -> {
			if (tvInstrumental.getSelectionModel().getSelectedItem() != null) {
				try {
					klassen.Datenbank
							.InstrumentalEntfernen(tvInstrumental.getSelectionModel().getSelectedItem().getId());
					int i = tvInstrumental.getSelectionModel().getSelectedIndex();
					olInstrumentalneu.remove(i);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			} else {
				msg.setText(" Bitte wählen Sie ein Objekt aus!");
			}
		});

		Hb.getChildren().addAll(tvInstrumental);

		FlowPane fpane = new FlowPane();

		fpane.setPadding(new Insets(20));
		fpane.getChildren().addAll(Hb, newN, updateN, deleteN, msg);
		 fpane.setStyle("-fx-background-color: aliceblue;");
		this.getDialogPane().setContent(fpane);
		ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().add(beenden);

	}
	 // Methode um ein sofortiges Bild der Istlage nach Änderungen
		// anzuzeigen 
	public void update01() {

		try {
			ArrayList<Instrumental> al = klassen.Datenbank.leseInstrumental(null);
			olInstrumentalneu.clear();
			for (Instrumental einI : al)
				olInstrumentalneu.add(new InstrumentalFX(einI)); // damit kopiere ich alle sin die Observallsit
		} catch (SQLException e) {

			new Alert(AlertType.ERROR, e.toString()).showAndWait();
			e.printStackTrace();
		}
	}
}