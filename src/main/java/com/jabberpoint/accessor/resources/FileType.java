package com.jabberpoint.accessor.resources;

public enum FileType
{
	XML(".xml");
	
	//more file types can be added later
	private final String extensionName;
	
	FileType(String extensionName)
	{
		this.extensionName = extensionName;
	}
	
	public String toString()
	{
		return extensionName;
	}
}
