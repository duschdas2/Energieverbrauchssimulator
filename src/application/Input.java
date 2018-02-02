package application;
	
import java.io.IOException;
import java.util.ArrayList;
import GerätePackage.Kühlschrank;
import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentyp;
import Hilfsmethoden.Ausgabe;
import Hilfsmethoden.Einlesen;
import Wahrscheinlichkeiten.WahrKühlschrank;
import Wahrscheinlichkeiten.WahrMikrowelle;
import Wahrscheinlichkeiten.WahrStaubsauger;
import Wahrscheinlichkeiten.WahrToaster;
import Wahrscheinlichkeiten.WahrWasserKocher;
import javafx.application.Application;
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
import javafx.scene.layout.Pane;

public class Input extends Application {
	
	@FXML private Pane anchorFenster;
	@FXML private CheckBox checkToaster;
	@FXML private CheckBox checkStaubsauger;
	@FXML private CheckBox checkWasserkocher;
	@FXML private CheckBox checkDiagramm;
	@FXML private CheckBox checkKühlschrank;
	@FXML private CheckBox checkPlasmaFernseher;
	@FXML private CheckBox checkLCDFernseher;
	@FXML private CheckBox checkMikrowelle;
	@FXML private Slider slider;
	@FXML private Label lblCountSlider;
	@FXML private ChoiceBox<String> choicePersTyp1;
	@FXML private ChoiceBox<String> choicePersTyp2;
	@FXML private ChoiceBox<String> choicePersTyp3;
	@FXML private ChoiceBox<String> choicePersTyp4;
	@FXML private Button btnStart;
	
	private ArrayList <String> geräte = new ArrayList<String>();
	private ArrayList<Person> list = new ArrayList<Person>();
	private boolean diagramm = false;

	@FXML public void initialize() {
		ObservableList<String> personenTypen = FXCollections.observableArrayList("Arbeitslos","Arbeiter","Kind","Student");
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
		slider.valueProperty().addListener((observable, oldValue, newValue) -> { {
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
		if(checkToaster.isSelected() == true) {
			geräte.add("toaster");
		}
		if(checkWasserkocher.isSelected() == true) {
			geräte.add("wasserkocher");
		}
		if(checkStaubsauger.isSelected() == true) {
			geräte.add("staubsauger");
		}
		if(checkKühlschrank.isSelected() == true) {
			geräte.add("kühlschrank");
		}
//		if(checkPlasmaFernseher.isSelected() == true) {
//			geräte.add("plasmaFernseher");
//		}
//		if(checkLCDFernseher.isSelected() == true) {
//			geräte.add("lcdFernseher");
//		}
		if(checkMikrowelle.isSelected() == true) {
			geräte.add("mikrowelle");
		}
		if(choicePersTyp1.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp2.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp3.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp4.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(checkDiagramm.isSelected() == true) {
			diagramm = true;
		}
		double [][] gerätAn = new double [1440][geräte.size()];
		double [][] statAnalysis = new double [1440][geräte.size()];
		getStatData(statAnalysis,geräte);
		erstelle(gerätAn,statAnalysis);
	}
	public void erstelle(double [][] gerätAn,double[][]statAnalysis) {
		Kühlschrank ks = new Kühlschrank();
		WahrWasserKocher wahrWk = new WahrWasserKocher();	//verbessern !!!
		WahrMikrowelle wahrMw = new WahrMikrowelle();
		WahrToaster wahrTs = new WahrToaster();
		WahrKühlschrank wahrKs = new WahrKühlschrank();
		WahrStaubsauger wahrSs = new WahrStaubsauger();
		
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		for(int tSlot = 0;tSlot < statAnalysis.length;tSlot++) { 			//Durchläuft alle TimeSlots
			for(int aktGerät = 0;aktGerät < geräte.size();aktGerät++) { 	//Durchläuft alle Geräte
				if(geräte.get(aktGerät) == "toaster") {
					wahrTs.getWahrToaster(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(geräte.get(aktGerät) == "wasserkocher") {
					wahrWk.getWahrWasserKocher(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(geräte.get(aktGerät) == "mikrowelle") {
					wahrMw.getWahrMikrowelle(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(geräte.get(aktGerät) == "staubsauger") {
					wahrSs.getWahrStaubsauger(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(geräte.get(aktGerät) == "kühlschrank") {
					wahrKs.getWahrKühlschrank(tSlot,gerätAn,aktGerät,ks);
				}
			}
		}
		if(diagramm == true) {
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
			//if(geräte.get(i) == "kühlschrank") { //fehlt noch
			//	tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			//}
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
			Scene scene = new Scene(root,400,425);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Energieverbrauchssimulator");
			primaryStage.show();
			primaryStage.setResizable(false);
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
