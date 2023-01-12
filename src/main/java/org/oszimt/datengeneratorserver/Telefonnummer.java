package org.oszimt.datengeneratorserver;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped

public class Telefonnummer {

	public String randomTelefonnummer() {
		Random rand = new Random();
		String randomZahl = "01";
		for (int i = 0; i < 10; i++) {
			int upperbound = 10;
			int int_random = rand.nextInt(upperbound);
			randomZahl += int_random;
		}
		return randomZahl;
	}
}


