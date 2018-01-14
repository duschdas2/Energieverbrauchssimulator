package application;
	
import java.io.IOException;
import java.util.ArrayList;
import Haushalt.Haushalt;
import Haushalt.Person;
import Hilfsmethoden.Ausgabe;
import Hilfsmethoden.Einlesen;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ1;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ2;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ3;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	@FXML private CheckBox checkDiagramm;
	@FXML private Slider slider;
	@FXML private Label lblCountSlider;
	@FXML private ChoiceBox<String> choicePersTyp1;
	@FXML private ChoiceBox<String> choicePersTyp2;
	@FXML private ChoiceBox<String> choicePersTyp3;
	@FXML private ChoiceBox<String> choicePersTyp4;
	@FXML private Button btnStart;
	
	private ArrayList <String> geräte = new ArrayList<String>();
	private double [][] gerätAn = new double [1440][geräte.size()];
	private double [][] statAnalysis = new double [1440][geräte.size()];
	private ArrayList<Person> list = new ArrayList<Person>();
	private boolean diagramm = false;

	@FXML public void initialize() {
		ObservableList<String> personenTypen = FXCollections.observableArrayList("Arbeitslos","Arbeiter","Kinder","Student");
		choicePersTyp1.setItems(personenTypen);
		choicePersTyp1.setValue("Arbeitslos");
		choicePersTyp2.setItems(personenTypen);
		choicePersTyp2.setValue("Arbeitslos");
		choicePersTyp3.setItems(personenTypen);
		choicePersTyp3.setValue("Arbeitslos");
		choicePersTyp4.setItems(personenTypen);
		choicePersTyp4.setValue("Arbeitslos");
		slider.setMin(1);
		slider.setMax(4);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setBlockIncrement(10);
		slider.setSnapToTicks(true);
		lblCountSlider.setText("1");
		choicePersTyp1.setDisable(false);
    	choicePersTyp2.setDisable(true);
    	choicePersTyp3.setDisable(true);
    	choicePersTyp4.setDisable(true);
		slider.valueProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                lblCountSlider.textProperty().setValue(
                        String.valueOf((int) slider.getValue()));
                if(slider.getValue() == 1) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(true);
                	choicePersTyp3.setDisable(true);
                	choicePersTyp4.setDisable(true);
                }
                if(slider.getValue() == 2) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(false);
                	choicePersTyp3.setDisable(true);
                	choicePersTyp4.setDisable(true);                }
                if(slider.getValue() == 3) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(false);
                	choicePersTyp3.setDisable(false);
                	choicePersTyp4.setDisable(true);                }
                if(slider.getValue() == 4) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(false);
                	choicePersTyp3.setDisable(false);
                	choicePersTyp4.setDisable(false);                }
            }
		});
	}
	public void bearbeite() throws IOException {
		Wahrscheinlichkeit_Typ1 w1 = new Wahrscheinlichkeit_Typ1();
		Wahrscheinlichkeit_Typ2 w2 = new Wahrscheinlichkeit_Typ2();
		Wahrscheinlichkeit_Typ3 w3 = new Wahrscheinlichkeit_Typ3();
		
		if(checkToaster.isSelected() == true) {
			geräte.add("toaster");
		}
		if(checkStaubsauger.isSelected() == true) {
			geräte.add("staubsauger");
		}
		if(checkWasserkocher.isSelected() == true) {
			geräte.add("wasserkocher");
		}
		if(choicePersTyp1.isDisabled() == false) {
			//list.add(new Person(choicePersTyp1.getValue());
		}
		if(choicePersTyp2.isDisabled() == false) {
			//list.add(new Person(choicePersTyp1.getValue());
		}
		if(choicePersTyp3.isDisabled() == false) {
			//list.add(new Person(choicePersTyp1.getValue());
		}
		if(choicePersTyp4.isDisabled() == false) {
			//list.add(new Person(choicePersTyp1.getValue());
		}
		if(checkDiagramm.isSelected() == true) {
			diagramm = true;
		}
		getStatData(statAnalysis,geräte);
		erstelle(w1,w2,w3);
	}
	public void erstelle(Wahrscheinlichkeit_Typ1 w1,Wahrscheinlichkeit_Typ2 w2, Wahrscheinlichkeit_Typ3 w3) {
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		for(int tSlot = 0;tSlot < statAnalysis.length;tSlot++) { 			//Durchläuft alle TimeSlots
			for(int aktGerät = 0;aktGerät < geräte.size();aktGerät++) { 	//Durchläuft alle Geräte
				if(aktGerät == 0) {
					w1.getWahrToaster(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(aktGerät == 1) {
					w1.getWahrWasserKocher(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(aktGerät == 2) {
					w3.getWahrStaubsauger(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(gerätAn[tSlot][aktGerät] == 1)
				{
					System.out.println("TimeSlot: " + tSlot + " " + geräte.get(aktGerät) + " : "+ gerätAn[tSlot][aktGerät]);
				}
			}
		}
		if(diagramm = true) {
			Ausgabe.erstelleArr(haushalt.getOccupancy(), gerätAn, geräte);
		}
	}
	
	public static void getStatData(double [][] statAnalysis,ArrayList <String> geräte) throws IOException {
		int auswertDaten = 24;
		double[] tmpData = new double[1440];
		for(int i = 0; i<geräte.size();i++) {
			if(geräte.get(i) == "wasserkocher") {
				tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			}
			for(int j = 0; j<1440;j++) {
				statAnalysis[j][i] = tmpData[j];
			}
		}
	}
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Input.fxml"));
			loader.setController(this);
			root = loader.load();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			initialize();
			btnStart.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			    	try {
						bearbeite();
						primaryStage.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    }
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
