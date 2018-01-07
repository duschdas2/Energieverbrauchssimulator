package GerätePackage;

public class Toaster extends GeräteTyp1{

	int betriebsdauer = 2;
	
	public Toaster() {		
		super(825, 0.1, 4.0);
	}

	public int getBetriebsdauer() {
		return betriebsdauer;
	}
}
