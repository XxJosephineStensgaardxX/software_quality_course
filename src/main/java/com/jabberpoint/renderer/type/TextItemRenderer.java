package com.jabberpoint.renderer.type;

import com.jabberpoint.renderer.Drawable;
import com.jabberpoint.renderer.RenderUtility;
import com.jabberpoint.slide.item.SlideItem;
import com.jabberpoint.slide.item.TextItem;
import com.jabberpoint.slide.metadata.Resolution;
import com.jabberpoint.style.Style;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItemRenderer extends SlideItemRenderer
{
	@Override
	public void drawItem(SlideItem item, RenderUtility renderUtility)
	{
		TextItem textItem = (TextItem) item;
		String text = textItem.getText();
		
		if (text == null || text.isEmpty())
		{
			return;
		}
		
		Graphics2D graphics2d = (Graphics2D) renderUtility.getGraphicHandler();
		Style style = renderUtility.getCurrentTheme().getStyle(textItem.getLevel());
		Rectangle area = renderUtility.getScreenArea();
		graphics2d.setColor(style.getColor());
		
		// Calculate starting position
		int x = area.x + style.getIndent();
		int y = area.y + style.getLeading();
		
		// Create and draw text layouts
		List<TextLayout> layouts = createTextLayouts(graphics2d, style, text);
		
		for (TextLayout layout : layouts)
		{
			y += (int) layout.getAscent();
			layout.draw(graphics2d, x, y);
			y += (int) layout.getDescent();
		}
	}
	
	private List<TextLayout> createTextLayouts(Graphics2D graphics2d, Style style, String text)
	{
		// Create attributed string with font styling
		AttributedString attributedText = new AttributedString(text);
		attributedText.addAttribute(TextAttribute.FONT, style.getFont(1.0f), 0, text.length());
		FontRenderContext fontRenderer = graphics2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attributedText.getIterator(), fontRenderer);
		
		// Calculate available width
		float availableWidth = Resolution.STANDARD_DISPLAY.getWidth() - style.getIndent();
		List<TextLayout> layouts = new ArrayList<>();
		measurer.setPosition(0);
		
		while (measurer.getPosition() < text.length())
		{
			layouts.add(measurer.nextLayout(availableWidth));
		}
		
		return layouts;
	}
	
	public Rectangle calculateBoundingBox(SlideItem item, Graphics2D graphics2d, Style style)
	{
		TextItem textItem = (TextItem) item;
		List<TextLayout> layouts = createTextLayouts(graphics2d, style, textItem.getText());
		
		int height = style.getLeading();
		int maxWidth = 0;
		
		for (TextLayout layout : layouts)
		{
			Rectangle2D bounds = layout.getBounds();
			maxWidth = Math.max(maxWidth, (int)bounds.getWidth());
			height += (int)(layout.getAscent() + layout.getDescent());
		}
		
		return new Rectangle(style.getIndent(), 0, maxWidth, height);
	}
}