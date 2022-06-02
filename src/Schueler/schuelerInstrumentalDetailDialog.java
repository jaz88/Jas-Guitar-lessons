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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import klassen.Instrumental;
import klassen.InstrumentalFX;
import klassen.Level;
import klassen.NotenblattFX;
/**
 * 
 * In dieser Klasse erstelle ich eine Tableview und sobald man auf ein Item drückt
 * wird der ChangeListener aktiviert der in einen modalen Dialog mündet
 * @author Jasmina Marinkovic
 *
 */
public class schuelerInstrumentalDetailDialog extends Dialog<ButtonType> {
	private ObservableList<InstrumentalFX> olInstrumentalneu = FXCollections.observableArrayList();

	private ObservableList<Level> olLevelneu = FXCollections.observableArrayList();
	
	@SuppressWarnings("unchecked")
	
/**
 * 
 * @param instrumentalFX ist ein Intrumental
 */
	public schuelerInstrumentalDetailDialog(InstrumentalFX instrumentalFX) {
		
	
	

			TableColumn<InstrumentalFX, String> idCol = new TableColumn<>("INSTRUMENTALID");
			idCol.setPrefWidth(100);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<InstrumentalFX, String> titelCol = new TableColumn<>("INSTRUMENTALTITEL");
			titelCol.setPrefWidth(200);
			titelCol.setCellValueFactory(new PropertyValueFactory<>("titel"));

			TableColumn<InstrumentalFX, String> interCol = new TableColumn<>("INSTRUMENTALINTERPRET");
			interCol.setPrefWidth(200);
			interCol.setCellValueFactory(new PropertyValueFactory<>("interpret"));

			TableColumn<InstrumentalFX, Level> levelCol = new TableColumn<>("INSTRUMENTALLEVEL");
			levelCol.setPrefWidth(150);
			levelCol.setCellValueFactory(new PropertyValueFactory<>("levelS"));

			TableColumn<InstrumentalFX, String> bildPCol = new TableColumn<>("INSTRUMENTALBILDPATH");
			bildPCol.setPrefWidth(100);
			bildPCol.setCellValueFactory(new PropertyValueFactory<>("bildPath"));

			TableView<InstrumentalFX> tvInstrumental = new TableView<>(olInstrumentalneu);
			tvInstrumental.getColumns().addAll(idCol, titelCol, interCol, levelCol, bildPCol);
			tvInstrumental.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			HBox Hb = new HBox();

			try {
				ArrayList<Instrumental> al = klassen.Datenbank.leseInstrumental(null);
				olInstrumentalneu.clear();
				for (Instrumental einI : al)
					olInstrumentalneu.add(new InstrumentalFX(einI)); // damit kopiere ich alle sin die Observallsit
			} catch (SQLException e) {

				new Alert(AlertType.ERROR, e.toString()).showAndWait();
				e.printStackTrace();
			}
			// hier wird der ChangeListener aufgerufen 
			 tvInstrumental.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InstrumentalFX>() {
					@Override
					public void changed(ObservableValue<? extends InstrumentalFX> observable, InstrumentalFX oldValue, InstrumentalFX newValue) {
						schuelerInstrumentalDetailDialog2 notenblattDialog = new schuelerInstrumentalDetailDialog2(newValue);
						notenblattDialog.showAndWait();
					}
				});

	  FlowPane fpane= new FlowPane();
	  fpane.getChildren().addAll(tvInstrumental);
	  fpane.setStyle("-fx-background-color: steelblue;");

			{
	 this.getDialogPane().setContent(fpane);
      ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
      this.getDialogPane().getButtonTypes().addAll( beenden);
      
     

}}}