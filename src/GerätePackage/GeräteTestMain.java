package GerätePackage;

public class GeräteTestMain {

	public static void main(String[] args) {
		Toaster toaster = new Toaster();
		Licht licht = new Licht();
		Staubsauger sbsg = new Staubsauger();
		PC pc = new PC();
		
		System.out.println("Toaster: "+toaster.randomisieren());
		System.out.println("Licht: "+licht.randomisieren());
		
		pc.setModus(2);
		System.out.println("PC: "+pc.randomisieren());
		
		for(int i = 1; i < 100; i++){
			sbsg.modusDauer++;
			
			sbsg.modifyÄnderWahrsch();	//wahrscheinlichkeit wird mit betriebsdauer erhöht
			
			//aktuellerVerbrauch wird geändert wenn änderungsWahrscheinlichkeit hoch genug ist (höher als Math.random())
			sbsg.setAktuellerVerbrauch();	
			
			System.out.println("Staubsauger: "+sbsg.randomisieren()
					+"	Änderungswahrscheinlichkeit: "+ sbsg.änderungsWahrscheinlichkeit);
		}
		
	}

}
