package Hilfsmethoden;
import java.io.IOException;
import java.util.ArrayList;
import Haushalt.Haushalt;
import Haushalt.Person;
/**
 *
 */
public class main {

	public static void main(String[] args) throws IOException {
		/*ArrayList <String> geräte = new ArrayList<String>();
		geräte.add("toaster");
		geräte.add("wasserkocher");
		geräte.add("staubsauger");
		double [][] gerätAn = new double [1440][geräte.size()];
		double [][] statAnalysis = new double [1440][geräte.size()];
		ArrayList<Person> list = new ArrayList<Person>();

		Person person = new Person(new Arbeiter());
		Person person2 = new Person(new Arbeiter());
		Person person3 = new Person(new Arbeiter());
		list.add(person);
		list.add(person2);
		list.add(person3);
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		
		Wahrscheinlichkeit_Typ1 w1 = new Wahrscheinlichkeit_Typ1();
		Wahrscheinlichkeit_Typ3 w2 = new Wahrscheinlichkeit_Typ3();
		getStatData(statAnalysis,geräte);
		
		for(int tSlot = 0;tSlot < statAnalysis.length;tSlot++) { 			//Durchläuft alle TimeSlots
			for(int aktGerät = 0;aktGerät < geräte.size();aktGerät++) { 	//Durchläuft alle Geräte
				if(aktGerät == 0) {
					w1.getWahrToaster(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(aktGerät == 1) {
					w1.getWahrWasserKocher(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(aktGerät == 2) {
					w2.getWahrStaubsauger(haushalt.getOccupancy(),statAnalysis,gerätAn,aktGerät,tSlot);
				}
				if(gerätAn[tSlot][aktGerät] == 1)
				{
					System.out.println("TimeSlot: " + tSlot + " " + geräte.get(aktGerät) + " : "+ gerätAn[tSlot][aktGerät]);
				}
			}
		}
		Ausgabe.erstelleArr(haushalt.getOccupancy(), gerätAn, geräte);
		*/
		double[] tmpData = new double[1440];
		double[] tmpDataWass = new double[1440];
		String[][] tmpData2 = new String[1440][2];
		tmpData = Eco.GetAll(1, "kühlschrank");
		//tmpDataWass = Eco.GetAll(1, "wasserkocher");
		for(int i = 0; i<tmpData.length;i++)
		{
			tmpData2[i][0] = String.valueOf(tmpData[i]);
			tmpData2[i][1] = String.valueOf(tmpDataWass[i]);
			//System.out.println(tmpData[i]);
		}
		String csv = Create_CSV.create(tmpData2);
		Diagramm.erzeuge(csv);
	}
	
	public static void getStatData(double [][] statAnalysis,ArrayList <String> geräte) throws IOException {
		int auswertDaten = 24;
		double[] tmpData = new double[1440];
		for(int i = 0; i<geräte.size();i++) {
			if(geräte.get(i) == "wasserkocher") {
				tmpData = Einlesen.GetAll(auswertDaten,geräte.get(i));
			}
			for(int j = 0; j<1440;j++) {
				statAnalysis[j][i] = tmpData[j];
			}
		}
	}
}
