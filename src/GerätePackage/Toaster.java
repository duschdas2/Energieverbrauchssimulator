package GerätePackage;

/**
 * @author Kevin Rabe
 *
 */
public class Toaster extends GeräteTyp1{

	private int betriebsdauer = 2;
	
	public Toaster() {		
		super(825, 0.1, 4.0);
	}

	public int getBetriebsdauer() {
		return betriebsdauer;
	}

}
