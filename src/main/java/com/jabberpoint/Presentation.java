package com.jabberpoint;

import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.SlideViewerComponent;

import java.util.ArrayList;


/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String title;
	private ArrayList<Slide> slides = null;
	private int currentSlideNumber = 0;
	private SlideViewerComponent slideViewComponent = null;
	
	public Presentation() {
		slideViewComponent = null;
		clear();
	}
	
	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		clear();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String nt) {
		title = nt;
	}
	
	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}
	
	// give the number of the current slide
	public int getSlideNumber() {
		return currentSlideNumber;
	}
	
	// change the current slide number and signal it to the window
	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		if (slideViewComponent != null) {
			slideViewComponent.update(this, getCurrentSlide());
		}
	}
	
	// go to the previous slide unless your at the beginning of the presentation
	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
		}
	}
	
	// go to the next slide unless your at the end of the presentation.
	public void nextSlide() {
		if (currentSlideNumber < (slides.size()-1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}
	
	public int getSize() {
		return slides.size();
	}
	
	// Delete the presentation to be ready for the next one.
	void clear() {
		slides = new ArrayList<Slide>();
		setSlideNumber(-1);
	}
	
	// Add a slide to the presentation
	public void append(Slide slide) {
		slides.add(slide);
	}
	
	// Get a slide with a certain slidenumber
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
		}
		return (Slide)slides.get(number);
	}
	
	// Give the current slide
	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}
	
	public void exit(int n) {
		System.exit(n);
	}
}
