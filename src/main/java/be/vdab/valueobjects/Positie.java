package be.vdab.valueobjects;

import java.util.Random;

import be.vdab.entities.Terrarium;

public class Positie {
	
	private int xLocatie,yLocatie;
	private Terrarium terrarium;
	
	public Terrarium getTerrarium() {
		return terrarium;
	}

	public int getxLocatie(){
		return xLocatie;
	}
	
	public void setxLocatie(int xLocatie) {
		this.xLocatie = xLocatie;
	}

	public void setyLocatie(int yLocatie) {
		this.yLocatie = yLocatie;
	}

	public int getyLocatie(){
		return yLocatie;
	}
	
	public Positie(int yLocatie,int xLocatie,Terrarium terrarium){
		if (xLocatie<0||xLocatie>terrarium.getBreedte()-1||yLocatie<0||yLocatie>terrarium.getHoogte()-1){
			throw new IllegalArgumentException();
		}
		this.xLocatie = xLocatie;
		this.yLocatie= yLocatie;
		this.terrarium= terrarium;
		}
	
	public Positie(Terrarium terrarium){
		this(new Random().nextInt(terrarium.getHoogte()), new Random().nextInt(terrarium.getBreedte()),terrarium);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Positie)){
			return false;
		}
		Positie pos = (Positie) obj;
		return(pos.getxLocatie() == xLocatie && pos.getyLocatie() == yLocatie);
	}

	@Override
	public int hashCode() {
		return yLocatie*10+xLocatie;
	}
	
	@Override
	public String toString(){
		return xLocatie + "," + yLocatie;
		
	}
	
	
}
