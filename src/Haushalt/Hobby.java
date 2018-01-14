package Haushalt;

import java.util.Random;

public class Hobby {

	private int hobbydauer;
	private int startzeit;
	private double chance;
	private String name;
	Random hobbyRnd = new Random();
	
	public Hobby() {
		setData();
	}

	//hobby wird zuf‰llig ausgew‰hlt, 50% garkein hobby und danach verschiedene hobbies mit verschiedenen zeiten
	public void setData(){
		switch(hobbyRnd.nextInt(10)){
		case 0: hobbydauer = 120;
				startzeit = 1080;
				chance = 0.4;
				name = "Fuﬂball";
				break;
		case 1: hobbydauer = 180;
				startzeit = 1140;
				chance = 0.2;
				name = "Schach";
				break;
		case 2: hobbydauer = 90;
				startzeit = 1020;
				chance = 0.6;
				name = "Fitnesstudio intensiv";
				break;
		case 3: hobbydauer = 120;
				startzeit = 1050;
				chance = 0.2;
				name = "Eishockey";
				break;
		case 4: hobbydauer = 60;
				startzeit = 1050;
				chance = 0.6;
				name = "Fitnesstudio";
				break;
		default:hobbydauer = 0;
				startzeit = 0;
				chance = 0;
				name = "Kein Hobby";
				break;
		}
	}
	
	public int getHobbydauer(){
		return hobbydauer;
	}
	
	public int getStartzeit(){
		return startzeit;
	}
	
	public double getChance(){
		return chance;
	}
	
	public String getName(){
		return name;
	}
	
}
