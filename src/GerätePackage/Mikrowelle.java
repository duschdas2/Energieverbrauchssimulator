package GerätePackage;

public class Mikrowelle extends GeräteTyp1{

	private int betriebsdauer = 3;
	
	public Mikrowelle() {
		super(1430, 0.6, 15);
	}

	public int getBetriebsdauer() {
		return betriebsdauer;
	}

}
