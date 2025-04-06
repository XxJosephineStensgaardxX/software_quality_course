package com.jabberpoint.accessor;

import com.jabberpoint.accessor.Accessor;
import com.jabberpoint.accessor.creator.AccessorCreator;
import com.jabberpoint.presentation.Presentation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccessorCreatorTest {
	
	private static class TestAccessorCreator extends AccessorCreator
	{
		@Override
		protected Accessor createAccessor()
		{
			return new TestAccessor();
		}
		
		private class TestAccessor extends Accessor
		{
			@Override
			public void loadFile(Presentation presentation, String filename) throws IOException
			{
				//left empty for demo purposes.
			}
			
			@Override
			public void saveFile(Presentation presentation, String filename) throws IOException
			{
			
			}
		}
	}
	
	@Test
	void getAccessor_whenCreateAccessorThrows_ShouldPropagateException()
	{
		AccessorCreator creator = new AccessorCreator()
		{
			@Override
			protected Accessor createAccessor()
			{
				throw new RuntimeException("Accessor creation failed");
			}
		};
		
		assertThrows(RuntimeException.class, creator::getAccessor,
					 "getAccessor should propagate exceptions from createAccessor");
	}
	
	@Test
	void getAccessor_multipleCalls_ShouldReturnNewNonNullInstances()
	{
		AccessorCreator creator = new TestAccessorCreator();
		
		Accessor accessor = creator.getAccessor();
		assertNotNull(accessor);
		
		// Verify that each call returns a new instance
		Accessor secondAccessor = creator.getAccessor();
		assertNotNull(secondAccessor);
		assertNotSame(accessor, secondAccessor);
	}
	
	@Test
	void createAccessor_usingReflection_ShouldReturnNonNullInstance()
	{
		TestAccessorCreator creator = new TestAccessorCreator();
		
		try
		{
			java.lang.reflect.Method createAccessorMethod =
					AccessorCreator.class.getDeclaredMethod("createAccessor");
			createAccessorMethod.setAccessible(true);
			
			Accessor accessor = (Accessor) createAccessorMethod.invoke(creator);
			assertNotNull(accessor);
		}
		catch (Exception e)
		{
			fail("Failed to invoke createAccessor method: " + e.getMessage());
		}
	}
}