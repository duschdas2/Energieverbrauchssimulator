package Hilfsmethoden;
/**
 * Diese Klasse regelt das einlesen der ECO-Daten.
 * @author Julian Grünker
 */
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class Eco {
	public static double[] GetAll(int size,String gerät) throws IOException{
		double [] tmp = new double[1440];
		double [] mean = new double[1440];
		for(int i = 1; i<=size;i++){
			tmp = GetData("CSV\\01\\"+gerät+"\\"+i+".csv");
		}
		return tmp;
	}
	
	public static double[] GetSpecific(int zahl,String gerät) throws IOException{
		double [] tmp = new double[1440];
		double [] mean = new double[1440];
		//tmp = GetData("CSV\\01\\"+gerät+"\\"+zahl+".csv");
		tmp = GetData("CSV\\ECO\\"+gerät+"\\"+zahl+".csv");
		return tmp;
	}
	public static double [] GetData(String path) throws IOException {
		double [] data = new double[86400];
		CSVReader reader = new CSVReader(new FileReader(path));
	    String [] nextLine;
	    int i = 0;
	    while ((nextLine = reader.readNext()) != null) {
	    	if(Double.parseDouble(nextLine[0]) != 0 && Double.parseDouble(nextLine[0]) != -1) {
	    		data[i] = Double.parseDouble(nextLine[0]);
	    	}
	        i++;
	    }
	    double [] tmp = SortData(data);
	    return tmp;
	}
	
	//
	public static double [] SortData(double [] data)
	{
		double [] real = new double[1440];
		int pos = 0;
		for(int i = 0; i < data.length;i++) {
			if(i % 60 == 0 && i > 0) {
				for(int counter = 60; counter > 0;counter --) {
					if(data[i-counter] != 0 && data[i-counter] != -1){
						real[pos] = real[pos]+data[i-counter];
					}
				}
				real[pos] = real[pos]/60;
			pos = pos+1;
			}
		}
		return real;
	}
}
