package com.jabberpoint.accessor;

import com.jabberpoint.accessor.Accessor;
import com.jabberpoint.accessor.XMLAccessor;
import com.jabberpoint.accessor.creator.XMLAccessorCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorCreatorTest {
	
	@Test
	void getAccessor_whenCalled_ShouldReturnXMLAccessorInstance()
	{
		XMLAccessorCreator creator = new XMLAccessorCreator();
		
		// Get the accessor and verify it's an XMLAccessor
		Accessor accessor = creator.getAccessor();
		assertNotNull(accessor);
		assertInstanceOf(XMLAccessor.class, accessor, "Accessor should be an instance of XMLAccessor");
	}
	
	@Test
	void getAccessor_multipleCalls_ShouldReturnUniqueInstances()
	{
		XMLAccessorCreator creator = new XMLAccessorCreator();
		
		// Verify that multiple calls return different instances
		Accessor firstAccessor = creator.getAccessor();
		Accessor secondAccessor = creator.getAccessor();
		
		assertNotNull(firstAccessor);
		assertNotNull(secondAccessor);
		assertNotSame(firstAccessor, secondAccessor);
	}
}