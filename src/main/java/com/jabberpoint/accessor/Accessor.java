package com.jabberpoint.accessor;

import com.jabberpoint.presentation.Presentation;

import java.io.IOException;

public abstract class Accessor
{
	public static final String DEMO_NAME = "Demonstration presentation";
	public static final String DEFAULT_EXTENSION = ".xml";
	public abstract void loadFile(Presentation presentation, String filename) throws IOException;
	public abstract void saveFile(Presentation presentation, String filename) throws IOException;
	
	protected String ensureExtension(String filename)
	{
		if (filename == null || filename.isEmpty())
		{
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		return filename.toLowerCase().endsWith(DEFAULT_EXTENSION)
				? filename
				: filename + DEFAULT_EXTENSION;
	}
}