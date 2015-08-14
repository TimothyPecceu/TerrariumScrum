package be.vdab.entities;

import java.util.Random;

import be.vdab.valueobjects.Positie;
import be.vdab.valueobjects.Richting;

public abstract class Dier extends Organisme {

	private int levenskracht = 0;

	public abstract void actie(Organisme organisme);

	public int getLevenskracht() {
		return levenskracht;
	}

	public void stap() {
		Richting[] alleRichtingen = Richting.values();
		Positie positie = new Positie(this.getPositie().getyLocatie(), this.getPositie().getxLocatie(),this.getTerrarium());
		Terrarium terrarium = getTerrarium();
		Positie nieuwePositie = null;
		
		while (positie.equals(getPositie())){
			Richting randomRichting = alleRichtingen[new Random().nextInt(alleRichtingen.length)];
			switch (randomRichting) {
			case OMHOOG:
				nieuwePositie = new Positie(positie.getyLocatie()-1, positie.getxLocatie(),this.getTerrarium());
				if(terrarium.isAanvaard(nieuwePositie) && terrarium.isLeeg(nieuwePositie)){
					positie.setyLocatie(positie.getyLocatie() - 1);
				}	
				break;
			case OMLAAG:
				nieuwePositie =new Positie(positie.getyLocatie()+1, positie.getxLocatie(),this.getTerrarium());
				if(terrarium.isAanvaard(nieuwePositie) && terrarium.isLeeg(nieuwePositie)){
					positie.setyLocatie(positie.getyLocatie() + 1);
				}
				break;
			case LINKS:
				nieuwePositie =new Positie(positie.getyLocatie(), positie.getxLocatie()-1,this.getTerrarium());
				if(terrarium.isAanvaard(nieuwePositie) && terrarium.isLeeg(nieuwePositie)){
					positie.setxLocatie(positie.getxLocatie() - 1);
				}
				break;
			case RECHTS:
				nieuwePositie =new Positie(positie.getyLocatie(), positie.getxLocatie()+1,this.getTerrarium());
				if(terrarium.isAanvaard(nieuwePositie) && terrarium.isLeeg(nieuwePositie)){
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
