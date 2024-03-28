package dev.medalhas.pdfgenerator;

public class Options {

	public Options(boolean barcode, boolean text, int paragraphs, int pages) {
		this.barcode = barcode;
		this.text = text;
		this.paragraphs = paragraphs;
		this.pages = pages;
	}

	private boolean barcode;
	private boolean text;
	private int paragraphs;
	private int pages;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public boolean isBarcode() {
		return barcode;
	}

	public void setBarcode(boolean barcode) {
		this.barcode = barcode;
	}

	public boolean isText() {
		return text;
	}

	public void setText(boolean text) {
		this.text = text;
	}

	public int getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(int paragraphs) {
		this.paragraphs = paragraphs;
	}
}
