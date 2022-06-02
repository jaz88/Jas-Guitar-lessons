package Lehrerin;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import klassen.Datenbank;
import klassen.Level;
import klassen.Notation;
import klassen.NotenblattFX;
import klassen.SongsFX;
/**
 *  
 * In dieser Klasse erzeuge ich erst eine ObservableList von SongsFX und der Klasse Level,
 * dann erschaffe ich eine Gridpane mit der man ,sowohl ein Item neu erschaffen kann 
 * als auch Bearbeiten kann 
 * @author Jasmina Marinkovic 
 *
 */
public class SongsDetailDialog2 extends Dialog<ButtonType> {
	private ObservableList<SongsFX> olSongsupdate = FXCollections.observableArrayList();
	private ObservableList<Level> olLevelneu = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * Der Konstruktor
	 * @param songFX ist der Song 
	 */
	public SongsDetailDialog2(SongsFX songFX) {

		GridPane gp = new GridPane();
		Label lbTitel = new Label("Titel");
		Label lbPfad = new Label("Bildpfad");
		Label lbInterpret = new Label("Interpret");
		Label lbNotation = new Label("Notation");
		Label lbllevel = new Label("Level");
		Label lb = new Label();
		lb.setPrefSize(200, 150);
		// hier setze ich unterschiedliche Layouts(Bilder ) 
				// für die neu erschaffen als auch bearbeiten Option ein 
		if( songFX.getId()>0) {
			lb.setGraphic(new ImageView(Paths.get(
					"C:\\Users\\Chef\\eclipse-workspace222\\JasGuitarLesson_Version1\\LayoutPic\\pandabearbeiternWerkLayout.png")
					.toUri().toString()));
			}else {
				lb.setGraphic(new ImageView(Paths.get(
						"C:\\Users\\Chef\\eclipse-workspace222\\JasGuitarLesson_Version1\\LayoutPic\\pandaneisWerklayout (2).png")
						.toUri().toString()));

			}

		TextField txtTitel = new TextField();
		TextField txtPfad = new TextField();
		TextField txtInterpret = new TextField();
		// hier stelle  ich sowohl bei Level als auch bei Notation 
		// eine Auswahlmöglichkeit ,dasgestellt durch ComboBoxen 
		ComboBox<String> txtNotation = new ComboBox<>(FXCollections.observableArrayList(Arrays.stream(Notation.values()).map(Notation::name).collect(Collectors.toList())));
		txtNotation.getSelectionModel().select(songFX.getNotation());
		try {

			olLevelneu.addAll(klassen.Datenbank.leseLevel(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ComboBox<Level> txtlevel = new ComboBox<>(olLevelneu);
		for(int i = 0; i < olLevelneu.size(); i++)
			if(olLevelneu.get(i).getId() == songFX.getLevel()) {
				txtlevel.getSelectionModel().select(i);
				break;
			}
		Button btnSuchen = new Button("suchen");

		txtPfad.setEditable(false);

		gp.setStyle("-fx-background-color: steelblue;");
		gp.setPadding(new Insets(10));
		gp.setHgap(15);
		gp.setVgap(15);
		gp.add(lbTitel, 0, 0);
		gp.add(lbPfad, 0, 1);
		gp.add(txtTitel, 1, 0);
		gp.add(txtPfad, 1, 1);
		gp.add(btnSuchen, 2, 1);
		gp.add(lbInterpret, 0, 2);
		gp.add(lbNotation, 0, 3);
		gp.add(lbllevel, 0, 4);
		gp.add(txtInterpret, 1, 2);
		gp.add(txtNotation, 1, 3);
		gp.add(lb, 1, 5);

		gp.add(txtlevel, 1, 4);

		txtTitel.setText(songFX.getTitel());
		txtPfad.setText(songFX.getBildPath());
		txtInterpret.setText(songFX.getInterpret());
		
		HBox hb1 = new HBox();

		hb1.getChildren().add(gp);
		 // hier sucht man nach einem BildPfad
		//wenn der Button gedrückt wird dann wird handle ausgeführt 
		btnSuchen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			
			public void handle(ActionEvent arg0) {
 	 			FileChooser fileChooser = new FileChooser();
				fileChooser.setInitialDirectory(
						new File("C:\\Users\\Chef\\eclipse-workspace222\\JasGuitarLesson_Version1\\resourses"));
				File f = fileChooser.showOpenDialog(getOwner());
				txtPfad.setText(f.getAbsolutePath());

			}
		});

		this.getDialogPane().setContent(gp);

		// --------------------------------------------------------------------
		ButtonType speichern1 = new ButtonType("Speichern", ButtonData.OK_DONE);
		ButtonType beenden1 = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(speichern1, beenden1);
		// setzt den Result  Converter 
		this.setResultConverter(new Callback<ButtonType, ButtonType>() {

			@Override
		
			public ButtonType call(ButtonType arg0) {
				if (arg0 == speichern1) {
					try {

						songFX.getModellSongs().setBildPath(txtPfad.getText());
						songFX.getModellSongs().setTitel(txtTitel.getText());
						songFX.getModellSongs().setInterpret(txtInterpret.getText());
						songFX.getModellSongs().setNotation(txtNotation.getSelectionModel().getSelectedIndex());
						songFX.getModellSongs().setLevel(txtlevel.getSelectionModel().getSelectedItem());
						if( songFX.getId()>0) {
							Datenbank.SongsBearbeiten(songFX.getModellSongs());}
							else {Datenbank.insertSong(songFX.getModellSongs());}
						Datenbank.SongsBearbeiten(songFX.getModellSongs());
					} catch (SQLException e) {
						new Alert(AlertType.ERROR, e.toString()).showAndWait();
						e.printStackTrace();
						return null;
					}
				}
				return arg0;
			}
		});

	}

}
