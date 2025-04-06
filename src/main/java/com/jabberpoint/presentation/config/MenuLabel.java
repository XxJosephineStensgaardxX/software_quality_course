package com.jabberpoint.presentation.config;

public enum MenuLabel {
	ABOUT("About"),
	FILE("File"),
	EXIT("Exit"),
	GOTO("Go to"),
	HELP("Help"),
	NEW("New"),
	NEXT("Next"),
	OPEN("Open"),
	PAGE_NUMBER("Page number?"),
	PREV("Prev"),
	SAVE("Save"),
	VIEW("View");
	
	private final String label;
	
	MenuLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		return label;
	}
}