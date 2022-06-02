package klassen;
/**
 * In dieser Klasse lege ich den Grundstein f�r die 
 * GUI Anwendungen in diese Falle die Practice , ich lege die Attribute fest 
 * ich gestalte einen Konstruktor der diese Attibute als Parameter anf�hrt 
 * ich erzeuge einen leeren Konstruktor der mir die M�glichkeit gibt neue Practice rein zu stellen
 * mit der Hilfe der Getter und Setter und der ToString Methode komplettiere 
 * ich diese Klasse , diese Klasse die der Grundstein f�r die SimpleProperty Klasse
 * PracticeFX ist 
 * @author Jasmina Marinkovic
 *
 */
public class Practice {
	private int id;
	private String titel;
	private String bildPath;
/**
 * Der Konstruktor der die gew�nschten Parameter  bereit stellt
 * @param id Identifikaktionsnummer der Practice 
 * @param titel stellt den Titel des Gew�nschten Items dar
 * @param bildPath String um den Bildpfad festzu legen 
 */
	public Practice(int id, String titel, String bildPath) {
		super();
		this.id = id;
		this.titel = titel;
		this.bildPath = bildPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/**
 * der Parameterlose Konstruktor der mir hilft neue Objekte zu erzeugen
 */
	public Practice() {
		super();
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
		return "Practice [id=" + id + ", titel=" + titel + ", bildPath=" + bildPath + "]";
	}

}