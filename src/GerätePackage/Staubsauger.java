package GerätePackage;

public class Staubsauger extends GeräteTyp3{
	
	public Staubsauger() {
		super(690, 110, 0.2, 6.5, 0.003, 0.003, 70);
		this.aktuellerVerbrauch = this.maxVerbrauch;
	}

}
