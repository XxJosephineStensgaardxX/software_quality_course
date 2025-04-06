package com.jabberpoint.style;

import java.awt.Color;
import java.awt.Font;

public class Style
{
	public static final int DEFAULT_LEADING = 10;
	public static final int DEFAULT_FONT_SIZE = 24;
	private int indent;
	private Color color;
	private Font font;
	private int fontSize;
	private int leading;
	private FontName fontName = FontName.ARIAL;
	
	public Style(int indent, Color color, int fontSize, int leading, FontName fontName)
	{
		this.indent = indent;
		this.color = color;
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.leading = leading;
		font = new Font(this.fontName.getName(), Font.BOLD, this.fontSize);
	}
	
	//default constructor. Arial font
	public Style()
	{
		this(0, Color.black, DEFAULT_FONT_SIZE, DEFAULT_LEADING, FontName.ARIAL);
	}
	
	public int getIndent()
	{
		return indent;
	}
	
	public void setIndent(int indent)
	{
		if (indent >= 0)
		{
			this.indent = indent;
		}
		else
			throw new IllegalArgumentException("indent cannot be less than zero");
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public void setFont(Font font)
	{
		this.font = font;
	}
	
	public int getFontSize()
	{
		return fontSize;
	}
	
	public void setFontSize(int fontSize)
	{
		if (fontSize >= 0)
			this.fontSize = fontSize;
		else
			throw new IllegalArgumentException("font size must be greater than zero");
	}
	
	public int getLeading()
	{
		return leading;
	}
	
	public void setLeading(int leading)
	{
		if (leading >= 0)
			this.leading = leading;
		else
			throw new IllegalArgumentException("leading must at least be zero");
	}
	
	public FontName getFontName()
	{
		return fontName;
	}
	
	public void setFontName(FontName fontName)
	{
		this.fontName = fontName;
	}
	
	public String toString() {
		return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
	}
	
	public Font getFont(float scale) {
		return font.deriveFont(fontSize * scale);
	}
}