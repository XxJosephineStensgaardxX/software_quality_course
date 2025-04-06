package com.jabberpoint.renderer;

import com.jabberpoint.style.Theme;

import java.awt.*;
import java.awt.image.ImageObserver;

public class RenderUtility
{
	private Graphics graphicHandler;
	private ImageObserver imageObserver;
	private Rectangle screenArea;
	private Theme currentTheme;
	
	public RenderUtility(Graphics graphicHandler, ImageObserver imageObserver, Rectangle screenArea, Theme theme)
	{
		this.graphicHandler = graphicHandler;
		this.screenArea = screenArea;
		this.imageObserver = imageObserver;
		this.currentTheme = theme;
	}
	
	public Graphics getGraphicHandler()
	{
		return graphicHandler;
	}
	
	public ImageObserver getImageObserver()
	{
		return imageObserver;
	}
	
	public Rectangle getScreenArea()
	{
		return screenArea;
	}
	
	public Theme getCurrentTheme()
	{
		return currentTheme;
	}
	
	public void setCurrentTheme(Theme currentTheme)
	{
		this.currentTheme = currentTheme;
	}
}
