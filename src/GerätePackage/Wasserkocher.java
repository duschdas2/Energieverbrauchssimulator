package GerätePackage;

public class Wasserkocher extends GeräteTyp1{

	int betriebsdauer = 2;

	public Wasserkocher() {
		super(1850, 0.1, 9.0);	// läuft bei 1 Liter Füllung etwa 3 minuten
	}
	
	public int getBetriebsdauer() {
		return betriebsdauer;
	}

}
