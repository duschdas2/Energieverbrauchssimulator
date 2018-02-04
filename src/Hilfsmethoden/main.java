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
		
		double[] tmpDataLampe = new double[1440];
		double[] tmpDataFern = new double[1440];
		double[] tmpDataKaffee = new double[1440];
		double[] tmpDataKühl = new double[1440];
		double[] tmpDataMikro = new double[1440];
		double[] tmpDataPc = new double[1440];
		double[] tmpDataTrock = new double[1440];
		double[] tmpDataWasch = new double[1440];
		double[] tmpDataWass = new double[1440];

		double[][] tmpData2 = new double[1440][9];
		ArrayList <String> geräte = new ArrayList<String>();
		geräte.add("deckenlampe");
		geräte.add("fernseher");
		geräte.add("kaffeemaschine");
		geräte.add("kühlschrank");
		geräte.add("mikrowelle");
		geräte.add("pc");
		geräte.add("trockner");
		geräte.add("waschmaschine");
		geräte.add("wasserkocher");

		tmpDataLampe = Eco.GetSpecific(5, "deckenlampe");
		tmpDataFern = Eco.GetSpecific(5, "fernseher");
		tmpDataKaffee = Eco.GetSpecific(5, "kaffeemaschine");
		tmpDataKühl = Eco.GetSpecific(5, "kühlschrank");
		tmpDataMikro = Eco.GetSpecific(5, "mikrowelle");
		tmpDataPc = Eco.GetSpecific(5, "pc");
		tmpDataTrock = Eco.GetSpecific(5, "trockner");
		tmpDataWasch = Eco.GetSpecific(5, "waschmaschine");
		tmpDataWass = Eco.GetSpecific(5, "wasserkocher");
		for(int i = 0; i<tmpDataKühl.length;i++)
		{
			tmpData2[i][0] = tmpDataLampe[i];
			tmpData2[i][1] = tmpDataFern[i];
			tmpData2[i][2] = tmpDataKaffee[i];
			tmpData2[i][3] = tmpDataKühl[i];
			tmpData2[i][4] = tmpDataMikro[i];
			tmpData2[i][5] = tmpDataPc[i];
			tmpData2[i][6] = tmpDataTrock[i];
			tmpData2[i][7] = tmpDataWasch[i];
			tmpData2[i][8] = tmpDataWass[i];
			//System.out.println(tmpData[i]);
		}
		int[] test = new int[1440];
		Ausgabe.erstelleArr(test, tmpData2, geräte);
		//String csv = Create_CSV.create(tmpData2);
		//Diagramm.erzeugeEco(csv);
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
