package com.jabberpoint.presentation;

import com.jabberpoint.slide.Slide;
import com.jabberpoint.slide.SlideViewerComponent;

import java.util.ArrayList;

public class Presentation
{
	private String title;
	private ArrayList<Slide> slides = null;
	private int currentSlideNumber = 0;
	private SlideViewerComponent slideViewComponent = null;
	
	public Presentation()
	{
		slideViewComponent = null;
		clear();
	}
	
	public Presentation(SlideViewerComponent slideViewerComponent)
	{
		this.slideViewComponent = slideViewerComponent;
		clear();
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setShowView(SlideViewerComponent slideViewerComponent)
	{
		this.slideViewComponent = slideViewerComponent;
	}
	
	public int getSlideNumber()
	{
		return currentSlideNumber;
	}
	
	public void setSlideNumber(int number)
	{
		if (number >= -1 && (slides.isEmpty() || number < slides.size()))
		{
			currentSlideNumber = number;
			if (slideViewComponent != null && currentSlideNumber >= 0)
			{
				slideViewComponent.update(this, getCurrentSlide());
			}
		}
	}
	
	public void prevSlide()
	{
		if (currentSlideNumber > 0)
		{
			setSlideNumber(currentSlideNumber - 1);
		}
	}
	
	public void nextSlide()
	{
		if (currentSlideNumber < (slides.size() - 1))
		{
			setSlideNumber(currentSlideNumber + 1);
		}
	}
	
	public int getSize()
	{
		return slides.size();
	}
	
	public void clear()
	{
		slides = new ArrayList<>();
		setSlideNumber(-1);
	}
	
	public void append(Slide slide)
	{
		slides.add(slide);
		
		if (slides.size() == 1 && currentSlideNumber == -1)
		{
			setSlideNumber(0);
		}
	}
	
	public Slide getSlide(int number)
	{
		if (number < 0 || number >= getSize())
		{
			return null;
		}
		return slides.get(number);
	}
	
	public Slide getCurrentSlide()
	{
		return getSlide(currentSlideNumber);
	}
	
	public void exit(int n)
	{
		System.exit(n);
	}
}