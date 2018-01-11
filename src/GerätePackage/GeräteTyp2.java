package GerätePackage;

import java.util.ArrayList;
import java.util.List;

public abstract class GeräteTyp2 
{
	double maxVerbrauch;
	double minVerbrauch;
	double startVerbrauch;	//durchschnitt von den ersten sekunden des verbrauchs (oder erste minute)
	double endVerbrauch;	//durchschnitt von den letzten sekunden des Verbrauchs	(oder letzte minute)
	double standby;
	double aktuellerVerbrauch;
	double schwankung;
	double schwankungsTendenz;
	int betriebsdauer = 0;
	double onWahrscheinlichkeit;
	double offWahrscheinlichkeit;
	boolean benutzt = false;
	int modus = 0;	//beginnt mit 0
	
	//Werte mit den gleichen Indizes der folgenden Listen sind Paare!
	List verbrauchsWerte = new ArrayList<Double>();
	List schwankungsWerte = new ArrayList<Double>();	
		
	public GeräteTyp2(double Max_Verbrauch, double Min_Verbrauch, double Start_Verbrauch, double End_Verbrauch, double Standby, double Schwankung, double Schwankungs_Tendenz){
		
		this.maxVerbrauch = Max_Verbrauch;
		this.minVerbrauch = Min_Verbrauch;
		this.startVerbrauch = Start_Verbrauch;
		this.endVerbrauch = End_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
		this.schwankungsTendenz = Schwankungs_Tendenz;
	}
	
	public double randomisieren(){
		
		this.aktuellerVerbrauch = (double) this.verbrauchsWerte.get(modus);
		this.schwankung = (double) this.schwankungsWerte.get(modus);
		
		double tmp = this.aktuellerVerbrauch;	//tmp damit aktuellerVerbrauch hier nicht verändert wird
		if(Math.random() < this.schwankungsTendenz) {
			tmp += Math.random() * this.schwankung;
		}else{
			tmp -= Math.random() * this.schwankung;
		}
		return(tmp);
	}
	
	public void setModus(int Modus){
		this.modus = Modus;
	}
	
	public void setBenutzt(boolean Benutzt) {
		this.benutzt = Benutzt;
	}
	
	public boolean getBenutzt() {
		return benutzt;
	}
	
	public void setBetriebsdauer(int Betriebs_Dauer){
		this.betriebsdauer = Betriebs_Dauer;
	}
	
	public void setOffWahrscheinlichkeit(double Off_Wahrscheinlichkeit){
		this.offWahrscheinlichkeit = Off_Wahrscheinlichkeit;		
	}
	
	public void setOnWahrscheinlichkeit(double On_Wahrscheinlichkeit){
		this.onWahrscheinlichkeit = On_Wahrscheinlichkeit;		
	}
	public double getStartVerbrauch() {
		return startVerbrauch;
	}

	public void setStartVerbrauch(double startVerbrauch) {
		this.startVerbrauch = startVerbrauch;
	}

	public double getEndVerbrauch() {
		return endVerbrauch;
	}

	public void setEndVerbrauch(double endVerbrauch) {
		this.endVerbrauch = endVerbrauch;
	}
}
