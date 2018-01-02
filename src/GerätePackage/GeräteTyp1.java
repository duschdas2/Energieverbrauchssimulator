package GerätePackage;

public abstract class GeräteTyp1 
{
	double maxVerbrauch;	
	double standby;
	double schwankung;
	double aktuellerVerbrauch;
	boolean benutzt = false;
	
	public GeräteTyp1(double Max_Verbrauch, double Standby, double Schwankung){
		this.maxVerbrauch = Max_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
	}
	
	//Im Fall von Geräte vom Typ 1 ist maxVerbrauch == aktuellerVerbrauch
	protected double randomisieren(double Aktueller_Verbrauch, double Schwankung){
		
		this.aktuellerVerbrauch = Aktueller_Verbrauch;
		if(Math.random() < 0.5){
			this.aktuellerVerbrauch += Math.random() * this.schwankung;
		}else{
			this.aktuellerVerbrauch -= Math.random() * this.schwankung;
		}
		return(this.aktuellerVerbrauch);
	}
	
	public void setBenutzt(boolean Benutzt) {
		this.benutzt = Benutzt;
	}
}
