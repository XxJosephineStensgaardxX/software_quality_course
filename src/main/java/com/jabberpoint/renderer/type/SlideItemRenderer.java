package com.jabberpoint.renderer.type;

import com.jabberpoint.renderer.Drawable;
import com.jabberpoint.renderer.RenderUtility;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.SlideItem;
import com.jabberpoint.style.Style;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class SlideItemRenderer implements Drawable {
	protected SlideItem currentItem;
	
	public void setItem(SlideItem item) {
		this.currentItem = item;
	}
	
	@Override
	public void draw(RenderUtility renderUtility) {
		// Verify an item has been set
		if (currentItem == null) {
			throw new IllegalStateException("No item set for rendering");
		}
		// Call the protected method that uses the current item
		drawItem(currentItem, renderUtility);
	}
	
	// Internal method that subclasses will implement
	protected abstract void drawItem(SlideItem item, RenderUtility renderUtility);
	
	public abstract Rectangle calculateBoundingBox(SlideItem item, Graphics2D graphics, Style style);
}