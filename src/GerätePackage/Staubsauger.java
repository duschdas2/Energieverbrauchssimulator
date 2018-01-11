package GerätePackage;

public class Staubsauger extends GeräteTyp3{
	
	public Staubsauger() {
	super(690, 110, 0.2, 6.5, 0.0015, 0.0015);
	this.aktuellerVerbrauch = this.maxVerbrauch;
	}
	
	//die änderungsWahrscheinlichkeit ändert sich mit der Betriebsdauer
	//sie steigt, bis eine Änderung eintritt und wird dann zurückgesetzt
	public void modifyÄnderWahrsch(int Modus_Dauer, double Änderungs_Wahrscheinlichkeit){	
		this.änderungsWahrscheinlichkeit += Modus_Dauer * this.constÄnderWahrsch;
	}

}
