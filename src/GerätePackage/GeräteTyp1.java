package GerätePackage;

/**
 * @author Kevin Rabe
 *
 */
public abstract class GeräteTyp1 
{
	protected double maxVerbrauch;	
	protected double standby;
	protected double schwankung;
	protected double aktuellerVerbrauch;
	protected  int betriebsdauer = 0;
	protected double onWahrscheinlichkeit;
	protected double offWahrscheinlichkeit;
	protected  boolean benutzt = false;
	
	public GeräteTyp1(double Max_Verbrauch, double Standby, double Schwankung){
		this.maxVerbrauch = Max_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
	}
	
	//Im Fall von Geräte vom Typ 1 ist maxVerbrauch == aktuellerVerbrauch
	public double randomisieren(){
		
		double tmp = this.maxVerbrauch;	//tmp damit aktuellerVerbrauch hier nicht verändert wird
		if(Math.random() < 0.5){
			tmp += Math.random() * this.schwankung;
		}else{
			tmp -= Math.random() * this.schwankung;
		}
		return(tmp);
	}
	
	public double getStandby() {
		return standby;
	}

	public void setStandby(double standby) {
		this.standby = standby;
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
	
	public void setOnWahrscheinlichkeit(double On_Wahrscheinlichkeit){
		this.onWahrscheinlichkeit = On_Wahrscheinlichkeit;		
	}

	public int getBetriebsdauer() {
		return betriebsdauer;
	}

	public double getOnWahrscheinlichkeit() {
		return onWahrscheinlichkeit;
	}

	public double getOffWahrscheinlichkeit() {
		return offWahrscheinlichkeit;
	}

	public boolean getBenutzt() {
		return benutzt;
	}
}
