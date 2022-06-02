package Lehrerin;

import java.nio.file.Paths;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import klassen.Chords;
import klassen.ChordsFX;
import klassen.Instrumental;
import klassen.InstrumentalFX;
import klassen.Level;
import klassen.LevelFX;
import klassen.Notenblatt;
import klassen.NotenblattFX;
import klassen.Practice;
import klassen.PracticeFX;
import klassen.Songs;
import klassen.SongsFX;
import javafx.application.Platform;
/**
 * In dieser Klasse in der sich auch die Main Methode befindet starte ich die stage,
 *  setze den Grundstein und die erste GUI Maske für die Lehhrerschaft aus der Sie dann
 *  weiter operieren kann, zuerst gestalte ich ObservableLists aus den SimpleProperty 
 *  Klassen ( NotenblattFX, ChordsFX,ImstrumentalFX, LevelFX, PracticeFX und SongsFX)
 *     
 * @author Jasmina Marinkovic 
 *
 */
public class Lehrerin_start extends Application {

 	private ObservableList<NotenblattFX> olnotenblatt = FXCollections.observableArrayList();
	private NotenblattFX notenblattFX;

	private ObservableList<ChordsFX> olChords = FXCollections.observableArrayList();
	private ChordsFX chordsFX;
	private ObservableList<InstrumentalFX> olInstrumental = FXCollections.observableArrayList();
	private InstrumentalFX instrumentalFX;
	private ObservableList<LevelFX> olLevel = FXCollections.observableArrayList();
	private LevelFX levelFX;
	private ObservableList<PracticeFX> olPractice = FXCollections.observableArrayList();
	private PracticeFX practiceFX;
	private ObservableList<SongsFX> olSongs = FXCollections.observableArrayList();
	private SongsFX songsFX;
/**
 *  im try Block rufe ich die jeweiligen CreateTableMethoden auf um
 *  Tabellen in der Datenbank zu erzeugen, im Catchblock fange ich gegebenenfalls
 *  eventuelle SQL Exceptions auf , dann setzen wir den Container und 
 *  das Layout des Containers fest , ich erzeuge Buttons die ich jeweils on Action 
 *  setze die dann zu einem modalen Dialog führen , ich erzeuge jeweils ein leeres Objekt 
 *  ich setze das Layout der Flowpane fest und erzeuge einen Button
 *  der das Fenster schliesst  
 
 */
	@Override
	public void start(Stage primaryStage) {
	 
		try {
			klassen.Datenbank.createNotenblattTable();
			klassen.Datenbank.createChordsTable();
			klassen.Datenbank.createLevelTable();
			klassen.Datenbank.createInstrumentalTable();
			klassen.Datenbank.createPracticeTable();
			klassen.Datenbank.createSongTable();

		} catch (SQLException e1) {
			new Alert(AlertType.ERROR, e1.toString()).showAndWait();
			e1.printStackTrace();
			return;
		}

		VBox r = new VBox();
		r.setSpacing(20);
		r.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		Label lb = new Label();
		lb.setPrefSize(200, 300);
		lb.setGraphic(new ImageView(Paths.get(
				"C:\\Users\\Chef\\eclipse-workspace222\\JasGuitarLesson_Version1\\LayoutPic\\LehrerinGuiAnfang (2).png")
				.toUri().toString()));
		VBox r2 = new VBox();
		r2.getChildren().add(lb);

		Label l = new Label("Bereich Für Lehrerinnnen ");
		l.setFont(new Font("Constantia", 20));
	
		Button lblsong = new Button("Songs");
		lblsong.setFont(new Font("Constantia", 15));
		lblsong.setPrefWidth(200);
		lblsong.setPadding(new Insets(10));

		Button lblpractice = new Button("Practice");
		lblpractice.setPrefWidth(200);
		lblpractice.setPadding(new Insets(10));
		lblpractice.setFont(new Font("Constantia", 15));

		Button lblnotenblatt = new Button("Notenblatt");
		lblnotenblatt.setPrefWidth(200);
		lblnotenblatt.setPadding(new Insets(10));
		lblnotenblatt.setFont(new Font("Constantia", 15));

		Button lblChord = new Button("Chords");
		lblChord.setPrefWidth(200);
		lblChord.setPadding(new Insets(10));
		lblChord.setFont(new Font("Constantia", 15));
		
		Button lblInstrum = new Button("Instrumental");
		lblInstrum.setPrefWidth(200);
		lblInstrum.setPadding(new Insets(10));
		lblInstrum.setFont(new Font("Constantia", 15));
		lblInstrum.setOnAction(e -> {

			instrumentalFX = new InstrumentalFX(new Instrumental());
			InstrumentalDetailDialog ndd = new InstrumentalDetailDialog(instrumentalFX);
			ndd.showAndWait();
		});

		Button lblLevel = new Button("Level");
		lblLevel.setPrefWidth(200);
		lblLevel.setPadding(new Insets(10));
		lblLevel.setFont(new Font("Constantia", 15));
		lblLevel.setOnAction(e -> {
			
			levelFX = new LevelFX(new Level());
			LevelDetailDialog ndd = new LevelDetailDialog(levelFX);
			ndd.showAndWait();
		});

		
		lblnotenblatt.setOnAction(e -> {
			

			notenblattFX = new NotenblattFX(new Notenblatt());
			NotenblattDetailDialog1 ndd = new NotenblattDetailDialog1(notenblattFX);
			ndd.showAndWait();
		});

		lblpractice.setOnAction(e -> {
			practiceFX = new PracticeFX(new Practice());
			PracticeDetailDialog1 ndd = new PracticeDetailDialog1(practiceFX);
			ndd.showAndWait();
		});

		lblChord.setOnAction(e -> {
			chordsFX = new ChordsFX(new Chords());
			ChordsDetailDialog1 ndd = new ChordsDetailDialog1(chordsFX);
			ndd.showAndWait();
		});

		lblsong.setOnAction(e -> {

			songsFX = new SongsFX(new Songs());
			SongsDetailDialog ndd = new SongsDetailDialog(songsFX);

			ndd.showAndWait();
		});

		Button schliessen = new Button("Beenden");
		schliessen.setAlignment(Pos.BOTTOM_RIGHT);
		schliessen.setFont(new Font("Constantia", 10));
		schliessen.setOnAction(e -> {
			Platform.exit();

		});
		r.setAlignment(Pos.CENTER);
		r.getChildren().addAll(l, lblsong, lblnotenblatt, lblpractice, lblChord, lblInstrum, lblLevel, schliessen);
		FlowPane fpane = new FlowPane();
		fpane.setPadding(new Insets(5, 5, 5, 5));
		fpane.getChildren().addAll(r, r2);
		fpane.setStyle("-fx-background-color: steelblue;");

		Scene sc = new Scene(fpane, 700, 450);

		primaryStage.setScene(sc);
		primaryStage.setTitle("Jas Guitar Lessons");

		primaryStage.show();

	}
/**
 * Methode um die Application zu starten 
 * @param args
 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
