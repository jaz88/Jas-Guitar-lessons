package klassen;
/**
 * In dieser Klasse lege ich den Grundstein für die 
 * GUI Anwendungen in diese Falle das Notenblatt , ich lege die Attribute fest 
 * ich gestalte einen Konstruktor der diese Attibute als Parameter anführt 
 * ich erzeuge einen leeren Konstruktor der mir die Möglichkeit gibt neue Notenblätter rein zu stellen
 * mit der Hilfe der Getter und Setter und der ToString Methode komplettiere 
 * ich diese Klasse , diese Klasse die der Grundstein für die SimpleProperty Klasse
 * NotenblattFX ist 
 * @author Jasmina Marinkovic
 *
 */
public class Notenblatt {
	private int id;
	private String titel;
	private String bildPath;
/**
 * Der Konstruktor der die gewünschten Parameter  bereit stellt
 * @param id Identifikaktionsnummer des Notenblatts  
 * @param titel  stellt den Titel des Gewünschten Items dar
 * @param bildPath String um den Bildpfad festzu legen 
 */
	public Notenblatt(int id, String titel, String bildPath) {
		super();
		this.id = id;
		this.titel = titel;
		this.bildPath = bildPath;

	}
/**
 * der Parameterlose Konstruktor der mir hilft neue Objekte zu erzeugen
 */
	public Notenblatt() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBildPath() {
		return bildPath;
	}

	public void setBildPath(String bildPath) {
		this.bildPath = bildPath;
	}
/**
 * die ToString Methode die die Informationen in einen String umsetzt 
 */
	@Override
	public String toString() {
		return "Notenblatt [id=" + id + ", titel=" + titel + ", bildPath=" + bildPath + "]";
	}

}
