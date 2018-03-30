package Hilfsmethoden;
import java.io.IOException;
import java.util.ArrayList;
import Haushalt.Haushalt;
import Haushalt.Person;
/**
 *
 */
public class HilfsKlasse {

	public static void main(String[] args) throws IOException {
		//Dient zur Ausgabe von ECO Diagrammen und zusammenführung von Geräten
		
		//double[] tmpDataLampe = new double[1440];
		//double[] tmpDataFern = new double[1440];
		//double[] tmpDataKaffee = new double[1440];
		double[] tmpDataKühl = new double[1440];
		//double[] tmpDataMikro = new double[1440];
		double[] tmpDataPc = new double[1440];
		double[] tmpDataTrock = new double[1440];
		double[] tmpDataWasch = new double[1440];
		double[] tmpDataWass = new double[1440];

		double[][] tmpData2 = new double[1440][5];
		ArrayList <String> geräte = new ArrayList<String>();
		//geräte.add("deckenlampe");
		//geräte.add("fernseher");
		//geräte.add("kaffeemaschine");
		geräte.add("kühlschrank");
		//geräte.add("mikrowelle");
		geräte.add("pc");
		geräte.add("trockner");
		geräte.add("waschmaschine");
		geräte.add("wasserkocher");

		//tmpDataLampe = Eco.GetSpecific(5, "deckenlampe");
		//tmpDataFern = Eco.GetSpecific(5, "fernseher");
		//tmpDataKaffee = Eco.GetSpecific(5, "kaffeemaschine");
		tmpDataKühl = Eco.GetSpecific(15, "kühlschrank");
		//tmpDataMikro = Eco.GetSpecific(5, "mikrowelle");
		tmpDataPc = Eco.GetSpecific(15, "pc");
		tmpDataTrock = Eco.GetSpecific(15, "trockner");
		tmpDataWasch = Eco.GetSpecific(15, "waschmaschine");
		tmpDataWass = Eco.GetSpecific(15, "wasserkocher");
		for(int i = 0; i<tmpDataKühl.length;i++)
		{
			//tmpData2[i][0] = tmpDataLampe[i];
			//tmpData2[i][1] = tmpDataFern[i];
			//tmpData2[i][2] = tmpDataKaffee[i];
			tmpData2[i][0] = tmpDataKühl[i];
			//tmpData2[i][4] = tmpDataMikro[i];
			tmpData2[i][1] = tmpDataPc[i];
			tmpData2[i][2] = tmpDataTrock[i];
			tmpData2[i][3] = tmpDataWasch[i];
			tmpData2[i][4] = tmpDataWass[i];
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
