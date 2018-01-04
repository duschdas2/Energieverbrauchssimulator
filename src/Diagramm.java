import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Diagramm {

	
	
	public static void main(String[] args) {
		test();
		test2();
	}
	
	public static void test2 () {
		// create a dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	    dataset.addValue( 15 , "schools" , "1970" );
	    dataset.addValue( 30 , "schools" , "1980" );
	    dataset.addValue( 60 , "schools" ,  "1990" );
	    dataset.addValue( 120 , "schools" , "2000" );
	    dataset.addValue( 240 , "schools" , "2010" );
	    dataset.addValue( 300 , "schools" , "2014" );
		
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
