package Wahrscheinlichkeiten;

import GerätePackage.Trockner;
import GerätePackage.Waschmaschine;

public class WahrTrockner {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*1;
	private double rndm = 73;
	private int rndmAn = (int) Math.random()*150+30;
	
	public void getWahrTrockner(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot,int timeSlotWashMa) {
		Trockner tO = new Trockner();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot == timeSlotWashMa+rndmAn) {
				if(betriebsDauer < rndm) {
					tO.setOnWahrscheinlichkeit(1.0);
					tO.setOffWahrscheinlichkeit(0.0);
				}
			}
		}	
		else { //Keine Veränderung niemand zu Hause!
				tO.setOnWahrscheinlichkeit(0.0);
				tO.setOffWahrscheinlichkeit(0.0);
		}
		
		if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < rndm) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(tO.getOnWahrscheinlichkeit() >=  Math.random() && (tO.getOnWahrscheinlichkeit()+tO.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			gerätAn[timeSlot][aktGerät] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			//System.out.println("TimeSlot: " + timeSlot);
		}		
	}
}
