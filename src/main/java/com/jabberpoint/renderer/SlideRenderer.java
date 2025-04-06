package com.jabberpoint.renderer;

import com.jabberpoint.renderer.strategy.SlideRenderingStrategy;
import com.jabberpoint.slide.Slide;

public class SlideRenderer
{
	private RenderingStrategy renderingStrategy;
	
	public SlideRenderer(Slide slide)
	{
		this.renderingStrategy = new SlideRenderingStrategy(slide);
	}
	
	public SlideRenderer(RenderingStrategy strategy)
	{
		this.renderingStrategy = strategy;
	}
	
	public void setRenderingStrategy(RenderingStrategy strategy)
	{
		this.renderingStrategy = strategy;
	}
	
	public RenderingStrategy getRenderingStrategy()
	{
		return renderingStrategy;
	}
	
	public int render(RenderUtility renderUtility)
	{
		if (renderingStrategy == null)
		{
			throw new IllegalStateException("No rendering strategy set");
		}
		
		return renderingStrategy.render(renderUtility);
	}
}