package com.jabberpoint.slide.item;

import com.jabberpoint.render.Visitor;

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
	
	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}
	
	@Override
	public void accept(Visitor visitor)
	{
		visitor.visit(this);
	}
}
