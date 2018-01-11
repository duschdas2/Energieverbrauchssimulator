package GerätePackage;

public abstract class GeräteTyp3 
{
	double maxVerbrauch;
	double minVerbrauch;
	double standby;
	double aktuellerVerbrauch;
	double schwankung;
	int betriebsdauer = 0;
	double onWahrscheinlichkeit;
	double offWahrscheinlichkeit;
	double änderungsWahrscheinlichkeit;		//Wahrscheinlichkeit, dass sich der Stromverbrauch ändert
	final double constÄnderWahrsch;			//kann und soll nicht geändert werden
	int modusDauer = 0;						//Dauer mit gleichbleibendem Verbrauch (ohne Berücksichtigung von Schwankungen)
	boolean benutzt = false;
	
	public GeräteTyp3(double Max_Verbrauch, double Min_Verbrauch, double Standby, double Schwankung, double Änderungs_Wahrscheinlichkeit, double Const_Änder_Wahrsch){
		this.maxVerbrauch = Max_Verbrauch;
		this.minVerbrauch = Min_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
		this.änderungsWahrscheinlichkeit = Änderungs_Wahrscheinlichkeit;
		this.constÄnderWahrsch = Const_Änder_Wahrsch;
	}
	
	public double randomisieren(double Aktueller_Verbrauch, double Schwankung){
		this.aktuellerVerbrauch = Aktueller_Verbrauch;
		
		double tmp = this.aktuellerVerbrauch; //tmp damit aktuellerVerbrauch hier nicht verändert wird
		if(Math.random() < 0.5){
			tmp += Math.random() * this.schwankung;
		}else{
			tmp -= Math.random() * this.schwankung;
		}
		return(tmp);
	}
	
	public double setAktuellerVerbrauch(double Max, double Min, double Änderungs_Wahrscheinlichkeit){
		if(Math.random() < Änderungs_Wahrscheinlichkeit){
			this.aktuellerVerbrauch = Math.random() * (Max - Min) + Min;
			this.änderungsWahrscheinlichkeit = this.constÄnderWahrsch;
			this.modusDauer = 0;
		}
		return(this.aktuellerVerbrauch);
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
