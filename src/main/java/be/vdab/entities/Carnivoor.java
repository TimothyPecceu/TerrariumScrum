package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public class Carnivoor extends Dier {

	public Carnivoor(Terrarium terrarium) {
		setTerrarium(terrarium);
		setPositie(new Positie(terrarium));
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

	private void carnivoorVecht(Dier dier) {
		if (this.getLevenskracht() != dier.getLevenskracht()) {
			if (this.getLevenskracht() > dier.getLevenskracht()) {
				this.verhoogLevenskracht(dier.getLevenskracht());
				getTerrarium().organismeVerwijderen(dier.getPositie());
			} else {
				dier.verhoogLevenskracht(this.getLevenskracht());
				getTerrarium().organismeVerwijderen(this.getPositie());
			}
		}
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {
	}

	@Override
	public void mensInteractie(Mens mens) {
		carnivoorVecht(mens);	
	}

}
