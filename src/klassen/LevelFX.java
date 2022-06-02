package klassen;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * Diese Klasse ist die SimpleProperty Klasse der Level, ich hole die Klasse Level 
 * erzeuge die dementsprechenden Attribute und diese dann als ObservableList in den GUI 
 * Klassen verwenden zu können 
 * @author Jasmina Marinkovic 
 *
 */
public class LevelFX {

	private Level modellLevel;
	private SimpleIntegerProperty id;

	private SimpleStringProperty bezeichnung;
/**
 * Der Konstruktor 
 * @param modellLevel hier wird der Level übergeben 
 */
	public LevelFX(Level modellLevel) {

		super();
		this.modellLevel = modellLevel;
		id = new SimpleIntegerProperty(modellLevel.getId());
		bezeichnung = new SimpleStringProperty(modellLevel.getBezeichnung());
	}

	public Level getModellLevel() {
		return modellLevel;
	}

	public int getId() {
		return id.get();
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public String getBezeichnung() {
		return bezeichnung.get();
	}

	public SimpleStringProperty bezeichnungProperty() {
		return bezeichnung;
	}
}