package com.jabberpoint.accessor;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.BitmapItem;
import com.jabberpoint.slide.item.SlideItem;
import com.jabberpoint.slide.item.TextItem;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class XMLAccessor extends Accessor
{
	protected static final String DEFAULT_API_TO_USE = "dom";
	protected static final String SHOWTITLE = "showtitle";
	protected static final String SLIDETITLE = "title";
	protected static final String SLIDE = "slide";
	protected static final String ITEM = "item";
	protected static final String LEVEL = "level";
	protected static final String KIND = "kind";
	protected static final String TEXT = "text";
	protected static final String IMAGE = "image";
	protected static final String PCE = "Parser Configuration Exception";
	protected static final String UNKNOWNTYPE = "Unknown Element type";
	protected static final String NFE = "Number Format Exception";
	
	private String getTitle(Element element, String tagName)
	{
		NodeList titles = element.getElementsByTagName(tagName);
		return titles.item(0).getTextContent();
	}
	
	public void loadFile(Presentation presentation, String filename) throws IOException
	{
		try
		{
			Document document = parseXmlDocument(filename);
			processDocument(presentation, document);
		} catch (IOException iox)
		{
			System.err.println(iox.toString());
			throw iox; // Re-throw to allow caller to handle
		} catch (SAXException sax)
		{
			System.err.println(sax.getMessage());
			throw new IOException("SAX parsing error", sax);
		} catch (ParserConfigurationException pcx)
		{
			System.err.println(PCE);
			throw new IOException("Parser configuration error", pcx);
		}
	}
	
	private Document parseXmlDocument(String filename) throws SAXException, IOException, ParserConfigurationException
	{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.parse(new File(filename));
	}
	
	private void processDocument(Presentation presentation, Document document)
	{
		Element doc = document.getDocumentElement();
		presentation.setTitle(getTitle(doc, SHOWTITLE));
		
		NodeList slides = doc.getElementsByTagName(SLIDE);
		processSlides(presentation, slides);
	}
	
	private void processSlides(Presentation presentation, NodeList slides)
	{
		int max = slides.getLength();
		for (int slideNumber = 0; slideNumber < max; slideNumber++)
		{
			Element xmlSlide = (Element) slides.item(slideNumber);
			processSlide(presentation, xmlSlide);
		}
	}
	
	private void processSlide(Presentation presentation, Element xmlSlide)
	{
		Slide slide = new Slide();
		slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
		presentation.append(slide);
		
		NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
		processSlideItems(slide, slideItems);
	}
	
	private void processSlideItems(Slide slide, NodeList slideItems)
	{
		int maxItems = slideItems.getLength();
		for (int itemNumber = 0; itemNumber < maxItems; itemNumber++)
		{
			Element item = (Element) slideItems.item(itemNumber);
			loadSlideItem(slide, item);
		}
	}
	
	protected void loadSlideItem(Slide slide, Element item)
	{
		try
		{
			int level = extractLevel(item);
			String type = extractItemType(item);
			String content = item.getTextContent();
			
			addSlideItemBasedOnType(slide, level, type, content);
		} catch (NumberFormatException x)
		{
			System.err.println(NFE);
		}
	}
	
	private int extractLevel(Element item)
	{
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		
		return leveltext != null
				? Integer.parseInt(leveltext)
				: 1; // default level
	}
	
	private String extractItemType(Element item)
	{
		NamedNodeMap attributes = item.getAttributes();
		return attributes.getNamedItem(KIND).getTextContent();
	}
	
	private void addSlideItemBasedOnType(Slide slide, int level, String type, String content)
	{
		if (TEXT.equals(type))
		{
			slide.addSlideItem(new TextItem(level, content));
		}
		else if (IMAGE.equals(type))
		{
			slide.addSlideItem(new BitmapItem(level, content));
		}
		else
		{
			System.err.println(UNKNOWNTYPE);
		}
	}
	
	public void saveFile(Presentation presentation, String filename) throws IOException
	{
		try (PrintWriter out = new PrintWriter(new FileWriter(filename)))
		{
			writeXmlHeader(out);
			writePresentationStart(presentation, out);
			writeSlides(presentation, out);
			writePresentationEnd(out);
		}
	}
	
	private void writeXmlHeader(PrintWriter out)
	{
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");
	}
	
	private void writePresentationStart(Presentation presentation, PrintWriter out)
	{
		out.print("<showtitle>");
		out.print(presentation.getTitle());
		out.println("</showtitle>");
	}
	
	private void writeSlides(Presentation presentation, PrintWriter out)
	{
		for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++)
		{
			Slide slide = presentation.getSlide(slideNumber);
			writeSlide(slide, out);
		}
	}
	
	private void writeSlide(Slide slide, PrintWriter out)
	{
		out.println("<slide>");
		out.println("<title>" + slide.getTitle() + "</title>");
		writeSlideItems(slide.getSlideItems(), out);
		out.println("</slide>");
	}
	
	private void writeSlideItems(List<SlideItem> slideItems, PrintWriter out)
	{
		for (SlideItem slideItem : slideItems)
		{
			writeSlideItem(slideItem, out);
		}
	}
	
	private void writeSlideItem(SlideItem slideItem, PrintWriter out)
	{
		out.print("<item kind=");
		
		if (slideItem instanceof TextItem)
		{
			TextItem textItem = (TextItem) slideItem;
			out.print("\"text\" level=\"" + textItem.getLevel() + "\">");
			out.print(textItem.getText());
		}
		else if (slideItem instanceof BitmapItem)
		{
			BitmapItem bitmapItem = (BitmapItem) slideItem;
			out.print("\"image\" level=\"" + bitmapItem.getLevel() + "\">");
			out.print(bitmapItem.getName());
		}
		else
		{
			System.out.println("Ignoring " + slideItem);
			return;
		}
		
		out.println("</item>");
	}
	
	private void writePresentationEnd(PrintWriter out)
	{
		out.println("</presentation>");
	}
}