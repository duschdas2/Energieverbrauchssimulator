/** 
 * Personen Klasse 
 */
package Haushalt;

public class Person {

	private int Alter;
	private Personentyp typ;
	private int[] realAwayTime;
	
	
	public Person(int Alter, Personentyp typ){
		this.Alter = Alter;
		this.typ = typ;
		realAwayTime = typ.getAwayTime();
	}
	
	public Person() {
		
	}
	
	public Personentyp getPersonentyp(){
		return typ;
	}
	
	
	public int[] getRealAwayTime(){
		return realAwayTime;
	}
	
	//gibt den Wert im Arbeitszeit Array an einem spezifischen Moment zurück
	public int getRealAwayTime(int i){
		return realAwayTime[i];
	}
	
	public void calcTime(){
		typ.calcTime();
		realAwayTime = typ.getRealAwayTime();
	}

	
	@Override
	public String toString(){
		String ausgabe = "";
		for(int i = 0; i < 1440; i++){
			ausgabe += realAwayTime[i] + "\n";
		}
		return ausgabe;
	}

}
