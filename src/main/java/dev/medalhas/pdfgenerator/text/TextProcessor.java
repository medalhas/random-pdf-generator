package dev.medalhas.pdfgenerator.text;

import static dev.medalhas.pdfgenerator.settings.Settings.FONT_SIZE;
import static dev.medalhas.pdfgenerator.settings.Settings.FONT_USED;
import static dev.medalhas.pdfgenerator.settings.Settings.MARGIN;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import dev.medalhas.pdfgenerator.GeneratorProcessor;


public class TextProcessor implements GeneratorProcessor {
	private float fontHeight;
	private float offsetY;
	private List<String> paragraphs;
	

	public TextProcessor(List<String> paragraphs) {
		float ascent = FONT_USED.getFontDescriptor().getAscent() / 1000 * FONT_SIZE;
		float descent = FONT_USED.getFontDescriptor().getDescent() / 1000 * FONT_SIZE;
		this.fontHeight = ascent - descent;
		this.paragraphs = paragraphs;
	}

	@Override
	public void process(PDPage page, PDPageContentStream contentStream, float offset) throws IOException {
		setOffsetY(offset);;

		float textWidth = page.getMediaBox().getWidth() - MARGIN * 2;

		contentStream.beginText();
		contentStream.setFont(FONT_USED, FONT_SIZE);
		contentStream.newLineAtOffset(MARGIN, getOffsetY());
		
		for (String paragraph : paragraphs) {

			ParagraphSplitter splitter = new ParagraphSplitter(paragraph, FONT_USED, FONT_SIZE , textWidth);
			
			List<String> lines = splitter.getLines();
			
			int size = lines.size();
			for (int index = 0; index < size; index++) {
				String line = lines.get(index);
				float charSpacing = 0;
				if (line.length() > 1 && index != size - 1) {
					float width = FONT_USED.getStringWidth(line) / 1000 * FONT_SIZE;
					float freeSpace = textWidth - width;
					charSpacing = freeSpace / (line.length() - 1);
				} else {
					charSpacing = 0;
				}

				contentStream.setCharacterSpacing(charSpacing);
				contentStream.showText(line);
				contentStream.newLine();
				contentStream.newLineAtOffset(0, -15);
				setOffsetY(getOffsetY() + (-15 * 2 + fontHeight));
			}
			contentStream.newLineAtOffset(0, -5);
			setOffsetY(getOffsetY() - 5);
		}
		contentStream.endText();

	}

	public float getOffsetY() {
		return offsetY;
	}

	private void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
}
