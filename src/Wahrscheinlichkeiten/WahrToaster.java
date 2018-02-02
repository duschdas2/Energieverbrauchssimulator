package Wahrscheinlichkeiten;

import GerätePackage.Toaster;

public class WahrToaster {

	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*2;
	
	public void getWahrToaster(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot) {
		Toaster ts = new Toaster();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < ts.getBetriebsdauer()) {
						ts.setOnWahrscheinlichkeit(1);
						ts.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 2) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.0005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						ts.setOffWahrscheinlichkeit(0.99995*occupancy[timeSlot]);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 390 && timeSlot <= 600) { // Benutzung zwischen 6:30 und 10 Uhr höher
								ts.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
								ts.setOffWahrscheinlichkeit(0.95*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.0001);
							ts.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							ts.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
						}
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				ts.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				ts.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Veränderung niemand zu Hause!
			if(anzahlAn < tmp) {
				ts.setOnWahrscheinlichkeit(0.0);
				ts.setOffWahrscheinlichkeit(0.0);
			}
		}
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < ts.getBetriebsdauer() && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(ts.getOnWahrscheinlichkeit() >=  Math.random() && (ts.getOnWahrscheinlichkeit()+ts.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer = 0;
			anzahlAn++;
		}		
	}
}
