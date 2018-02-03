package GerätePackage;

public class Trockner extends GeräteTyp2 {

	public Trockner() {
		super(2660, 175, 1990, 175, 0.1);

		// Koch-/Buntwäsche, Extra Schranktrocken, volle Wäscheladung
		// Pausen zumindest bisher nicht berücksichtigt
		// alle Modi für den Durchlauf in zeitlich chronologischer Reihenfolge
		
		this.verbrauchsWerte.add(1990.0); // Modus 0: Start (ersten 2 min) schleudern + erhitzen
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);

		this.verbrauchsWerte.add(2660.0); // Modus 1: schnelleres schleudern und/oder erhöhte temperatur
		this.schwankungsWerte.add(25.0);
		this.modiDauer.add(24);

		this.verbrauchsWerte.add(1980.0); // Modus 2: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(4);
		
		this.verbrauchsWerte.add(2660.0); // Modus 3: schnelleres schleudern und/oder erhöhte temperatur
		this.schwankungsWerte.add(25.0);
		this.modiDauer.add(4);
		
		this.verbrauchsWerte.add(1990.0); // Modus 4: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(5);
		
		this.verbrauchsWerte.add(910.0); // Modus 5: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(1990.0); // Modus 6: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(8);

		this.verbrauchsWerte.add(910.0); // Modus 7: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(1990.0); // Modus 8: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(3);
		
		this.verbrauchsWerte.add(910.0); // Modus 9: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(2000.0); // Modus 10: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(3);
		
		this.verbrauchsWerte.add(910.0); // Modus 11: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(1990.0); // Modus 12: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(915.0); // Modus 13: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(2000.0); // Modus 14: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(905.0); // Modus 15: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(2010.0); // Modus 16: ähnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(175.0); // Modus 17: ende
		this.schwankungsWerte.add(5.0);
		this.modiDauer.add(7);
	}
}
