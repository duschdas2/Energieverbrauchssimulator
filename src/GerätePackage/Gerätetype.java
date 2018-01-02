/**
 * Bestimmt den Gerätetypen und übergibt Eigenschaften.
 */
public class Gerätetype 
{
	int Type;
	int Zyklus;
	
	public Gerätetype(int Type){
		if(Type == 1){
			Gerätetype1();
		}
		else if(Type == 2) {
			Gerätetype2();
		}
		else if(Type == 3) {
			Gerätetype3();
		}
		else if(Type == 4) {
			Gerätetype4();
		}
	}
	
	public void Gerätetype1(){
		
	}
	
	public void Gerätetype2(){
		
	}
	
	public void Gerätetype3(){
		
	}
	
	public void Gerätetype4(){
		
	}
}
