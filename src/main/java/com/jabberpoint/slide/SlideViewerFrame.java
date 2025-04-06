package com.jabberpoint.slide;

import com.jabberpoint.KeyController;
import com.jabberpoint.MenuController;
import com.jabberpoint.Presentation;
import com.jabberpoint.slide.metadata.Resolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;

public class SlideViewerFrame extends JFrame
{
	private Resolution resolution = Resolution.STANDARD_DISPLAY;
	
	@Serial
	private static final long serialVersionUID = 3227L;
	private static final String WINDOW_TITLE = "JabberPoint 3";
	
	public SlideViewerFrame(String title, Presentation presentation)
	{
		super(title);
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		presentation.setShowView(slideViewerComponent);
		this.setupWindow(slideViewerComponent, presentation);
	}
	
	public Resolution getResolution()
	{
		return resolution;
	}
	
	public void setResolution(Resolution resolution)
	{
		this.resolution = resolution;
	}
	
	public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation)
	{
		configureWindowProperties();
		addWindowCloseListener();
		addComponents(slideViewerComponent);
		addControllers(presentation);
		setVisible(true);
	}
	
	private void configureWindowProperties()
	{
		setTitle(WINDOW_TITLE);
		setSize(new Dimension(this.resolution.getWidth(), this.resolution.getHeight()));
	}
	
	private void addWindowCloseListener()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	private void addComponents(SlideViewerComponent slideViewerComponent)
	{
		getContentPane().add(slideViewerComponent);
	}
	
	private void addControllers(Presentation presentation)
	{
		addKeyListener(new KeyController(presentation));
		setMenuBar(new MenuController(this, presentation));
	}
}