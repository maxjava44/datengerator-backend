import java.util.Random;

public class Telefonnummer {

	public static String randomTelefonnummer() {
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


