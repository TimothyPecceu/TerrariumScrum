package be.vdab.entities;

import be.vdab.valueobjects.Positie;

public class Mens extends Dier{
	
	
	public Mens (Terrarium terrarium){
		setTerrarium(terrarium);
		setPositie(new Positie());
	}

	@Override
	public void actie(Organisme organisme) {
		if (organisme==null){
			stap();
		} else {
			organisme.mensInteractie(this);
		}
		
	}
	
	public void mensVrijtMetMens(){
		Mens nieuweMens = new Mens(getTerrarium());
		getTerrarium().organismeToevoegen(nieuweMens);
	}
	
	private void mensVecht(Dier dier) {
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
	public void carnivoorInteractie(Carnivoor carnivoor) {
		mensVecht(carnivoor);		
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {}

	@Override
	public void mensInteractie(Mens mens) {
		mensVrijtMetMens();		
	}

}
