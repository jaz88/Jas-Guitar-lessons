package Schueler;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import klassen.Datenbank;
import klassen.Level;
import klassen.LevelFX;
import klassen.Notenblatt;
import klassen.NotenblattFX;
import klassen.Songs;
import klassen.SongsFX;
/**
 * In dieser Klasse erstelle ich eine Tableview und sobald man auf ein Item drückt
 * wird der ChangeListener aktiviert der in einen modalen Dialog mündet
 * @author Jasmina Marinkovic 
 *
 */
public class schuelerSongsDetailDialog extends Dialog<ButtonType> {
	private ObservableList<SongsFX> olSongneu = FXCollections.observableArrayList();
	private ObservableList<Level> olLevelneu = FXCollections.observableArrayList();
 
	private LevelFX levelFX;
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param songsFX ist ien Song 
	 */
	public schuelerSongsDetailDialog(SongsFX songsFX) {
		
	
	
		// Liste der Themen in die ComboBox eintragen
		//cbThemen.setItems(FXCollections.observableArrayList(alThemen));
	
		
	

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

			try {
				ArrayList<Songs> al = klassen.Datenbank.leseSongs(null);
				olSongneu.clear();
				for (Songs einS : al)
					olSongneu.add(new SongsFX(einS)); // damit kopiere ich alle sin die Observallsit
			} catch (SQLException e) {

				new Alert(AlertType.ERROR, e.toString()).showAndWait();
				e.printStackTrace();
			}
			 // hier wird der ChangeListener aufgerufen 
			 tvSong.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SongsFX>() {
					@Override
					public void changed(ObservableValue<? extends SongsFX> observable, SongsFX oldValue, SongsFX newValue) {
						schuelerSongsDetailDialog2 notenblattDialog = new schuelerSongsDetailDialog2(newValue);
						notenblattDialog.showAndWait();
					}
				});
				
			  
				  
					  
					  
				 
				  FlowPane fpane= new FlowPane();
				  fpane.getChildren().addAll(tvSong);
				  fpane.setStyle("-fx-background-color: steelblue;");

						{
				 this.getDialogPane().setContent(fpane);
				
			      ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
			      this.getDialogPane().getButtonTypes().addAll( beenden);
			      
			     
		      
				
			}

		}
			
	}

