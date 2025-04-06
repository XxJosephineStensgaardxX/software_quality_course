package com.jabberpoint.slide.item;

import com.jabberpoint.render.Visitor;
import com.jabberpoint.slide.item.SlideItem;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BitmapItem extends SlideItem {
	private BufferedImage bufferedImage;
	private String imageName;
	
	protected static final String FILE = "File ";
	protected static final String NOTFOUND = " not found";
	
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND);
		}
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