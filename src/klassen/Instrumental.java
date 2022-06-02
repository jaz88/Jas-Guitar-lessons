package klassen;
/**
 * In dieser Klasse lege ich den Grundstein für die 
 * GUI Anwendungen in diese Falle die Instrumental , ich lege die Attribute fest 
 * ich gestalte einen Konstruktor der diese Attibute als Parameter anführt 
 * ich erzeuge einen leeren Konstruktor der mir die Möglichkeit gibt neue Instrumental rein zu stellen
 * mit der Hilfe der Getter und Setter und der ToString Methode komplettiere 
 * ich diese Klasse , diese Klasse die der Grundstein für die SimpleProperty Klasse
 * InstrumentalFX ist 
 * @author Jasmina Marinkovic 
 *
 */
public class Instrumental {
	private int id;
	private String titel;
	private String interpret;
	private Level level;
	private String bildPath;
/**
 * Der Konstruktor der die gewünschten Parameter  bereit stellt
 * @param id Identifikationsnummer der Instrumentals
 * @param titel stellt den Titel des gewünschten Items dar
 * @param interpret stellt den Künstler der es erschaffen hat dar 
 * @param level stellt den Schwierigkeitsgrad des Stückes dar 
 * @param bildPath String um den BildPfad fest zulegen
 */
	public Instrumental(int id, String titel, String interpret, Level level, String bildPath) {
		super();
		this.id = id;
		this.titel = titel;
		this.interpret = interpret;
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
	public Instrumental() {
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

	public Level getLevel() {
		return this.level;
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
		return "Instrumental [id=" + id + ", titel=" + titel + ", interpret=" + interpret + ", level=" + level
				+ ", bildPath=" + bildPath + "]";
	}

}