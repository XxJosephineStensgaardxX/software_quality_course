package com.jabberpoint.render;

import com.jabberpoint.slide.item.BitmapItem;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.TextItem;
import com.jabberpoint.style.Style;
import com.jabberpoint.style.Theme;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class RenderingVisitor implements Visitor
{
	public static final float DEFAULT_SCALE = 1.0f;
	private Graphics graphics;
	private ImageObserver observer;
	private Rectangle area;
	private Theme currentTheme;
	private int yPosition;
	
	public RenderingVisitor(Graphics graphics, ImageObserver observer, Rectangle area, Theme theme)
	{
		this.graphics = graphics;
		this.observer = observer;
		this.area = area;
		this.currentTheme = theme;
		this.yPosition = area.y;
	}
	
	@Override
	public void visit(Slide slide)
	{
		if (slide.getTitle() != null && !slide.getTitle().isEmpty())
		{
			TextItem titleItem = new TextItem(0, slide.getTitle());
			visit(titleItem);
		}
	}
	
	@Override
	public void visit(TextItem textItem)
	{
		String text = textItem.getText();
		
		if (text == null || text.isEmpty())
		{
			return;
		}
		
		Graphics2D g2d = (Graphics2D) graphics;
		Style style = currentTheme.getStyle(textItem.getLevel());
		g2d.setColor(style.getColor());
		
		int x = area.x + style.getIndent();
		
		List<TextLayout> layouts = createTextLayouts(g2d, style, text);
		
		for (TextLayout layout : layouts)
		{
			yPosition += (int) layout.getAscent();
			layout.draw(g2d, x, yPosition);
			yPosition += (int) layout.getDescent();
		}
	}
	
	@Override
	public void visit(BitmapItem bitmapItem)
	{
		Style style = currentTheme.getStyle(bitmapItem.getLevel());
		float scale = DEFAULT_SCALE;
		
		int x = area.x + (int) (style.getIndent() * scale);
		int y = yPosition + (int) (style.getLeading() * scale);
		
		graphics.drawImage(
				bitmapItem.getBufferedImage(),
				x, y,
				(int) (bitmapItem.getBufferedImage().getWidth(observer) * scale),
				(int) (bitmapItem.getBufferedImage().getHeight(observer) * scale),
				observer
		);
		
		yPosition += (int) (style.getLeading() * scale) +
				(int) (bitmapItem.getBufferedImage().getHeight(observer) * scale);
	}
	
	private List<TextLayout> createTextLayouts(Graphics2D g2d, Style style, String text)
	{
		AttributedString attributedText = new AttributedString(text);
		attributedText.addAttribute(TextAttribute.FONT, style.getFont(DEFAULT_SCALE), 0, text.length());
		FontRenderContext fontRenderer = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attributedText.getIterator(), fontRenderer);
		
		float availableWidth = area.width - style.getIndent();
		List<TextLayout> layouts = new ArrayList<>();
		measurer.setPosition(0);
		
		while (measurer.getPosition() < text.length())
		{
			layouts.add(measurer.nextLayout(availableWidth));
		}
		
		return layouts;
	}
	
	public int getYPosition()
	{
		return yPosition;
	}
}