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

	@Override
	public void carnivoorInteractie(Carnivoor carnivoor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void herbivoorInteractie(Herbivoor herbivoor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mensInteractie(Mens mens) {
		mensVrijtMetMens();		
	}

}
