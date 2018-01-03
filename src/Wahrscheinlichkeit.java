
public class Wahrscheinlichkeit 
{
	int AktWahrscheinlichkeit = 0;
	int x = 90; //Gerätelauf dauer muss von Gerät importiert werden
	
	public Wahrscheinlichkeit() {
		
	}	
	
	//Überpürft die Wahrscheinlichkeit eines bestimmten Zeitslots
	//Benutzt zusätzlich Nachbarn
	public void checkStatus(double [] statAnalysis,double[][] gerätAn,int auswertDaten,int geräte, int timeSlot) {
		for(int i = 0;i<geräte;i++) {
			if(timeSlot > 0) //Wenn nicht erstes Elelemnt
			{
				if(gerätAn[timeSlot-1][i] == 1) {	//Falls Gerät in vorherigen Element an war
					//Benötigt check auf GeräteTyp und Gerät
					//Variable je nach dem wie lange das Gerät an
					AktWahrscheinlichkeit = +75;
				}
				else if(gerätAn[timeSlot-1][i] == 0) { //Falls Gerät nicht im vorherigen Element an war
					if(statAnalysis[timeSlot-1] >= (auswertDaten/2)) { //Falls Gerät in statistischen Daten an war
						AktWahrscheinlichkeit = +75;
					}
					else {
						AktWahrscheinlichkeit = +25;
					}
				}
			}
			if(AktWahrscheinlichkeit >= 70) {
				if(Math.random()*x >= 70) {
					gerätAn[timeSlot][i] = 1;
				}
			}
		}
	}
}
