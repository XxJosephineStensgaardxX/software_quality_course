package com.nhlstenden.renderer.type;

import com.nhlstenden.slide.item.TextItem;
import com.nhlstenden.renderer.Drawable;
import com.nhlstenden.renderer.RenderUtility;
import com.nhlstenden.slide.Slide;
import com.nhlstenden.slide.item.SlideItem;
import com.nhlstenden.style.Style;

public class SlideRenderer implements Drawable
{
	private Slide slide;
	
	public SlideRenderer(Slide slide)
	{
		this.slide = slide;
	}
	
	public Slide getSlide()
	{
		return slide;
	}
	
	public void setSlide(Slide slide)
	{
		this.slide = slide;
	}
	
	@Override
	public void draw(RenderUtility renderUtility)
	{
		float scale = slide.getScale(renderUtility.getScreenArea());
		int currentY = renderUtility.getScreenArea().y;
		
		// Draw the title and slide items
		currentY = drawTitleItem(currentY, scale, renderUtility);
		currentY = drawSlideItems(renderUtility.getScreenArea().x, currentY, scale, renderUtility);
	}
	
	private int drawTitleItem(int y, float scale, RenderUtility renderUtility)
	{
		TextItem titleItem = new TextItem(0, slide.getTitle());
		TextItemRenderer textItemRenderer = new TextItemRenderer(titleItem);
		Style style = renderUtility.getCurrentTheme().getStyle(titleItem.getLevel());
		
		textItemRenderer.draw(renderUtility);
		
		return y + titleItem.getBoundingBox(renderUtility.getGraphicHandler(), renderUtility.getImageObserver(), scale, style).height;
	}
	
	private int drawSlideItems(int x, int y, float scale, RenderUtility renderUtility)
	{
		int currentY = y;
		
		for (SlideItem item : slide.getSlideItems())
		{
			Style style = renderUtility.getCurrentTheme().getStyle(item.getLevel());
			
			item.draw(x, currentY, scale, renderUtility.getGraphicHandler(), style, renderUtility.getImageObserver());
			
			currentY += item.getBoundingBox(renderUtility.getGraphicHandler(), renderUtility.getImageObserver(), scale, style).height;
		}
		
		return currentY;
	}
}