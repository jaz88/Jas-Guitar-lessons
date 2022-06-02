package klassen;
/**
 * In dieser Klasse lege ich den Grundstein für die 
 * GUI Anwendungen in diesem Falle die Chords , ich lege die Attribute fest 
 * ich gestalte einen Konstruktor der diese Attibute als Parameter anführt 
 * ich erzeuge einen leeren Konstruktor der mir die Möglichkeit gibt neue Chords rein zu stellen
 * mit der Hilfe der Getter und Setter und der ToString Methode kompettiere 
 * ich diese Klasse , diese Klasse die der Grundstein für die SimpleProperty Klasse
 * ChordsFX ist   
 *  
 *  
 * @author Jasmina Marinkovic 
 *
 */
public class Chords {
	
	private int id;
	private String titel;
	private int notation;
	/**
	 * 
	 */
	private String bildPath;
/**
 * Der Konstruktor der die gewünschten Parameter  bereit stellt
 * @param id Identifikaktionsnummer der Chords 
 * @param titel stellt den Titel des gewünschten Items dar
 * @param notation stellt die gewünschte Notation dar 
 * @param bildPath String um den Bildpfad festzu legen 
 */
	public Chords(int id, String titel, int notation, String bildPath) {
		super();
		this.id = id;
		this.titel = titel;
		this.notation = notation;
		this.bildPath = bildPath;

	}

	public int getId() {
		return id;
	}
/**
 * der Parameterlose Konstruktor der mir hilft neue Objekte zu erzeugen 
 */
	public Chords() {
		super();
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

	public int getNotation() {
		return notation;
	}

	public void setNotation(int notation) {
		this.notation = notation;
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
		return "Chords [id=" + id + ", titel=" + titel + ", notation=" + notation + ", bildPath=" + bildPath + "]";
	}

}