package com.jabberpoint.accessor.creator;

import com.jabberpoint.accessor.Accessor;
import com.jabberpoint.accessor.XMLAccessor;

public class XMLAccessorCreator extends AccessorCreator
{
	@Override
	protected Accessor createAccessor() {
		return new XMLAccessor();
	}
}