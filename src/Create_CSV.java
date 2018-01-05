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
	private static final String STRING_ARRAY_SAMPLE = "C:/Users/Manuel/CSV-Dateien/csv1.csv";
	public static void Create(/*String [][] data*/) throws IOException{
		try (
				Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
		
		        CSVWriter csvWriter = new CSVWriter(writer, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		    ) {
				String [] [] data = new String [2] [2];
				data[0][0] = "00";
				data[0][1] = "01";
				data[1][0] = "10";
				data[1][1] = "11";
				for (int i = 0; i < data.length; i ++) {
					String [] tmp = new String [data[i].length];
					for (int c = 0; c < data[i].length; c++) {
						System.out.print(data[i][c] + ", ");
						tmp[c] = data[i][c];
					}
					System.out.println();
					csvWriter.writeNext(tmp);
				}
				
			
//		        String[] headerRecord = {"Name", "Email", "Phone", "Country"};
//		        csvWriter.writeNext(headerRecord);
//		
//		        csvWriter.writeNext(new String[]{"Sundar Pichai" , "sundar.pichai@gmail.com", "+1-1111111111", "India"});
//		        csvWriter.writeNext(new String[]{"Satya Nadella", "satya.nadella@outlook.com", "+1-1111111112", "India"});
		    }
	}
	
	public static void Read() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(STRING_ARRAY_SAMPLE));
		CSVReader csvReader = new CSVReader(reader, ';');
		//CSVReaderBuilder reader2 = new CSVReaderBuilder (new FileReader(STRING_ARRAY_SAMPLE));
	    String [] nextLine;
	    int i = 0;
	    while ((nextLine = csvReader.readNext()) != null) {
	    	System.out.println("Name : " + nextLine[0]);
            System.out.println("Email : " + nextLine[1]);
            System.out.println("Phone : " + nextLine[2]);
            System.out.println("Country : " + nextLine[3]);
            System.out.println("==========================");
//	    	System.out.println(nextLine[i]);
//	    	i++;
	    }
	}
	
	public static void main(String [] args) {
		try {
			Create();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
