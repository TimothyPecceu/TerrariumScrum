package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public class Herbivoor extends Dier {

	public Herbivoor() {
		setPositie(new Positie());
	}

	public void actie(Organisme organisme) {
		if (organisme == null) {
			stap();
		} else {
			organisme.herbivoorInteractie(this);
		}
	}

	private void herbivoorVrijtHerbivoor(Herbivoor herbivoor) {	
			Herbivoor nieuweHerbivoor = new Herbivoor();
			Terrarium.getInstance().organismeToevoegen(nieuweHerbivoor);
	}

	public void herbivoorWordtGegeten(Carnivoor carnivoor) {
		carnivoor.verhoogLevenskracht(this.getLevenskracht());
		Terrarium.getInstance().organismeVerwijderen(this.getPositie());
	}

	@Override
	public void carnivoorInteractie(Carnivoor carnivoor) {
		herbivoorWordtGegeten(carnivoor);
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {
		herbivoorVrijtHerbivoor(herbivoor);
	}
}
