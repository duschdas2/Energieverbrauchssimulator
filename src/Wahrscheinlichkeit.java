import java.util.ArrayList;

import GerätePackage.Toaster;
import Haushalt.Person;

public class Wahrscheinlichkeit 
{
	int betriebsDauer = 0;
	public Wahrscheinlichkeit() {
		
	}	
	
	//Überpürft die Wahrscheinlichkeit eines bestimmten Zeitslots
	//Benutzt zusätzlich Vörgänger (momentan für Toaster zu geschnitten zum test)
	public void checkStatus(ArrayList<Person> pList, double [] statAnalysis,double[][] gerätAn,int auswertDaten,String gerät,int geräte, int timeSlot) {
		Toaster ts = new Toaster();	
		double randomNum = Math.random();
		for(int person = 0; person<pList.size();person++)	//Durchlaufe alle Personen um zu checken ob jemand zu Hause ist
		{
			if(pList.get(person).getRealAwayTime(geräte)== 1) {	//Wenn jemand zu Hause
				if(timeSlot > 0) {
					//Nach GeräteTyp filter um betriebsDauer festzustellen !
					if(gerätAn[timeSlot-1][geräte] == 1 && betriebsDauer < ts.getBetriebsdauer() && betriebsDauer >= 0) {
						ts.setOnWahrscheinlichkeit(1);
						ts.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 2) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.0001);
						ts.setOffWahrscheinlichkeit(0.9999);
						betriebsDauer = 0;
						ts.setBenutzt(false);
					}
					else if (gerätAn[timeSlot-1][geräte] == 0 && pList.size() == 1) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 390 && timeSlot <= 600) { // Benutzung zwischen 6:30 und 10 Uhr höher
								ts.setOnWahrscheinlichkeit(0.003);
								ts.setOffWahrscheinlichkeit(0.997);
						}
						else if(timeSlot >= 1200 || timeSlot <= 300) { //Benutzung nach 20 Uhr unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.0001);
							ts.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung über den Tag eher Unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.002);
							ts.setOffWahrscheinlichkeit(0.998);
						}
					}
					else if(pList.size() > 1 && gerätAn[timeSlot-1][geräte] == 0){	//mehr als 1 Person im Haushalt, Toaster evtl. 2 mal laufen lassen
						if(timeSlot >= 390 && timeSlot <= 600) {
							ts.setOnWahrscheinlichkeit(0.003*pList.size());
							ts.setOffWahrscheinlichkeit(1-(0.003*pList.size()));
						}
						else {
							ts.setOnWahrscheinlichkeit(0.0002*pList.size());
							ts.setOffWahrscheinlichkeit(1-0.0002*pList.size());
						}
					}
				}
				else if(timeSlot == 0) {
					ts.setOnWahrscheinlichkeit(0.002);
					ts.setOffWahrscheinlichkeit(0.998);
				}
			}
			else { //Keine Veränderung niemand zu Hause!
				ts.setOnWahrscheinlichkeit(0.0);
				ts.setOffWahrscheinlichkeit(0.0);
			}
		}
		//EVTL mit statistischen Daten draufrechnen um Genauigkeit zu erhöhen!!
		
		//System.out.println(ts.getOnWahrscheinlichkeit());
		//System.out.println(ts.getOffWahrscheinlichkeit());
		//System.out.println(randomNum);
		
		if(ts.getOffWahrscheinlichkeit() <= randomNum && (ts.getOnWahrscheinlichkeit()+ts.getOffWahrscheinlichkeit() != 0)) {
			betriebsDauer++;
			ts.setBenutzt(true);
			gerätAn[timeSlot][geräte] = 1;
		}		
	}
}
