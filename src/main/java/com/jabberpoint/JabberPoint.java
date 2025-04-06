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

/**
 * JabberPoint Main Application Class
 */
public class JabberPoint {
	
	private static final String TITLE = "JabberPoint";
	
	/**
	 * Main method for the JabberPoint application.
	 *
	 * @param args Command line arguments (optional file name)
	 */
	public static void main(String[] args) {
		StyleManager styleManager = new StyleManager();
		Presentation presentation = new Presentation();
		
		// Select the appropriate creator based on arguments
		AccessorCreator accessorCreator;
		if (args.length == 0) {
			// No file specified, use demo presentation
			accessorCreator = new DemoAccessorCreator();
		} else {
			// Use XML accessor for file loading
			accessorCreator = new XMLAccessorCreator();
		}
		
		try {
			// Get the accessor from the creator and load the presentation
			Accessor accessor = accessorCreator.getAccessor();
			
			if (args.length == 0) {
				accessor.loadFile(presentation, "");
			} else {
				accessor.loadFile(presentation, args[0]);
			}
			
			// Set initial slide number to 0 to display the first slide
			presentation.setSlideNumber(0);
			
			// Create the presentation viewer frame
			SlideViewerFrame frame = new SlideViewerFrame(TITLE, presentation);
			
			// Set the menu controller with the appropriate accessor creator
			frame.setMenuController(accessorCreator, presentation);
			
		} catch (IOException e) {
			System.err.println("Error loading presentation: " + e.getMessage());
			System.exit(1);
		}
	}
}