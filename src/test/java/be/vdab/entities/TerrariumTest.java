package be.vdab.entities;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class TerrariumTest {

	private Terrarium terrarium;

	@Before
	public void before() {
		terrarium = new Terrarium(6,6);
	}

	@Test
	public void matrixNietLeeg() {
		assertFalse(terrarium.getTerrarium().isEmpty());
	}
	
	@Test
	public void plantToevoegenIsGelukt(){
		terrarium.clearTerrarium();
		terrarium.organismeToevoegen(new Plant(terrarium));
	}
	
	@Test
	public void herbivoorToevoegenIsGelukt(){
		terrarium.clearTerrarium();
		terrarium.organismeToevoegen(new Herbivoor(terrarium));
	}
	
	@Test
	public void carnivoorToevoegenIsGelukt(){
		terrarium.clearTerrarium();
		terrarium.organismeToevoegen(new Carnivoor(terrarium));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void terrariumKanMaximumZessenDertigOrganismenBevatten(){
		for(int i=0; i != 37;i++){
			terrarium.organismeToevoegen(new Plant(terrarium));
		}
	}
	
}
