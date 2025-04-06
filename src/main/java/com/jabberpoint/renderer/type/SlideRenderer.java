package com.jabberpoint.renderer.type;

import com.jabberpoint.slide.item.TextItem;
import com.jabberpoint.renderer.Drawable;
import com.jabberpoint.renderer.RenderUtility;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.SlideItem;
import com.jabberpoint.style.Style;

import java.awt.*;

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
		TextItemRenderer textItemRenderer = new TextItemRenderer();
		textItemRenderer.setItem(titleItem);
		
		Style style = renderUtility.getCurrentTheme().getStyle(titleItem.getLevel());
		
		// Update the position in the render utility
		renderUtility.getScreenArea().y = y;
		
		// Draw the item
		textItemRenderer.draw(renderUtility);
		
		// Use the renderer's calculateBoundingBox method
		Rectangle boundingBox = textItemRenderer.calculateBoundingBox(
				titleItem,
				(Graphics2D)renderUtility.getGraphicHandler(),
				style
		);
		
		return y + boundingBox.height;
	}
	
	private int drawSlideItems(int x, int y, float scale, RenderUtility renderUtility) {
		int currentY = y;
		
		for (SlideItem item : slide.getSlideItems()) {
			// Get the style for this item
			Style style = renderUtility.getCurrentTheme().getStyle(item.getLevel());
			
			// Create and configure the appropriate renderer based on item type
			SlideItemRenderer renderer;
			if (item instanceof TextItem) {
				renderer = new TextItemRenderer();
			}
//			else if (item instanceof BitmapItem) {
//				renderer = new BitmapItemRenderer();
//			}
			else {
				throw new IllegalArgumentException("Unknown slide item type: " + item.getClass().getName());
			}
			
			// Set the current item for the renderer
			renderer.setItem(item);
			
			// Update the position in the render utility
			renderUtility.getScreenArea().y = currentY;
			renderUtility.getScreenArea().x = x;
			
			// Draw the item
			renderer.draw(renderUtility);
			
			// Calculate the bounding box to determine the height
			Rectangle boundingBox = renderer.calculateBoundingBox(
					item,
					(Graphics2D)renderUtility.getGraphicHandler(),
					style
			);
			
			// Update the Y position for the next item
			currentY += boundingBox.height;
		}
		
		return currentY;
	}
}