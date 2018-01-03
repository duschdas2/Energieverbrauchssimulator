/** 
 * Personen Klasse 
 */
package Haushalt;

import java.util.Random;

public class Person {

	private int Alter;
	private Personentyp typ;
	private int[] awayTime;
	private int[] percentAwayTime = new int[1440];
	private Random arbeitsFaktor = new Random();
	
	
	public Person(int Alter, Personentyp typ){
		this.Alter = Alter;
		this.typ = typ;
		awayTime = typ.getAwayTime();
	}
	
	public Person() {
		
	}
	
	public int[] getAwayTime(){
		return awayTime;
	}
	
	public int[] getPercentAwayTime(){
		return percentAwayTime;
	}
	
	//gibt den Wert im Arbeitszeit Array an einem spezifischen Moment zurück
	public int getPercentAwayTime(int i){
		return percentAwayTime[i];
	}
	
	
	//berechnet wannn die Person daheim ist, 1 = daheim, 0 = nicht mit Wahrscheinlichkeit anhand ihrer Arbeitszeit
	public void calcTime(){
		for(int i = 0; i< awayTime.length; i++){
			if(awayTime[i] == 1 && (arbeitsFaktor.nextInt(6)+1) <= 5) // 5/7 chance weil 5 Tage die woche arbeitet
				percentAwayTime[i] = 1;
		}
	}
	
	@Override
	public String toString(){
		String ausgabe = "";
		for(int i = 0; i < 1440; i++){
			ausgabe += percentAwayTime[i] + "\n";
		}
		return ausgabe;
	}

}
