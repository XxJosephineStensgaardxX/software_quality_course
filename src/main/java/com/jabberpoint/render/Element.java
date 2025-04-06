package com.jabberpoint.render;

public interface Element
{
	void accept(Visitor visitor);
}
