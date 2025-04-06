package com.jabberpoint.style;

import com.jabberpoint.prototype.Cloneable;

import java.awt.Color;
import java.awt.Font;

public class ViewerStyle implements Cloneable<ViewerStyle> {
	private Color backgroundColor;
	private Color textColor;
	private String fontName;
	private int fontStyle;
	private int fontSize;
	private int xPosition;
	private int yPosition;
	
	public ViewerStyle(Color backgroundColor, Color textColor, String fontName,
					   int fontStyle, int fontSize, int xPosition, int yPosition) {
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
		this.fontName = fontName;
		this.fontStyle = fontStyle;
		this.fontSize = fontSize;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	// Default constructor with standard values
	public ViewerStyle() {
		this(Color.white, Color.black, "Dialog", Font.BOLD, 10, 1100, 20);
	}
	
	// Getters and setters
	public Color getBackgroundColor() { return backgroundColor; }
	public void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor; }
	
	public Color getTextColor() { return textColor; }
	public void setTextColor(Color textColor) { this.textColor = textColor; }
	
	public String getFontName() { return fontName; }
	public void setFontName(String fontName) { this.fontName = fontName; }
	
	public int getFontStyle() { return fontStyle; }
	public void setFontStyle(int fontStyle) { this.fontStyle = fontStyle; }
	
	public int getFontSize() { return fontSize; }
	public void setFontSize(int fontSize) { this.fontSize = fontSize; }
	
	public int getXPosition() { return xPosition; }
	public void setXPosition(int xPosition) { this.xPosition = xPosition; }
	
	public int getYPosition() { return yPosition; }
	public void setYPosition(int yPosition) { this.yPosition = yPosition; }
	
	public Font getFont() {
		return new Font(fontName, fontStyle, fontSize);
	}
	
	@Override
	public ViewerStyle clone() {
		return new ViewerStyle(
				new Color(backgroundColor.getRGB()),
				new Color(textColor.getRGB()),
				fontName,
				fontStyle,
				fontSize,
				xPosition,
				yPosition
		);
	}
}