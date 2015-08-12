package be.vdab.valueobjects;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PositieTest {

	@Test(expected = IllegalArgumentException.class)
	public void xLocatieKleinerDanNulIsFout() {
		new Positie(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void yLocatieKleinerDanNulIsFout() {
		new Positie(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void xLocatieGroterDan5IsFout() {
		new Positie(6, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void yLocatieGroterDan5IsFout() {
		new Positie(0, 6);
	}

	@Test
	public void xLocatieVan3IsCorrect() {
		Positie positie = new Positie(3, 0);
		assertNotNull(positie);
	}

	@Test
	public void yLocatieVan3IsCorrect() {
		Positie positie = new Positie(0, 3);
		assertNotNull(positie);
	}
}
