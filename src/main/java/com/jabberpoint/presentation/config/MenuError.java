package com.jabberpoint.presentation.config;

public enum MenuError {
	IO_EXCEPTION("IO Exception: "),
	LOAD_ERROR("Load Error"),
	SAVE_ERROR("Save Error");
	
	private final String message;
	
	MenuError(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}