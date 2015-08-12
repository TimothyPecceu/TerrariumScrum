package be.vdab.valueobjects;

import java.util.Random;

import be.vdab.entities.Terrarium;

public class Positie {
	
	private int xLocatie,yLocatie;
	
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
	
	public Positie(int yLocatie,int xLocatie){
		if (xLocatie<0||xLocatie>Terrarium.getBreedte()-1||yLocatie<0||yLocatie>Terrarium.getHoogte()-1){
			throw new IllegalArgumentException();
		}
		this.xLocatie = xLocatie;
		this.yLocatie= yLocatie;
		}
	
	public Positie(){
		this(new Random().nextInt(Terrarium.getHoogte()), new Random().nextInt(Terrarium.getBreedte()));
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
