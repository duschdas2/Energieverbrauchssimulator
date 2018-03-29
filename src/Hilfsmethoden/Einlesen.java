package Hilfsmethoden;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

/**
 * 
 * @author Manuel Neis
 *
 */
public class Einlesen {
	
	//Liest eine bestimmte Anzahl der Geräte ein und ermittelt die häufigkeit der Benutzungen pro Minute
	public static double[] GetAll(int size,String gerät) throws IOException{
		double [] tmp = new double[1440];
		double [] mean = new double[1440];
		for(int i = 1; i<=size;i++){
			tmp = GetData("CSV\\01\\"+gerät+"\\"+i+".csv");
			for(int b = 0; b<tmp.length;b++) {
				if(tmp[b] == 1)
				{
					mean[b] = mean[b]+1;
				}
			}
		}
		return mean;	
	}
	
	//Liest die Datei ein und speichert sie im Array data
	public static double [] GetData(String path) throws IOException {
		double [] data = new double[86400];
		CSVReader reader = new CSVReader(new FileReader(path));
	    String [] nextLine;
	    int i = 0;
	    while ((nextLine = reader.readNext()) != null) {
	    	if(Double.parseDouble(nextLine[0]) != 0 && Double.parseDouble(nextLine[0]) != -1) {
	    		data[i] = data[i]+1;
	    	}
	        i++;
	    }
	    double [] tmp = SortData(data);
	    return tmp;
	}
	
	//Überprüft Minuten genau ob ein Gerät lief, wenn ja dann +1 (>30 um fehlerhafte oder zu kleine Daten zu vermeiden)
	public static double [] SortData(double [] data)
	{
		double [] real = new double[1440];
		int pos = 0;
		for(int i = 0; i < data.length;i++) {
			if(i % 60 == 0 && i > 30) {
				for(int counter = 60; counter > 0;counter --) {
					if(data[i-counter] != 0 && data[i-counter] != -1){
						real[pos] = real[pos]+1;
						break;
					}
				}
				pos = pos+1;
			}
		}
		return real;
	}
}
