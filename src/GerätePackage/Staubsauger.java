package GerätePackage;

public class Staubsauger extends GeräteTyp3{
	
	public Staubsauger() {
	super(690, 110, 0.2, 6.5, 0.001, 0.001, 300);
	this.aktuellerVerbrauch = this.maxVerbrauch;
	}
	
	//die änderungsWahrscheinlichkeit ändert sich mit der Betriebsdauer
	//sie steigt, bis eine Änderung eintritt und wird dann zurückgesetzt
	public void modifyÄnderWahrsch(int Modus_Dauer){	
		this.änderungsWahrscheinlichkeit += Modus_Dauer * this.constÄnderWahrsch;
	}

}
