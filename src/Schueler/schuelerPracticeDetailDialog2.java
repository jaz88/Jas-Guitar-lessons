package Schueler;

import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import klassen.Drucken;
import klassen.NotenblattFX;
import klassen.PracticeFX;
/**
 * 
 * In dieser Klasse rufe ich mit hilfe der ImageView den absoluten BildPfad des gespeicheren 
 * Items 
 * @author Jasmina Marinkovic
 *
 */
public class schuelerPracticeDetailDialog2 extends Dialog<ButtonType> {
	ObservableList olPracticeNeu= FXCollections.observableArrayList();
	/**
	 * 
	 * @param practiceFX ist eine Practice 
	 */
	public schuelerPracticeDetailDialog2(PracticeFX practiceFX) {
		
			
			ImageView iv = new ImageView(new Image(String.valueOf(Paths.get(practiceFX.getBildPath()).toUri())));
			iv.setPreserveRatio(true);
			iv.setFitWidth(400);
			Button print = new Button("Drucken");
			// sobald der Print Button on action gesetzt wird 
			// wird die Methode drucken aus der Duckerklasse aufgerufen 
			// un ddas Item wird ausgedruckt
				print.setOnAction(e -> {
					Drucken.drucken(iv);
				});
		   VBox hb0 = new VBox(20, iv,print);
			
		     
		   
		   
			  this.getDialogPane().setContent(hb0);
				
		      ButtonType beenden = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		      this.getDialogPane().getButtonTypes().addAll( beenden);
		      
	       

	}

}
