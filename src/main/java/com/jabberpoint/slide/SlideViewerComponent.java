package com.jabberpoint.slide;

import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.render.RenderingVisitor;
import com.jabberpoint.style.ViewerStyle;
import com.jabberpoint.style.styleManager.StyleManager;

import javax.swing.*;
import java.awt.*;

public class SlideViewerComponent extends JComponent {
	private Slide slide;
	private Font labelFont;
	private Presentation presentation;
	private JFrame frame;
	private StyleManager styleManager;
	private static final long serialVersionUID = 227L;
	
	public SlideViewerComponent(Presentation presentation, JFrame frame) {
		if (presentation == null || frame == null) {
			throw new IllegalArgumentException("Presentation and frame cannot be null");
		}
		
		this.presentation = presentation;
		this.frame = frame;
		this.styleManager = new StyleManager();
		
		// Use viewerStyle from theme
		ViewerStyle viewerStyle = styleManager.getTheme("Default Theme").getViewerStyle();
		setBackground(viewerStyle.getBackgroundColor());
		this.labelFont = viewerStyle.getFont();
		
		// Set a preferred size for better visibility
		setPreferredSize(new Dimension(800, 600));
	}
	
	public void update(Presentation presentation, Slide data) {
		if (presentation == null) return;
		this.presentation = presentation;
		
		this.slide = data;
		repaint();
		
		if (frame != null) {
			frame.setTitle(presentation.getTitle());
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		ViewerStyle viewerStyle = styleManager.getTheme("Default Theme").getViewerStyle();
		graphics.setColor(viewerStyle.getBackgroundColor());
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		if (presentation == null || presentation.getSlideNumber() < 0) {
			return;
		}
		
		if (slide == null) {
			// Try to get current slide if not set
			slide = presentation.getCurrentSlide();
			if (slide == null) {
				return;
			}
		}
		
		graphics.setFont(viewerStyle.getFont());
		graphics.setColor(viewerStyle.getTextColor());
		graphics.drawString(
				"Slide " + (presentation.getSlideNumber() + 1) + " of " +
						presentation.getSize(),
				viewerStyle.getXPosition(),
				viewerStyle.getYPosition()
		);
		
		Rectangle area = new Rectangle(
				0,
				viewerStyle.getYPosition(),
				getWidth(),
				getHeight() - viewerStyle.getYPosition()
		);
		
		RenderingVisitor renderVisitor = new RenderingVisitor(
				graphics,
				this,
				area,
				styleManager.getTheme("Default Theme")
		);
		
		slide.accept(renderVisitor);
	}
}