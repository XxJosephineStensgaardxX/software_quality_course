package com.jabberpoint.slide.item;

import com.jabberpoint.render.Visitor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BitmapItem extends SlideItem {
	private BufferedImage bufferedImage;
	private String imageName;
	
	protected static final String FILE = "File ";
	protected static final String NOTFOUND = " not found";
	
	// Default placeholder dimensions
	private static final int PLACEHOLDER_WIDTH = 320;
	private static final int PLACEHOLDER_HEIGHT = 240;
	
	// Colors for the placeholder
	private static final Color PLACEHOLDER_BG = new Color(200, 200, 200);
	private static final Color PLACEHOLDER_TEXT = new Color(80, 80, 80);
	private static final Color PLACEHOLDER_BORDER = new Color(150, 150, 150);
	
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try {
			File imageFile = new File(imageName);
			if (imageFile.exists()) {
				bufferedImage = ImageIO.read(imageFile);
			} else {
				System.err.println(FILE + imageName + NOTFOUND);
				bufferedImage = createPlaceholderImage();
			}
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND + ": " + e.getMessage());
			bufferedImage = createPlaceholderImage();
		}
	}
	
	//creates placeholder if image file is missing
	private BufferedImage createPlaceholderImage() {
		BufferedImage placeholder = new BufferedImage(
				PLACEHOLDER_WIDTH, PLACEHOLDER_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = placeholder.createGraphics();
		
		g2d.setColor(PLACEHOLDER_BG);
		g2d.fillRect(0, 0, PLACEHOLDER_WIDTH, PLACEHOLDER_HEIGHT);
		
		g2d.setColor(PLACEHOLDER_BORDER);
		g2d.drawRect(1, 1, PLACEHOLDER_WIDTH - 3, PLACEHOLDER_HEIGHT - 3);
		
		g2d.setColor(PLACEHOLDER_TEXT);
		g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		String text = "Image not found: " + imageName;
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		
		g2d.drawString(text,
					   (PLACEHOLDER_WIDTH - textWidth) / 2,
					   PLACEHOLDER_HEIGHT / 2);
		
		drawImageSymbol(g2d);
		
		g2d.dispose();
		return placeholder;
	}
	
	//helper method
	private void drawImageSymbol(Graphics2D g2d) {
		int symbolSize = 60;
		int x = (PLACEHOLDER_WIDTH - symbolSize) / 2;
		int y = (PLACEHOLDER_HEIGHT - symbolSize) / 2 - 40;
		
		// Mountain/landscape icon
		g2d.setColor(PLACEHOLDER_TEXT);
		int[] xPoints = {x, x + symbolSize/2, x + symbolSize};
		int[] yPoints = {y + symbolSize, y + symbolSize/3, y + symbolSize};
		g2d.fillPolygon(xPoints, yPoints, 3);
		
		// Sun
		g2d.fillOval(x + symbolSize/2 - 10, y, 20, 20);
	}
	
	public BitmapItem() {
		this(0, null);
	}
	
	public String getName() {
		return imageName;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}