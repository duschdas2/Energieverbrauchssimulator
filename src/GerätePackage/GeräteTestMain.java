package GerätePackage;

public class GeräteTestMain {

	public static void main(String[] args) {
		Toaster toaster = new Toaster();
		Licht licht = new Licht();
		Staubsauger sbsg = new Staubsauger();
		PC pc = new PC();
		
		System.out.println("Toaster: "+toaster.randomisieren(toaster.maxVerbrauch, toaster.schwankung));
		System.out.println("Licht: "+licht.randomisieren(licht.maxVerbrauch, licht.schwankung));
		
		pc.setModus(1);
		System.out.println("PC: "+pc.randomisieren());
		
		sbsg.setAktuellerVerbrauch(sbsg.maxVerbrauch, sbsg.minVerbrauch);
		System.out.println("Staubsauger: "+sbsg.randomisieren(sbsg.aktuellerVerbrauch, sbsg.schwankung));

		
	}

}
