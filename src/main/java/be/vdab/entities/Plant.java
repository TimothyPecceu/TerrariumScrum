package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public class Plant extends Organisme {

	public Plant(){
		setPositie(new Positie());
	}
	
	@Override
	public void carnivoorInteractie(Carnivoor carnivoor) {
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {
		wordGegeten(herbivoor);
	}
	
	@Override
	public void actie(Organisme organisme){
	}

	private void wordGegeten(Herbivoor herbivoor) {
		herbivoor.verhoogLevenskracht(1);
		Terrarium.getInstance().organismeVerwijderen(this.getPositie());
	}

}
