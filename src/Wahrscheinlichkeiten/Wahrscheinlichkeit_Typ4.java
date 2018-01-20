package Wahrscheinlichkeiten;

import GerätePackage.Kühlschrank;

public class Wahrscheinlichkeit_Typ4 {

	public void getWahrKühlschrank(int timeSlot,double[][] gerätAn,int aktGerät) {
		Kühlschrank ks = new Kühlschrank();
		ks.setBetriebsdauer(timeSlot);
		gerätAn[timeSlot][aktGerät] = ks.getAktuellerVerbrauch();
	}
}
