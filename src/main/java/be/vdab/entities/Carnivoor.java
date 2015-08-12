package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public class Carnivoor extends Dier {

	public Carnivoor(){	
		setPositie(new Positie());
	}
	public void actie(Organisme organisme) {
		if(organisme == null){
			stap();
		}else{
		organisme.carnivoorInteractie(this);
		}
	}

	public void carnivoorInteractie(Carnivoor carnivoor) {
		carnivoorVecht(carnivoor);
	}

	private void carnivoorVecht(Carnivoor carnivoor) {
		if (this.getLevenskracht() != carnivoor.getLevenskracht()) {
			if (this.getLevenskracht() > carnivoor.getLevenskracht()) {
				this.verhoogLevenskracht(carnivoor.getLevenskracht());
				Terrarium.getInstance().organismeVerwijderen(carnivoor.getPositie());
			} else {
				carnivoor.verhoogLevenskracht(this.getLevenskracht());
				Terrarium.getInstance().organismeVerwijderen(this.getPositie());
			}
		}
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {
	}

}
