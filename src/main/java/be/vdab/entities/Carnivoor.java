package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public class Carnivoor extends Dier {

	public Carnivoor(Terrarium terrarium) {
		setTerrarium(terrarium);
		setPositie(new Positie());
	}

	public void actie(Organisme organisme) {
		if (organisme == null) {
			stap();
		} else {
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
				getTerrarium().organismeVerwijderen(carnivoor.getPositie());
			} else {
				carnivoor.verhoogLevenskracht(this.getLevenskracht());
				getTerrarium().organismeVerwijderen(this.getPositie());
			}
		}
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {
	}

	@Override
	public void mensInteractie(Mens mens) {
		// TODO Auto-generated method stub
		
	}

}
