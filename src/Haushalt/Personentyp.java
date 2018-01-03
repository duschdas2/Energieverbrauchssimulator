/**
 * Klasse mit den Typen der Pesonen
 */
package Haushalt;

import java.util.Random;

public class Personentyp {

	protected int[] awayTime = new int[1440]; //Wann am Tag gearbeitet werden soll
	protected int[] realAwayTime = new int[1440]; //Wann wirklich gearbeitet wird
	private Random arbeitsFaktor = new Random();
	
	public Personentyp() {
		initializeAwayTime();
		realAwayTime = awayTime;
	}
	
	
	public int[] getAwayTime(){
		return awayTime;
	}
	
	public int[] getRealAwayTime(){
		return realAwayTime;
	}
	
	public int getRealAwayTime(int i){
		return realAwayTime[i];
	}
	
	
	/*fügt die arbeitszeiten in das array rein und berechnet daran die chance , dass die person daheim ist
	 *  es müssen noch richtige werte hinzugefügt werden
	 *  1 = daheim, 0 = nicht daheim
	*/
	public void initializeAwayTime(){
		for(int i = 0; i< awayTime.length; i++){
			awayTime[i] = 1;
		}
	}
	
	//berechnet wannn der Personentyp daheim ist, 1 = daheim, 0 = nicht mit Wahrscheinlichkeit anhand ihrer Arbeitszeit
	//muss noch stark überarbeitet werden
	public void calcTime(){
		for(int i = 0; i< awayTime.length; i++){
			if(awayTime[i] == 0){
				if(arbeitsFaktor.nextInt(6)+1 > 5) // 5/7 chance weil 5 Tage die woche arbeitet
					realAwayTime[i] = 1;
			}
		}
	}

}
