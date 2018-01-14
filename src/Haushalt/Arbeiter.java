package Haushalt;

public class Arbeiter extends Personentyp {

	public Arbeiter() {
		if(0.11 <= Math.random())
			arbeitszeit = 492; //Arbeitszeit nach destatis 2016, 41 stunden woche aufgeteilt auf 5 arbeitstage
		else
			arbeitszeit = 576; //Nach destatis, 11% aller Arbeiter arbeiten 48 stunden in der woche
		startzeit = 420;
		initializeAwayTime();
	}
	

	


}
