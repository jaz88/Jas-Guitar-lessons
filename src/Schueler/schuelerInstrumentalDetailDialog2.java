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
import klassen.InstrumentalFX;
import klassen.Level;
 /**
  * In dieser Klasse rufe ich mit hilfe der ImageView den absoluten BildPfad des gespeicheren 
 * Items 
  * @author Jasmina Marinkovic 
  *
  */
public class schuelerInstrumentalDetailDialog2  extends Dialog<ButtonType>{
	private ObservableList<InstrumentalFX> olInstrumentalneu = FXCollections.observableArrayList();

	private ObservableList<Level> olLevelneu = FXCollections.observableArrayList();
   /**
    * 
    * @param instrumentalFX ist ein Instrumental 
    */
	public schuelerInstrumentalDetailDialog2(InstrumentalFX instrumentalFX) {
		ImageView iv = new ImageView(new Image(String.valueOf(Paths.get(instrumentalFX.getBildPath()).toUri())));
		iv.setPreserveRatio(true);
		iv.setFitWidth(600);
		iv.setFitHeight(800);
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
	      

}}
