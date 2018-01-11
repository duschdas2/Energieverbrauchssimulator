import GerätePackage.Toaster;

public class Wahrscheinlichkeit_Typ1 
{
	int betriebsDauer = 0;
	public Wahrscheinlichkeit_Typ1() {
		
	}	
	//STAUBSAUGER benutzen!!
	//Überpürft die Wahrscheinlichkeit eines bestimmten Zeitslots
	//Benutzt zusätzlich Vörgänger
	public void getWahrschToaster(int [] occupancy, double [] statAnalysis,double[][] gerätAn,int auswertDaten,String gerät,int geräte, int timeSlot) {
		Toaster ts = new Toaster();	
		//Jahreszeit für (Licht,Computer,Fernseher)
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(gerätAn[timeSlot-1][geräte] == 1 && betriebsDauer < ts.getBetriebsdauer() && betriebsDauer >= 0) {
					ts.setOnWahrscheinlichkeit(1);
					ts.setOffWahrscheinlichkeit(0);
				}
				else if(betriebsDauer == 2) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
					ts.setOnWahrscheinlichkeit(0.0005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
					ts.setOffWahrscheinlichkeit(0.99995*occupancy[timeSlot]);
					betriebsDauer = 0;
					ts.setBenutzt(false);
				}
				else if (gerätAn[timeSlot-1][geräte] == 0) {	//Wenn Toaster gerade nicht benutzt
					if(timeSlot >= 390 && timeSlot <= 600) { // Benutzung zwischen 6:30 und 10 Uhr höher
							ts.setOnWahrscheinlichkeit(0.005*occupancy[timeSlot]);
							ts.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
					}
					else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
						ts.setOnWahrscheinlichkeit(0.0001);
						ts.setOffWahrscheinlichkeit(0.9999);
					}
					else { //Benutzung über den Tag eher Unwarscheinlich
						ts.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
						ts.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
					}
				}
			}
			else if(timeSlot == 0) {
				ts.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				ts.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Veränderung niemand zu Hause!
			ts.setOnWahrscheinlichkeit(0.0);
			ts.setOffWahrscheinlichkeit(0.0);
		}
		//EVTL mit statistischen Daten draufrechnen um Genauigkeit zu erhöhen!!
		
		if(ts.getOnWahrscheinlichkeit() >=  Math.random() && (ts.getOnWahrscheinlichkeit()+ts.getOffWahrscheinlichkeit() != 0)) {
			betriebsDauer++;
			ts.setBenutzt(true);
			gerätAn[timeSlot][geräte] = 1;
		}		
	}
}
