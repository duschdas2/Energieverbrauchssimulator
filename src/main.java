import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentype;
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
		Wahrscheinlichkeit w1 = new Wahrscheinlichkeit();
		
		statAnalysis = Einlesen.GetAll(auswertDaten);
		for(int i = 0;i<statAnalysis.length;i++) {
			w1.checkStatus(statAnalysis,gerätAn,auswertDaten,geräte,i);
			System.out.println(gerätAn[i][0]);
		}
		
		Person person = new Person(15, new Personentype());
		person.calcTime();
		//System.out.print(person);
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(person);
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		//System.out.print(haushalt);
	}
}
