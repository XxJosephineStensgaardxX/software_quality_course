package com.jabberpoint.slide.item;

import com.jabberpoint.style.Style;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public abstract class SlideItem
{
	private int level = 0;
	
	public SlideItem(int lev)
	{
		level = lev;
	}
	
	public SlideItem()
	{
		this(0);
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	// Give the bounding box
	public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);
}
