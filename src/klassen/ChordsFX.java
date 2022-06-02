package klassen;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Diese Klasse ist die SimpleProperty Klasse der Chords, ich hole die Klasse Chords 
 * erzeuge die dementsprechenden Attribute um diese dann als ObservableList in den GUI 
 * Klassen verwenden zu können 
 * @author Jasmina Marinkovic 
 *
 */
public class ChordsFX {

	private Chords modellChords;
	private SimpleIntegerProperty id;
	private SimpleStringProperty titel;
	private SimpleIntegerProperty notation;
	private SimpleStringProperty notationS;
	private SimpleStringProperty bildPath;
/**
 *  Konstruktor 
 * @param modellChords hier wird der Chord übergeben  
 */
	public ChordsFX(Chords modellChords) {
		super();
		this.modellChords = modellChords;
	
		id = new SimpleIntegerProperty(modellChords.getId());
		titel = new SimpleStringProperty(modellChords.getTitel());
		notation= new SimpleIntegerProperty(modellChords.getNotation());
		notationS  = new SimpleStringProperty(Notation.values()[ modellChords.getNotation() ].name());
		bildPath = new SimpleStringProperty(modellChords.getBildPath());
	}

	public Chords getModellChords() {
		return modellChords;
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

	public int getNotation() {
		return notation.get();
	}

	public SimpleIntegerProperty notationProperty() {
		return notation;
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

}