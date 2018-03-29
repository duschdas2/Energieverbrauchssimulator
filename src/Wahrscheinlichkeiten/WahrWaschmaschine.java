package Wahrscheinlichkeiten;

import java.util.ArrayList;

import GerätePackage.Waschmaschine;
import Haushalt.Person;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit der Waschmaschine.
 * @author Julian Grünker
 */	
public class WahrWaschmaschine {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*1;
	private int temp = 0;
	private double rndm = 32;
	private int [] occupancy = new int[1440];
	private int anzKinder = 1;
	
	public void sucheKind(ArrayList<Person> personen,int [] occupancy) {
		for(int i = 0;i<occupancy.length;i++) {
			this.occupancy[i] = occupancy[i];
		}
		for(int a = 0; a <1440;a++) {
			for(int i = 0; i<personen.size();i++) {
				if(personen.get(i).getPersonentyp().getTyp() == "Kind") {				
					this.occupancy[a] --;
				}
			}
		}
		for(int i = 0; i<personen.size();i++) {
			if(personen.get(i).getPersonentyp().getTyp() == "Kind") {				
				anzKinder++;
			}
		}
	}
	
	public int getWahrWaschmaschine(double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot,boolean statData) {
		Waschmaschine wM = new Waschmaschine();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < rndm) {
						wM.setOnWahrscheinlichkeit(1);
						wM.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == rndm-1) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						wM.setOnWahrscheinlichkeit(0.00001*occupancy[timeSlot]*anzKinder);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind * Kinder im Haushalt
						wM.setOffWahrscheinlichkeit(0.99999*occupancy[timeSlot]*anzKinder);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Waschmaschine gerade nicht benutzt
						if(timeSlot >= 360 && timeSlot <= 1200 || timeSlot >= 900 && timeSlot <= 1140) { // Benutzung zwischen 11 und 13 Uhr und 15-19 Uhr höher
								wM.setOnWahrscheinlichkeit(0.0005*occupancy[timeSlot]*anzKinder);
								wM.setOffWahrscheinlichkeit(0.9995*occupancy[timeSlot]*anzKinder);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlicher
							wM.setOnWahrscheinlichkeit(0.00001);
							wM.setOffWahrscheinlichkeit(0.99999);
						}
						else {
							wM.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]*anzKinder);
							wM.setOffWahrscheinlichkeit(0.99998*occupancy[timeSlot]*anzKinder);
						}
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				wM.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]*anzKinder);
				wM.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]*anzKinder);
			}
		}
		else { //Keine Veränderung niemand zu Hause!
			if(anzahlAn < tmp) {
				wM.setOnWahrscheinlichkeit(0.0);
				wM.setOffWahrscheinlichkeit(0.0);
			}
		}
		
		//if(statAnalysis[timeSlot][aktGerät] >= 1 && occupancy[timeSlot] != 0 && statData == true) {
		//	if(anzahlAn < tmp) {
		//		wM.setOnWahrscheinlichkeit(wM.getOnWahrscheinlichkeit()+0.0005);
		//		wM.setOffWahrscheinlichkeit(1-wM.getOffWahrscheinlichkeit()+0.0005);
		//	}
		//}
		
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < rndm) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			if(betriebsDauer >= rndm) {
				double tmp = Math.random()*200+30;
				temp = (int) (timeSlot+tmp);
			}
		}
		else if(wM.getOnWahrscheinlichkeit() >=  Math.random() && (wM.getOnWahrscheinlichkeit()+wM.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer = 0;
			anzahlAn++;
		}
		return temp;
	}
}
