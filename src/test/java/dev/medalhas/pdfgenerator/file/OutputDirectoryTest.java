package dev.medalhas.pdfgenerator.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class OutputDirectoryTest {

	@Test
	public void testIsValidWithValidDirectory() {
		// Create a temporary directory for testing
		Path tempDirectory;
		try {
			tempDirectory = Files.createTempDirectory("test");
		} catch (Exception e) {
			fail("Failed to create temporary directory for testing");
			return;
		}

		OutputDirectory outputDir = new OutputDirectory(tempDirectory.toString());
		assertTrue(outputDir.isValid());

		// Clean up
		deleteDirectory(tempDirectory.toFile());
	}

	@Test
	public void testIsValidWithNonexistentDirectory() {
		OutputDirectory outputDir = new OutputDirectory("nonexistent-directory");
		assertFalse(outputDir.isValid());
	}

	@Test
	public void testIsValidWithNonDirectory() {
		// Create a temporary file for testing
		Path tempFile;
		try {
			tempFile = Files.createTempFile("test", ".txt");
		} catch (Exception e) {
			fail("Failed to create temporary file for testing");
			return;
		}

		OutputDirectory outputDir = new OutputDirectory(tempFile.toString());
		assertFalse(outputDir.isValid());

		// Clean up
		tempFile.toFile().delete();
	}

	@Test
	public void testIsValidWithUnwritableDirectory() {
		// Create a temporary directory with no write permissions
		Path tempDirectory;
		try {
			tempDirectory = Files.createTempDirectory("test");
			tempDirectory.toFile().setWritable(false);
		} catch (Exception e) {
			fail("Failed to create temporary directory for testing");
			return;
		}

		OutputDirectory outputDir = new OutputDirectory(tempDirectory.toString());
		assertFalse(outputDir.isValid());

		// Clean up
		tempDirectory.toFile().setWritable(true);
		deleteDirectory(tempDirectory.toFile());
	}

	@Test
	public void testGetOutputFile() {
		OutputDirectory outputDir = new OutputDirectory("output-directory");
		File outputFile = outputDir.getOutputFile("output.txt");
		assertEquals("output-directory/output.txt", outputFile.getPath());
	}

	private void deleteDirectory(File directory) {
		if (directory == null || !directory.exists()) {
			return;
		}
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		}
		directory.delete();
	}
}
