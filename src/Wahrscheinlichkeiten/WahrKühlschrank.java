package Wahrscheinlichkeiten;

import GerätePackage.Kühlschrank;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit des Kühlschrank.
 * @author Julian Grünker
 */	
public class WahrKühlschrank {
	
	public void getWahrKühlschrank(int timeSlot,double[][] gerätAn,int aktGerät,Kühlschrank ks) {
		ks.setBetriebsdauer(timeSlot);
		gerätAn[timeSlot][aktGerät] = ks.setAktuellerVerbrauch();
	}
}
