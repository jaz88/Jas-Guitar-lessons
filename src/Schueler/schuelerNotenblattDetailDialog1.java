package Schueler;

import java.nio.file.Path;


import java.sql.SQLException;
import java.util.ArrayList;

import Lehrerin.NotenblattDetailDialog1;

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
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import klassen.Notenblatt;
import klassen.NotenblattFX;


/**
 *  In dieser Klasse erstelle ich eine Tableview und sobald man auf ein Item drückt
 * wird der ChangeListener aktiviert der in einen modalen Dialog mündet
 * @author Jasmina Marinkovic
 *
 */
public class schuelerNotenblattDetailDialog1 extends Dialog<ButtonType> {
ObservableList olNotenblattNeu= FXCollections.observableArrayList();
/**
 * 
 * @param notenblattFX ist ein Notenblatt
 */
	public schuelerNotenblattDetailDialog1(NotenblattFX notenblattFX) {
		
		
		
		
		TableColumn<NotenblattFX,String>idCol= new TableColumn<>("NOTENBLATTID");
		idCol.setPrefWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<NotenblattFX,String>titelCol= new TableColumn<>("NOTENBLATTTITEL");
		titelCol.setPrefWidth(200);
		titelCol.setCellValueFactory(new PropertyValueFactory<>("titel"));
		
		TableColumn<NotenblattFX,String>bildPCol= new TableColumn<>("NOTENBLATTBILDPATH");
		bildPCol.setPrefWidth(150);
	    bildPCol.setCellValueFactory(new PropertyValueFactory<>("bildPath"));
	 
	 TableView<NotenblattFX> tvNotenblatt= new TableView<>(olNotenblattNeu);
	tvNotenblatt.getColumns().addAll(idCol,titelCol,bildPCol);
		tvNotenblatt.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HBox Hb= new HBox();
	

	    try {
			ArrayList<Notenblatt> al= klassen.Datenbank.leseNotenblatt(null);
			olNotenblattNeu.clear(); 
			for ( Notenblatt einN: al)
				olNotenblattNeu.add(new NotenblattFX(einN)); // damit kopiere ich alle sin die Observallsit 
		} catch (SQLException e) {
			
			new Alert( AlertType.ERROR,e.toString()).showAndWait(); 
		}
	 // hier wird der ChangeListener aufgerufen 
	    tvNotenblatt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<NotenblattFX>() {
			@Override
			public void changed(ObservableValue<? extends NotenblattFX> observable, NotenblattFX oldValue, NotenblattFX newValue) {
				schuelerNotenblattDetailDialog2 notenblattDialog = new schuelerNotenblattDetailDialog2(newValue);
				notenblattDialog.showAndWait();
			}
		});
		
	  
		  
			  
			  
		 
		  FlowPane fpane= new FlowPane();
		  fpane.getChildren().addAll(tvNotenblatt);
		  fpane.setStyle("-fx-background-color: steelblue;");

				{
		 this.getDialogPane().setContent(fpane);
		
	      ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
	      this.getDialogPane().getButtonTypes().addAll( beenden);
	      
	     
      
		
	}

}
	
}