package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public class Herbivoor extends Dier {

	public Herbivoor(Terrarium terrarium) {
		setTerrarium(terrarium);
		setPositie(new Positie());
	}

	public void actie(Organisme organisme) {
		if (organisme == null) {
			stap();
		} else {
			organisme.herbivoorInteractie(this);
		}
	}

	private void herbivoorVrijtHerbivoor() {	
			Herbivoor nieuweHerbivoor = new Herbivoor(getTerrarium());
			getTerrarium().organismeToevoegen(nieuweHerbivoor);
	}

	public void herbivoorWordtGegeten(Dier dier) {
		dier.verhoogLevenskracht(this.getLevenskracht());
		getTerrarium().organismeVerwijderen(this.getPositie());
	}

	@Override
	public void carnivoorInteractie(Carnivoor carnivoor) {
		herbivoorWordtGegeten(carnivoor);
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {
		herbivoorVrijtHerbivoor();
	}

	@Override
	public void mensInteractie(Mens mens) {
		herbivoorWordtGegeten(mens);	
	}
}
