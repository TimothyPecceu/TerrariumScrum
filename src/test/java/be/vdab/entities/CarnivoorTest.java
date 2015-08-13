package be.vdab.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.vdab.valueobjects.Positie;

public class CarnivoorTest {

	private Carnivoor carnivoorLinks;
	private Terrarium terrarium;

	@Before
	public void before() {
		terrarium = new Terrarium(6,6);
		carnivoorLinks = new Carnivoor(terrarium);		
		terrarium.organismeToevoegen(carnivoorLinks);
	}

	@Test
	public void carnivoorBegintMetLevenskrachtNul(){
		assertEquals(0, carnivoorLinks.getLevenskracht());
	}
	
	@Test
	public void tweeEvenSterkeCarnivorenVechtenErVeranderdNiets() {
		int levenkrachtCarnivoorLinks = carnivoorLinks.getLevenskracht();
		carnivoorLinks.actie(new Carnivoor(terrarium));
		assertEquals(levenkrachtCarnivoorLinks, carnivoorLinks.getLevenskracht());
	}
	
	@Test
	public void levenskrachtCarnivoorGaatOmhoogMetLevenskrachtHerbivoor(){
		Herbivoor herbivoor = new Herbivoor(terrarium);
		terrarium.organismeToevoegen(herbivoor);
		int levenskrachtCarnivoor = carnivoorLinks.getLevenskracht()+herbivoor.getLevenskracht();
		carnivoorLinks.actie(herbivoor);
		assertEquals(levenskrachtCarnivoor, carnivoorLinks.getLevenskracht());
	}
	
	@Test
	public void levenskrachtCarnivoorGaatOmhoogMetLevenskrachtZwakkereCarnivoor(){		
		terrarium.organismeToevoegen(carnivoorLinks);
		Carnivoor carnivoorRechts = new Carnivoor(terrarium);
		terrarium.organismeToevoegen(carnivoorRechts);
		carnivoorLinks.verhoogLevenskracht(2);
		carnivoorRechts.verhoogLevenskracht(1);
		int levenskrachtCarnivoor = carnivoorLinks.getLevenskracht()+carnivoorRechts.getLevenskracht();
		carnivoorLinks.actie(carnivoorRechts);
		assertEquals(levenskrachtCarnivoor, carnivoorLinks.getLevenskracht());
	}
	
	@Test
	public void eenCarnivoorNaastEenPlantDoetNiets(){
		Positie positieCarnivoor = carnivoorLinks.getPositie();
		carnivoorLinks.actie(new Plant(terrarium));
		assertEquals(positieCarnivoor, carnivoorLinks.getPositie());
	}
	
	@Test
	public void carnivoorMetLegeRechterzijdeDoetAtRandom1StapNaarBovenOnderLinksOfRechts(){
		Positie positieCarnivoor = carnivoorLinks.getPositie();
		terrarium.clearTerrarium();
		int originelePositie = (positieCarnivoor.getxLocatie() + positieCarnivoor.getyLocatie())%2;
		carnivoorLinks.actie(null);
		int tweedePositie = (carnivoorLinks.getPositie().getxLocatie() +carnivoorLinks.getPositie().getyLocatie())%2;
		assertTrue((originelePositie - tweedePositie) != 0);
	}
	
}
