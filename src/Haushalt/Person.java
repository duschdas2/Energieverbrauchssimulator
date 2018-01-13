/** 
 * Personen Klasse 
 */
package Haushalt;

public class Person {

	private Personentyp typ;
	private int[] realAwayTime;
	private Hobby hobby = new Hobby();
	
	
	public Person(Personentyp typ){
		this.typ = typ;
		realAwayTime = typ.getAwayTime();
		useHobby();
	}
	
	public void useHobby(){
		if(hobby.getChance() <= Math.random()){
			if(realAwayTime[hobby.getStartzeit()] == 1){
				for(int i = hobby.getStartzeit(); i <= hobby.getStartzeit() + hobby.getHobbydauer(); i++){
						realAwayTime[i] = 0;
				}
			}
		}
	}
	
	public Person() {
		
	}
	
	public void initializeAwayTime(){
		typ.initializeAwayTime();
		realAwayTime = typ.getAwayTime();
		useHobby();
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
	
	public Hobby getHobby(){
		return hobby;
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
