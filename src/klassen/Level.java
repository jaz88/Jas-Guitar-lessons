package klassen;
/**
 * In dieser Klasse lege ich den Grundstein für die 
 * GUI Anwendungen in diese Falle die Level , ich lege die Attribute fest 
 * ich gestalte einen Konstruktor der diese Attibute als Parameter anführt 
 * ich erzeuge einen leeren Konstruktor der mir die Möglichkeit gibt neue Level rein zu stellen
 * mit der Hilfe der Getter und Setter und der ToString Methode komplettiere 
 * ich diese Klasse , diese Klasse die der Grundstein für die SimpleProperty Klasse
 * LevelFX ist 
 * @author Jasmina Marinkovic 
 *
 */
public class Level {
	private int id;
	private String bezeichnung;
/**
 * Der Konstruktor der die gewünschten Parameter  bereit stellt
 * @param id Identifikationsnummer der Level
 * @param bezeichnung stellt den Schwierigkeitsgrad dar 
 */
	public Level(int id, String bezeichnung) {
		this.id = id;
		this.bezeichnung = bezeichnung;

	}
/**
 * der Parameterlose Konstruktor der mir hilft neue Objekte zu erzeugen
 */
	public Level() {
		super();
	}

	public Level(int int1) {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
/**
 * die ToString Methode die die Informationen in einen String umsetzt 
 */
	@Override
	public String toString() {
		return bezeichnung;
	}

}