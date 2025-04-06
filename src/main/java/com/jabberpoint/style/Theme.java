package com.nhlstenden.style;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Theme
{
	private String themeName;
	private List<Style> styles;
	private Color slideBackgroundColor;
//	private Color
	
	public Theme(String themeName)
	{
		this.themeName = themeName;
		this.styles = new ArrayList<>();
	}
	
	public String getThemeName()
	{
		return themeName;
	}
	
	public void setThemeName(String themeName)
	{
		this.themeName = themeName;
	}
	
	public List<Style> getStyles()
	{
		return styles;
	}
	
	public void setStyles(List<Style> styles)
	{
		this.styles = styles;
	}
	
	public void addStyle(Style style)
	{
		this.styles.add(style);
	}
	
	public void removeStyle(int level)
	{
		this.styles.remove(level);
	}
	
	public void updateStyle(int level, Style style)
	{
		this.styles.set(level, style);
	}
	
	public Style getStyle(int level)
	{
		return this.styles.get(level);
	}
}
