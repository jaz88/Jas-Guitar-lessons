package klassen;

import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
/**
 * Diese Klasse gibt mir die M�glichkeit ein Bild zu drucken,sie zeigt eine Methode 
 * an in der die Klasse PrinterJob aufgerufen wird um den Scenegraph zu drucken , jedoch habe 
 * durch die Aufrufung der Klasse ImageView ( inklusive Parameter ) die Option entsteht
 *  nur ein gew�nschtes Bild zu drucken 
 * @author Jasmina Marinkovic 
 *
 */
public class Drucken {
	/**
	 * Methode um zu drucken inklusive 2 Alerts die 
	 * anzeigen falls der Druck fehlschl�gt 
	 * @param iv �bergibt ein Image 
	 */
	public static void drucken(ImageView iv) {
		PrinterJob pj = PrinterJob.createPrinterJob();
		if(pj != null) {
			boolean printed = pj.printPage(iv);
			if(printed)
				pj.endJob();
			else
				new Alert(AlertType.ERROR, "Druck fehlgeschlagen").showAndWait();
		}
		else
			new Alert(AlertType.ERROR, "PrinterJob nicht erzeugt").showAndWait();
		
		
	}

}
