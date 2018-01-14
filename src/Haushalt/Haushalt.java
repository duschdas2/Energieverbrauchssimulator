/**
 * Klasse für den Haushalt
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
	//erstellt jedes mal ein array mit anderen werten für jede person und gesamt
	public void calcOccupancy(){
		for(int j = 0; j < personen.size() ; j++){
			personen.get(j).initializeAwayTime();
			for(int i = 0; i < 1440 ; i++){
				if( personen.get(j).getRealAwayTime(i) == 1)
					occupancy[i]++;
			}
		}
	}
	
	//setzt die Werte am Anfang auf 0, so dass sie später hoch gezählt werden können
	public void initializeOccupancy(){
		for(int i=0; i< 1440; i++)
			occupancy[i] = 0;
	}
	
	@Override
	public String toString(){
		String ausgabe = "";
		for(int i = 0; i < 1440; i++){
			ausgabe += "Minute: " + (i+1) + "\t" + occupancy[i] + "\t";
			for(int j = 0; j < personen.size(); j++){
				ausgabe += personen.get(j).getRealAwayTime(i) + "\t";
			}
			ausgabe += "\n";
		}
		return ausgabe;
	}

}
