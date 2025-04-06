package com.jabberpoint.style;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FontNameTest {

    @Test
    void enumValues_allEnumsShouldExist() {
        // Test that all enum values exist
        assertEquals(5, FontName.values().length);
        assertNotNull(FontName.ARIAL);
        assertNotNull(FontName.HELVETICA);
        assertNotNull(FontName.TIMES_NEW_ROMAN);
        assertNotNull(FontName.COURIER_NEW);
        assertNotNull(FontName.DIALOG);
    }

    @Test
    void getName_shouldReturnAllTheNamesOfTheEnums() {
        // Test that getName returns the correct string for each enum
        assertEquals("Arial", FontName.ARIAL.getName());
        assertEquals("Helvetica", FontName.HELVETICA.getName());
        assertEquals("Times New Roman", FontName.TIMES_NEW_ROMAN.getName());
        assertEquals("Courier New", FontName.COURIER_NEW.getName());
        assertEquals("Dialog", FontName.DIALOG.getName());
    }

    @Test
    void getNameByEnumValue_shouldBeRetrievable() {
        // Test that we can get enum values by name
        assertSame(FontName.ARIAL, FontName.valueOf("ARIAL"));
        assertSame(FontName.HELVETICA, FontName.valueOf("HELVETICA"));
        assertSame(FontName.TIMES_NEW_ROMAN, FontName.valueOf("TIMES_NEW_ROMAN"));
        assertSame(FontName.COURIER_NEW, FontName.valueOf("COURIER_NEW"));
        assertSame(FontName.DIALOG, FontName.valueOf("DIALOG"));
    }

    @Test
    void valueOfWithInvalidName_shouldThrowException() {
        // Test that tries to get an enum with an invalid name throws an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FontName.valueOf("NONEXISTENT_FONT");
        });

        assertTrue(exception.getMessage().contains("No enum constant"));
    }
}