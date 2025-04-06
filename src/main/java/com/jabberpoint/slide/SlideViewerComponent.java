package com.jabberpoint.slide;

import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.render.RenderingVisitor;
import com.jabberpoint.style.styleManager.StyleManager;

import javax.swing.*;
import java.awt.*;

public class SlideViewerComponent extends JComponent
{
	private Slide slide;
	private Font labelFont;
	private Presentation presentation;
	private JFrame frame;
	private StyleManager styleManager;
	
	private static final long serialVersionUID = 227L;
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONT_STYLE = Font.BOLD;
	private static final int FONT_HEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;
	
	public SlideViewerComponent(Presentation presentation, JFrame frame)
	{
		if (presentation == null || frame == null)
		{
			throw new IllegalArgumentException("Presentation and frame cannot be null");
		}
		
		setBackground(BGCOLOR);
		this.presentation = presentation;
		this.labelFont = new Font(FONTNAME, FONT_STYLE, FONT_HEIGHT);
		this.frame = frame;
		this.styleManager = new StyleManager();
	}
	
	public Slide getSlide()
	{
		return slide;
	}
	
	public void setSlide(Slide slide)
	{
		this.slide = slide;
	}
	
	public Font getLabelFont()
	{
		return labelFont;
	}
	
	public void setLabelFont(Font labelFont)
	{
		this.labelFont = labelFont;
	}
	
	public Presentation getPresentation()
	{
		return presentation;
	}
	
	public void setPresentation(Presentation presentation)
	{
		this.presentation = presentation;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return slide != null
				? new Dimension(slide.getResolution().getWidth(), slide.getResolution().getHeight())
				: new Dimension(800, 600);
	}
	
	public void update(Presentation presentation, Slide data)
	{
		if (presentation == null) return;
		this.presentation = presentation;
		
		if (data == null)
		{
			repaint();
			return;
		}
		
		this.slide = data;
		repaint();
		
		if (frame != null)
		{
			frame.setTitle(presentation.getTitle());
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		
		// Paint background
		graphics.setColor(BGCOLOR);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		if (presentation == null || slide == null || presentation.getSlideNumber() < 0)
		{
			return;
		}
		
		// Draw slide number info
		graphics.setFont(labelFont);
		graphics.setColor(COLOR);
		graphics.drawString("Slide " + (presentation.getSlideNumber() + 1) + " of " + presentation.getSize(), XPOS, YPOS);
		
		// Create the rendering area
		Rectangle area = new Rectangle(0, YPOS, getWidth(), getHeight() - YPOS);
		
		// Create the rendering visitor with the current theme
		RenderingVisitor renderVisitor = new RenderingVisitor(graphics, this, area, styleManager.getTheme("Default Theme"));
		
		// Let the slide accept the visitor, which will render it and all its items
		slide.accept(renderVisitor);
	}
}