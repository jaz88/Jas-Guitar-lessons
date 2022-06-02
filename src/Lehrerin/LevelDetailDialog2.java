package Lehrerin;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import klassen.Datenbank;
import klassen.LevelFX;
import klassen.NotenblattFX;
/**
 * In dieser Klasse erzeuge ich erst eine ObservableList von LevelFX ,
 * dann erschaffe ich eine Gridpane mit der man ,sowohl ein Item neu erschaffen kann 
 * als auch Bearbeiten kann 
 * @author Jasmina Marinkovic 
 *
 */
public class LevelDetailDialog2 extends Dialog<ButtonType> {
	private ObservableList<LevelFX> olLevelupdate = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * Der Konstruktor
	 * @param levelFX  ist ein Level
	 */
	public LevelDetailDialog2(LevelFX levelFX) {

		GridPane gp = new GridPane();
		Label lbBezeichnung = new Label("Bezeichnung");
		Label lb = new Label();
		lb.setPrefSize(200, 150);
		// hier setze ich unterschiedliche Layouts(Bilder ) 
		// für die neu erschaffen als auch bearbeiten Option ein 

		if( levelFX.getId()>0) {
			lb.setGraphic(new ImageView(Paths.get(
					"C:\\Users\\Chef\\eclipse-workspace222\\JasGuitarLesson_Version1\\LayoutPic\\pandabearbeiternWerkLayout.png")
					.toUri().toString()));
			}else {
				lb.setGraphic(new ImageView(Paths.get(
						"C:\\Users\\Chef\\eclipse-workspace222\\JasGuitarLesson_Version1\\LayoutPic\\pandaneisWerklayout (2).png")
						.toUri().toString()));

			}
		TextField txtBezeichnung = new TextField();

		txtBezeichnung.setText(levelFX.getBezeichnung());
		gp.setStyle("-fx-background-color: steelblue;");
		gp.setPadding(new Insets(10));
		gp.setHgap(15);
		gp.setVgap(15);
		gp.add(lbBezeichnung, 0, 0);

		gp.add(txtBezeichnung, 1, 0);
		gp.add(lb, 1, 4);

		HBox hb1 = new HBox();

		hb1.getChildren().add(gp);

		this.getDialogPane().setContent(gp);

		// --------------------------------------------------------------------
		ButtonType speichern1 = new ButtonType("Speichern ", ButtonData.OK_DONE);
		ButtonType beenden1 = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		
		this.getDialogPane().getButtonTypes().addAll(speichern1, beenden1);
		// setzt den Result  Converter
		this.setResultConverter(new Callback<ButtonType, ButtonType>() {

			@Override
			
			public ButtonType call(ButtonType arg0) {
				if (arg0 == speichern1) {
					try {

						levelFX.getModellLevel().setBezeichnung(txtBezeichnung.getText());
						if( levelFX.getId()>0) {
							Datenbank.levelBearbeiten(levelFX.getModellLevel());}
							else {Datenbank.insertLevel(levelFX.getModellLevel());}
						Datenbank.levelBearbeiten(levelFX.getModellLevel());
					} catch (SQLException e) {
						new Alert(AlertType.ERROR, e.toString()).showAndWait();
						return null;
					}
				}
				return arg0;
			}
		});

	}
}
