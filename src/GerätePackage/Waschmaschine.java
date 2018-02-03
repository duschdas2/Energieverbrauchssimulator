package GerätePackage;

public class Waschmaschine extends GeräteTyp2{

	public Waschmaschine() {
		super(2010, 3, 50, 3, 0.2);
		
		//Waschgang: 40°C, Buntwäsche
		//alle Modi für das Express-Programm in zeitlich chronologischer Reihenfolge
		
		this.verbrauchsWerte.add(50.0);		//Modus 0: Start (ersten 2 min) langsames wirbeln und wasser ziehen
		this.schwankungsWerte.add(5.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(70.0);		//Modus 1: nach Start (wieder 2 min) langsames wirbeln und wasser ziehen
		this.schwankungsWerte.add(5.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(2010.0);	//Modus 2: Wasser wird erhitzt (10 min) und schleudern wird schneller
		this.schwankungsWerte.add(50.0);
		this.modiDauer.add(10);
		
		this.verbrauchsWerte.add(120.0);	//Modus 3: nur schleudern (schnelligkeit von modus 2)
		this.schwankungsWerte.add(30.0);
		this.modiDauer.add(3);

		this.verbrauchsWerte.add(450.0);	//Modus 4: sehr schnelles schleudern
		this.schwankungsWerte.add(50.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(130.0);	//Modus 5: wieder normales schleudern + wasser ziehen
		this.schwankungsWerte.add(20.0);
		this.modiDauer.add(5);
		
		this.verbrauchsWerte.add(400.0);	//Modus 6: sehr schnelles schleudern
		this.schwankungsWerte.add(50.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(3.0);		//Modus 7: pause
		this.schwankungsWerte.add(0.2);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(450.0);	//Modus 8: sehr schnelles schleudern
		this.schwankungsWerte.add(50.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(675.0);	//Modus 9: schnellstes schleudern
		this.schwankungsWerte.add(15.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(15.0);		//Modus 10: ganz langsames schleudern
		this.schwankungsWerte.add(0.4);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(3.0);		//Modus 11: ende
		this.schwankungsWerte.add(0.2);
		this.modiDauer.add(1);
	}

}
