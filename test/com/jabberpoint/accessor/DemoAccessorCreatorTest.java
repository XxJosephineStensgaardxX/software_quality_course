package com.jabberpoint.accessor;

import com.jabberpoint.accessor.Accessor;
import com.jabberpoint.DemoPresentation;
import com.jabberpoint.accessor.creator.DemoAccessorCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DemoAccessorCreatorTest
{
	
	@Test
	void getAccessor_whenCalled_ShouldReturnDemoPresentationInstance()
	{
		DemoAccessorCreator creator = new DemoAccessorCreator();
		
		Accessor accessor = creator.getAccessor();
		assertNotNull(accessor);
		assertTrue(accessor instanceof DemoPresentation,
				   "Accessor should be an instance of DemoPresentation");
	}
	
	@Test
	void getAccessor_multipleCalls_ShouldReturnUniqueInstances()
	{
		DemoAccessorCreator creator = new DemoAccessorCreator();
		
		Accessor firstAccessor = creator.getAccessor();
		Accessor secondAccessor = creator.getAccessor();
		
		assertNotNull(firstAccessor);
		assertNotNull(secondAccessor);
		assertNotSame(firstAccessor, secondAccessor);
	}
}