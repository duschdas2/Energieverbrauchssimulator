/**
 * Klasse f�r den Haushalt
 */
package Haushalt;

import java.util.ArrayList;

public class Haushalt {
	
	private ArrayList<Person> personen = new ArrayList<Person>();
	private int[] occupancy = new int[1440];

	public Haushalt(ArrayList<Person> personen){
		this.personen = personen;
		initializeOccupancy();
	}
	
	public Haushalt() {
		// TODO Auto-generated constructor stub
	}
	
	public int[] getOccupancy(){
		return occupancy;
	}
	
	
	//berechnet wie viele Personen da sind, 0 = keiner, 1 = einer und so weiter
	public void calcOccupancy(){
		for(int j = 0; j < personen.size() ; j++){
			personen.get(j).calcTime();
			for(int i = 0; i < 1440 ; i++){
				if( personen.get(j).getPercentAwayTime(i) == 1)
					occupancy[i]++;
			}
		}
	}
	
	//setzt die Werte am Anfang auf 0, so dass sie sp�ter hoch gez�hlt werden k�nnen
	public void initializeOccupancy(){
		for(int i=0; i< 1440; i++)
			occupancy[i] = 0;
	}
	
	@Override
	public String toString(){
		String ausgabe = "";
		for(int i = 0; i < 1440; i++){
			ausgabe += "Minute: " + (i+1) + "\t" + occupancy[i] + "\n";
		}
		return ausgabe;
	}

}