package dev.medalhas.pdfgenerator.file;

import static dev.medalhas.pdfgenerator.settings.Settings.TOP_MARGIN;
import static org.apache.pdfbox.pdmodel.common.PDRectangle.A4;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import dev.medalhas.pdfgenerator.Options;
import dev.medalhas.pdfgenerator.barcode.BarcodeProcessor;
import dev.medalhas.pdfgenerator.text.TextProcessor;
import net.datafaker.Faker;

public class FileGenerator {

	public void randomPdf(String fileName, Options options) throws IOException {
		Faker faker = new Faker();
		List<String> paragraphs = faker.lorem().paragraphs(10);
		
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				float offsetY = A4.getHeight() - TOP_MARGIN;

				if (options.isText()) {
					TextProcessor textProcessor = new TextProcessor(paragraphs);
					textProcessor.process(page, contentStream, offsetY);
					offsetY = textProcessor.getOffsetY();
				}
				
				if (options.isBarcode()) {
					BarcodeProcessor barcodeProcessor = new BarcodeProcessor(document);
					barcodeProcessor.process(page, contentStream, offsetY);
				}
				
			}

			document.save(fileName);
		}
	
	}
}
