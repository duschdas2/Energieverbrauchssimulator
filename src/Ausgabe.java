import GerätePackage.*;

public class Ausgabe {
	
	public static void main(String [] args) {
		
	}
	
	/**
	 * Erstellt das Array für die CSV-Datei
	 * @param occupancy
	 * @param geräteAn
	 * @param names
	 */
	public static void erstelleArr(int [] occupancy, double [][] geräteAn, String [] names) {
		String [][] ausgabe = new String [1441][geräteAn[0].length +2];
		ausgabe[0][0] = "Occupancy";
		ausgabe[0][1] = "GesamtLast";
		for (int i = 0; i < names.length; i++) {	//Schreibt die Namen der Geräte in die erste Reihe des Arrays
			ausgabe[0][i+2] =  names[i];
		}
		geräte(geräteAn, names);
	}
	
	/**
	 * Überschreibt das geräteAn Array mit den Verbrauchswerten der Geräte
	 * @param geräteAn
	 * @param names
	 */
	private static void geräte(double [][] geräteAn, String [] names) {
		//Immer wieder neu den aktuellen Verbrauch des Geräts berechnen 
		for (int i = 0; i < names.length; i++) {	//Spalte des Arrays
			String s = names[i];
			for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
				switch (s){
				case "Toaster" :
					Toaster ts = new Toaster();
					if (geräteAn[c][i] == 0) {
						geräteAn[c][i] = ts.getStandby();	 	//Wenn das Gerät nicht aktiv genutzt wird, wird der Standbyverbrauch genommen
					}
					else {
						geräteAn[c][i] *= ts.randomisieren();	//Ist das Gerät aktiv genutzt, berechne den aktuellen Verbrauch
					}
					break;
				case "PC":
					PC pc = new PC();
					//Hier noch den Modus vom pc setzen
					if (geräteAn[c][i] == 0) {
						geräteAn[c][i] = pc.getStandby();
					}
					else {
						geräteAn[c][i] *= pc.randomisieren();
					}
					break;
				case "Staubsauger":
					Staubsauger ssg = new Staubsauger();
					//woher soll ich den aktuellen verbrauch bekommen?
					if (geräteAn[c][i] == 0) {
						geräteAn[c][i] = ssg.getStandby(); //macht das hier Sinn, weil der Staubsauger nur im Gebrauch angeschlossen ist
					}
					else {
						geräteAn[c][i] *= ssg.randomisieren(Aktueller_Verbrauch, Schwankung);
					}
				case "Kühlschrank":
					//Kühlschrakn erstellen
					break;
				case "Licht":
					Licht l = new Licht();
					if (geräteAn[c][i] == 0) {
						geräteAn[c][i] = l.getStandby(); //hat licht einen Standby verbrauch?
					}
					else {
						geräteAn[c][i] *= l.randomisieren(Aktueller_Verbrauch, Schwankung);
					}
					break;
				default:
					break;
			}
			}
		}
	}
}
