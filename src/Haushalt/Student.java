package Haushalt;

public class Student extends Personentyp {

	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public void initializeAwayTime(){
		for(int i = 0; i< awayTime.length; i++){
			awayTime[i] = 1;
		}
		for(int i = awayTime.length/4; i < 900; i++){
			awayTime[i] = 0;
		}
	}


}
