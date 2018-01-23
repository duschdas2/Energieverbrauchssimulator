package GerätePackage;

public class Kühlschrank extends GeräteTyp4{

	public Kühlschrank() {
		super(83.0, 0.1, 0.6, 0.3);
		
		if(Math.random() < 0.75){	//kompressor ist zu 75% der zeit aus (90 min aus, 30 min an)
			this.modus = 0;	//aus
		}else{
			this.modus = 1;	//an
		}
		
		this.modusVerbrauch.add(0.1);	//Modus 0: kompressor aus
		this.maxModusDauer.add(90);
		
		this.modusVerbrauch.add(83.0);	//Modus 1: kompressor an
		this.maxModusDauer.add(30);
	}
	
		public double setAktuellerVerbrauch(){
			
			if(this.modus == 0){	//kompressor aus
				
				this.modusDauer++;
				
				if(this.betriebsdauer == 0){	
					//so kann kann der Kühlschrank bei Minute 0 auch schon mitten im Modus sein
					this.modusDauer = (int) (Math.random()*85);
				}
				
				this.aktuellerVerbrauch = this.modusVerbrauch.get(this.modus);
				
				if(this.modusDauer > this.maxModusDauer.get(modus)-(Math.random()*(20-5)+5)){
					this.modus = 1;
					this.modusDauer = 0;
				}
			}
			else if(this.modus == 1){	//kompressor an
				
				this.modusDauer++;
				int tmp;
				if(this.betriebsdauer == 0){
					tmp = (int) (Math.random()*25);
					this.modusDauer = tmp;
				}else{tmp = 1;}
				
				if(this.modusDauer == 1){
					this.aktuellerVerbrauch = this.modusVerbrauch.get(this.modus) - (Math.random() * (this.maxSchwankung - this.minSchwankung) + this.minSchwankung);
				}
				else if(this.betriebsdauer == 0){
					//so kann kann der Kühlschrank bei Minute 0 auch schon mitten im Modus sein
					for(int i = 0; i < tmp; i++){
						if(i == 0){
						this.aktuellerVerbrauch = this.modusVerbrauch.get(this.modus) - Math.random() * (this.maxSchwankung - this.minSchwankung) + this.minSchwankung;
						}else{
							this.aktuellerVerbrauch -= Math.random() * (this.maxSchwankung - this.minSchwankung) + this.minSchwankung;
						}
					}
				}
				else{
					this.aktuellerVerbrauch -= Math.random() * (this.maxSchwankung - this.minSchwankung) + this.minSchwankung;
				}
				
				if(this.modusDauer > this.maxModusDauer.get(modus)+(Math.random()*(10-2)+2)){
					this.modus = 0;
					this.modusDauer = 0;
				}
			}
					
			return(this.aktuellerVerbrauch);
		}

}
