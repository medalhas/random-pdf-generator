package dev.medalhas.pdfgenerator;

public class Options {
	public Options(boolean barcode, boolean text) {
		this.barcode = barcode;
		this.text = text;
	}

	private boolean barcode;
	private boolean text;

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
}
