package dev.medalhas.pdfgenerator.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputDirectory {
	private Path outputPath;
	
	public OutputDirectory(String output) {
		outputPath = Paths.get(output);
	}
	
	public boolean isValid() {
		File outputDirectory = outputPath.toFile();
		
		if (!outputDirectory.exists()) {
			System.out.println("Output directory doesn't exist!");
			return false;
		}
		
		if (!outputDirectory.isDirectory()) {
			System.out.println("It's not a directory!");
			return false;
		}
		
		if (!outputDirectory.canWrite()) {
			System.out.println("You don't have permissions to write on this directory!");
			return false;
		}
		return true;
	}
	
	public File getOutputFile(String fileName) {
		return outputPath.resolve(fileName).toFile();
	}

}
