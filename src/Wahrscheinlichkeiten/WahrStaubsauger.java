package Wahrscheinlichkeiten;

import GerätePackage.Staubsauger;

public class WahrStaubsauger {
	
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double randomDauer = 5+Math.random()*40;
	
	public void getWahrStaubsauger(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot) {
		Staubsauger sb = new Staubsauger();
		
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < Math.random()*3)
					{
					if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer <= randomDauer) {
						sb.setOnWahrscheinlichkeit(0.5*occupancy[timeSlot]);
						sb.setOffWahrscheinlichkeit(0.5*occupancy[timeSlot]);
					}
					else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Staubsauger gerade nicht benutzt
						if(timeSlot >= 900 && timeSlot <= 1140 || timeSlot >= 540 && timeSlot <= 720) { // Benutzung zwischen 15-19 Uhr und 9-12 Uhr höher (evtl Random mit einbinden)
								sb.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
								sb.setOffWahrscheinlichkeit(0.998*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							sb.setOnWahrscheinlichkeit(0.0001);
							sb.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							sb.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							sb.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
						}
					}	
				}
				else if(timeSlot == 0) {
					sb.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
					sb.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
				}
			}
			else {
				sb.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
				sb.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
			}
		}
		else if(occupancy[timeSlot] == 0){ //Keine Veränderung niemand zu Hause!
			sb.setOnWahrscheinlichkeit(0.0);
			sb.setOffWahrscheinlichkeit(0.0);
		}
	
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer <= randomDauer && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
		}
		if(sb.getOnWahrscheinlichkeit() >=  Math.random() && (sb.getOnWahrscheinlichkeit()+sb.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			sb.setBenutzt(true);
			gerätAn[timeSlot][aktGerät] = 1;
			anzahlAn++;
			betriebsDauer = 0;
			randomDauer = 5+Math.random()*40;
		}		
	}
}
