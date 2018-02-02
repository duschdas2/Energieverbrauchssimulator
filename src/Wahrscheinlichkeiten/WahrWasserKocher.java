package Wahrscheinlichkeiten;

import GerätePackage.Wasserkocher;

public class WahrWasserKocher {

	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*2;
	
	public void getWahrWasserKocher(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot) {
		Wasserkocher wk = new Wasserkocher();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) { //läuft maximal tmp mal am Tag
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < wk.getBetriebsdauer()-1) { 
						wk.setOnWahrscheinlichkeit(1);
						wk.setOffWahrscheinlichkeit(0);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Wasserkocher gerade nicht benutzt
						if(timeSlot >= 360 && timeSlot <= 540 || timeSlot >= 720 && timeSlot <= 840 || timeSlot >= 1080 && timeSlot <= 1200) { // Benutzung zwischen 8-9 Uhr,12 und 14 Uhr und 18-20 Uhr höher (evtl Random mit einbinden)
								wk.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
								wk.setOffWahrscheinlichkeit(0.998*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							wk.setOnWahrscheinlichkeit(0.00005);
							wk.setOffWahrscheinlichkeit(0.99995);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							wk.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
							wk.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
						}
					}	
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
				wk.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		if (timeSlot > 0 && occupancy[timeSlot] == 0 && occupancy[timeSlot-1] == 0 && occupancy[timeSlot+1] == 0){ //Keine Veränderung niemand zu Hause!
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(0.0);
				wk.setOffWahrscheinlichkeit(0.0);
			}
		}
		else if(occupancy[timeSlot] == 0)
		{
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(0.0);
				wk.setOffWahrscheinlichkeit(0.0);
			}
		}
		if(timeSlot > 200 && betriebsDauer == 0 && anzahlAn == 0 && occupancy[timeSlot] != 0) {
			for(int i = 200; i>0 ;i--) {
				if(gerätAn[timeSlot-i][aktGerät] == 1) {
					wk.setOnWahrscheinlichkeit(0.00001*occupancy[timeSlot]);
					wk.setOffWahrscheinlichkeit(0.99999*occupancy[timeSlot]);
				}
			}
		}
		if(statAnalysis[timeSlot][aktGerät] >= 1 && occupancy[timeSlot] != 0) {	//Wert verändern
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(wk.getOnWahrscheinlichkeit()+0.04);
				wk.setOffWahrscheinlichkeit(wk.getOffWahrscheinlichkeit()+0.04);
			}
		}
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < wk.getBetriebsdauer()-1 && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(wk.getOnWahrscheinlichkeit() >=  Math.random() && (wk.getOnWahrscheinlichkeit()+wk.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			//wk.setBenutzt(true);
			gerätAn[timeSlot][aktGerät] = 1;
			//if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < wk.getBetriebsdauer()) {
			//	betriebsDauer++;
			//}
			//else
			//{
				betriebsDauer = 0;
				anzahlAn++;
				//wk.setBenutzt(false);
			//}
			//if(anzahlAn > tmp) //bearbeiten !!!
			//{
				//anzahlAn--;
			//}
			//System.out.println("TimeSlot: " + timeSlot);
			//System.out.println("AnzahlAn: " + anzahlAn + " , tmp: " +tmp);
			//System.out.println(betriebsDauer);
		}		
	}
	
}
