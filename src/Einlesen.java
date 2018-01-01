import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

public class Einlesen {
	
	private static double [] mean = new double[1440];
	
	//Liest eine bestimmte Anzahl der Geräte ein und ermittelt die häufigkeit der Benutzungen pro Minute
	public static double[] GetAll(int size) throws IOException{
		double [] tmp = new double[1440];
		for(int i = 1; i<=size;i++){
			//Pfad muss momentan noch geändert werden
			tmp = GetData("C:\\Users\\Jannik Schulze\\git\\Energieverbrauchssimulator\\CSV\\01\\"+i+".csv");
			for(int b = 0; b<tmp.length;b++) {
				if(tmp[b] == 1)
				{
					mean[b] = mean[b]+1;
				}
			}
		}
		return mean;
		
	}
	
	//List die Datei ein und speichert sie im Array data
	public static double [] GetData(String path) throws IOException {
		double [] data = new double[86400];
		CSVReader reader = new CSVReader(new FileReader(path));
	    String [] nextLine;
	    int i = 0;
	    while ((nextLine = reader.readNext()) != null) {
	    	if(Double.parseDouble(nextLine[0]) != 0) {
	    		data[i] = data[i]+1;
	    	}
	        i++;
	    }
	    double [] tmp = SortData(data);
	    return tmp;
	}
	
	//Überprüft Minuten genau ob ein Gerät lief, wenn ja dann +1
	public static double [] SortData(double [] data)
	{
		double [] real = new double[1440];
		int pos = 0;
		for(int i = 0; i < data.length;i++) {
			if(i % 60 == 0) {
				for(int counter = 60; counter > 0;counter --) {
					if(data[i] != 0){
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
