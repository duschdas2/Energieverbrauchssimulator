package Hilfsmethoden;
import java.io.IOException;
import java.util.ArrayList;

import GerätePackage.*;

public class Ausgabe {
	
	private static String [][] ausgabe;
	private static double [] gesamtLast = new double [1440];
	private static int [] occupancy;
	private static double [][] gerätAn;
	static ArrayList <String> names;
	
	/**
	 * Erstellt das Array für die CSV-Datei
	 * @param occupancy
	 * @param geräteAn
	 * @param names
	 */
	public static void erstelleArr(int [] Occupancy, double [][] GerätAn, ArrayList <String> Names) {
		occupancy = Occupancy;
		gerätAn = GerätAn;
		names = Names;
		ausgabe = new String [1441][gerätAn[0].length +2];
		for (int i = 0; i < gesamtLast.length; i++) {	//Initialisiert das Array mit 0
			gesamtLast[i] = 0;
		}
		geraete();
		gesamt();
		fuelle();
		try {
			String csv = Create_CSV.create(ausgabe);
			Diagramm.erzeuge(csv);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fuellt das ausgabe Array mit den gesammelten und berechneten Werten
	 */
	private static void fuelle() {
		ausgabe[0][0] = "Occupancy";
		ausgabe[0][1] = "GesamtLast";
		for (int i = 0; i < names.size(); i++) {		//Schreibt die Namen der Geräte in die erste Reihe des Arrays
			ausgabe[0][i+2] =  names.get(i);
		}
		
		for (int i = 0; i < ausgabe.length-1; i++) {					//Reihe
			for (int c = 0; c < ausgabe[0].length; c++) {				//Spalte
				if (c == 0) {
					ausgabe[i+1][c] = String.valueOf(occupancy[i]);		//Schreibt die aktuelle Occupancy für die Minute i in die erste Spalte des ausgabe Array
				}
				else if (c == 1) {
					ausgabe[i+1][c] = String.valueOf(gesamtLast[i]);	//Schreibt die aktuelle GesamtLast für die Minute i in die zweite Spalte des ausgabe Array
				}
				else {
					ausgabe[i+1][c] = String.valueOf(gerätAn[i][c-2]);	//Schreibt den aktuellen Verbrauch von Gerät c für die Minute i in das ausgabe Array
				}
			}
		}
	}
	
	/**
	 * Berechnet die GesamtLast jeder Minute durch die Geraete
	 * @param geräteAn
	 * @param gesamtLast
	 */
	private static void gesamt() {
		for (int i = 0; i < gerätAn.length; i++) {			//Reihe des Arrays
			for (int c = 0; c < gerätAn[0].length; c++) {	//Spalte des Arrays
				gesamtLast[i] += gerätAn[i][c];				//Die GesamtLast einer Reihe wird um den Verbrauch jedes Gerätes dieser Minute erhöht
				gesamtLast[i] = Math.round(100.0 * gesamtLast[i]) / 100.0;
			}
		}
	}
	
	/**
	 * Überschreibt das geräteAn Array mit den Verbrauchswerten der Geräte
	 * @param geräteAn
	 * @param names
	 */
	private static void geraete() {
		Kühlschrank kühl = new Kühlschrank();
		//Immer wieder neu den aktuellen Verbrauch des Geräts berechnen 
		for (int i = 0; i < names.size(); i++) {	//Spalte des Arrays
			String s = names.get(i);
			switch (s){
			case "toaster" :
				Toaster ts = new Toaster();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = ts.getStandby();	 	//Wenn das Gerät nicht aktiv genutzt wird, wird der Standbyverbrauch genommen
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * ts.randomisieren()) / 100.0;	//Ist das Gerät aktiv genutzt, berechne den aktuellen Verbrauch. Mit nur 2 Nachkommastellen
					}
				}
				break;
			case "wasserkocher" :
				Wasserkocher wk = new Wasserkocher();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = wk.getStandby();	 	
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * wk.randomisieren()) / 100.0;	
					}
				}
				break;
			case "mikrowelle" :
				Mikrowelle mw = new Mikrowelle();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = mw.getStandby();	 	
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * mw.randomisieren()) / 100.0;	
					}
				}
				break;
			case "deckenlampe" :
				DeckenLampe dl = new DeckenLampe();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = dl.getStandby();	 	
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * dl.randomisieren()) / 100.0;	
					}
				}
				break;
			case "pc":
				PC pc = new PC();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					//Hier noch den Modus vom pc setzen
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = pc.getStandby();
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * pc.randomisieren()) / 100.0;
					}
				}
				break;
			case "kaffeemaschine":
				Kaffeemaschine kf = new Kaffeemaschine();
				boolean läuft = false;
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = kf.getStandby();
						kf.setModus(0);
						läuft = false;
					}
					else if (gerätAn[c][i] == 1 && läuft == false){
						gerätAn[c][i] *= Math.round(100.0 * kf.randomisieren()) / 100.0;
						kf.setModus(kf.getModus() +1);
						läuft = true;
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * kf.randomisieren()) / 100.0;
					}
				}
				break;
			case "waschmaschine":
				Waschmaschine wm = new Waschmaschine();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = wm.getStandby();
					}
					else {
						for (int t2 = 0; t2 < wm.getModiDauer().size(); t2++) {
							for (int dauer = 0; dauer < (int) wm.getModiDauer().get(t2); dauer++) {
								gerätAn[c][i] *= Math.round(100.0 * wm.randomisieren()) / 100.0;
								c++;
							}
							wm.setModus(wm.getModus() +1);
						}
						wm.setModus(0);
					}
				}
				break;
			case "trockner":
				Trockner tr = new Trockner();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = tr.getStandby();
					}
					else {
						for (int t2 = 0; t2 < tr.getModiDauer().size(); t2++) {
							for (int dauer = 0; dauer < (int) tr.getModiDauer().get(t2); dauer++) {
								gerätAn[c][i] *= Math.round(100.0 * tr.randomisieren()) / 100.0;
								c++;
							}
							tr.setModus(tr.getModus() +1);
						}
						tr.setModus(0);
					}
				}
				break;
			case "staubsauger":
				Staubsauger ssg = new Staubsauger();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						ssg.setModusDauer(0);
						ssg.setAktuellerVerbrauch(ssg.getMaxVerbrauch());
					}
					else {
						ssg.setModusDauer(ssg.getModusDauer() +1);
						ssg.modifyÄnderWahrsch();
						ssg.setAktuellerVerbrauch();
						gerätAn[c][i] *= Math.round(100.0 * ssg.randomisieren()) / 100.0;
					}
				}
				break;
			case "lcdFernseher":
				LCDFernseher lcdF = new LCDFernseher();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						lcdF.setModusDauer(0);
						lcdF.setAktuellerVerbrauch(lcdF.getMaxVerbrauch());
					}
					else {
						lcdF.setModusDauer(lcdF.getModusDauer() +1);
						lcdF.modifyÄnderWahrsch();
						lcdF.setAktuellerVerbrauch();
						gerätAn[c][i] *= Math.round(100.0 * lcdF.randomisieren()) / 100.0;
					}
				}
				break;
			case "plasmaFernseher":
				Plasmafernseher pF = new Plasmafernseher();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						pF.setModusDauer(0);
						pF.setAktuellerVerbrauch(pF.getMaxVerbrauch());
					}
					else {
						pF.setModusDauer(pF.getModusDauer() +1);
						pF.modifyÄnderWahrsch();
						pF.setAktuellerVerbrauch();
						gerätAn[c][i] *= Math.round(100.0 * pF.randomisieren()) / 100.0;
					}
				}
				break;
			case "kühlschrank":
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					gerätAn[c][i] = Math.round(100.0 * kühl.setAktuellerVerbrauch()) / 100.0;
					kühl.setBetriebsdauer(kühl.getBetriebsdauer()+1);
				}
				break;
			case "licht":
				Licht l = new Licht();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = l.getStandby(); //hat licht einen Standby verbrauch?
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * l.randomisieren()) / 100.0;
					}
				}
				break;
			default:
				break;
			}
		}
	}
}
