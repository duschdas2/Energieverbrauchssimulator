package Wahrscheinlichkeiten;

import java.util.ArrayList;

import GerätePackage.LCDFernseher;
import GerätePackage.Mikrowelle;
import GerätePackage.Plasmafernseher;
import Haushalt.Person;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit der Fernsehr.
 * @author Julian Grünker
 */	
public class WahrFernseher {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	private int rndm = Math.random() < 0.5 ? -1 : 1;
	private double maxZeit = Math.floor((Math.random() * 250) + 15);
	private boolean hatKind = false;
	private int anzKind = 0;
	
	public void sucheKind(ArrayList<Person> personen) {
		for(int i = 0;i<personen.size();i++) {
			if(personen.get(0).getPersonentyp().getTyp() == "Kind") {
				hatKind = true;
				anzKind++;
				tmp = tmp+2;
				maxZeit = maxZeit+4*anzKind;
			}
		}
	}
	public void getWahrFernseher(int [] occupancy, double [][] statAnalysis,double[][] gerätAn,int aktGerät, int timeSlot,ArrayList<Person> personen,boolean statData, String type) {
		if(type == "LCD") {
			LCDFernseher lF = new LCDFernseher();
			if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
				if(timeSlot > 0) {	//Falls nicht erster Eintrag
					if(anzahlAn < tmp) {
						if(gerätAn[timeSlot-1][aktGerät] == 0) {
							maxZeit = Math.floor((Math.random() * 250) + 15);
						}
						if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < maxZeit) {
							lF.setOnWahrscheinlichkeit(1);
							lF.setOffWahrscheinlichkeit(0);
						}
						else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Fernseher gerade nicht benutzt
							if(hatKind == true) {
								if(timeSlot >= 420 && timeSlot <= 1200) { // Benutzung zwischen 17 und 24 Uhr höher
									lF.setOnWahrscheinlichkeit(0.005*occupancy[timeSlot]);
									lF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
								}
							}
							if(timeSlot >= 1020 && timeSlot <= 1440) { // Benutzung zwischen 17 und 24 Uhr höher
									lF.setOnWahrscheinlichkeit(0.003*occupancy[timeSlot]);
									lF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
							}
							else if(timeSlot >= 1441 || timeSlot <= 420) { //Benutzung nach 24 Uhr unwarscheinlich
								lF.setOnWahrscheinlichkeit(0.00005);
								lF.setOffWahrscheinlichkeit(0.99995);
							}
							else { //Benutzung über den Tag eher Unwarscheinlich
								lF.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
								lF.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
							}
						}
						else if(betriebsDauer == maxZeit) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
							lF.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
							lF.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
						}
					}
				}
				else if(timeSlot == 0 && anzahlAn < tmp) {
					lF.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
					lF.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
				}
			}
			else { //Keine Veränderung niemand zu Hause!
				if(anzahlAn < tmp) {
					lF.setOnWahrscheinlichkeit(0.0);
					lF.setOffWahrscheinlichkeit(0.0);
				}
			}
			if(personen.contains("Kind")) {
				for(int i = 0;i<personen.size();i++) {
					lF.setOnWahrscheinlichkeit(lF.getOnWahrscheinlichkeit()+0.01*anzKind);
					lF.setOffWahrscheinlichkeit(1-lF.getOffWahrscheinlichkeit()+0.01*anzKind);
				}
			}
			
			//if(statAnalysis[timeSlot][aktGerät] >= 1 && occupancy[timeSlot] != 0 && statData == true) { Falsche ECO Daten ?
			//	if(anzahlAn < tmp) {
			//		lF.setOnWahrscheinlichkeit(lF.getOnWahrscheinlichkeit()+0.01);
			//		lF.setOffWahrscheinlichkeit(1-lF.getOffWahrscheinlichkeit()+0.01);
			//	}
			//}
			
			if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < maxZeit && occupancy[timeSlot] != 0) {
				gerätAn[timeSlot][aktGerät] = 1;
				betriebsDauer++;
				//System.out.println("TimeSlot: " + timeSlot);
			}
			else if(lF.getOnWahrscheinlichkeit() >=  Math.random() && (lF.getOnWahrscheinlichkeit()+lF.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
				gerätAn[timeSlot][aktGerät] = 1;
				betriebsDauer = 0;
				anzahlAn++;
			}		
		}
		else if(type == "Plasma") {
			Plasmafernseher pF = new Plasmafernseher();
			if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
				if(timeSlot > 0) {	//Falls nicht erster Eintrag
					if(anzahlAn < tmp) {
						if(gerätAn[timeSlot-1][aktGerät] == 0) {
							maxZeit = Math.floor((Math.random() * 250) + 15);
						}
						if(gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < maxZeit) {
							pF.setOnWahrscheinlichkeit(1);
							pF.setOffWahrscheinlichkeit(0);
						}
						else if (gerätAn[timeSlot-1][aktGerät] == 0) {	//Wenn Fernseher gerade nicht benutzt
							if(hatKind == true) {
								if(timeSlot >= 420 && timeSlot <= 1200) { // Benutzung zwischen 17 und 24 Uhr höher
									pF.setOnWahrscheinlichkeit(0.005*occupancy[timeSlot]);
									pF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
								}
							}
							if(timeSlot >= 1020 && timeSlot <= 1440) { // Benutzung zwischen 17 und 24 Uhr höher
								pF.setOnWahrscheinlichkeit(0.003*occupancy[timeSlot]);
								pF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
							}
							else if(timeSlot >= 1441 || timeSlot <= 420) { //Benutzung nach 24 Uhr unwarscheinlich
								pF.setOnWahrscheinlichkeit(0.00005);
								pF.setOffWahrscheinlichkeit(0.99995);
							}
							else { //Benutzung über den Tag eher Unwarscheinlich
								pF.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
								pF.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
							}
						}
						else if(betriebsDauer == maxZeit) { //Gerät war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
							pF.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
							pF.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
						}
					}
				}
				else if(timeSlot == 0 && anzahlAn < tmp) {
					pF.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
					pF.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
				}
			}
			else { //Keine Veränderung niemand zu Hause!
				if(anzahlAn < tmp) {
					pF.setOnWahrscheinlichkeit(0.0);
					pF.setOffWahrscheinlichkeit(0.0);
				}
			}
			if(personen.contains("Kind"))
			{
				for(int i = 0;i<personen.size();i++) {
					pF.setOnWahrscheinlichkeit(pF.getOnWahrscheinlichkeit()+0.01*anzKind);
					pF.setOffWahrscheinlichkeit(1-pF.getOffWahrscheinlichkeit()+0.01*anzKind);
				}
			}
			
			//if(statAnalysis[timeSlot][aktGerät] >= 1 && occupancy[timeSlot] != 0 && statData == true) { Falsche ECO Daten ?
			//	if(anzahlAn < tmp) {
			//		lF.setOnWahrscheinlichkeit(lF.getOnWahrscheinlichkeit()+0.01);
			//		lF.setOffWahrscheinlichkeit(1-lF.getOffWahrscheinlichkeit()+0.01);
			//	}
			//}
			
			if(timeSlot > 0 && gerätAn[timeSlot-1][aktGerät] == 1 && betriebsDauer < maxZeit && occupancy[timeSlot] != 0) {
				gerätAn[timeSlot][aktGerät] = 1;
				betriebsDauer++;
				//System.out.println("TimeSlot: " + timeSlot);
			}
			else if(pF.getOnWahrscheinlichkeit() >=  Math.random() && (pF.getOnWahrscheinlichkeit()+pF.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
				gerätAn[timeSlot][aktGerät] = 1;
				betriebsDauer = 0;
				anzahlAn++;
			}		
		}
	}

	public int getRndm() {
		return rndm;
	}

	public void setRndm(int rndm) {
		this.rndm = rndm;
	}

	public double getMaxZeit() {
		return maxZeit;
	}

	public void setMaxZeit(double maxZeit) {
		this.maxZeit = maxZeit;
	}
}
