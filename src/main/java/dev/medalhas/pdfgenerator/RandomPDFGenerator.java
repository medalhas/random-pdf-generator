package dev.medalhas.pdfgenerator;

import java.io.IOException;

import dev.medalhas.pdfgenerator.file.FileGenerator;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "RandomPDFGenerator", description = "Random PDF Generator with text and barcodes")
public class RandomPDFGenerator implements Runnable {
	@Option(names = {  "-n", "--number" }, description = "Number of pdf's to generate")
	private int number = 1;
	
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "displays this help message")
    private boolean helpRequested = false;
    
	@Option(names = { "--barcode" }, description = "Generates with barcode", negatable = true, defaultValue = "true", fallbackValue = "true")
	private boolean barcode = true;

	@Option(names = { "--text" }, description = "Generates with text", negatable = true, defaultValue = "true", fallbackValue = "true")
	private boolean text = true;

	@Override
	public void run() {
		var options = new Options(barcode, text);
		try {
			for (int i = 1; i <= number; i++) {
				FileGenerator fileGenerator = new FileGenerator();
				String fileName = String.format("random_pdf_%d.pdf", i);
				fileGenerator.randomPdf(fileName, options);

				System.out.printf("The file %s was generated.", fileName);
			}
		} catch (IOException e) {
			System.out.println("An unexpected error has ocurred");
		}
	}

	public static void main(String[] args) {
		CommandLine cmd = new CommandLine(new RandomPDFGenerator());
		cmd.execute(args);
	}
}
