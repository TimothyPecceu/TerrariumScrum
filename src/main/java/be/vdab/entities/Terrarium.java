package be.vdab.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import be.vdab.valueobjects.Positie;

public class Terrarium {

	private Map<Positie, Organisme> terrarium;
	private int dag;
	private boolean vol;
	private static int hoogte = 6;
	private static int breedte = 6;
	int aantal;

	public Terrarium(int hoogte, int breedte) {
		terrarium = new HashMap<>();
		Terrarium.hoogte = hoogte;
		Terrarium.breedte = breedte;
		dag = 1;
		vol = false;
		aantal = breedte * hoogte;

		if (aantal < 12) {
			aantal = 12;
		}
		try {
			int random = new Random().nextInt(aantal / 10) + 1;
			for (int i = 0; i != random; i++) {
				organismeToevoegen(new Plant(this));
			}

			random = new Random().nextInt(aantal / 10) + 1;
			for (int i = 0; i != random; i++) {
				organismeToevoegen(new Herbivoor(this));
			}

			random = new Random().nextInt(aantal / 10) + 1;
			for (int i = 0; i != random; i++) {
				organismeToevoegen(new Carnivoor(this));
			}

			random = new Random().nextInt(aantal / 10) + 1;
			for (int i = 0; i != random; i++) {
				organismeToevoegen(new Mens(this));
			}
		} catch (IllegalArgumentException ex) {
			vol = true;
		}

	}

	public void volgendeDag() {

		for (Organisme organisme : getTerrarium()) {
			if (organisme.getPositie().getxLocatie() < (breedte - 1)) {
				Positie pos = new Positie(organisme.getPositie().getyLocatie(),
						organisme.getPositie().getxLocatie() + 1);
				try {
					organisme.actie(terrarium.get(pos));
				} catch (IllegalArgumentException ex) {
					vol = true;
				}
			}
		}
		int random = new Random().nextInt(aantal / 10) + 1;
		for (int i = 0; i != random; i++) {
			try {
				organismeToevoegen(new Plant(this));
			} catch (IllegalArgumentException ex) {
				vol = true;
			}
		}
		dag++;
	}

	public Set<Organisme> getTerrarium() {
		return new HashSet<>(terrarium.values());
	}

	public Map<Positie, Organisme> getTerrariumMap() {
		return terrarium;
	}

	public void organismeToevoegen(Organisme organisme) {
		if (getTerrarium().size() < (breedte * hoogte)) {
			if (terrarium.get(organisme.getPositie()) == null) {
				terrarium.put(organisme.getPositie(), organisme);
			} else {
				while (!isLeeg(organisme.getPositie())) {
					organisme.setPositie(new Positie());
				}
				terrarium.put(organisme.getPositie(), organisme);
			}
		} else {
			throw new IllegalArgumentException();

		}
	}

	public void organismeVerwijderen(Positie positie) {
		terrarium.remove(positie);
	}

	public void organismeVerplaatsen(Organisme organisme, Positie positie) {
		Positie oudePositie = organisme.getPositie();
		organisme.setPositie(positie);
		organismeVerwijderen(oudePositie);
		organismeToevoegen(organisme);
	}

	public boolean isLeeg(Positie positie) {
		return !terrarium.containsKey(positie);
	}

	public int getDag() {
		return dag;
	}

	public Organisme[][] getOrganismes() {
		Organisme[][] weergave = new Organisme[hoogte][breedte];
		for (Entry<Positie, Organisme> entry : terrarium.entrySet()) {
			weergave[entry.getKey().getyLocatie()][entry.getKey().getxLocatie()] = entry.getValue();
		}
		return weergave;
	}

	public void clearTerrarium() {
		terrarium.clear();
	}

	public void printTerrarium() {

		Organisme[][] weergave = getOrganismes();
		System.out.println("Dag: " + dag);
		System.out.println("--------------------");
		for (int y = 0; y != hoogte; y++) {
			System.out.println(' ');
			for (int x = 0; x != breedte; x++) {
				if (weergave[y][x] != null) {
					System.out.print(weergave[y][x].getClass().getSimpleName().charAt(0) + " ");
				} else {
					System.out.print(". ");
				}
			}
		}
		System.out.println();
		if (vol) {
			System.out.println("Terrarium zit vol!");
		}
		System.out.println("--------------------");
	}

	public static int getHoogte() {
		return hoogte;
	}

	public static int getBreedte() {
		return breedte;
	}

}
