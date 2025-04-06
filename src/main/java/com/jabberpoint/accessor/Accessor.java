package com.jabberpoint.accessor;

import com.jabberpoint.presentation.Presentation;

import java.io.IOException;

/**
 * Abstract class for file handling in the JabberPoint application.
 * This class provides a common interface for loading and saving presentations.
 */
public abstract class Accessor {
	public static final String DEMO_NAME = "Demonstration presentation";
	public static final String DEFAULT_EXTENSION = ".xml";
	
	/**
	 * Loads a presentation from a file.
	 *
	 * @param presentation The presentation to load data into
	 * @param filename The name of the file to load from
	 * @throws IOException If an I/O error occurs during loading
	 */
	public abstract void loadFile(Presentation presentation, String filename) throws IOException;
	
	/**
	 * Saves a presentation to a file.
	 *
	 * @param presentation The presentation to save
	 * @param filename The name of the file to save to
	 * @throws IOException If an I/O error occurs during saving
	 */
	public abstract void saveFile(Presentation presentation, String filename) throws IOException;
	
	/**
	 * Utility method to check if a filename has the correct extension.
	 * If not, it appends the default extension.
	 *
	 * @param filename The filename to check
	 * @return The filename with the correct extension
	 */
	protected String ensureExtension(String filename) {
		if (filename == null || filename.isEmpty()) {
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		return filename.toLowerCase().endsWith(DEFAULT_EXTENSION)
				? filename
				: filename + DEFAULT_EXTENSION;
	}
}