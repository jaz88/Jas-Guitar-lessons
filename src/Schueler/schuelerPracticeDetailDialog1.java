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
import klassen.NotenblattFX;
import klassen.Practice;
import klassen.PracticeFX;
/**
 * In dieser Klasse erstelle ich eine Tableview und sobald man auf ein Item drückt
 * wird der ChangeListener aktiviert der in einen modalen Dialog mündet
 * @author Jasmina Marinkovic
 *
 */
public class schuelerPracticeDetailDialog1  extends Dialog<ButtonType>{
	private ObservableList<PracticeFX> olPracticeneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param practiceFX ist eine Practice 
	 */
	public schuelerPracticeDetailDialog1(PracticeFX practiceFX) {
		
		


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

			try {
				ArrayList<Practice> al = klassen.Datenbank.lesePractice(null);
				olPracticeneu.clear();
				for (Practice einP : al)
					olPracticeneu.add(new PracticeFX(einP)); // damit kopiere ich alle sin die Observallsit
			} catch (SQLException e) {

				new Alert(AlertType.ERROR, e.toString()).showAndWait();
			}
			 // hier wird der ChangeListener aufgerufen 
			tvPractice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PracticeFX>() {
				@Override
				public void changed(ObservableValue<? extends PracticeFX> observable, PracticeFX oldValue, PracticeFX newValue) {
					schuelerPracticeDetailDialog2 notenblattDialog = new schuelerPracticeDetailDialog2(newValue);
			 		notenblattDialog.showAndWait();
				}
			});
			
		  
			  
				  
				  
			 
			  FlowPane fpane= new FlowPane();
			  fpane.getChildren().addAll(tvPractice);
			  fpane.setStyle("-fx-background-color: steelblue;");

					{
			 this.getDialogPane().setContent(fpane);
			
		      ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		      this.getDialogPane().getButtonTypes().addAll( beenden);
		      
	}

	}
}