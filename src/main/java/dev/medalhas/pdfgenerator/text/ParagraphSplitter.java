package dev.medalhas.pdfgenerator.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ParagraphSplitter {
	private List<String> lines;
	

	public ParagraphSplitter(String paragraph, PDType1Font font, float fontSize, float width) {
		this.setLines(splitTextIntoLines(paragraph, font, fontSize, width));
	}

	private List<String> splitTextIntoLines(String paragraph, PDType1Font font, float fontSize, float width) {
		try {
			List<String> lines = new ArrayList<>();
			StringBuilder currentLine = new StringBuilder();
			float currentWidth = 0;

			for (String word : paragraph.split("\\s+")) {
				float wordWidth = getStringWidth(word, font, fontSize);

				if (currentWidth + wordWidth > width) {
					lines.add(currentLine.toString());
					currentLine.setLength(0);
					currentWidth = 0;
				}

				if (currentLine.length() > 0) {
					currentLine.append(" ");
					currentWidth += getStringWidth(" ", font, fontSize);
				}

				currentLine.append(word);
				currentWidth += wordWidth;
			}

			lines.add(currentLine.toString());
			return lines;
		} catch (IOException e) {
			// if any error occurs returns the paragraph
			List<String> lines = new ArrayList<>();
			lines.add(paragraph);
			return lines;
		}
	}

	private float getStringWidth(String text, PDType1Font font, float fontSize) throws IOException {
		return font.getStringWidth(text) * fontSize / 1000f;
	}

	public List<String> getLines() {
		return lines;
	}

	private void setLines(List<String> lines) {
		this.lines = lines;
	}

}
