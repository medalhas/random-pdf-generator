package dev.medalhas.pdfgenerator.barcode;

import java.util.Random;

public class RandomBarcodeGenerator {
	private static final String[] VALUES = { 
			"PT123456799",
			"PT301440000468",
			"PT301440000973",
			"PT301440000771",
			"PT301340954980",
			"PT301440002589",
			"PT301340942957" };

	public static String getRandomValue() {
		Random random = new Random();
		int index = random.nextInt(VALUES.length);
		return VALUES[index];
	}

}

