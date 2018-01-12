import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReaderBuilder;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Klasse die CSV-Dateien schreibt
 * @author Manuel
 *
 */
public class Create_CSV {
	
	private static final String STRING_ARRAY_SAMPLE = "CSV\\Test\\csv1.csv";
	
	public static void create(String [][] data) throws IOException{
		try (
				Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
		
		        CSVWriter csvWriter = new CSVWriter(writer, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		    ) {
				for (int i = 0; i < data.length; i ++) {
					String [] tmp = new String [data[i].length];
					for (int c = 0; c < data[i].length; c++) {
						System.out.print(data[i][c] + ", ");
						tmp[c] = data[i][c];
					}
					System.out.println();
					csvWriter.writeNext(tmp);
				}
		    }
	}
	
	public static void main(String [] args) {
		try {
			String [] [] data = new String [5] [3];
			data[0][0] = "TEST1";
			data[0][1] = "TEST2";
			data[0][2] = "TEST3";
			data[1][0] = "0";
			data[1][1] = "0";
			data[1][2] = "0";
			data[2][0] = "18";
			data[2][1] = "10";
			data[2][2] = "21";
			data[3][0] = "30";
			data[3][1] = "23";
			data[3][2] = "28";
			data[4][0] = "35";
			data[4][1] = "29";
			data[4][2] = "33";
			create(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
