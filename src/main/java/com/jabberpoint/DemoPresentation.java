package com.jabberpoint;

import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.item.BitmapItem;

public class DemoPresentation extends Accessor {
	
	public void loadFile(Presentation presentation, String unusedFilename) {
		presentation.setTitle("Demo Presentation");
		Slide slide = new Slide();
		slide.setTitle("JabberPoint");
		slide.addSlideItem(1, "The Java Presentation Tool");
		slide.addSlideItem(2, "Copyright (c) 1996-2000: Ian Darwin");
		slide.addSlideItem(2, "Copyright (c) 2000-now:");
		slide.addSlideItem(2, "Gert Florijn andn Sylvia Stuurman");
		slide.addSlideItem(4, "Starting JabberPoint without a filename");
		slide.addSlideItem(4, "shows this presentation");
		slide.addSlideItem(1, "Navigate:");
		slide.addSlideItem(3, "Next slide: PgDn or Enter");
		slide.addSlideItem(3, "Previous slide: PgUp or up-arrow");
		slide.addSlideItem(3, "Quit: q or Q");
		presentation.append(slide);
		
		slide = new Slide();
		slide.setTitle("Demonstration of levels and stijlen");
		slide.addSlideItem(1, "Level 1");
		slide.addSlideItem(2, "Level 2");
		slide.addSlideItem(1, "Again level 1");
		slide.addSlideItem(1, "Level 1 has style number 1");
		slide.addSlideItem(2, "Level 2 has style number  2");
		slide.addSlideItem(3, "This is how level 3 looks like");
		slide.addSlideItem(4, "And this is level 4");
		presentation.append(slide);
		
		slide = new Slide();
		slide.setTitle("The third slide");
		slide.addSlideItem(1, "To open a new presentation,");
		slide.addSlideItem(2, "use File->Open from the menu.");
		slide.addSlideItem(1, " ");
		slide.addSlideItem(1, "This is the end of the presentation.");
		slide.addSlideItem(new BitmapItem(1, "JabberPoint.jpg"));
		presentation.append(slide);
	}
	
	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Save As->Demo! called");
	}
}
