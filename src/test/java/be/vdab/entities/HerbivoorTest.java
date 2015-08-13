package be.vdab.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.vdab.valueobjects.Positie;

public class HerbivoorTest {

	Herbivoor herbivoorLinks;
	Terrarium terrarium;
	@Before
	public void before(){
		terrarium = new Terrarium(6,6);
		herbivoorLinks= new Herbivoor(terrarium);
		
	}
	
	@Test
	public void LevenskrachtVanHerbivoorDiePlantEetStijgtMet1(){
		Plant plant = new Plant(terrarium);
		terrarium.organismeToevoegen(plant);
		int levenskrachtHerbivoor= herbivoorLinks.getLevenskracht()+1;
		herbivoorLinks.actie(plant);
		assertEquals(levenskrachtHerbivoor, herbivoorLinks.getLevenskracht());
	}
	
	@Test
	public void eenHerbivoorNaastEenCarnivoorDoetNiets(){
		Positie positieHerbivoor = herbivoorLinks.getPositie();
		herbivoorLinks.actie(new Carnivoor(terrarium));
		assertEquals(positieHerbivoor, herbivoorLinks.getPositie());
	}
	
	@Test
	public void eenHerbivoorNaastEenHerbivoorMaaktEenNieuweHerbivoor(){
		terrarium.clearTerrarium();
		terrarium.organismeToevoegen(herbivoorLinks);
		int aantal = terrarium.getTerrarium().size()+1;
		herbivoorLinks.actie(new Herbivoor(terrarium));
		assertEquals(aantal, terrarium.getTerrarium().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void eenHerbivoorKanGeenKinderenKrijgenInEenVolTerrarium(){
		for(int i = 0; i != 36; i++){
			terrarium.organismeToevoegen(new Herbivoor(terrarium));
		}
		herbivoorLinks.actie(new Herbivoor(terrarium));
	}
	
	@Test
	public void herbivoorMetLegeRechterzijdeDoetAtRandom1StapNaarBovenOnderLinksOfRechts(){
		terrarium.clearTerrarium();
		terrarium.organismeToevoegen(herbivoorLinks);
		Positie positieHerbivoor = herbivoorLinks.getPositie();
		int originelePositie = (positieHerbivoor.getxLocatie() + positieHerbivoor.getyLocatie())%2;
		herbivoorLinks.actie(null);
		int tweedePositie = (herbivoorLinks.getPositie().getxLocatie() + herbivoorLinks.getPositie().getyLocatie())%2;
		assertTrue((originelePositie - tweedePositie) != 0);
	}
	
	@Test
	public void herbivoorTegenRandGaatNietOutOfBounds(){
		Positie positieHerbivoor = new Positie(0, 0,terrarium);
		herbivoorLinks.setPositie(positieHerbivoor);
		int originelePositie = (positieHerbivoor.getxLocatie() + positieHerbivoor.getyLocatie())%2;
		herbivoorLinks.actie(null);
		int tweedePositie = (herbivoorLinks.getPositie().getxLocatie() + herbivoorLinks.getPositie().getyLocatie())%2;
		assertTrue(originelePositie - tweedePositie != 0);
		
	}
}
