package Schueler;

import java.nio.file.Paths;

import java.sql.SQLException;

import Lehrerin.ChordsDetailDialog1;
import Lehrerin.InstrumentalDetailDialog;
import Lehrerin.LevelDetailDialog;
import Lehrerin.NotenblattDetailDialog1;
import Lehrerin.PracticeDetailDialog1;
import Lehrerin.SongsDetailDialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
 *  setze den Grundstein und die erste GUI Maske für die Schuelerinnen aus der Sie dann
 *  weiter operieren kann,ich gestalte  ObservableLists aus den SimpleProperty 
 *  Klassen ( NotenblattFX, ChordsFX,ImstrumentalFX, LevelFX, PracticeFX und SongsFX)
 *    
 * @author Jasmina Marinkovic 
 *
 */
public class Schueler_Start extends Application {
	
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
 * im try Block rufe ich die jeweiligen CreateTableMethoden auf um
 *  Tabellen in der datenbank zu erzeugen, im Catchblock fange ich gegebenenfalls
 *  eventuelle SQL Exceptions auf , dann setzen wir den Container und 
 *  das layout de Containers fest , ich erzeuge Buttons die ich jeweils on Action 
 *  setze die dann zu einem modalen Dialog führen , ich erzeuge jeweils ein leeres Objekt 
 *  ich setze das Layout der Flowpane fest und erzeuge einen Button
 *  der das Fenster schliesst  
 */
	@Override
	public void start(Stage primaryStage) {
		try {
			klassen.Datenbank.createNotenblattTable();
			klassen.Datenbank.createChordsTable();
			klassen.Datenbank.createInstrumentalTable();
			klassen.Datenbank.createLevelTable();
			klassen.Datenbank.createPracticeTable();
			klassen.Datenbank.createSongTable();

		} catch (SQLException e1) {
			new Alert(AlertType.ERROR, e1.toString()).showAndWait();
			return;
		}

		VBox r = new VBox();
		r.setSpacing(20);
		r.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		Label l = new Label("Bereich Für Schülerinnen ");
		l.setFont(new Font("Constantia", 20));
		Button lblsong = new Button("Songs");
		lblsong.setPrefWidth(200);
		lblsong.setPadding(new Insets(10));
		lblsong.setFont(new Font("Constantia", 15));

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
			schuelerInstrumentalDetailDialog ndd = new schuelerInstrumentalDetailDialog(instrumentalFX);
			ndd.showAndWait();
		});

		 Button schliessen= new Button ( "Beenden");
	     schliessen.setAlignment(Pos.BOTTOM_RIGHT);
	     schliessen.setFont(new Font("Constantia", 10));
	     schliessen.setOnAction(e->{
	    	 Platform.exit();
	    	 
	     });

		
		lblnotenblatt.setOnAction(e -> {
			

			notenblattFX = new NotenblattFX(new Notenblatt());
			schuelerNotenblattDetailDialog1 ndd = new schuelerNotenblattDetailDialog1(notenblattFX);
			ndd.showAndWait();
		});

		lblpractice.setOnAction(e -> {
			practiceFX = new PracticeFX(new Practice());
			schuelerPracticeDetailDialog1 ndd = new schuelerPracticeDetailDialog1(practiceFX);
			ndd.showAndWait();
		});

		lblChord.setOnAction(e -> {
			chordsFX = new ChordsFX(new Chords());
			schuelerChordsDetailDialog1 ndd = new schuelerChordsDetailDialog1(chordsFX);
			ndd.showAndWait();
		});

		lblsong.setOnAction(e -> {
			Label lblnachricht = new Label ();
			lblnachricht.setText(" Wenn du auf die Balken ");
			VBox vb= new VBox(); 
			vb.getChildren().add(lblnotenblatt);
 
			songsFX = new SongsFX(new Songs());
			schuelerSongsDetailDialog ndd = new schuelerSongsDetailDialog(songsFX);
			ndd.showAndWait();
		});
		
		r.setAlignment(Pos.CENTER);
		r.getChildren().addAll(l,lblsong,lblnotenblatt,lblpractice,lblChord,lblInstrum,schliessen);//level nicht vergessen
		
		Label lB= new Label();
		lB.setPrefSize(200,150);
		lB.setGraphic(new ImageView(Paths.get("C:\\Users\\Chef\\eclipse-workspace222\\JasGuitarLesson_Version1\\LayoutPic\\Schuelerlayout2schueler.png").toUri().toString()));
 		
		VBox r2= new VBox(lB); 
		
		FlowPane rp= new FlowPane(r,r2); 
		rp.setStyle("-fx-background-color: steelblue;");

		Scene sc = new Scene(rp, 700,400); 

		primaryStage.setScene(sc);
		primaryStage.setTitle("Jas Guitar Lessons");

		primaryStage.show();

		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
