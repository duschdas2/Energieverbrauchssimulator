package Wahrscheinlichkeiten;

import Ger�tePackage.Waschmaschine;

public class WahrWaschmaschine {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*1;
	private int temp = 0;
	private double rndm = 32+Math.random()*5;
	
	public int getWahrWaschmaschine(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot) {
		Waschmaschine wM = new Waschmaschine();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < rndm) {
						wM.setOnWahrscheinlichkeit(1);
						wM.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == rndm-1) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						wM.setOnWahrscheinlichkeit(0.00001*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						wM.setOffWahrscheinlichkeit(0.99999*occupancy[timeSlot]);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Waschmaschine gerade nicht benutzt
						if(timeSlot >= 360 && timeSlot <= 1200) { // Benutzung zwischen 11 und 13 Uhr h�her
								wM.setOnWahrscheinlichkeit(0.0005*occupancy[timeSlot]);
								wM.setOffWahrscheinlichkeit(0.9995*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							wM.setOnWahrscheinlichkeit(0.0001);
							wM.setOffWahrscheinlichkeit(0.9999);
						}
						else {
							wM.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							wM.setOffWahrscheinlichkeit(0.99998*occupancy[timeSlot]);
						}
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				wM.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				wM.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Ver�nderung niemand zu Hause!
			if(anzahlAn < tmp) {
				wM.setOnWahrscheinlichkeit(0.0);
				wM.setOffWahrscheinlichkeit(0.0);
			}
		}
		if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < rndm) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
			if(betriebsDauer >= rndm) {
				double tmp = Math.random()*200+30;
				temp = (int) (timeSlot+tmp);
			}
		}
		else if(wM.getOnWahrscheinlichkeit() >=  Math.random() && (wM.getOnWahrscheinlichkeit()+wM.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			//System.out.println("TimeSlot2: " + timeSlot);
		}
		return temp;
	}
}