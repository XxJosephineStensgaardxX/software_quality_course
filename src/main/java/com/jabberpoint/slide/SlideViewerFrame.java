package com.jabberpoint.slide;

import com.jabberpoint.accessor.creator.AccessorCreator;
import com.jabberpoint.accessor.creator.XMLAccessorCreator;
import com.jabberpoint.presentation.KeyController;
import com.jabberpoint.presentation.MenuController;
import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.slide.metadata.Resolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SlideViewerFrame extends JFrame {
	private Resolution resolution = Resolution.STANDARD_DISPLAY;
	private static final long serialVersionUID = 3227L;
	private static final String WINDOW_TITLE = "JabberPoint 3";
	
	public SlideViewerFrame(String title, Presentation presentation) {
		super(title);
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		presentation.setShowView(slideViewerComponent);
		setupWindow(slideViewerComponent, presentation);
		// Make the frame visible after setup
		setVisible(true);
	}
	
	public Resolution getResolution() {
		return resolution;
	}
	
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	
	private void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
		// Configure window properties
		setTitle(WINDOW_TITLE);
		setSize(resolution.getWidth(), resolution.getHeight());
		
		// Add close listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// Add main component
		getContentPane().add(slideViewerComponent);
		
		// Add controllers
		KeyController keyController = new KeyController(presentation);
		addKeyListener(keyController);
	}
	
	public void setMenuController(AccessorCreator accessorCreator, Presentation presentation) {
		MenuController menuController = new MenuController(this, presentation, accessorCreator);
		setMenuBar(menuController);
	}
}