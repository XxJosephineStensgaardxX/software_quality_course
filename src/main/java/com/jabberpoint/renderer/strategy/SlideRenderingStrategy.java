package com.jabberpoint.renderer.strategy;

import com.jabberpoint.BitmapItem;
import com.jabberpoint.renderer.RenderUtility;
import com.jabberpoint.renderer.RenderingStrategy;
import com.jabberpoint.renderer.strategy.itemRendering.BitmapItemRenderingStrategy;
import com.jabberpoint.renderer.strategy.itemRendering.SlideItemRenderingStrategy;
import com.jabberpoint.renderer.strategy.itemRendering.TextItemRenderingStrategy;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.SlideItem;
import com.jabberpoint.slide.item.TextItem;
import com.jabberpoint.style.Style;

import java.awt.*;

public class SlideRenderingStrategy implements RenderingStrategy
{
	private Slide slide;
	
	public SlideRenderingStrategy(Slide slide)
	{
		this.slide = slide;
	}
	
	public void setSlide(Slide slide)
	{
		this.slide = slide;
	}
	
	@Override
	public int render(RenderUtility renderUtility)
	{
		if (slide == null)
		{
			return renderUtility.getScreenArea().y;
		}
		
//		float scale = slide.getScale(renderUtility.getScreenArea());
		int currentY = renderUtility.getScreenArea().y;
		currentY = renderTitle(currentY, renderUtility);
		currentY = renderItems(renderUtility.getScreenArea().x, currentY, renderUtility);
		
		return currentY;
	}
	
	private int renderTitle(int y, RenderUtility renderUtility)
	{
		if (slide.getTitle() == null || slide.getTitle().isEmpty())
		{
			return y;
		}
		
		TextItem titleItem = new TextItem(0, slide.getTitle());
		Style style = renderUtility.getCurrentTheme().getStyle(titleItem.getLevel());
		
		// Create a copy of the render utility with updated Y position
		RenderUtility titleRenderUtility = new RenderUtility(renderUtility.getGraphicHandler(), renderUtility.getImageObserver(),
				new Rectangle(renderUtility.getScreenArea().x, y,
							  renderUtility.getScreenArea().width, renderUtility.getScreenArea().height),
				renderUtility.getCurrentTheme()
		);
		
		// Create and use item strategy
		SlideItemRenderingStrategy titleStrategy = new TextItemRenderingStrategy(titleItem);
		return titleStrategy.render(titleRenderUtility);
	}
	
	private int renderItems(int x, int y, RenderUtility renderUtility)
	{
		int currentY = y;
		
		for (SlideItem item : slide.getSlideItems())
		{
			// Create a copy of the render utility with updated position
			RenderUtility itemRenderUtility = new RenderUtility(
					renderUtility.getGraphicHandler(), renderUtility.getImageObserver(),
					new Rectangle(x, currentY,
								  renderUtility.getScreenArea().width,
								  renderUtility.getScreenArea().height - (currentY - renderUtility.getScreenArea().y)),
					renderUtility.getCurrentTheme()
			);
			
			// Create and use appropriate item strategy
			SlideItemRenderingStrategy itemStrategy = getRenderingStrategyForItem(item);
			currentY = itemStrategy.render(itemRenderUtility);
		}
		
		return currentY;
	}
	
	private SlideItemRenderingStrategy getRenderingStrategyForItem(SlideItem item)
	{
		if (item instanceof TextItem)
		{
			return new TextItemRenderingStrategy((TextItem) item);
		}
		else if (item instanceof BitmapItem)
		{
			return new BitmapItemRenderingStrategy((BitmapItem) item);
		}
		else
		{
			throw new IllegalArgumentException("No rendering strategy for item type: " + item.getClass().getName());
		}
	}
}