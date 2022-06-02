package klassen;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Der Speicherpfad der Datenbank und die jeweiligen Tabellen und
 * Methoden(Create, Insert,Select, Update und Delete ) die dann in den GUI's
 * Nutzung finden,
 * 
 * @author Jasmina Marinkovic
 *
 */

public class Datenbank {
	private static final String DBLocation = "C:\\Temp\\DatabaseGuitar";
	private static final String ConnString = "jdbc:derby:" + DBLocation + ";create=true";

	private static final String SONG = "Song";
	private static final String SONGID = "SongId";
	private static final String SONGTITEL = "SongTitel";
	private static final String SONGINTERPRET = "SongInterpret";
	private static final String SONGNOTATION = "SongNotation";
	private static final String SONGLEVEL = "SongLevel";
	private static final String SONGBILDPATH = "SongBildpath";

	private static final String INSTRUMENTAL = "Instrumental";
	private static final String INSTRUMENTALID = "InstrumentalId";
	private static final String INSTRUMENTALTITEL = "InstrumentalTitel";
	private static final String INSTRUMENTALINTERPRET = "InstrumentalInterpret";
	private static final String INSTRUMENTALLEVEL = "IstrumentalLevel";
	private static final String INSTRUMENTALBILDPATH = "InstrumentalBildpath";

	private static final String CHORD = "Chord";
	private static final String CHORDID = "ChordsId";
	private static final String CHORDTITEL = "ChordsTitel";
	private static final String CHORDNOTATION = "ChordsNotation";
	private static final String CHORDBILDPATH = "ChordsBildpath";

	private static final String NOTENBLATT = "Notenblatt";
	private static final String NOTENBLATTID = "NotenblattID";
	private static final String NOTENBLATTTITEL = "NotenblattTitel";
	private static final String NOTENBLATTBILDPATH = "NotenblattBildpath";

	private static final String PRACTICE = "Practice";
	private static final String PRACTICEID = "PracticeId";
	private static final String PRACTICETITEL = "PracticeTitel";
	private static final String PRACTICEBILDPATH = "PracticeBildpath";

	private static final String LEVEL = "Level";
	private static final String LEVELID = "LevelId";
	private static final String LEVELBEZEICHNUNG = "LevelBezeichnung";

	/**
	 * Hier wird eine Tabelle in der Datenbank erzeugt , in diesem Falle ist es das
	 * Notenblatt, und diese Methode wirft eine SQL exception wenn
	 * 
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public static void createNotenblattTable() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.createStatement();

			rs = conn.getMetaData().getTables(null, null, NOTENBLATT.toUpperCase(), new String[] { "TABLE" });

			if (rs.next()) {

				return;
			}

			String ctN = "CREATE TABLE " + NOTENBLATT + " (" + NOTENBLATTID + " INTEGER GENERATED ALWAYS AS IDENTITY," + //
					NOTENBLATTTITEL + " VARCHAR(200)," + NOTENBLATTBILDPATH + " VARCHAR(200)," + "PRIMARY KEY("
					+ NOTENBLATTID + "))";

			stmt.executeUpdate(ctN);

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird eine Tabelle in der Datenbank erzeugt , in diesem Falle ist es das
	 * Practice , und diese Methode wirft eine SQL exception wenn
	 * 
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public static void createPracticeTable() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.createStatement();

			rs = conn.getMetaData().getTables(null, null, PRACTICE.toUpperCase(), new String[] { "TABLE" });

			if (rs.next()) {
				return;
			}

			String ctP = "CREATE TABLE " + PRACTICE + " (" + PRACTICEID + " INTEGER GENERATED ALWAYS AS IDENTITY," + //
					PRACTICETITEL + " VARCHAR(200)," + PRACTICEBILDPATH + " VARCHAR(200)," + "PRIMARY KEY(" + PRACTICEID
					+ "))";

			stmt.executeUpdate(ctP);

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird eine Tabelle in der Datenbank erzeugt , in diesem Falle ist es das
	 * Chords , und diese Methode wirft eine SQL exception wenn
	 * 
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public static void createChordsTable() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.createStatement();

			rs = conn.getMetaData().getTables(null, null, CHORD.toUpperCase(), new String[] { "TABLE" });

			if (rs.next()) {
				return;
			}

			String ctC = "CREATE TABLE " + CHORD + " (" + CHORDID + " INTEGER GENERATED ALWAYS AS IDENTITY,"
					+ CHORDTITEL + " VARCHAR(200)," + CHORDNOTATION + " INTEGER," + CHORDBILDPATH + " VARCHAR(200),"
					+ "PRIMARY KEY(" + CHORDID + "))";

			stmt.executeUpdate(ctC);

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird eine Tabelle in der Datenbank erzeugt , in diesem Falle ist es das
	 * Level , und diese Methode wirft eine SQL exception wenn
	 * 
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public static void createLevelTable() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.createStatement();

			rs = conn.getMetaData().getTables(null, null, LEVEL.toUpperCase(), new String[] { "TABLE" });

			if (rs.next()) {
				return;
			}

			String ctL = "CREATE TABLE " + LEVEL + " (" + LEVELID + " INTEGER GENERATED ALWAYS AS IDENTITY,"
					+ LEVELBEZEICHNUNG + " VARCHAR(200)," + "PRIMARY KEY(" + LEVELID + "))";

			stmt.executeUpdate(ctL);

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird eine Tabelle in der Datenbank erzeugt , in diesem Falle ist es das
	 * Song , und diese Methode wirft eine SQL exception wenn
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public static void createSongTable() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.createStatement();

			rs = conn.getMetaData().getTables(null, null, SONG.toUpperCase(), new String[] { "TABLE" });

			if (rs.next()) {
				return;
			}

			String ctS = "CREATE TABLE " + SONG + " (" + SONGID + " INTEGER GENERATED ALWAYS AS IDENTITY," + //
					SONGTITEL + " VARCHAR(200)," + SONGINTERPRET + " VARCHAR(200)," + SONGNOTATION + " INTEGER,"
					+ SONGLEVEL + " INTEGER," + SONGBILDPATH + " VARCHAR(200)," + "PRIMARY KEY(" + SONGID + "),"
					+ "FOREIGN KEY( " + SONGLEVEL + ") REFERENCES " + LEVEL + "(" + LEVELID + " )" + " )";

			stmt.executeUpdate(ctS);

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird eine Tabelle in der Datenbank erzeugt , in diesem Falle ist es das
	 * Instrumental , und diese Methode wirft eine SQL exception wenn
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public static void createInstrumentalTable() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.createStatement();

			rs = conn.getMetaData().getTables(null, null, INSTRUMENTAL.toUpperCase(), new String[] { "TABLE" });

			if (rs.next()) {
				return;
			}

			String ctI = "CREATE TABLE " + INSTRUMENTAL + " (" + INSTRUMENTALID
					+ " INTEGER GENERATED ALWAYS AS IDENTITY," + INSTRUMENTALTITEL + " VARCHAR(200),"
					+ INSTRUMENTALINTERPRET + " VARCHAR(200)," + INSTRUMENTALLEVEL + " INTEGER," + INSTRUMENTALBILDPATH
					+ " VARCHAR(200)," + "PRIMARY KEY(" + INSTRUMENTALID + ")," + "FOREIGN KEY( " + INSTRUMENTALLEVEL
					+ ") REFERENCES " + LEVEL + "(" + LEVELID + " )" + " )";

			stmt.executeUpdate(ctI);

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird die Tabelle mit Notenblättern befüllt und diese Methode wirft eine
	 * SQL exception wenn
	 * 
	 * @param notenblatt um die Platzhalter zu befüllen
	 * @throws SQLException if a database access error occurs
	 */
	public static void insertNotenblatt(Notenblatt notenblatt) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Statement st = null;

		String insertN = "INSERT INTO " + NOTENBLATT + " (" + NOTENBLATTTITEL + "," + NOTENBLATTBILDPATH
				+ ") VALUES(?, ?)";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(insertN);

			stmt.setString(1, notenblatt.getTitel());
			stmt.setString(2, notenblatt.getBildPath());
			stmt.executeUpdate();

			String identity = "SELECT IDENTITY_VAL_LOCAL() FROM " + NOTENBLATT;

			st = conn.createStatement();

			rs = st.executeQuery(identity);
			if (rs.next())
				notenblatt.setId(rs.getInt("1"));
			rs.close();

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird die Tabelle mit Practice befüllt und diese Methode wirft eine SQL
	 * exception wenn
	 * 
	 * @param practice um die Platzhalter zu befüllen
	 * @throws SQLException if a database access error occurs
	 */
	public static void insertPractice(Practice practice) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Statement st = null;

		String insertP = "INSERT INTO " + PRACTICE + " (" + PRACTICETITEL + "," + PRACTICEBILDPATH + ") VALUES(?, ?)";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(insertP);

			stmt.setString(1, practice.getTitel());
			stmt.setString(2, practice.getBildPath());
			stmt.executeUpdate();

			String identity = "SELECT IDENTITY_VAL_LOCAL() FROM " + PRACTICE;

			st = conn.createStatement();

			rs = st.executeQuery(identity);
			if (rs.next())
				practice.setId(rs.getInt("1"));
			rs.close();

		} catch (SQLException e) {
			throw e;
//			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird die Tabelle mit Practice befüllt und diese Methode wirft eine SQL
	 * exception wenn
	 * 
	 * @param chords um die Platzhalter zu befüllen
	 * @throws SQLException if a database access error occurs
	 */
	public static void insertChords(Chords chords) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Statement st = null;

		String insertC = "INSERT INTO " + CHORD + " (" + CHORDTITEL + "," + CHORDNOTATION + "," + CHORDBILDPATH
				+ ") VALUES(?, ?, ?)";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(insertC);

			stmt.setString(1, chords.getTitel());
			stmt.setInt(2, chords.getNotation());
			stmt.setString(3, chords.getBildPath());
			stmt.executeUpdate();

			String identity = "SELECT IDENTITY_VAL_LOCAL() FROM " + CHORD;
			st = conn.createStatement();

			rs = st.executeQuery(identity);
			if (rs.next())
				chords.setId(rs.getInt("1"));
			rs.close();

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird die Tabelle mit Instrumental befüllt und diese Methode wirft eine
	 * SQL exception wenn
	 * 
	 * @param instrumental um die Platzhalter zu befüllen
	 * @throws SQLException if a database access error occurs
	 */
	public static void insertInstrumental(Instrumental instrumental) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Statement st = null;

		String insertI = "INSERT INTO " + INSTRUMENTAL + " (" + INSTRUMENTALTITEL + "," + INSTRUMENTALINTERPRET + ","
				+ INSTRUMENTALLEVEL + "," + INSTRUMENTALBILDPATH + ") VALUES(?, ?, ?, ?)";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(insertI);

			stmt.setString(1, instrumental.getTitel());
			stmt.setString(2, instrumental.getInterpret());
			stmt.setInt(3, instrumental.getLevel().getId());
			stmt.setString(4, instrumental.getBildPath());
			stmt.executeUpdate();

			String identity = "SELECT IDENTITY_VAL_LOCAL() FROM " + INSTRUMENTAL;
			st = conn.createStatement();

			rs = st.executeQuery(identity);
			if (rs.next())
				instrumental.setId(rs.getInt("1"));
			rs.close();

		} catch (SQLException e) {
			throw e;
//			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird die Tabelle mit Songs befüllt und diese Methode wirft eine SQL
	 * exception wenn
	 * 
	 * @param songs um die Platzhalter zu befüllen 
	 * @throws SQLException if a database access error occurs
	 */
	public static void insertSong(Songs songs) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Statement st = null;

		String insertS = "INSERT INTO " + SONG + " (" + SONGTITEL + "," + SONGINTERPRET + "," + SONGNOTATION + ","
				+ SONGLEVEL + "," + SONGBILDPATH + ") VALUES(?, ?, ?, ?, ?)";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(insertS);

			stmt.setString(1, songs.getTitel());
			stmt.setString(2, songs.getInterpret());
			stmt.setInt(3, songs.getNotation());
			stmt.setInt(4, songs.getLevel().getId());
			stmt.setString(5, songs.getBildPath());
			stmt.executeUpdate();

			String identity = "SELECT IDENTITY_VAL_LOCAL() FROM " + SONG;
			st = conn.createStatement();

			rs = st.executeQuery(identity);
			if (rs.next())
				songs.setId(rs.getInt("1"));
			rs.close();

		} catch (SQLException e) {
			throw e;
//			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Hier wird die Tabelle mit Level befüllt und diese Methode wirft eine SQL
	 * exception wenn
	 * 
	 * @param level um die Platzhalter zu befüllen
	 * @throws SQLException if a database access error occurs
	 */
	public static void insertLevel(Level level) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Statement st = null;

		String insertL = "INSERT INTO " + LEVEL + " (" + LEVELBEZEICHNUNG + ") VALUES(?)";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(insertL);

			stmt.setString(1, level.getBezeichnung());
			stmt.executeUpdate();

			String identity = "SELECT IDENTITY_VAL_LOCAL() FROM " + LEVEL;
			st = conn.createStatement();

			rs = st.executeQuery(identity);
			if (rs.next())
				level.setId(rs.getInt("1"));
			rs.close();

		} catch (SQLException e) {
			throw e;
//			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Diese Methode besteht zu dem Zweck eine Arraylist von der Tabelle Notenblatt
	 * erstellen zu können um diese dann zb in einer Gui als TableView anzeigen zu
	 * können
	 * 
	 * @param notenblatt dient zur Indentifikation des Notenblatt Eintrages
	 * @return ArrayList der Elemente des Notenblatts zur jeweiligen Identifilation 
	 * @throws SQLException if a database access error occurs
	 */
	public static ArrayList<Notenblatt> leseNotenblatt(String notenblatt) throws SQLException {// für den Schüler und
																								// Lehrer
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Notenblatt> alb = new ArrayList<>();

		String selectN = "SELECT * FROM " + NOTENBLATT;

		if (notenblatt != null)
			selectN += " WHERE " + NOTENBLATTID + "=?";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(selectN);

			if (notenblatt != null)
				// "SELECT * FROM " + NOTENBLATT + WHERE " + NOTENBLATTID + "=?" + notenblatt
				stmt.setString(1, notenblatt);
			rs = stmt.executeQuery();

			while (rs.next()) {

				alb.add(new Notenblatt(rs.getInt(NOTENBLATTID), rs.getString(NOTENBLATTTITEL),
						rs.getString(NOTENBLATTBILDPATH)));
			}
			rs.close();

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}

		return alb;
	}

	/**
	 * Diese Methode besteht zu dem Zweck eine Arraylist von der Tabelle Practice
	 * erstellen zu können um diese dann zb in einer Gui als TableView anzeigen zu
	 * können
	 * 
	 * @param practice dient zur Indentifikation des Practice Eintrages
	 * @return an ArrayList der Elemente der Practice zur jeweiligen Identifilation 
	 * @throws SQLException if a database access error occurs
	 */
	public static ArrayList<Practice> lesePractice(String practice) throws SQLException {// für den Schüler und Lehrer
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Practice> alP = new ArrayList<>();

		String selectN = "SELECT * FROM " + PRACTICE;

		if (practice != null)
			selectN += " WHERE " + PRACTICEID + "=?";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(selectN);

			if (practice != null)
				stmt.setString(1, practice);
			rs = stmt.executeQuery();

			while (rs.next()) {

				alP.add(new Practice(rs.getInt(PRACTICEID), rs.getString(PRACTICETITEL),
						rs.getString(PRACTICEBILDPATH)));
			}
			rs.close();
		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw e;
			}
		}

		return alP;
	}

	/**
	 * Diese Methode besteht zu dem Zweck eine Arraylist von der Tabelle Chords
	 * erstellen zu können um diese dann zb in einer Gui als TableView anzeigen zu
	 * können
	 * 
	 * @param chords dient zur Indentifikation des Chord Eintrages
	 * @return an ArrayList der Elemente des Chords zur jeweiligen Identifilation 
	 * @throws SQLException if a database access error occurs
	 */
	public static ArrayList<Chords> leseChords(String chords) throws SQLException {// für den Schüler und Lehrer zum
																					// lesen der Datenbank
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Chords> alC = new ArrayList<>();
		String selectN = "SELECT * FROM " + CHORD;

		if (chords != null)
			selectN += " WHERE " + CHORDID + "=?";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(selectN);

			if (chords != null)
				stmt.setString(1, chords);
			rs = stmt.executeQuery();

			while (rs.next()) {

				alC.add(new Chords(rs.getInt(CHORDID), rs.getString(CHORDTITEL), rs.getInt(CHORDNOTATION),
						rs.getString(CHORDBILDPATH)));
			}
			rs.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return alC;
	}

	/**
	 * Diese Methode besteht zu dem Zweck eine Arraylist von der Tabelle
	 * Instrumental erstellen zu können um diese dann zb in einer Gui als TableView
	 * anzeigen zu können
	 * 
	 * @param instrumental dient zur Indentifikation des Instrumental Eintrages
	 * @return an ArrayList der Elemente des Instrumentals zur jeweiligen Identifilation 
	 * @throws SQLException if a database access error occurs
	 */
	public static ArrayList<Instrumental> leseInstrumental(String instrumental) throws SQLException {// für den Schüler
																										// und Lehrer
																										// zum lesen der
																										// Datenbank
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Instrumental> alI = new ArrayList<>();
		String selectI = "SELECT * FROM " + INSTRUMENTAL + " INNER JOIN " + LEVEL + " ON " + LEVEL + "." + LEVELID + "="
				+ INSTRUMENTAL + "." + INSTRUMENTALLEVEL;

		if (instrumental != null)
			selectI += " WHERE " + INSTRUMENTALID + "=?";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(selectI);

			if (instrumental != null)
				stmt.setString(1, instrumental);
			rs = stmt.executeQuery();

			while (rs.next()) {

				alI.add(new Instrumental(rs.getInt(INSTRUMENTALID), rs.getString(INSTRUMENTALTITEL),
						rs.getString(INSTRUMENTALINTERPRET),
						new Level(rs.getInt(LEVELID), rs.getString(LEVELBEZEICHNUNG)), // new Level( rs_vonlevel.get.. )
						rs.getString(INSTRUMENTALBILDPATH)));
			}
			rs.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return alI;
	}

	/**
	 * Diese Methode besteht zu dem Zweck eine Arraylist von der Tabelle Songs
	 * erstellen zu können um diese dann zb in einer Gui als TableView anzeigen zu
	 * können
	 * 
	 * @param songs dient zur Indentifikation des Songs Eintrages
	 * @return ArrayList der Elemente des Songs zur jeweiligen Identifilation 
	 * @throws SQLException if a database access error occurs
	 */
	public static ArrayList<Songs> leseSongs(String songs) throws SQLException {// für den Schüler und Lehrer zum lesen
																				// der Datenbank
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Songs> alS = new ArrayList<>();
		String selectS = "SELECT * FROM " + SONG + " INNER JOIN " + LEVEL + " ON " + LEVEL + "." + LEVELID + "=" + SONG
				+ "." + SONGLEVEL;

		if (songs != null)
			selectS += " WHERE " + SONGID + "=?";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(selectS);

			if (songs != null)
				stmt.setString(1, songs);
			rs = stmt.executeQuery();

			while (rs.next()) {

				alS.add(new Songs(rs.getInt(SONGID), rs.getString(SONGTITEL), rs.getString(SONGINTERPRET),
						rs.getInt(SONGNOTATION), new Level(rs.getInt(LEVELID), rs.getString(LEVELBEZEICHNUNG)),
						rs.getString(SONGBILDPATH)));
			}
			rs.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return alS;
	}

	/**
	 * Diese Methode besteht zu dem Zweck eine Arraylist von der Tabelle Level
	 * erstellen zu können um diese dann zb in einer Gui als TableView anzeigen zu
	 * können
	 * 
	 * @param level dient zur Indentifikation des Level Eintrages
	 * @return ArrayList der Elemente des Level zur jeweiligen Identifilation
	 * @throws SQLException if a database access error occurs
	 */
	public static ArrayList<Level> leseLevel(String level) throws SQLException {// für den Schüler und
		// Lehrer
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Level> albL = new ArrayList<>();
		String selectN = "SELECT * FROM " + LEVEL;

		if (level != null)
			selectN += " WHERE " + LEVELID + "=?";
		try {

			conn = DriverManager.getConnection(ConnString);

			stmt = conn.prepareStatement(selectN);

			if (level != null)
				stmt.setString(1, level);
			rs = stmt.executeQuery();

			while (rs.next()) {

				albL.add(new Level(rs.getInt(LEVELID), rs.getString(LEVELBEZEICHNUNG)));
			}
			rs.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return albL;
	}

	/**
	 * Diese Methode gibt die Möglichkeit Änderungen an der Tabelle Notenblatt in
	 * der Datenbank vornehmen zu können
	 * 
	 * @param notenblatt das zu bearbeitende  Notenblatt
	 * @throws SQLException if a database access error occurs
	 */
	public static void notenblattBearbeiten(Notenblatt notenblatt) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(ConnString);

			String update = "UPDATE " + NOTENBLATT + " SET " + NOTENBLATTTITEL + "=?," + NOTENBLATTBILDPATH
					+ "=? WHERE " + NOTENBLATTID + "=" + notenblatt.getId();
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, notenblatt.getTitel());
			pstmt.setString(2, notenblatt.getBildPath());

			// UPDATE Befehl ausführen
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode gibt die Möglichkeit Änderungen an der Tabelle Practice in der
	 * Datenbank vornehmen zu können
	 * 
	 * @param practice die zu bearbeitende  Practice
	 * @throws SQLException if a database access error occurs
	 */
	public static void practiceBearbeiten(Practice practice) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(ConnString);

			String updateP = "UPDATE " + PRACTICE + " SET " + PRACTICETITEL + "=?," + PRACTICEBILDPATH + "=? WHERE "
					+ PRACTICEID + "=" + practice.getId();
			pstmt = conn.prepareStatement(updateP);
			pstmt.setString(1, practice.getTitel());
			pstmt.setString(2, practice.getBildPath());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode gibt die Möglichkeit Änderungen an der Tabelle Chords in der
	 * Datenbank vornehmen zu können
	 * 
	 * @param chords der zu bearbeitende  Chord
	 * @throws SQLException if a database access error occurs
	 */
	public static void chordsBearbeiten(Chords chords) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(ConnString);
			String updateC = "UPDATE " + CHORD + " SET " + CHORDTITEL + "=?," + CHORDNOTATION + "=?," + CHORDBILDPATH
					+ "=? WHERE " + CHORDID + "=" + chords.getId();

			pstmt = conn.prepareStatement(updateC);
			pstmt.setString(1, chords.getTitel());
			pstmt.setInt(2, chords.getNotation());
			pstmt.setString(3, chords.getBildPath());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode gibt die Möglichkeit Änderungen an der Tabelle Instrumental in
	 * der Datenbank vornehmen zu können
	 * 
	 * @param instrumental das zu bearbeitende  Instrumental
	 * @throws SQLException if a database access error occurs
	 */
	public static void InstrumentalBearbeiten(Instrumental instrumental) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(ConnString);

			String updateI = "UPDATE " + INSTRUMENTAL + " SET " + INSTRUMENTALTITEL + "=?," + INSTRUMENTALINTERPRET
					+ "=?," + INSTRUMENTALLEVEL + "=?," + INSTRUMENTALBILDPATH + "=? WHERE " + INSTRUMENTALID + "="
					+ instrumental.getId();

			pstmt = conn.prepareStatement(updateI);
			pstmt.setString(1, instrumental.getTitel());
			pstmt.setString(2, instrumental.getInterpret());
			pstmt.setInt(3, instrumental.getLevel().getId());
			pstmt.setString(4, instrumental.getBildPath());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode gibt die Möglichkeit Änderungen an der Tabelle Songs in der
	 * Datenbank vornehmen zu können
	 * 
	 * @param songs der zu bearbeitende Song
	 * @throws SQLException if a database access error occurs
	 */
	public static void SongsBearbeiten(Songs songs) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(ConnString);

			String updateS = "UPDATE " + SONG + " SET " + SONGTITEL + "=?," + SONGINTERPRET + "=?," + SONGNOTATION
					+ "=?," + SONGLEVEL + "=?," + SONGBILDPATH + "=? WHERE " + SONGID + "=" + songs.getId();

			pstmt = conn.prepareStatement(updateS);
			pstmt.setString(1, songs.getTitel());
			pstmt.setString(2, songs.getInterpret());
			pstmt.setInt(3, songs.getNotation());
			pstmt.setInt(4, songs.getLevel().getId());
			pstmt.setString(5, songs.getBildPath());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode gibt die Möglichkeit Änderungen an der Tabelle Level in der
	 * Datenbank vornehmen zu können
	 * 
	 * @param level das zu bearbeitende  Level
	 * @throws SQLException if a database access error occurs
	 */
	public static void levelBearbeiten(Level level) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(ConnString);

			String updateL = "UPDATE " + LEVEL + " SET " + LEVELBEZEICHNUNG + "=? WHERE " + LEVELID + "="
					+ level.getId();
			pstmt = conn.prepareStatement(updateL);
			pstmt.setString(1, level.getBezeichnung());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode ist um ein Item in der Notenblatt Tabelle entfernen zu können
	 * 
	 * @param notenblattId die Id des Notenblatts das entfernt werden soll 
	 * @throws SQLException if a database access error occurs
	 */
	public static void notenblattEntfernen(int notenblattId) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		String s1 = "DELETE FROM " + NOTENBLATT + " WHERE " + NOTENBLATTID + " = " + notenblattId;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			stmt.executeUpdate(s1);

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode ist um ein Item in der Practice Tabelle entfernen zu können
	 * 
	 * @param practiceId die Id der Practice die entfernt werden soll 
	 * @throws SQLException if a database access error occurs
	 */
	public static void practiceEntfernen(int practiceId) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		String s2 = "DELETE FROM " + PRACTICE + " WHERE " + PRACTICEID + " = " + practiceId;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			stmt.executeUpdate(s2);

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode ist um ein Item in der Chords Tabelle entfernen zu können
	 * 
	 * @param chordsId die Id des Chords die entfernt werden soll 
	 * @throws SQLException if a database access error occurs
	 */
	public static void ChordsEntfernen(int chordsId) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		String s3 = "DELETE FROM " + CHORD + " WHERE " + CHORDID + " = " + chordsId;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			stmt.executeUpdate(s3);

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode ist um ein Item in der Instrumetal Tabelle entfernen zu können
	 * 
	 * @param instrumentalId die Id des Instrumental das entfernt werden soll 
	 * @throws SQLException if a database access error occurs
	 */
	public static void InstrumentalEntfernen(int instrumentalId) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		String s4 = "DELETE FROM " + INSTRUMENTAL + " WHERE " + INSTRUMENTALID + " = " + instrumentalId;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			stmt.executeUpdate(s4);

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode ist um ein Item in der Songs Tabelle entfernen zu können
	 * 
	 * @param songsId die Id des Songs das entfernt werden soll 
	 * @throws SQLException if a database access error occurs
	 */
	public static void SongsEntfernen(int songsId) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		String s5 = "DELETE FROM " + SONG + " WHERE " + SONGID + " = " + songsId;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			stmt.executeUpdate(s5);

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}

	/**
	 * Diese Methode ist um ein Item in der Level Tabelle entfernen zu können
	 * 
	 * @param levelId die Id des Levels das entfernt werden soll 
	 * @throws SQLException if a database access error occurs
	 */

	public static void LevelEntfernen(int levelId) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		String s6 = "DELETE FROM " + LEVEL + " WHERE " + LEVELID + " = " + levelId;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			stmt.executeUpdate(s6);

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return;
	}
}
