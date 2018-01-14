package application;
	
import java.util.ArrayList;
import Haushalt.Person;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ1;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ3;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Input extends Application {
	
	@FXML private Pane anchorFenster;
	@FXML private CheckBox checkToaster;
	@FXML private CheckBox checkStaubsauger;
	@FXML private CheckBox checkWasserkocher;
	@FXML private Slider slider;
	@FXML private Label lblCountSlider;
	@FXML private ChoiceBox<String> choiceBox1;
	@FXML private ChoiceBox<String> choicePersTyp2;
	@FXML private ChoiceBox<String> choicePersTyp3;
	@FXML private ChoiceBox<String> choicePersTyp4;
	
	private ArrayList <String> geräte = new ArrayList<String>();
	private double [][] gerätAn = new double [1440][geräte.size()];
	private double [][] statAnalysis = new double [1440][geräte.size()];
	private ArrayList<Person> list = new ArrayList<Person>();


	@FXML public void initialize() {
		//ArrayList<String> list = new ArrayList<String>();
		//list.add("Arbeitslos");
		//ObservableList<String> personenTypen = FXCollections.observableArrayList(list);
		//System.out.println(choiceBox1.getItems());
		//choicePersTyp1 = new ChoiceBox<String>();
		//choicePersTyp1.setItems(personenTypen);
		//choicePersTyp1.setValue("Arbeitslos");
		//choicePersTyp4.setItems(personenTypen);
		//choicePersTyp4.setValue("Arbeitslos");
		System.out.println(anchorFenster.getScaleX());
		//slider.setMin(1);
		//slider.setMax(4);
		/*slider.valueProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                lblCountSlider.textProperty().setValue(
                        String.valueOf((int) slider.getValue()));
                System.out.println(slider.getMax());

            }
		});
		*/
	}
	public void bearbeite() {
		Wahrscheinlichkeit_Typ1 w1 = new Wahrscheinlichkeit_Typ1();
		Wahrscheinlichkeit_Typ3 w2 = new Wahrscheinlichkeit_Typ3();
		
		if(checkToaster.isSelected() == true) {
			geräte.add("toaster");
		}
		if(checkStaubsauger.isSelected() == true) {
			geräte.add("staubsauger");
		}
		if(checkWasserkocher.isSelected() == true) {
			geräte.add("wasserkocher");
		}
	}
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Input.fxml"));
			loader.setController(root);
			root = loader.load();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			initialize();
			//bearbeite();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
