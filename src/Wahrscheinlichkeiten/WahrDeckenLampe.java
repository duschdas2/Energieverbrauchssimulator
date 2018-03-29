package Wahrscheinlichkeiten;

import GerätePackage.DeckenLampe;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit der Deckenlampe.
 * @author Julian Grünker
 */
public class WahrDeckenLampe {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*10+20;
	private int rndm = (int) (Math.random()*5+1);
	
	public void getWahrDeckenLampe(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot,boolean statData) {
		DeckenLampe dL = new DeckenLampe();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < rndm) {
						dL.setOnWahrscheinlichkeit(1);
						dL.setOffWahrscheinlichkeit(0);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn DeckenLampe gerade nicht benutzt
						if(timeSlot >= 420 && timeSlot <= 600) { // Benutzung zwischen 7 und 13 Uhr höher
								dL.setOnWahrscheinlichkeit(0.004*occupancy[timeSlot]);
								dL.setOffWahrscheinlichkeit(0.996*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1140 && timeSlot <= 60) { // Benutzung zwischen 19 und 1 Uhr höher
							dL.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
							dL.setOffWahrscheinlichkeit(0.998*occupancy[timeSlot]);
						}
						else if(timeSlot >= 61 || timeSlot <= 419) { //Benutzung nach 1 Uhr unwarscheinlich
							dL.setOnWahrscheinlichkeit(0.0005);
							dL.setOffWahrscheinlichkeit(0.9995);
						}
					}
					else if(betriebsDauer == rndm) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						dL.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						dL.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				dL.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				dL.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Veränderung niemand zu Hause!
			if(anzahlAn < tmp) {
				dL.setOnWahrscheinlichkeit(0.0);
				dL.setOffWahrscheinlichkeit(0.0);
			}
		}
		
		if(statAnalysis[timeSlot][aktGerät] >= 1 && occupancy[timeSlot] != 0 && statData == true) {
			if(anzahlAn < tmp) {
				dL.setOnWahrscheinlichkeit(dL.getOnWahrscheinlichkeit()+0.008);
				dL.setOffWahrscheinlichkeit(1-dL.getOffWahrscheinlichkeit()+0.008);
			}
		}
		
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < rndm && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(dL.getOnWahrscheinlichkeit() >=  Math.random() && (dL.getOnWahrscheinlichkeit()+dL.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			rndm = (int) (Math.random()*15+1);
			//System.out.println("TimeSlot2: " + timeSlot);
		}		
	}
}
