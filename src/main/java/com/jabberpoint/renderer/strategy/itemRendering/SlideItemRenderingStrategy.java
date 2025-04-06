package com.jabberpoint.renderer.strategy.itemRendering;

import com.jabberpoint.renderer.RenderUtility;
import com.jabberpoint.renderer.RenderingStrategy;
import com.jabberpoint.slide.item.SlideItem;

public abstract class SlideItemRenderingStrategy implements RenderingStrategy
{
	//Strategy for rendering all slide items
	protected SlideItem item;
	
	public SlideItemRenderingStrategy(SlideItem item)
	{
		this.item = item;
	}
	
	public void setItem(SlideItem item)
	{
		this.item = item;
	}
	
	public SlideItem getItem()
	{
		return item;
	}
	
	@Override
	public abstract int render(RenderUtility renderUtility);
}