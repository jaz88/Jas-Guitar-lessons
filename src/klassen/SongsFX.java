package klassen;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Diese Klasse ist die SimpleProperty Klasse der Songs , ich hole die Klasse Songs  
 * erzeuge die dementsprechenden Attribute und diese dann als ObservableList in den GUI 
 * Klassen verwenden zu können 
 * @author Jasmina Marinkovic 
 *
 */
public class SongsFX {

	private Songs modellSongs;
	private SimpleIntegerProperty id;
	private SimpleStringProperty titel;
	private SimpleStringProperty interpret;
	private SimpleIntegerProperty notation;
	private SimpleStringProperty notationS;
	private SimpleIntegerProperty level;
	private SimpleStringProperty levelS;
	private SimpleStringProperty bildPath;
 /**
  * Der Konstruktor 
  * @param modellSongs hier wird der Song  übergeben 
  */
	public SongsFX(Songs modellSongs) {
		super();
		this.modellSongs = modellSongs;
	
		id = new SimpleIntegerProperty(modellSongs.getId());
		titel = new SimpleStringProperty(modellSongs.getTitel());
		interpret = new SimpleStringProperty(modellSongs.getInterpret());
		notation = new SimpleIntegerProperty(modellSongs.getNotation());
		notationS = new SimpleStringProperty(Notation.values()[modellSongs.getNotation()].name());
		level = new SimpleIntegerProperty(modellSongs.getLevel() != null ? modellSongs.getLevel().getId() : 0);
		levelS = new SimpleStringProperty(modellSongs.getLevel() != null ? modellSongs.getLevel().getBezeichnung() : "?");
		bildPath = new SimpleStringProperty(modellSongs.getBildPath());
	}

	public Songs getModellSongs() {
		return modellSongs;
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

	public String getInterpret() {
		return interpret.get();
	}

	public SimpleStringProperty interpretProperty() {
		return interpret;
	}

	public int getNotation() {
		return notation.get();
	}

	public SimpleIntegerProperty notationProperty() {
		return notation;
	}

	public int getLevel() {
		return level.get();
	}

	public SimpleIntegerProperty levelProperty() {
		return level;
	}

	public String getBildPath() {
		return bildPath.get();
	}

	public SimpleStringProperty bildPathProperty() {
		return bildPath;
	}

	public final SimpleStringProperty notationSProperty() {
		return this.notationS;
	}
	

	public final String getNotationS() {
		return this.notationSProperty().get();
	}
	
	

	public final SimpleStringProperty levelSProperty() {
		return this.levelS;
	}
	

	public final String getLevelS() {
		return this.levelSProperty().get();
	}
	

}
