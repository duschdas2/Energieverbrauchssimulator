package Hilfsmethoden;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;

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
			erzeuge(STRING_ARRAY_SAMPLE2);
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
		JFreeChart chart = ChartFactory.createLineChart("Simmulierter Haushalt", "Zeit in Minuten", "Verbrauch in Watt", dataset, PlotOrientation.VERTICAL, true, true, false);
		Plot plot = chart.getPlot();
		plot.setBackgroundPaint(Color.black);
		
		// Erstellt das Frame zum abbilden des Graphen
		ChartFrame frame = new ChartFrame("Diagramm", chart);
		frame.pack();
		frame.setVisible(true);
	}
	// Für Eco datenset Ausgabe
	public static void erzeugeEco(String s) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(s));
		CSVReader csvReader = new CSVReader(reader, ';');
		String[] header = csvReader.readNext();
		String [] nextLine;
		
		// Erstellt die Datensätze für den Graphen aus dem Array
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		int c = 1;		//Minuten angabe
		while ((nextLine = csvReader.readNext()) != null) {	//für jede Zeile ausführen
			for (int i = 0; i < header.length; i++) {		//für jede Spalte(Geräte) einmal ausführen
				dataset.addValue(Double.valueOf(nextLine[i]), header[i],  Integer.toString(c));
			}
			c++;		//zählt die Minuten hoch
		}
		
		// Erstellt den Graphen
		JFreeChart chart = ChartFactory.createLineChart("Simmulierter Haushalt", "Zeit in Minuten", "Verbrauch in Watt", dataset, PlotOrientation.VERTICAL, true, true, false);
		Plot plot = chart.getPlot();
		plot.setBackgroundPaint(Color.black);
		
		// Erstellt das Frame zum abbilden des Graphen
		ChartFrame frame = new ChartFrame("Diagramm", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
//	public static void erzeuge2(String s) {
//		JFreeChart xylineChart = ChartFactory.createXYLineChart("Simmulierter Haushalt", "Zeit in Minuten", "Verbrauch in Watt", erstelleDataset(), PlotOrientation.VERTICAL, true, true, false);
//		XYPlot plot = xylineChart.getXYPlot();
//		plot.setBackgroundPaint(Color.black);
//	    ChartPanel chartPanel = new ChartPanel( xylineChart );
//	    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
//		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
//	    renderer.setSeriesPaint( 0 , Color.RED );
//	    renderer.setSeriesPaint( 1 , Color.GREEN );
//	    renderer.setSeriesPaint( 2 , Color.YELLOW );
//	    renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
//	    renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
//	    renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
//	    plot.setRenderer( renderer ); 
//	    setContentPane( chartPanel ); 
//	}
//	
//	private static XYDataset erstelleDataset() {
//		Reader reader = Files.newBufferedReader(Paths.get(s));
//		CSVReader csvReader = new CSVReader(reader, ';');
//		String[] header = csvReader.readNext();
//		String [] nextLine;
//		http://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-xy-line-chart-with-xydataset
//		XYSeriesCollection dataset = new XYSeriesCollection();
//		for (int i = 0; i < header.length; i++){
//			dataset.addSeries(erstelleSerie(i, header[i]));
//		}
//	}
//	
//	private static XYSeries erstelleSerie(int stelle, String name) {
//		XYSeries series = new XYSeries(name);
//		Wahrscheinlich müssen alle benötigten Dinge um die CSV auszulesen global für die Klasse werden
//		Dann einfach eine While Schleife die die neue Reihe liest und dann nur die Stelle i der Reihe in die Serie einfügen mit dem Zähler von 1 bis 1440
//	}
}
