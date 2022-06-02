package klassen;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Diese Klasse ist die SimpleProperty Klasse der Practice, ich hole die Klasse Practice 
 * erzeuge die dementsprechenden Attribute und diese dann als ObservableList in den GUI 
 * Klassen verwenden zu können 
 * @author Jasmina Marinkovic 
 *
 */
public class PracticeFX {

	private Practice modellPractice;
	private SimpleIntegerProperty id;
	private SimpleStringProperty titel;
	private SimpleStringProperty bildPath;
/**
 * Der Konstruktor 
 * @param modellPractice hier wird die Practice übergeben 
 */
	public PracticeFX(Practice modellPractice) {
		super();
		this.modellPractice = modellPractice;
		id = new SimpleIntegerProperty(modellPractice.getId());
		titel = new SimpleStringProperty(modellPractice.getTitel());
		bildPath = new SimpleStringProperty(modellPractice.getBildPath());
	}

	public Practice getModellPractice() {
		return modellPractice;
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