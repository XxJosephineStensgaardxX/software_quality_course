package com.jabberpoint;

import com.jabberpoint.style.styleManager.StyleManager;

public class JabberPoint
{
	public static void main(String[] args)
	{
		StyleManager sharedStyles = new StyleManager(); //Rather than using a singleton, we use a single instance created on the application level.
	}
}
