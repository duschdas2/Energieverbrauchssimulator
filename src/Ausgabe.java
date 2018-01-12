import java.io.IOException;
import java.util.ArrayList;

import GerätePackage.*;

public class Ausgabe {
	
	private static String [][] ausgabe;
	private static double [] gesamtLast = new double [1440];
	private static int [] occupancy;
	private static double [][] gerätAn;
	static ArrayList <String> names;
	
	public static void main(String [] args) {
		
	}
		
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
			for (int c = 0; c < ausgabe[0].length; c++) {			//Spalte
				if (c == 0) {
					ausgabe[i+1][c] = String.valueOf(occupancy[i]);	//Schreibt die aktuelle Occupancy für die Minute i in die erste Spalte des ausgabe Array
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
				gesamtLast[i] += gerätAn[i][c];			//Die GesamtLast einer Reihe wird um den Verbrauch jedes Gerätes dieser Minute erhöht
			}
		}
	}
	
	/**
	 * Überschreibt das geräteAn Array mit den Verbrauchswerten der Geräte
	 * @param geräteAn
	 * @param names
	 */
	private static void geraete() {
		//Immer wieder neu den aktuellen Verbrauch des Geräts berechnen 
		for (int i = 0; i < names.size(); i++) {	//Spalte des Arrays
			String s = names.get(i);
			for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
				switch (s){
				case "Toaster" :
					Toaster ts = new Toaster();
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = ts.getStandby();	 	//Wenn das Gerät nicht aktiv genutzt wird, wird der Standbyverbrauch genommen
					}
					else {
						gerätAn[c][i] *= Math.round(100.0 * ts.randomisieren()) / 100.0;	//Ist das Gerät aktiv genutzt, berechne den aktuellen Verbrauch. Mit nur 2 Nachkommastellen
					}
					break;
				case "PC":
					PC pc = new PC();
					//Hier noch den Modus vom pc setzen
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = pc.getStandby();
					}
					else {
						gerätAn[c][i] *= pc.randomisieren();
					}
					break;
				case "Staubsauger":
					Staubsauger ssg = new Staubsauger();
					//woher soll ich den aktuellen verbrauch bekommen?
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = ssg.getStandby(); //macht das hier Sinn, weil der Staubsauger nur im Gebrauch angeschlossen ist
					}
					else {
						gerätAn[c][i] *= ssg.randomisieren();
					}
				case "Kühlschrank":
					//Kühlschrakn erstellen
					break;
				case "Licht":
					Licht l = new Licht();
					if (gerätAn[c][i] == 0) {
						gerätAn[c][i] = l.getStandby(); //hat licht einen Standby verbrauch?
					}
					else {
						gerätAn[c][i] *= l.randomisieren();
					}
					break;
				default:
					break;
			}
			}
		}
	}
}
