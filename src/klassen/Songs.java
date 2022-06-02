package klassen;
/**
 * In dieser Klasse lege ich den Grundstein für die 
 * GUI Anwendungen in diese Falle die Songs , ich lege die Attribute fest 
 * ich gestalte einen Konstruktor der diese Attibute als Parameter anführt 
 * ich erzeuge einen leeren Konstruktor der mir die Möglichkeit gibt neue Songs rein zu stellen
 * mit der Hilfe der Getter und Setter und der ToString Methode komplettiere 
 * ich diese Klasse , diese Klasse die der Grundstein für die SimpleProperty Klasse
 * SongsFX ist 
 * @author Jasmina Marinkovic 
 *
 */
public class Songs {
	private int id;
	private String titel;
	private String interpret;
	private int notation;
	private Level level;
	private String bildPath;
/**
 * Der Konstruktor der die gewünschten Parameter  bereit stellt
 * @param id Identifikaktionsnummer der Songs 
 * @param titel titel stellt den Titel des Gewünschten Items dar
 * @param interpret stellt den Erschaffer des Werkes dar 
 * @param notation stellt die gewünschte Notation dar 
 * @param level steht für den Schwierigkeitsgrad dar 
 * @param bildPath String um den Bildpfad festzu legen 
 */
	public Songs(int id, String titel, String interpret, int notation, Level level, String bildPath) {
		super();
		this.id = id;
		this.titel = titel;
		this.interpret = interpret;
		this.notation = notation;
		this.level = level;
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
	public Songs() {
		super();
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getInterpret() {
		return interpret;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

	public int getNotation() {
		return notation;
	}

	public void setNotation(int notation) {
		this.notation = notation;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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
		return "Songs [id=" + id + ", titel=" + titel + ", interpret=" + interpret + ", notation=" + notation
				+ ", level=" + level + ", bildPath=" + bildPath + "]";
	}

}