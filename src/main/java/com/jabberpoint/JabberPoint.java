package com.jabberpoint;

import com.jabberpoint.accessor.Accessor;
import com.jabberpoint.accessor.creator.AccessorCreator;
import com.jabberpoint.accessor.creator.DemoAccessorCreator;
import com.jabberpoint.accessor.creator.XMLAccessorCreator;
import com.jabberpoint.presentation.MenuController;
import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.slide.SlideViewerFrame;
import com.jabberpoint.style.styleManager.StyleManager;

import java.io.IOException;

public class JabberPoint {
	
	private static final String TITLE = "JabberPoint";
	
	public static void main(String[] args) {
		StyleManager styleManager = new StyleManager();
		Presentation presentation = new Presentation();
		
		AccessorCreator accessorCreator;
		if (args.length == 0) {
			accessorCreator = new DemoAccessorCreator();
		} else {
			accessorCreator = new XMLAccessorCreator();
		}
		
		try {
			Accessor accessor = accessorCreator.getAccessor();
			
			if (args.length == 0) {
				accessor.loadFile(presentation, "");
			} else {
				accessor.loadFile(presentation, args[0]);
			}
			
			presentation.setSlideNumber(0);
			SlideViewerFrame frame = new SlideViewerFrame(TITLE, presentation);
			frame.setMenuController(accessorCreator, presentation);
			
		} catch (IOException e) {
			System.err.println("Error loading presentation: " + e.getMessage());
			System.exit(1);
		}
	}
}