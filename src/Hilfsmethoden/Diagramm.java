package Hilfsmethoden;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

import com.opencsv.CSVReader;

import org.jfree.data.category.DefaultCategoryDataset;

public class Diagramm {

	private static final String STRING_ARRAY_SAMPLE1 = "CSV\\Test\\csv1.csv";
	private static final String STRING_ARRAY_SAMPLE2 = "C:/Users/Manuel/Downloads/UNI KRAM/Kappes/jdemandmodel-master/data/output/1.csv";
	private static final String STRING_ARRAY_SAMPLE3 = "C:/Users/Manue/Downloads/Kappes Projekt/Jdemand/jdemandmodel-master/data/output/1.csv";
	private static final String STRING_ARRAY_SAMPLE4 = "C:/Users/Manue/Downloads/Kappes Projekt/01/01/2012-06-01.csv";
	private static final String STRING_ARRAY_SAMPLE5 = "CSV\\Test\\1.csv";
	
	public static void main(String[] args) {
		try {
			erzeuge(STRING_ARRAY_SAMPLE5);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Erzeugt ein Linien Diagramm aus der übergebenden CSV_Datei
	 * @param s
	 * @throws IOException
	 */
	public static void erzeuge(String s) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(s));
		CSVReader csvReader = new CSVReader(reader, ';');
		String[] header = csvReader.readNext();
		String [] nextLine;
		
		// Erstellt die Datensätze für den Graphen aus dem Array
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		int c = 1;		//Minuten angabe
		while ((nextLine = csvReader.readNext()) != null) {	//für jede Zeile ausführen
			for (int i = 0; i < header.length; i++) {		//für jede Spalte(Geräte) einmal ausführen
				if (i == 0) {								//Die Occupancy wird aus anschaulichen Gründen mal 10 genommen
					dataset.addValue(Double.valueOf(nextLine[i]) *10, header[i],  Integer.toString(c));
				}else {
					dataset.addValue(Double.valueOf(nextLine[i]), header[i],  Integer.toString(c));
				}
			}
			c++;		//zählt die Minuten hoch
		}
		
		// Erstellt den Graphen
		JFreeChart chart = ChartFactory.createLineChart("Haushalt", "Zeit in Minuten", "Watt", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		// Erstellt das Frame zum abbilden des Graphen
		ChartFrame frame = new ChartFrame("Diagramm", chart);
		frame.pack();
		frame.setVisible(true);
	}
}
