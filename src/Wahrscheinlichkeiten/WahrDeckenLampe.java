package Wahrscheinlichkeiten;

import GerätePackage.DeckenLampe;

public class WahrDeckenLampe {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*5;
	private int rndm = (int) (Math.random()*10+5);
	
	public void getWahrDeckenLampe(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot) {
		DeckenLampe dL = new DeckenLampe();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < rndm) {
						dL.setOnWahrscheinlichkeit(1);
						dL.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == rndm) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						dL.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						dL.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn DeckenLampe gerade nicht benutzt
						if(timeSlot >= 420 && timeSlot <= 600) { // Benutzung zwischen 7 und 13 Uhr höher
								dL.setOnWahrscheinlichkeit(0.01*occupancy[timeSlot]);
								dL.setOffWahrscheinlichkeit(0.99*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1140 && timeSlot <= 60) { // Benutzung zwischen 19 und 1 Uhr höher
							dL.setOnWahrscheinlichkeit(0.01*occupancy[timeSlot]);
							dL.setOffWahrscheinlichkeit(0.99*occupancy[timeSlot]);
						}
						else if(timeSlot >= 61 || timeSlot <= 419) { //Benutzung nach 1 Uhr unwarscheinlich
							dL.setOnWahrscheinlichkeit(0.001);
							dL.setOffWahrscheinlichkeit(0.999);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							dL.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							dL.setOffWahrscheinlichkeit(0.99998*occupancy[timeSlot]);
						}
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
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < rndm && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			System.out.println(anzahlAn);
			System.out.println(tmp);
			System.out.println("TimeSlot: " + timeSlot);
		}
		else if(dL.getOnWahrscheinlichkeit() >=  Math.random() && (dL.getOnWahrscheinlichkeit()+dL.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			System.out.println(anzahlAn);
			System.out.println("TimeSlot2: " + timeSlot);
		}		
	}
}
