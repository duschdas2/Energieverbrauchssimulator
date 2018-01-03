package Haushalt;

public class Kind extends Personentyp {

	public Kind() {
		// TODO Auto-generated constructor stub
	}
	
	public void initializeAwayTime(){
		for(int i = 0; i< awayTime.length; i++){
			awayTime[i] = 1;
		}
		for(int i = awayTime.length/4; i < 750; i++){
			awayTime[i] = 0;
		}
	}

}
