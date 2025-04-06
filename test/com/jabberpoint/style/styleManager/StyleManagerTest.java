package com.jabberpoint.style.styleManager;

import com.jabberpoint.style.FontName;
import com.jabberpoint.style.Style;
import com.jabberpoint.style.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StyleManagerTest {

    private StyleManager styleManager;

    @BeforeEach
    void setUp() {
        styleManager = new StyleManager();
    }

    @Test
    void getThemes_constructorInitializesDefaultTheme() {
        // Verify that constructor creates default theme
        assertNotNull(styleManager.getThemes());
        assertFalse(styleManager.getThemes().isEmpty());
        assertNotNull(styleManager.getTheme("Default Theme"));
    }

    @Test
    void addThemeAndGetTheme() {
        // Create a test theme
        Theme testTheme = new Theme("Test Theme");
        Style style = new Style(10, Color.green, 20, 5, FontName.COURIER_NEW);
        testTheme.addStyle(style);

        // Add the theme
        styleManager.addTheme(testTheme);

        // Verify theme was added and can be retrieved
        assertEquals(testTheme, styleManager.getTheme("Test Theme"));
        assertTrue(styleManager.getThemes().containsKey("Test Theme"));
    }

    @Test
    void removeTheme_shouldRemoveATestTheme() {
        // Create and add a test theme
        Theme testTheme = new Theme("Theme To Remove");
        styleManager.addTheme(testTheme);

        // Verify theme exists before removal
        assertNotNull(styleManager.getTheme("Theme To Remove"));

        // Remove the theme
        styleManager.removeTheme("Theme To Remove");

        // Verify theme no longer exists
        assertNull(styleManager.getTheme("Theme To Remove"));
    }

    @Test
    void addAndGetNamedStyle_styleShouldBeRetrievable() {
        // Create a test style
        Style testStyle = new Style(15, Color.yellow, 16, 8, FontName.HELVETICA);

        // Add as a named style
        styleManager.addNamedStyle("HighlightStyle", testStyle);

        // Verify style was added and can be retrieved
        assertEquals(testStyle, styleManager.getNamedStyle("HighlightStyle"));
    }

    @Test
    void removeNamedStyle() {
        // Create and add a test style
        Style testStyle = new Style(15, Color.yellow, 16, 8, FontName.HELVETICA);
        styleManager.addNamedStyle("StyleToRemove", testStyle);

        // Verify style exists before removal
        assertNotNull(styleManager.getNamedStyle("StyleToRemove"));

        // Remove the style
        styleManager.removeNamedStyle("StyleToRemove");

        // Verify style no longer exists
        assertNull(styleManager.getNamedStyle("StyleToRemove"));
    }

    @Test
    void setThemesAndNamedStyles_collectionShouldBePlacedCorrectly() {
        // Create the new collections
        Map<String, Theme> newThemes = new HashMap<>();
        Map<String, Style> newStyles = new HashMap<>();

        // Add items to them
        Theme customTheme = new Theme("Custom");
        newThemes.put("Custom", customTheme);

        Style customStyle = new Style(5, Color.pink, 14, 7, FontName.DIALOG);
        newStyles.put("Custom", customStyle);

        // Set them in the manager
        styleManager.setThemes(newThemes);
        styleManager.setNamedStyles(newStyles);

        // Verify the collections were replaced
        assertEquals(1, styleManager.getThemes().size());
        assertTrue(styleManager.getThemes().containsKey("Custom"));
        assertEquals(1, styleManager.getNamedStyles().size());
        assertTrue(styleManager.getNamedStyles().containsKey("Custom"));
    }

    @Test
    void defaultThemeStructure_shouldReturnTheAttributesOfTheDefaultTheme() {
        Theme defaultTheme = styleManager.getTheme("Default Theme");
        assertNotNull(defaultTheme);

        // Default theme should have 5 styles
        assertEquals(5, defaultTheme.getStyles().size());

        // Check first style attributes
        Style style1 = defaultTheme.getStyle(0);
        assertEquals(0, style1.getIndent());
        assertEquals(Color.red, style1.getColor());
        assertEquals(48, style1.getFontSize());
        assertEquals(20, style1.getLeading());
        assertEquals(FontName.ARIAL, style1.getFontName());
    }
}