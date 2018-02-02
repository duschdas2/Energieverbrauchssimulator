package Wahrscheinlichkeiten;

import GerätePackage.Kühlschrank;

public class WahrKühlschrank {
	
	public void getWahrKühlschrank(int timeSlot,double[][] gerätAn,int aktGerät,Kühlschrank ks) {
		ks.setBetriebsdauer(timeSlot);
		gerätAn[timeSlot][aktGerät] = ks.setAktuellerVerbrauch();
	}
}
