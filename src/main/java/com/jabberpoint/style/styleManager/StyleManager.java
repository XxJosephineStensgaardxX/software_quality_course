package com.jabberpoint.style.styleManager;

import com.jabberpoint.style.FontName;
import com.jabberpoint.style.Style;
import com.jabberpoint.style.Theme;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StyleManager
{
	private Map<String, Theme> themes;
	private Map<String, Style> namedStyles;
	
	public StyleManager()
	{
		this.themes = new HashMap<>();
		this.namedStyles = new HashMap<>();
		this.buildDefaultTheme();
	}
	
	public Map<String, Theme> getThemes()
	{
		return themes;
	}
	
	public void setThemes(Map<String, Theme> themes)
	{
		this.themes = themes;
	}
	
	public Map<String, Style> getNamedStyles()
	{
		return namedStyles;
	}
	
	public void setNamedStyles(Map<String, Style> namedStyles)
	{
		this.namedStyles = namedStyles;
	}
	
	public void addTheme(Theme theme)
	{
		this.themes.put(theme.getThemeName(), theme);
	}
	
	public Theme getTheme(String name)
	{
		return this.themes.get(name);
	}
	
	public void removeTheme(String name)
	{
		if (!this.themes.containsKey(name))
		{
			this.themes.remove(name);
		}
	}
	
	public void addNamedStyle(String name, Style style)
	{
		this.namedStyles.put(name, style);
	}
	
	public Style getNamedStyle(String name)
	{
		return this.namedStyles.get(name);
	}
	
	public void removeNamedStyle(String name)
	{
		this.themes.remove(name);
	}
	
	private void buildDefaultTheme()
	{
		Style style1 = new Style(0, Color.red, 48, 20, FontName.ARIAL);
		Style style2 = new Style(20, Color.blue,  40, 10, FontName.ARIAL);
		Style style3 = new Style(50, Color.black, 36, 10, FontName.ARIAL);
		Style style4 = new Style(70, Color.black, 30, 10, FontName.ARIAL);
		Style style5 = new Style(90, Color.black, 24, 10, FontName.ARIAL);
		
		Style uiStyle = new Style(0, Color.black, 10, 5, FontName.ARIAL);
		
		Theme defaultTheme = new Theme("Default Theme");
		defaultTheme.addStyle(style1);
		defaultTheme.addStyle(style2);
		defaultTheme.addStyle(style3);
		defaultTheme.addStyle(style4);
		defaultTheme.addStyle(style5);
		
		this.themes.put(defaultTheme.getThemeName(), defaultTheme);
	}
}
