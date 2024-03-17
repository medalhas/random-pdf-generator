package dev.medalhas.pdfgenerator;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public interface GeneratorProcessor {

	public void process(PDPage page, PDPageContentStream contentStream, float offset) throws IOException;

	public float getOffsetY();
}
