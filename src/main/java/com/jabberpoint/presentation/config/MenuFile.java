package com.jabberpoint.presentation.config;

public enum MenuFile {
	TEST("test.xml"),
	SAVE("dump.xml");
	
	private final String filename;
	
	MenuFile(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}
}