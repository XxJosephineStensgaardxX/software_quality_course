package com.jabberpoint.slide.item;

import com.jabberpoint.renderer.type.TextItemRenderer;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.metadata.Resolution;
import com.jabberpoint.style.Style;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class TextItem extends SlideItem
{
	private String text;
	private static final String EMPTY_TEXT = "No Text Given";
	
	public TextItem(int level, String string)
	{
		super(level);
		this.text = string;
	}
	
	public TextItem() {
		this(0, EMPTY_TEXT);
	}
	
	public String getText()
	{
		return this.text == null ? "" : this.text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style)
	{
		TextItemRenderer renderer = new TextItemRenderer(this);
		return renderer.calculateBoundingBox(g, observer, scale, style);
	}
	
	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}
}
