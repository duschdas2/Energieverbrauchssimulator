package Wahrscheinlichkeiten;

import GerätePackage.PC;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit der PC.
 * @author Julian Grünker
 */	
public class WahrPc {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	private double dauer = Math.floor((Math.random() * 100) + 10);
	
	public void getWahrPc(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot, boolean statData) {
		PC pC = new PC();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < dauer) {
						pC.setOnWahrscheinlichkeit(1);
						pC.setOffWahrscheinlichkeit(0);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn PC gerade nicht benutzt
						if(timeSlot >= 1080 && timeSlot <= 1440) { // Benutzung zwischen 18 und 24 Uhr am höchsten
								pC.setOnWahrscheinlichkeit(0.003*occupancy[timeSlot]);
								pC.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 840 && timeSlot <= 1079) { // Benutzung zwischen 14 und 18 Uhr höher
							pC.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
							pC.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							pC.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
							pC.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
						}
					}
					else if(betriebsDauer == dauer) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						pC.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						pC.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
				pC.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
			}
		}
		else { //Keine Veränderung niemand zu Hause!
			if(anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(0.0);
				pC.setOffWahrscheinlichkeit(0.0);
			}
		}
		
		if(statAnalysis[timeSlot][aktGerät] >= 1 && occupancy[timeSlot] != 0 && statData == true) {	//Wert verändern
			if(anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(pC.getOnWahrscheinlichkeit()+0.002);
				pC.setOffWahrscheinlichkeit(1-pC.getOffWahrscheinlichkeit()+0.002);
			}
		}
		
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < dauer && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(pC.getOnWahrscheinlichkeit() >=  Math.random() && (pC.getOnWahrscheinlichkeit()+pC.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			dauer = Math.floor((Math.random() * 100) + 10);
		}		
	}
}
