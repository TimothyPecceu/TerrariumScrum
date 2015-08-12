package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public abstract class Organisme {
	
	private Terrarium terrarium;
	
	private Positie positie;
	
	public Positie getPositie() {
		return positie;
	}

	public void setPositie(Positie positie){
		this.positie = positie;
	}
	
	public abstract void actie(Organisme organisme);
	
	public abstract void carnivoorInteractie(Carnivoor carnivoor);

	public abstract void herbivoorInteractie(Herbivoor herbivoor);
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
	
	public void setTerrarium(Terrarium terrarium){
		this.terrarium=terrarium;
	}

	public Terrarium getTerrarium() {
		return terrarium;
	}
	
	
}
