package be.vdab.valueobjects;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import be.vdab.entities.Terrarium;

public class PositieTest {

	@Test(expected = IllegalArgumentException.class)
	public void xLocatieKleinerDanNulIsFout() {
		new Positie(-1, 0, new Terrarium(5, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void yLocatieKleinerDanNulIsFout() {
		new Positie(0, -1, new Terrarium(5, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void xLocatieGroterDanTerrariumBreedteIsFout() {
		new Positie(6, 0, new Terrarium(5, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void yLocatieGroterDanTerrariumHoogteIsFout() {
		new Positie(0, 6, new Terrarium(5, 5));
	}

	@Test
	public void xLocatieVan3IsCorrect() {
		Positie positie = new Positie(3, 0, new Terrarium(5, 5));
		assertNotNull(positie);
	}

	@Test
	public void yLocatieVan3IsCorrect() {
		Positie positie = new Positie(0, 3, new Terrarium(5, 5));
		assertNotNull(positie);
	}
}
