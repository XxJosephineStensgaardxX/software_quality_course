package com.jabberpoint.slide;

import com.jabberpoint.render.Element;
import com.jabberpoint.render.Visitor;
import com.jabberpoint.slide.item.SlideItem;
import com.jabberpoint.slide.item.TextItem;
import com.jabberpoint.slide.metadata.Resolution;
import com.jabberpoint.style.Style;
import com.jabberpoint.style.Theme;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Slide implements Element
{
	private Resolution resolution = Resolution.STANDARD_DISPLAY;
	protected String title;
	protected List<SlideItem> slideItems;
	
	public Slide()
	{
		slideItems = new ArrayList<>();
	}
	
	public Resolution getResolution()
	{
		return this.resolution;
	}
	
	public void setResolution(Resolution resolution)
	{
		this.resolution = resolution;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public List<SlideItem> getSlideItems()
	{
		return this.slideItems;
	}
	
	public void setSlideItems(List<SlideItem> slideItems)
	{
		this.slideItems = slideItems;
	}
	
	public int getSize() {
		return this.slideItems.size();
	}
	
	public void addSlideItem(int level, String message)
	{
		this.addSlideItem(new TextItem(level, message));
	}
	
	public void addSlideItem(SlideItem anItem) {
		this.slideItems.add(anItem);
	}
	
	// give the  SlideItem
	public SlideItem getSlideItem(int number) {
		return (SlideItem)this.slideItems.get(number);
	}
	
	public float getScale(Rectangle area) {
		return Math.min(
				((float)area.width) / this.resolution.getWidth(),
				((float)area.height) / this.resolution.getHeight()
		);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		// Let each slide item accept the visitor
		for (SlideItem item : slideItems) {
			item.accept(visitor);
		}
	}
}
