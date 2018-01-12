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
	
	public static void main(String[] args) {
//		test();
//		test2();
		try {
			test3();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void test3() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(STRING_ARRAY_SAMPLE1));
		CSVReader csvReader = new CSVReader(reader, ';');
		String[] header = csvReader.readNext();
		String [] nextLine;
		
		// Erstellt die Datensätze für den Graphen aus dem Array
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		int c = 1;		//Minuten angabe
		while ((nextLine = csvReader.readNext()) != null) {
			for (int i = 0; i < header.length; i++) {		//für jede Spalte(Geräte) einmal ausführen
				dataset.addValue(Double.valueOf(nextLine[i]), header[i],  Integer.toString(c));
			}
			c++;		//zählt die Minuten hoch
			if (c == 10000) {
				break;
			}
		}
		
		// Erstellt den Graphen
		JFreeChart chart = ChartFactory.createLineChart("Test Household", "Time in minutes", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		// Erstellt das Frame zum abbilden des Graphen
		ChartFrame frame = new ChartFrame("Chart", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void test2 () {
		// create a dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	    dataset.addValue( 15 , "schools" , "1970" );
	    dataset.addValue( 30 , "schools" , "1980" );
	    dataset.addValue( 60 , "schools" ,  "1990" );
	    dataset.addValue( 120 , "schools" , "2000" );
	    dataset.addValue( 240 , "TEST" , "1980" );
	    dataset.addValue( 0 , "TEST" ,  "1990" );
	    dataset.addValue( 120 , "TEST" , "2000" );
	    dataset.addValue( 300 , "TEST" , "2014" );
		
		// create a chart...
		JFreeChart chart = ChartFactory.createLineChart("Numer of Schools vs years", "Years", "Number of Schools", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		// create and display a frame...
		ChartFrame frame = new ChartFrame("Chart", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void test () {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Category 1", 43.2);
		data.setValue("Category 2", 27.9);
		data.setValue("Category 3", 79.5);
		
		// create a chart...
		JFreeChart chart = ChartFactory.createPieChart(
		"Sample Pie Chart",
		data,
		true, // legend?
		true, // tooltips?
		false // URLs?
		);
		
		// create and display a frame...
		ChartFrame frame = new ChartFrame("Chart", chart);
		frame.pack();
		frame.setVisible(true);
	}

}
