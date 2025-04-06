package com.jabberpoint.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThemeTest {

    private Theme theme;
    private Style style1;
    private Style style2;

    @BeforeEach
    void setUp() {
        theme = new Theme("Test Theme");
        style1 = new Style(10, Color.blue, 28, 14, FontName.ARIAL);
        style2 = new Style(20, Color.green, 24, 12, FontName.HELVETICA);
    }

    @Test
    void constructorTest_constructorShouldBeEmpty() {
        assertEquals("Test Theme", theme.getThemeName());
        assertNotNull(theme.getStyles());
        assertTrue(theme.getStyles().isEmpty());
    }

    @Test
    void setThemeName_verifiesNewThemeName() {
        theme.setThemeName("New Theme Name");
        assertEquals("New Theme Name", theme.getThemeName());
    }

    @Test
    void addStyle_shouldAddStyle() {
        theme.addStyle(style1);

        assertEquals(1, theme.getStyles().size());
        assertSame(style1, theme.getStyles().get(0));

        // Add a second style
        theme.addStyle(style2);
        assertEquals(2, theme.getStyles().size());
        assertSame(style2, theme.getStyles().get(1));
    }

    @Test
    void getStyle_shouldGetStyle1AndStyle2() {
        theme.addStyle(style1);
        theme.addStyle(style2);

        assertEquals(style1, theme.getStyle(0));
        assertEquals(style2, theme.getStyle(1));
    }

    @Test
    void updateStyle_shouldVerifyTheNewStyle() {
        theme.addStyle(style1);

        // Create a new style to update with
        Style updatedStyle = new Style(15, Color.red, 32, 16, FontName.TIMES_NEW_ROMAN);

        theme.updateStyle(0, updatedStyle);

        // Verify the style was updated
        assertSame(updatedStyle, theme.getStyle(0));
    }

    @Test
    void removeStyle_verifiesStyle1HasBeenRemoved_style2ShouldRemain() {
        theme.addStyle(style1);
        theme.addStyle(style2);

        assertEquals(2, theme.getStyles().size());

        // Remove the first style
        theme.removeStyle(0);

        assertEquals(1, theme.getStyles().size());
        assertSame(style2, theme.getStyle(0)); // style2 should now be at index 0
    }

    @Test
    void setStyles_style1AndStyle2AreSet() {
        // Create a list of styles
        List<Style> styleList = new ArrayList<>();
        styleList.add(style1);
        styleList.add(style2);

        // Set the styles
        theme.setStyles(styleList);

        // Verify the styles were set
        assertEquals(2, theme.getStyles().size());
        assertSame(style1, theme.getStyle(0));
        assertSame(style2, theme.getStyle(1));
    }

    @Test
    void getStyle_invalidIndex_shouldThrowAnException() {
        theme.addStyle(style1); // Only add one style

        // Try to get a style at an index that doesn't exist
        assertThrows(IndexOutOfBoundsException.class, () -> {
            theme.getStyle(1);
        });
    }

    @Test
    void updateAndAddStyle_styleAddedToInvalidIndex_shouldThrowsException() {
        theme.addStyle(style1); // Only add one style

        // Try to update a style at an index that doesn't exist
        assertThrows(IndexOutOfBoundsException.class, () -> {
            theme.updateStyle(1, style2);
        });
    }

    @Test
    void removeStyle_invalidIndex_shouldThrowException() {
        theme.addStyle(style1); // Only add one style

        // Try to remove a style at an index that doesn't exist
        assertThrows(IndexOutOfBoundsException.class, () -> {
            theme.removeStyle(1);
        });
    }
}