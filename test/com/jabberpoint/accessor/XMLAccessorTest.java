package com.jabberpoint.accessor;

import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.render.Element;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class XMLAccessorTest {
	
	private XMLAccessor xmlAccessor;
	private Presentation presentation;
	
	@BeforeEach
	void setUp() {
		xmlAccessor = new XMLAccessor();
		presentation = new Presentation();
	}
	
	@Test
	void saveFileAndLoadFile_withValidPresentation_ShouldPersistAndRestoreData() throws IOException
	{
		// Prep
		presentation.setTitle("Test Presentation");
		Slide slide = new Slide();
		slide.setTitle("Test Slide");
		slide.addSlideItem(new TextItem(1, "Test Content"));
		presentation.append(slide);
		
		// Create a temporary file for testing
		Path tempFile = Files.createTempFile("test", ".xml");
		
		try
		{
			Path dtdFile = new File(tempFile.getParent().toFile(), "jabberpoint.dtd").toPath();
			Files.writeString(dtdFile, "<!ELEMENT presentation (showtitle,slide+)>");
			
			xmlAccessor.saveFile(presentation, tempFile.toString());
			presentation.clear();
			xmlAccessor.loadFile(presentation, tempFile.toString());
			
			// Verify loaded data
			assertEquals("Test Presentation", presentation.getTitle());
			assertEquals(1, presentation.getSize(), "Presentation should have 1 slide");
			
			Slide loadedSlide = presentation.getSlide(0);
			assertEquals("Test Slide", loadedSlide.getTitle());
			assertEquals(1, loadedSlide.getSlideItems().size());
			
			TextItem loadedItem = (TextItem) loadedSlide.getSlideItem(0);
			assertEquals(1, loadedItem.getLevel());
			assertEquals("Test Content", loadedItem.getText());
		}
		finally
		{
			Files.deleteIfExists(tempFile);
			Files.deleteIfExists(new File(tempFile.getParent().toFile(), "jabberpoint.dtd").toPath());
		}
	}
	
	@Test
	void saveFile_withEmptyPresentation_ShouldCreateValidXMLStructure() throws IOException
	{
		// Create a temporary file for testing
		Path tempFile = Files.createTempFile("empty", ".xml");
		
		try
		{
			Path dtdFile = new File(tempFile.getParent().toFile(), "jabberpoint.dtd").toPath();
			Files.writeString(dtdFile, "<!ELEMENT presentation (showtitle,slide+)>");
			xmlAccessor.saveFile(presentation, tempFile.toString());
			assertTrue(Files.exists(tempFile));
			String fileContent = Files.readString(tempFile);
			
			// Check basic XML structure
			assertTrue(fileContent.contains("<?xml version=\"1.0\"?>"));
			assertTrue(fileContent.contains("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">"));
			assertTrue(fileContent.contains("<presentation>"));
			assertTrue(fileContent.contains("</presentation>"));
		}
		finally
		{
			Files.deleteIfExists(tempFile);
			Files.deleteIfExists(new File(tempFile.getParent().toFile(), "jabberpoint.dtd").toPath());
		}
	}
	
	@Test
	void loadSlideItem_withValidTextElement_ShouldAddTextItemToSlide()
	{
		Slide slide = new Slide();
		org.w3c.dom.Element mockElement = mock(org.w3c.dom.Element.class);
		org.w3c.dom.NamedNodeMap mockAttributes = mock(org.w3c.dom.NamedNodeMap.class);
		org.w3c.dom.Node mockLevelNode = mock(org.w3c.dom.Node.class);
		org.w3c.dom.Node mockKindNode = mock(org.w3c.dom.Node.class);
		
		// Prepare mock behavior
		when(mockElement.getAttributes()).thenReturn(mockAttributes);
		when(mockAttributes.getNamedItem("level")).thenReturn(mockLevelNode);
		when(mockLevelNode.getTextContent()).thenReturn("2");
		when(mockAttributes.getNamedItem("kind")).thenReturn(mockKindNode);
		when(mockKindNode.getTextContent()).thenReturn("text");
		when(mockElement.getTextContent()).thenReturn("Test Text");
		
		try
		{
			java.lang.reflect.Method method = XMLAccessor.class.getDeclaredMethod(
					"loadSlideItem", Slide.class, org.w3c.dom.Element.class);
			method.setAccessible(true);
			method.invoke(xmlAccessor, slide, mockElement);
			
			assertEquals(1, slide.getSize());
			TextItem addedItem = (TextItem) slide.getSlideItem(0);
			assertEquals(2, addedItem.getLevel());
			assertEquals("Test Text", addedItem.getText());
		}
		catch (Exception e)
		{
			fail("Failed to invoke loadSlideItem method: " + e.getMessage());
		}
	}
	
	@Test
	void saveFile_withNullPresentation_ShouldThrowNullPointerException()
	{
		// Verify saving null presentation throws an exception
		Path tempFile;
		try {
			tempFile = Files.createTempFile("null-test", ".xml");
		} catch (IOException e) {
			fail("Failed to create temporary file");
			return;
		}
		
		assertThrows(NullPointerException.class, () ->
							 xmlAccessor.saveFile(null, tempFile.toString()),
					 "Saving null presentation should throw NullPointerException"
		);
	}
	
	@Test
	void saveFile_withInvalidFilePath_ShouldThrowIOException()
	{
		String invalidPath = "/non/existent/directory/presentation.xml";
		
		assertThrows(IOException.class, () ->
							 xmlAccessor.saveFile(presentation, invalidPath),
					 "Saving to invalid path should throw IOException"
		);
	}
	
	@Test
	void saveFile_withRestrictedPath_ShouldThrowIOException()
	{
		String restrictedPath = "/root/restricted/presentation.xml";
		
		assertThrows(IOException.class, () ->
							 xmlAccessor.saveFile(presentation, restrictedPath),
					 "Saving to restricted path should throw IOException"
		);
	}
}