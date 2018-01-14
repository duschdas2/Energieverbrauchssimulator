/**
 * Klasse mit den Typen der Pesonen
 */
package Haushalt;


public class Personentyp {

	private int startzeit;
	private int arbeitszeit;
	private int[] awayTime = new int[1440]; //Wann am Tag gearbeitet werden soll
	private String typ;
	
	public Personentyp(String typ) {
		this.typ = typ;
		chooseTyp(typ);
		initializeAwayTime();
	}
	
	public int getStartzeit(){
		return startzeit;
	}
	
	public int[] getAwayTime(){
		return awayTime;
	}
	
	
	public int getAwayTime(int i){
		return awayTime[i];
	}
	
	
	/*fügt die arbeitszeiten in das array rein und berechnet daran die chance , dass die person daheim ist
	 *  es müssen noch richtige werte hinzugefügt werden
	 *  1 = daheim, 0 = nicht daheim
	*/
	public void initializeAwayTime(){
		double schwankung = 0;
		for(int i = 0; i< awayTime.length; i++){
			awayTime[i] = 1;
		}
		/* Anpassung des Arrays mit der Arbeitszeit, in der die Person nicht Zuhause ist
		 * Inklusive einer "Schwankung", da man nicht immer genau gleich nach Hause kommt durch Verkehr z.B.
		 * Schwankung zählt immer höher, bis sie maximal eine 0.5% chance wird, dass die Person jetzt daheim ist, anstatt
		 * die Schleife zu Ende zu laufen
		 */
		for(int i = startzeit; i <= arbeitszeit + startzeit; i++){
			schwankung += 0.005/arbeitszeit;
			if(schwankung >= Math.random())
				break;
			else
				awayTime[i] = 0;
		}
		
	}
	
	public void chooseTyp(String typ){
		switch(typ){
		case "Arbeiter":if(0.11 <= Math.random())
							arbeitszeit = 492; //Arbeitszeit nach destatis 2016, 41 stunden woche aufgeteilt auf 5 arbeitstage
						else
							arbeitszeit = 576; //Nach destatis, 11% aller Arbeiter arbeiten 48 stunden in der woche
						startzeit = 420;
						break;
		case "Kind":	arbeitszeit = 462; //Schulzeit pro Woche laut Unicef Umfrage
						startzeit = 420;
						break;
		case "Student":	arbeitszeit = 411; //Studienzeit pro woche laut spiegel
						startzeit = 600;
						break;
		default:		System.out.println("Error: Falsche Typeneingabe");
						break;
		}
	}
	

}
