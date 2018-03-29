package Wahrscheinlichkeiten;

import java.util.ArrayList;

import GerätePackage.Mikrowelle;
import Haushalt.Person;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit der Mikrowelle.
 * @author Julian Grünker
 */	
public class WahrMikrowelle {

	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	private int [] occupancy = new int[1440];

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
	}
	
	public void getWahrMikrowelle(double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot,boolean statData) {
		Mikrowelle mw = new Mikrowelle();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < mw.getBetriebsdauer()-1) {
						mw.setOnWahrscheinlichkeit(1);
						mw.setOffWahrscheinlichkeit(0);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Mikrowelle gerade nicht benutzt
						if(timeSlot >= 660 && timeSlot <= 780) { // Benutzung zwischen 11 und 13 Uhr höher
								mw.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
								mw.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1080 && timeSlot <= 1200) { // Benutzung zwischen 18 und 20 Uhr höher
							mw.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
							mw.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							mw.setOnWahrscheinlichkeit(0.0001);
							mw.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							mw.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							mw.setOffWahrscheinlichkeit(0.99998*occupancy[timeSlot]);
						}
					}
					else if(betriebsDauer == mw.getBetriebsdauer()) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						mw.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						mw.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				mw.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				mw.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Veränderung niemand zu Hause!
			if(anzahlAn < tmp) {
				mw.setOnWahrscheinlichkeit(0.0);
				mw.setOffWahrscheinlichkeit(0.0);
			}
		}
		
		if(statAnalysis[timeSlot][aktGerät] >= 1 && occupancy[timeSlot] != 0 && statData == true) {
			if(anzahlAn < tmp) {
				mw.setOnWahrscheinlichkeit(mw.getOnWahrscheinlichkeit()+0.01);
				mw.setOffWahrscheinlichkeit(1-mw.getOffWahrscheinlichkeit()+0.01);
			}
		}
		
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < mw.getBetriebsdauer()-1 && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(mw.getOnWahrscheinlichkeit() >=  Math.random() && (mw.getOnWahrscheinlichkeit()+mw.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			//System.out.println("TimeSlot: " + timeSlot);
		}		
	}
}
