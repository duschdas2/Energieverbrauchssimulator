package GerätePackage;

public class GeräteTestMain {

	public static void main(String[] args) {
		Toaster toaster = new Toaster();
		Licht licht = new Licht();
		Staubsauger sbsg = new Staubsauger();
		PC pc = new PC();
		
		System.out.println("Toaster: "+toaster.randomisieren(toaster.maxVerbrauch, toaster.schwankung));
		System.out.println("Licht: "+licht.randomisieren(licht.maxVerbrauch, licht.schwankung));
		
		pc.setModus(2);
		System.out.println("PC: "+pc.randomisieren());
		
		for(int i = 1; i < 100; i++){
			sbsg.modusDauer++;
			double wahrCheck = sbsg.änderungsWahrscheinlichkeit;
			
			sbsg.modifyÄnderWahrsch(sbsg.modusDauer, sbsg.änderungsWahrscheinlichkeit);	//wahrscheinlichkeit wird mit betriebsdauer erhöht
			
			//aktuellerVerbrauch wird geändert wenn änderungsWahrscheinlichkeit hoch genug ist (höher als Math.random())
			sbsg.setAktuellerVerbrauch(sbsg.maxVerbrauch, sbsg.minVerbrauch, sbsg.änderungsWahrscheinlichkeit);	
			
			System.out.println("Staubsauger: "+sbsg.randomisieren(sbsg.aktuellerVerbrauch, sbsg.schwankung)
					+"	Änderungswahrscheinlichkeit: "+ sbsg.änderungsWahrscheinlichkeit);
		}
		
	}

}
