import com.opencsv.CSVWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

/**
 * Klasse die CSV-Dateien schreibt
 * @author Manuel
 *
 */
public class Create_CSV {
	
	private static final String PATH = "CSV\\Test\\";
	
	/**
	 * Erstellt eine CSV_Datei aus dem übergebenden Daten
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String create(String [][] data) throws IOException{
		String path = date_time();
		try (
				Writer writer = Files.newBufferedWriter(Paths.get(path));
		
		        CSVWriter csvWriter = new CSVWriter(writer, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		    ) {
				for (int i = 0; i < data.length; i ++) {
					String [] tmp = new String [data[i].length];
					for (int c = 0; c < data[i].length; c++) {
						//System.out.print(data[i][c] + ", ");
						tmp[c] = data[i][c];
					}
					//System.out.println();
					csvWriter.writeNext(tmp);
				}
		    }
		return path;
	}
	
	/**
	 * Gibt der zu erstellenden CSV_Datei das aktuelle Datum und Uhrzeit als Name
	 * @return
	 */
	private static String date_time() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter df;
		df = DateTimeFormatter.ISO_DATE_TIME;
		df = DateTimeFormatter.ofPattern("dd.MM.yyyy_kk.mm");
		String s = PATH;
		s = s.concat(now.format(df).concat(".csv"));
		return s;
	}
	
	public static void main(String [] args) {
		try {
			//System.out.println(date_time());
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
