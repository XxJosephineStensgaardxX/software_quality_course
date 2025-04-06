package com.jabberpoint.slide.item;

import com.jabberpoint.render.Element;
import com.jabberpoint.render.Visitor;

public abstract class SlideItem implements Element
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
	
	@Override
	public abstract void accept(Visitor visitor);
}
