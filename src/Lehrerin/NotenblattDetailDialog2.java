package Lehrerin;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import klassen.Datenbank;
import klassen.Notenblatt;
import klassen.NotenblattFX;
/**
 * 
 * In dieser Klasse erzeuge ich erst eine ObservableList von NotenblattFX,
 * dann erschaffe ich eine Gridpane mit der man ,sowohl ein Item neu erschaffen kann 
 * als auch Bearbeiten kann 
 * @author Jasmina Marinkovic 
 *
 */
public class NotenblattDetailDialog2 extends Dialog<ButtonType> {
	private ObservableList<NotenblattFX> olNotenblattupdate = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	/**
	 * Der Konstruktor
	 * @param notenblattFX ist das Notenblatt
	 */
	public NotenblattDetailDialog2(NotenblattFX notenblattFX) {

		GridPane gp = new GridPane();
		Label lbTitel = new Label("Titel");
		Label lbPfad = new Label("Bildpfad");
		Label lb = new Label();
		lb.setPrefSize(200, 150);
		// hier setze ich unterschiedliche Layouts(Bilder ) 
				// für die neu erschaffen als auch bearbeiten Option ein 
		if( notenblattFX.getId()>0) {
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
		Button btnSuchen = new Button("suchen");

		txtTitel.setText(notenblattFX.getTitel());
		txtPfad.setText(notenblattFX.getBildPath());

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
		gp.add(lb, 1, 4);

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
		ButtonType speichern1 = new ButtonType("Speichern ", ButtonData.OK_DONE);
		ButtonType beenden1 = new ButtonType("Beenden", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(speichern1, beenden1);
		// setzt den Result  Converter 
		this.setResultConverter(new Callback<ButtonType, ButtonType>() {

			@Override
			
			public ButtonType call(ButtonType arg0) {
				if (arg0 == speichern1) {
					try {
						notenblattFX.getModellNotenblatt().setBildPath(txtPfad.getText());
						notenblattFX.getModellNotenblatt().setTitel(txtTitel.getText());
						if( notenblattFX.getId()>0) {
							Datenbank.notenblattBearbeiten(notenblattFX.getModellNotenblatt());}
							else {Datenbank.insertNotenblatt(notenblattFX.getModellNotenblatt());}
						Datenbank.notenblattBearbeiten(notenblattFX.getModellNotenblatt());
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