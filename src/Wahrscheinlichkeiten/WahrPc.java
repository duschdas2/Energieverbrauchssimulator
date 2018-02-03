package Wahrscheinlichkeiten;

import GerätePackage.PC;

public class WahrPc {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	private double dauer = Math.floor((Math.random() * 100) + 10);
	
	public void getWahrPc(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot) {
		PC pC = new PC();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < dauer) {
						pC.setOnWahrscheinlichkeit(1);
						pC.setOffWahrscheinlichkeit(0);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Mikrowelle gerade nicht benutzt
						if(timeSlot >= 1021 && timeSlot <= 119) { // Benutzung zwischen 11 und 13 Uhr am höchsten
								pC.setOnWahrscheinlichkeit(0.003*occupancy[timeSlot]);
								pC.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 600 && timeSlot <= 1020) { // Benutzung zwischen 10 und 17 Uhr höher
							pC.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
							pC.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 120 || timeSlot <= 480) { //Benutzung zwischen 2 und 8 Uhr unwarscheinlich
							pC.setOnWahrscheinlichkeit(0.0001);
							pC.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							pC.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							pC.setOffWahrscheinlichkeit(0.99998*occupancy[timeSlot]);
						}
					}
					else if(betriebsDauer == dauer) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						pC.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						pC.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				pC.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Veränderung niemand zu Hause!
			if(anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(0.0);
				pC.setOffWahrscheinlichkeit(0.0);
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
