package com.jabberpoint.accessor.creator;

import com.jabberpoint.accessor.Accessor;

public abstract class AccessorCreator
{
	protected abstract Accessor createAccessor();
	
	public Accessor getAccessor()
	{
		return createAccessor();
	}
}