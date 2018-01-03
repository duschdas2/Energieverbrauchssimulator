package GerätePackage;

import java.util.ArrayList;
import java.util.List;

public abstract class GeräteTyp2 
{
	double maxVerbrauch;
	double minVerbrauch;
	double standby;
	double aktuellerVerbrauch;
	double schwankung;
	double schwankungsTendenz;
	int betriebsdauer = 0;
	double onWahrscheinlichkeit;
	double offWahrscheinlichkeit;
	boolean benutzt = false;
	int modus;	//beginnt mit 0
	
	//Werte mit den gleichen Indizes der folgenden Listen sind Paare!
	List verbrauchsWerte = new ArrayList<Double>();
	List schwankungsWerte = new ArrayList<Double>();	
		
	public GeräteTyp2(double Max_Verbrauch, double Min_Verbrauch, double Standby, double Schwankung, double Schwankungs_Tendenz){
		
		this.maxVerbrauch = Max_Verbrauch;
		this.minVerbrauch = Min_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
		this.schwankungsTendenz = Schwankungs_Tendenz;
	}
	
	protected double randomisieren(){
		
		this.aktuellerVerbrauch = (double) this.verbrauchsWerte.get(modus);
		this.schwankung = (double) this.schwankungsWerte.get(modus);
		
		if(Math.random() < this.schwankungsTendenz) {	//toDo: Glockenkurve
			aktuellerVerbrauch += Math.random() * schwankung;
		}else{
			aktuellerVerbrauch -= Math.random() * schwankung;
		}
		return(aktuellerVerbrauch);
	}
	
	protected void setModus(int Modus){
		this.modus = Modus;
	}
	
	public void setBenutzt(boolean Benutzt) {
		this.benutzt = Benutzt;
	}
	
	public void setBetriebsdauer(int Betriebs_Dauer){
		this.betriebsdauer = Betriebs_Dauer;
	}
	
	public void setOffWahrscheinlichkeit(double Off_Wahrscheinlichkeit){
		this.offWahrscheinlichkeit = Off_Wahrscheinlichkeit;		
	}
	
	public void setOnWahrscheinlichkeit(double Off_Wahrscheinlichkeit){
		this.onWahrscheinlichkeit = Off_Wahrscheinlichkeit;		
	}
}
