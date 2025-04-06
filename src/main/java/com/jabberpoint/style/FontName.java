package com.jabberpoint.style;

public enum FontName {
	ARIAL("Arial"),
	HELVETICA("Helvetica"),
	TIMES_NEW_ROMAN("Times New Roman"),
	COURIER_NEW("Courier New"),
	DIALOG("Dialog");
	
	private String name;
	
	FontName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}