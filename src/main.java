import java.io.FileReader;
import java.io.IOException;

/**
 *
 */
public class main {

	public static void main(String[] args) throws IOException {
		double test[] = new double [1440];
		//Geräte pc = new Geräte("pc1",100,10,1,2,1,1);
		test = Einlesen.GetAll(10);
		for(int i = 0;i<test.length;i++) {
			System.out.println("Minute: "+i+ " : "+ test[i]);
		}
	}
}
