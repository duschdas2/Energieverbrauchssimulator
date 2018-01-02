package GerätePackage;

public class PC extends GeräteTyp2{

	public PC() {
		super(355, 100, 1.5, 30, 0.5);
		
		verbrauchsWerte.add(99.0);	//Modus 0: Leerlauf
		schwankungsWerte.add(1.5);
		
		verbrauchsWerte.add(112.0);	//Modus 1: Videos schauen (YouTube)
		schwankungsWerte.add(6.0);
		
		verbrauchsWerte.add(120);	//Modus 2: aktives browsen im Internet (Facebook und andere, komplexe Seiten)
		schwankungsWerte.add(25);
		
		verbrauchsWerte.add(115);	//Modus 3: Anwendung (simples Spiel)
		schwankungsWerte.add(10);
		
		verbrauchsWerte.add(220);	//Modus 4: Anwendung (komplexeres Spiel)
		schwankungsWerte.add(25);
		
		verbrauchsWerte.add(310);	//Modus 5: Anwendung (komplexes Spiel)
		schwankungsWerte.add(30);
	}

}
