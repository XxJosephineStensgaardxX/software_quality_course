package com.jabberpoint.accessor.creator;

import com.jabberpoint.DemoPresentation;
import com.jabberpoint.accessor.Accessor;

public class DemoAccessorCreator extends AccessorCreator
{
	@Override
	protected Accessor createAccessor()
	{
		return new DemoPresentation();
	}
}