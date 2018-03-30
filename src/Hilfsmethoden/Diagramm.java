package Hilfsmethoden;
import java.awt.Color;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.category.DefaultCategoryDataset;
import com.opencsv.CSVReader;

/**
 * 
 * @author Manuel Neis
 *
 */
public class Diagramm {

	private static final String STRING_ARRAY_SAMPLE1 = "C:\\\\Users\\\\Julian\\\\Desktop\\\\jdemandmodel-master\\\\data\\\\output\\\\1.csv";
	private static final String STRING_ARRAY_SAMPLE2 = "C:\\Users\\Julian\\Desktop\\jdemandmodel-master\\data\\output\\2.csv";
	private static final String STRING_ARRAY_SAMPLE3 = "C:\\\\Users\\\\Julian\\\\Desktop\\\\jdemandmodel-master\\\\data\\\\output\\\\3.csv";
	private static final String STRING_ARRAY_SAMPLE4 = "C:\\Users\\Julian\\Documents\\JDemand\\1.csv.csv";
	
	public static void main(String[] args) {
		try {
			erzeuge2(STRING_ARRAY_SAMPLE4);
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
		JFreeChart chart = ChartFactory.createLineChart("Simulierter Haushalt", "Zeit in Minuten", "Verbrauch in Watt", dataset, PlotOrientation.VERTICAL, true, true, false);
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
		JFreeChart chart = ChartFactory.createLineChart("Real Eco Daten", "Zeit in Minuten", "Verbrauch in Watt", dataset, PlotOrientation.VERTICAL, true, true, false);
		Plot plot = chart.getPlot();
		plot.setBackgroundPaint(Color.black);
		
		// Erstellt das Frame zum abbilden des Graphen
		ChartFrame frame = new ChartFrame("Diagramm", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void erzeuge2(String s) throws IOException {
		JFreeChart xylineChart = ChartFactory.createXYLineChart("Simmulierter Haushalt", "Zeit in Minuten", "Verbrauch in Watt", erstelleDataset(s), PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = xylineChart.getXYPlot();
		plot.setBackgroundPaint(Color.black);
		plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
	    ChartPanel chartPanel = new ChartPanel( xylineChart );
	    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		
		// Erstellt das Frame zum abbilden des Graphen
		ChartFrame frame = new ChartFrame("Diagramm", xylineChart);
		frame.pack();
		frame.setVisible(true);
	}
	
	private static XYDataset erstelleDataset(String s) throws IOException {
		XYSeriesCollection dataset = new XYSeriesCollection();
		Reader reader = Files.newBufferedReader(Paths.get(s));
		CSVReader csvReader = new CSVReader(reader, ';');
		String[] header = csvReader.readNext();
		String [] nextLine;
		ArrayList <XYSeries> serien = new ArrayList <XYSeries>();
		double c = 1.0;
		
		for (int i = 0; i < header.length; i++) {
			XYSeries series = new XYSeries(header[i]);
			serien.add(series);
		}
		
		while ((nextLine = csvReader.readNext()) != null) {
			for (int i = 0; i < header.length; i++){
				double tmp = Double.valueOf(nextLine[i]);
				if (i == 0) {								//Die Occupancy wird aus anschaulichen Gründen mal 10 genommen
					serien.get(i).add(c, tmp*10);
				}else {
					serien.get(i).add(c, tmp);
				}
			}
			c++;
		}
		
		for (int i = 0; i < serien.size(); i++) {
			dataset.addSeries(serien.get(i));
		}
		return dataset;
	}
}