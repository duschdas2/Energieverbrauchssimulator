/** 
 * Klasse für die Erstellung der Personenzeiten/Anwesendheit im Haushalt im Bezug auf das Hobby.
 * @author Jannik Schulze
 */
package Haushalt;

public class Person {

	private Personentyp typ;
	private int[] realAwayTime;
	private Hobby hobby = new Hobby();
	private boolean isAway = false;
	
	
	public Person(Personentyp typ){
		this.typ = typ;
		realAwayTime = typ.getAwayTime();
		useHobby();
	}
	
	
	//Berechnung wie lange die Person bei ihrem Hobby ist
	//dabei gibt es eine Schwankung wann sie losgehen und wann sie wiederkommen
	public void useHobby(){
		if(hobby.getChance() <= Math.random()){
			if(realAwayTime[hobby.getStartzeit()] == 1){
				double schwankungDaheim = 0.05, schwankungWeg = 0;
				int lateness = 0;
				for(int i = hobby.getStartzeit(); i <= hobby.getStartzeit() + hobby.getHobbydauer(); i++){
					if(isAway == false){
						if(schwankungDaheim <= Math.random()){
							lateness++;
							schwankungDaheim += 0.05;
						}
						else
							isAway = true;
					}
					else{
						schwankungWeg += 0.00002/(hobby.getHobbydauer() - lateness);
						if(schwankungWeg >= Math.random())
							break;
						else
							realAwayTime[i] = 0;
					}
				}
				isAway = false;
			}
		}
	}
	
	public Person() {
		
	}
	
	//erstellt eine neues array mit anderen arbeitszeiten/hobbyzeiten
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
