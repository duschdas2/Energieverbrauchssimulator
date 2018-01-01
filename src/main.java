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
		double test[] = new double [1440];
		//Geräte pc = new Geräte("pc1",100,10,1,2,1,1);
		test = Einlesen.GetAll(10);
		for(int i = 0;i<test.length;i++) {
			System.out.println("Minute: "+i+ " : "+ test[i]);
		}
		Person person = new Person(15, new Personentype());
		person.calcTime();
		System.out.print(person);
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(person);
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		System.out.print(haushalt);
	}
}
