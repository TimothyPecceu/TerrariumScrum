package be.vdab.entities;

import java.util.Random;

import be.vdab.valueobjects.Positie;
import be.vdab.valueobjects.Richting;

public abstract class Dier extends Organisme {

	private int levenskracht = 1;

	public abstract void actie(Organisme organisme);

	public int getLevenskracht() {
		return levenskracht;
	}

	public void stap() {
		Richting[] alleRichtingen = Richting.values();
		Positie positie = new Positie(this.getPositie().getyLocatie(), this.getPositie().getxLocatie());
		Terrarium terrarium = getTerrarium();
		
		while (positie.equals(getPositie())){
			Richting randomRichting = alleRichtingen[new Random().nextInt(alleRichtingen.length)];
			switch (randomRichting) {
			case OMHOOG:
				if((positie.getyLocatie()>0) && terrarium.isLeeg(new Positie(positie.getyLocatie()-1, positie.getxLocatie()))){
					positie.setyLocatie(positie.getyLocatie() - 1);
				}	
				break;
			case OMLAAG:
				if((positie.getyLocatie()<Terrarium.getHoogte()-1) && terrarium.isLeeg(new Positie(positie.getyLocatie()+1, positie.getxLocatie()))){
					positie.setyLocatie(positie.getyLocatie() + 1);
				}
				break;
			case LINKS:
				if((positie.getxLocatie()>0)&& terrarium.isLeeg(new Positie(positie.getyLocatie(), positie.getxLocatie()-1))){
					positie.setxLocatie(positie.getxLocatie() - 1);
				}
				break;
			case RECHTS:
				if((positie.getxLocatie()<Terrarium.getBreedte()-1)&& terrarium.isLeeg(new Positie(positie.getyLocatie(), positie.getxLocatie()+1))){
					positie.setxLocatie(positie.getxLocatie() + 1);
				}
				break;
			}
		}
		
		terrarium.organismeVerplaatsen(this, positie);
		
	}

	public void verhoogLevenskracht(int verhoging) {
		levenskracht = levenskracht + verhoging;
	}
}
