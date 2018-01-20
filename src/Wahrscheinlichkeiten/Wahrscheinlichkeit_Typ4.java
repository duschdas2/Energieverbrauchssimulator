package Wahrscheinlichkeiten;

import GerätePackage.Kühlschrank;

public class Wahrscheinlichkeit_Typ4 {
	Kühlschrank ks = new Kühlschrank();

	public void getWahrKühlschrank(int timeSlot,double[][] gerätAn,int aktGerät) {
		ks.setBetriebsdauer(timeSlot);
		gerätAn[timeSlot][aktGerät] = ks.getAktuellerVerbrauch();
	}
}
