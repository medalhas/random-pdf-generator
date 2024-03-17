package dev.medalhas.pdfgenerator.barcode;

import static dev.medalhas.pdfgenerator.settings.Settings.MARGIN_AFTER_TEXT;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import dev.medalhas.pdfgenerator.GeneratorProcessor;

public class BarcodeProcessor implements GeneratorProcessor{
	
	private String barcodeData;
	private PDDocument document;
	private float offsetY;
	
	public BarcodeProcessor(PDDocument document) {
		this.barcodeData = RandomBarcodeGenerator.getRandomValue();
		this.document = document;
	}

	@Override
	public void process(PDPage page, PDPageContentStream contentStream, float offset) throws IOException {
		BufferedImage barcodeImage = new BarcodeImage(barcodeData).generateBarcodeImage();

		PDImageXObject pdImage = LosslessFactory.createFromImage(document, barcodeImage);
		float pageWidth = page.getMediaBox().getWidth();
		float imageWidth = pdImage.getWidth();
		float x = (pageWidth - imageWidth) / 2;

		float y = offset - MARGIN_AFTER_TEXT;
		float width = pdImage.getWidth();
		float height = pdImage.getHeight();

		contentStream.drawImage(pdImage, x, y, width, height);
		offsetY = offset - height - MARGIN_AFTER_TEXT;
	}

	public float getOffsetY() {
		return offsetY;
	}
}
