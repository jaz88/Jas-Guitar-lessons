package Schueler;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import klassen.Chords;
import klassen.ChordsFX;
import klassen.NotenblattFX;
/**
 * In dieser Klasse erstelle ich eine Tableview und sobald man auf ein Item drückt
 * wird der ChangeListener aktiviert der in einen modalen Dialog mündet
 * @author Jasmina Marinkovic
 *
 */
public class schuelerChordsDetailDialog1  extends Dialog<ButtonType>{
	private ObservableList<ChordsFX> olChordsneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param chordsFX ist ein Chord 
	 */
	public schuelerChordsDetailDialog1(ChordsFX chordsFX) {
		

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
		

			try {
				ArrayList<Chords> al = klassen.Datenbank.leseChords(null);
				olChordsneu.clear();
				for (Chords einC : al)
					olChordsneu.add(new ChordsFX(einC)); // damit kopiere ich alle sin die Observallsit
			} catch (SQLException e) {

				new Alert(AlertType.ERROR, e.toString()).showAndWait();
				e.printStackTrace();
			}
			// hier wird der ChangeListener aufgerufen 
			 tvChords.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ChordsFX>() {
					@Override
					public void changed(ObservableValue<? extends ChordsFX> observable, ChordsFX oldValue, ChordsFX newValue) {
						schuelerChordsDetailDialog2 notenblattDialog = new schuelerChordsDetailDialog2(newValue);
						notenblattDialog.showAndWait();
					}
				});
			 

			  FlowPane fpane= new FlowPane();
			  fpane.getChildren().addAll(tvChords);
			  fpane.setStyle("-fx-background-color: steelblue;");
			

					{
			 this.getDialogPane().setContent(fpane);
		      ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		      this.getDialogPane().getButtonTypes().addAll( beenden);
		      
	}

}
}