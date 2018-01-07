/** 
 * Personen Klasse 
 */
package Haushalt;

public class Person {

	private Personentyp typ;
	private int[] realAwayTime;
	
	
	public Person(Personentyp typ){
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
	
	
	@Override
	public String toString(){
		String ausgabe = "";
		for(int i = 0; i < 1440; i++){
			ausgabe += "Minute: " + (i+1) + "\t" + realAwayTime[i] + "\n";
		}
		return ausgabe;
	}

}
