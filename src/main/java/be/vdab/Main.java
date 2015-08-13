package be.vdab;

import java.util.Scanner;

import be.vdab.entities.Terrarium;

public class Main {

	public static void main(String[] args) {
		Terrarium terrarium = new Terrarium(6,6);		
		terrarium.printTerrarium();
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		while(!input.equalsIgnoreCase("s")){
			terrarium.volgendeDag();
			terrarium.printTerrarium();
			input = scanner.nextLine();
		}
		scanner.close();
	}

}
