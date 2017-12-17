/**
 * Erfasst alle Geräte Daten.
 */
public class Geräte 
{
	String Name;
	int Max_Verbrauch;
	int Durch_Verbrauch;
	int Standby;
	int Anlaufzeit;
	int Auslaufzeit;
	Gerätetype Gerätetype;
	
	public Geräte(String Name,int Max_Verbrauch,int Durch_Verbrauch,int Standby, int Anlaufzeit, int Auslaufzeit, int Type){
		this.Name = Name;
		this.Max_Verbrauch = Max_Verbrauch;
		this.Durch_Verbrauch = Durch_Verbrauch;
		this.Standby = Standby;
		this.Anlaufzeit = Anlaufzeit;
		this.Auslaufzeit = Auslaufzeit;
		Gerätetype = new Gerätetype(Type);
	}
}
