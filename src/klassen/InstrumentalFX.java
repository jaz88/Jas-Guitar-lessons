package klassen;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Diese Klasse ist die SimpleProperty Klasse der Instrumental, ich hole die Klasse Instrumental 
 * erzeuge die dementsprechenden Attribute und diese dann als ObservableList in den GUI 
 * Klassen verwenden zu können 
 * @author Jasmina Marinkovic 
 *
 */
public class InstrumentalFX {

	private Instrumental modellInstrumental;
	private SimpleIntegerProperty id;
	private SimpleStringProperty titel;
	private SimpleStringProperty interpret;
	private SimpleIntegerProperty level;
	private SimpleStringProperty levelS;
	private SimpleStringProperty bildPath;
/**
 * Der Konstruktor 
 * @param modellInstrumental hier wird das Instrumental übergeben 
 */
	public InstrumentalFX(Instrumental modellInstrumental) {
		super();
		this.modellInstrumental = modellInstrumental;
		id = new SimpleIntegerProperty(modellInstrumental.getId());
		titel = new SimpleStringProperty(modellInstrumental.getTitel());
		interpret = new SimpleStringProperty(modellInstrumental.getInterpret());
		level = new SimpleIntegerProperty(modellInstrumental.getLevel() != null ? modellInstrumental.getLevel().getId() : 0);
		levelS = new SimpleStringProperty(modellInstrumental.getLevel() != null ? modellInstrumental.getLevel().getBezeichnung() : "?");
		bildPath = new SimpleStringProperty(modellInstrumental.getBildPath());
	}

	public Instrumental getModellInstrumental() {
		return modellInstrumental;
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

	public final SimpleStringProperty levelSProperty() {
		return this.levelS;
	}
	

	public final String getLevelS() {
		return this.levelSProperty().get();
	}
	

}