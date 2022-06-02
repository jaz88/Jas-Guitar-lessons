package klassen;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * Diese Klasse ist die SimpleProperty Klasse des Notenblattes, ich hole die Klasse Notenblatt 
 * erzeuge die dementsprechenden Attribute und diese dann als ObservableList in den GUI 
 * Klassen verwenden zu können 
 * @author Jasmina Marinkovic 
 *
 */
public class NotenblattFX {

	private Notenblatt modellNotenblatt; 
	private SimpleIntegerProperty id;
	private SimpleStringProperty titel;
	private SimpleStringProperty bildPath;
/**
 * Der Konstruktor 
 * @param modellNotenblatt hier wird das Notenblatt übergeben 
 */
	public NotenblattFX(Notenblatt modellNotenblatt) {
		super();
		this.modellNotenblatt = modellNotenblatt;
		id = new SimpleIntegerProperty(modellNotenblatt.getId());
		titel = new SimpleStringProperty(modellNotenblatt.getTitel());
		bildPath = new SimpleStringProperty(modellNotenblatt.getBildPath());
	}

	public Notenblatt getModellNotenblatt() {
		return modellNotenblatt;
	}

	public int getId() {
		return id.get();
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public String getTitel() {
		return titel.get();
	}

	public SimpleStringProperty titelProperty() {
		return titel;
	}

	public String getBildPath() {
		return bildPath.get();
	}

	public SimpleStringProperty bildPathProperty() {
		return bildPath;
	}

}