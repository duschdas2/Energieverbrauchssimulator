import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentyp;
/**
 *
 */
public class main {

	public static void main(String[] args) throws IOException {
		//Geräte pc = new Geräte("pc1",100,10,1,2,1,1);
		int geräte = 1;
		int auswertDaten = 10;
		double [] statAnalysis = new double [1440];
		double [][] gerätAn = new double [1440][geräte];
		
		Person person = new Person(new Personentyp());
		//System.out.print(person);
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(person);
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		//System.out.print(haushalt);
		
		Wahrscheinlichkeit w1 = new Wahrscheinlichkeit();
		statAnalysis = Einlesen.GetAll(auswertDaten);
		for(int i = 0;i<statAnalysis.length;i++) { //Durchläuft alle TimeSlots
			for(int geräteListe = 0;geräteListe<geräte;geräteListe++) { //Durchläuft alle Geräte
			//System.out.println(list.get(0).getPercentAwayTime(i));
			w1.checkStatus(list,statAnalysis,gerätAn,auswertDaten,"Toaster",geräteListe,i);
			if(gerätAn[i][0] == 1)
			{
				System.out.println("TimeSlot: " + i + " : "+ gerätAn[i][0]);
			}
			}
		}
	}
}
