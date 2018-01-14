package GerätePackage;

public class Staubsauger extends GeräteTyp3{
	
	public Staubsauger() {
		super(690, 110, 0.2, 6.5, 0.002, 0.002, 125);
		this.aktuellerVerbrauch = this.maxVerbrauch;
	}

}
