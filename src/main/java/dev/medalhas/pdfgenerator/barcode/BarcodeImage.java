package dev.medalhas.pdfgenerator.barcode;

import static com.google.zxing.BarcodeFormat.CODE_128;
import static dev.medalhas.pdfgenerator.settings.Settings.BARCODE_HEIGHT;
import static dev.medalhas.pdfgenerator.settings.Settings.BARCODE_WIDTH;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import java.awt.image.BufferedImage;

import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class BarcodeImage {
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private String barcodeData;
	
	public BarcodeImage(String barcodeData) {
		this.barcodeData = barcodeData;
	}

	public BufferedImage generateBarcodeImage() {
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			BitMatrix bitMatrix = multiFormatWriter.encode(barcodeData, CODE_128, BARCODE_WIDTH, BARCODE_HEIGHT);
			
			return toBufferedImage(bitMatrix);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private BufferedImage toBufferedImage(final BitMatrix matrix) {
		final int width = matrix.getWidth();
		final int height = matrix.getHeight();
		
		final BufferedImage image = new BufferedImage(width, height, TYPE_INT_RGB);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		
		return image;
	}

}
