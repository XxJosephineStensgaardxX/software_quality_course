package com.jabberpoint.renderer.strategy.itemRendering;

import com.jabberpoint.BitmapItem;
import com.jabberpoint.renderer.RenderUtility;
import com.jabberpoint.style.Style;

import java.awt.*;

public class BitmapItemRenderingStrategy extends SlideItemRenderingStrategy
{
	public BitmapItemRenderingStrategy(BitmapItem bitmapItem)
	{
		super(bitmapItem);
	}
	
	@Override
	public int render(RenderUtility renderUtility)
	{
		BitmapItem bitmapItem = (BitmapItem) item;
		Graphics g = renderUtility.getGraphicHandler();
		Style style = renderUtility.getCurrentTheme().getStyle(bitmapItem.getLevel());
		Rectangle area = renderUtility.getScreenArea();
		
		float scale = 1.0f;
		bitmapItem.draw(area.x, area.y, scale, g, style, renderUtility.getImageObserver());
		Rectangle boundingBox = bitmapItem.getBoundingBox(g, renderUtility.getImageObserver(), scale, style);
		
		return area.y + boundingBox.height;
	}
}