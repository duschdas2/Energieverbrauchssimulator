package GerätePackage;

public abstract class GeräteTyp3 
{
	protected double maxVerbrauch;
	protected double minVerbrauch;
	protected double standby;
	protected double aktuellerVerbrauch;
	protected double schwankung;
	protected int betriebsdauer = 0;
	protected double onWahrscheinlichkeit;
	protected double offWahrscheinlichkeit;
	protected double änderungsWahrscheinlichkeit;		//Wahrscheinlichkeit, dass sich der Stromverbrauch ändert
	protected final double constÄnderWahrsch;			//kann und soll nicht geändert werden
	protected int modusDauer = 0;						//Dauer mit gleichbleibendem Verbrauch (ohne Berücksichtigung von Schwankungen)
	protected boolean benutzt = false;
	
	public GeräteTyp3(double Max_Verbrauch, double Min_Verbrauch, double Standby, double Schwankung, double Änderungs_Wahrscheinlichkeit, double Const_Änder_Wahrsch){
		this.maxVerbrauch = Max_Verbrauch;
		this.minVerbrauch = Min_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
		this.änderungsWahrscheinlichkeit = Änderungs_Wahrscheinlichkeit;
		this.constÄnderWahrsch = Const_Änder_Wahrsch;
	}
	
	public double randomisieren(){
		
		double tmp = this.aktuellerVerbrauch; //tmp damit aktuellerVerbrauch hier nicht verändert wird
		if(Math.random() < 0.5){
			tmp += Math.random() * this.schwankung;
		}else{
			tmp -= Math.random() * this.schwankung;
		}
		return(tmp);
	}
	
	public double setAktuellerVerbrauch(){
		if(Math.random() < this.änderungsWahrscheinlichkeit){
			this.aktuellerVerbrauch = Math.random() * (this.maxVerbrauch - this.minVerbrauch) + this.minVerbrauch;
			this.änderungsWahrscheinlichkeit = this.constÄnderWahrsch;
			this.modusDauer = 0;
		}
		return(this.aktuellerVerbrauch);
	}
	
	public double getStandby() {
		return standby;
	}

	public void setStandby(double standby) {
		this.standby = standby;
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
	
	public double getÄnderungsWahrscheinlichkeit() {
		return änderungsWahrscheinlichkeit;
	}

	public void setÄnderungsWahrscheinlichkeit(double änderungsWahrscheinlichkeit) {
		this.änderungsWahrscheinlichkeit = änderungsWahrscheinlichkeit;
	}
	
	public int getModusDauer() {
		return modusDauer;
	}

	public void setModusDauer(int modusDauer) {
		this.modusDauer = modusDauer;
	}
	
	public void setBenutzt(boolean Benutzt) {
		this.benutzt = Benutzt;
	}
	
	public boolean getBenutzt() {
		return benutzt;
	}
}
