package GerätePackage;

public class Kaffeemaschine extends GeräteTyp2{

	public Kaffeemaschine() {
		super(1800, 400, 1650, 1750, 0.2);
		
		// Modus 0: Notwendige automatische Spülung beim Einschalten des Geräts
		this.verbrauchsWerte.add(1650.0); 
		this.schwankungsWerte.add(30);
		this.modiDauer.add(1);
		
		// Modus 1: Kaffeebohnen werden gemahlen und wasser wird erhitzt
		this.verbrauchsWerte.add(1750.0); 
		this.schwankungsWerte.add(35);
		this.modiDauer.add(1);
	}

}
