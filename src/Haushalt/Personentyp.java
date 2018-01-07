/**
 * Klasse mit den Typen der Pesonen
 */
package Haushalt;


public class Personentyp {

	protected int startzeit;
	protected int arbeitszeit;
	protected int[] awayTime = new int[1440]; //Wann am Tag gearbeitet werden soll
	
	public Personentyp() {
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
	

}
