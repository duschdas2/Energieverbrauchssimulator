package application;
import java.io.IOException;
import java.util.ArrayList;
import GerätePackage.Kühlschrank;
import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentyp;
import Hilfsmethoden.Ausgabe;
import Hilfsmethoden.Einlesen;
import Wahrscheinlichkeiten.WahrDeckenLampe;
import Wahrscheinlichkeiten.WahrFernseher;
import Wahrscheinlichkeiten.WahrKaffeeMaschine;
import Wahrscheinlichkeiten.WahrKühlschrank;
import Wahrscheinlichkeiten.WahrMikrowelle;
import Wahrscheinlichkeiten.WahrPc;
import Wahrscheinlichkeiten.WahrStaubsauger;
import Wahrscheinlichkeiten.WahrToaster;
import Wahrscheinlichkeiten.WahrTrockner;
import Wahrscheinlichkeiten.WahrWaschmaschine;
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
/**
 * Diese Klasse regelt das User Interface der Anwendung.
 * @author Julian Grünker
 */	
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
	@FXML private CheckBox checkKaffeemaschine;
	@FXML private CheckBox checkWaschmaschine;
	@FXML private CheckBox checkTrockner;
	@FXML private CheckBox checkPc;
	@FXML private CheckBox checkDeckenLampe;
	@FXML private CheckBox checkStat;
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
	private boolean statData = false;

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
		if(checkPlasmaFernseher.isSelected() == true) {
			geräte.add("plasmaFernseher");
		}
		if(checkLCDFernseher.isSelected() == true) {
			geräte.add("lcdFernseher");
		}
		if(checkMikrowelle.isSelected() == true) {
			geräte.add("mikrowelle");
		}
		if(checkKaffeemaschine.isSelected() == true) {
			geräte.add("kaffeemaschine");
		}
		if(checkWaschmaschine.isSelected() == true) {
			geräte.add("waschmaschine");
		}
		if(checkTrockner.isSelected() == true) {
			geräte.add("trockner");
		}
		if(checkDeckenLampe.isSelected() == true) {
			geräte.add("deckenlampe");
		}
		if(checkPc.isSelected() == true) {
			geräte.add("pc");
		}
		if(checkStat.isSelected() == true) {
			statData = true;
		}
		if(choicePersTyp1.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp2.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp2.getValue())));
		}
		if(choicePersTyp3.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp3.getValue())));
		}
		if(choicePersTyp4.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp4.getValue())));
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
		WahrKaffeeMaschine wahrKm = new WahrKaffeeMaschine();
		WahrFernseher wahrFs = new WahrFernseher();
		WahrFernseher wahrFs2 = new WahrFernseher();
		WahrWaschmaschine wahrWm = new WahrWaschmaschine();
		WahrTrockner wahrTo = new WahrTrockner();
		WahrDeckenLampe wahrDl = new WahrDeckenLampe();
		WahrPc wahrPc = new WahrPc();

		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		int waschMaAn = 0;
		boolean firstRun = true;
		
		for(int tSlot = 0;tSlot < statAnalysis.length;tSlot++) { 			//Durchläuft alle TimeSlots
			for(int aktGerät = 0;aktGerät < geräte.size();aktGerät++) { 	//Durchläuft alle Geräte
				if(geräte.get(aktGerät) == "toaster") {
					wahrTs.getWahrToaster(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(geräte.get(aktGerät) == "wasserkocher") {
					wahrWk.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					wahrWk.getWahrWasserKocher(statAnalysis,gerätAn,aktGerät,tSlot,statData);
				}
				if(geräte.get(aktGerät) == "mikrowelle") {
					wahrMw.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					wahrMw.getWahrMikrowelle(statAnalysis,gerätAn,aktGerät,tSlot,statData);
				}
				if(geräte.get(aktGerät) == "staubsauger") {
					wahrSs.getWahrStaubsauger(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(geräte.get(aktGerät) == "kühlschrank") {
					wahrKs.getWahrKühlschrank(tSlot,gerätAn,aktGerät,ks);
				}
				if(geräte.get(aktGerät) == "kaffeemaschine") {
					wahrKm.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					wahrKm.getWahrKaffeemaschine(statAnalysis,gerätAn,aktGerät,tSlot,statData);
				}
				if(geräte.get(aktGerät) == "lcdFernseher") {
					if(firstRun == true) {
						wahrFs.sucheKind(haushalt.getPersonen());
					}
					wahrFs.getWahrFernseher(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot,haushalt.getPersonen(),statData,"LCD");
				}
				if(geräte.get(aktGerät) == "plasmaFernseher") {
					if(firstRun == true) {
						wahrFs2.sucheKind(haushalt.getPersonen());
					}
					wahrFs2.getWahrFernseher(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot,haushalt.getPersonen(),statData,"Plasma");
				}
				if(geräte.get(aktGerät) == "waschmaschine") {
					wahrWm.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					waschMaAn = wahrWm.getWahrWaschmaschine(statAnalysis,gerätAn,aktGerät,tSlot,statData);
				}
				if(geräte.get(aktGerät) == "trockner") {
					if(waschMaAn != 0) {
						wahrTo.getWahrTrockner(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot,waschMaAn);
					}
				}
				if(geräte.get(aktGerät) == "deckenlampe") {
					wahrDl.getWahrDeckenLampe(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot,statData);
				}
				if(geräte.get(aktGerät) == "pc") {
					wahrPc.getWahrPc(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot,statData);
				}
				firstRun = false;
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
			if(geräte.get(i) == "kühlschrank") { 
				tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			}
			if(geräte.get(i) == "kaffeemaschine") { 
				tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			}
			if(geräte.get(i) == "pc") { 
				tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			}
			if(geräte.get(i) == "trockner") { 
				tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			}
			//Nicht 100 Prozent funktionsfähig!
			//if(geräte.get(i) == "waschmaschine") {  
			//	tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			//}
			//if(geräte.get(i) == "fernseher") {  
			//	tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			//}
			//if(geräte.get(i) == "mikrowelle") { 
			//	tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			//}
			if(geräte.get(i) == "deckenlampe") { 
				tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			}
			for(int j = 0; j<1440;j++) {
				statAnalysis[j][i] = tmpData[j];
			}
		}
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Input.fxml"));
			loader.setController(this);
			root = loader.load();
			Scene scene = new Scene(root,522,425);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Energieverbrauchssimulator");
			primaryStage.show();
			primaryStage.setResizable(false);
			initialize();
			btnStart.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
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
