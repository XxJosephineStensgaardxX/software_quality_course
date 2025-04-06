package com.jabberpoint.slide.metadata;

public enum Resolution
{
	STANDARD_DISPLAY(1200, 900),
	HD_TV(1280, 720),
	WIDESCREEN_DISPLAY(2560, 1600);
	
	private final int width;
	private final int height;
	
	Resolution(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public String toString() {
		return this.width + " x " + this.height;
	}
	
	public double getAspectRatio() {
		return (double) width / height;
	}
}
