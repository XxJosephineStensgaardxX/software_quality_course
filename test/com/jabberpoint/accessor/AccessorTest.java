package com.jabberpoint.accessor;

import com.jabberpoint.presentation.Presentation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccessorTest
{
	private static class TestAccessor extends Accessor
	{
		@Override
		public void loadFile(Presentation presentation, String filename)
		{
			throw new UnsupportedOperationException("Save not supported");
		}
		
		@Override
		public void saveFile(Presentation presentation, String filename)
		{
			throw new UnsupportedOperationException("Save not supported");
		}
	}
	
	@Test
	void ensureExtension_nullOrEmptyInput_ShouldThrowException()
	{
		TestAccessor accessor = new TestAccessor();
		
		assertThrows(IllegalArgumentException.class,
					 () -> accessor.ensureExtension(null),
					 "Null input should throw IllegalArgumentException");
		
		assertThrows(IllegalArgumentException.class,
					 () -> accessor.ensureExtension(""),
					 "Empty string should throw IllegalArgumentException");
	}
	
	@Test
	void loadFileAndSaveFile_onTestAccessor_ShouldThrowUnsupportedOperation() {
		TestAccessor accessor = new TestAccessor();
		Presentation presentation = new Presentation();
		
		// Test loadFile
		assertThrows(UnsupportedOperationException.class,
					 () -> accessor.loadFile(presentation, "test"),
					 "loadFile should throw UnsupportedOperationException");
		
		// Test saveFile
		assertThrows(UnsupportedOperationException.class,
					 () -> accessor.saveFile(presentation, "test"),
					 "saveFile should throw UnsupportedOperationException");
	}
	
	@Test
	void ensureExtension_filenamesWithSpecialCharacters_ShouldAppendXmlExtension()
	{
		TestAccessor accessor = new TestAccessor();
		
		// Test filename with invalid characters
		String[] invalidFilenames = {
				"file<name.xml",
				"file>name.xml",
				"file:name.xml",
				"file\"name.xml",
				"file/name.xml",
				"file\\name.xml",
				"file|name.xml",
				"file?name.xml",
				"file*name.xml"
		};
		
		for (String invalidFilename : invalidFilenames)
		{
			String result = accessor.ensureExtension(invalidFilename);
			assertTrue(result.endsWith(".xml"),
					   "Should still append .xml extension to filename with special characters");
		}
	}
	
	@Test
	void ensureExtension_extremelyLongFilename_ShouldAppendXmlExtension()
	{
		TestAccessor accessor = new TestAccessor();
		
		StringBuilder longFilenameBuilder = new StringBuilder();
		for (int i = 0; i < 1000; i++)
		{
			longFilenameBuilder.append("a");
		}
		
		String longFilename = longFilenameBuilder.toString();
		
		String result = accessor.ensureExtension(longFilename);
		assertTrue(result.endsWith(".xml"),
				   "Should handle extremely long filenames");
	}
	
	@Test
	void ensureExtension_variousValidInputs_ShouldAppendXmlIfMissing()
	{
		TestAccessor accessor = new TestAccessor();
		
		// Test adding extension to a filename without extension
		String filename1 = "test";
		assertEquals("test.xml", accessor.ensureExtension(filename1),
					 "Should add .xml extension");
		
		// Test filename already with extension
		String filename2 = "test.XML";
		assertEquals("test.XML", accessor.ensureExtension(filename2),
					 "Should not modify filename with existing extension");
		
		// Test filename with lowercase extension
		String filename3 = "test.xml";
		assertEquals("test.xml", accessor.ensureExtension(filename3),
					 "Should not modify filename with correct extension");
	}
	
	@Test
	void accessorConstants_defaultValues_ShouldMatchExpected()
	{
		assertEquals("Demonstration presentation", Accessor.DEMO_NAME,
					 "DEMO_NAME should match expected value");
		assertEquals(".xml", Accessor.DEFAULT_EXTENSION,
					 "DEFAULT_EXTENSION should match expected value");
	}
}