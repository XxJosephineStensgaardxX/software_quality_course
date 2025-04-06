package com.jabberpoint.render;

import com.jabberpoint.slide.item.BitmapItem;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.TextItem;

public interface Visitor
{
	void visit(Slide slide);
	
	void visit(TextItem textItem);
	
	void visit(BitmapItem bitmapItem);
}