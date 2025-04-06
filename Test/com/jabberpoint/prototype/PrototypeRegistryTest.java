package com.jabberpoint.prototype;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.jabberpoint.prototype.Registries.StyleRegistry;
import com.jabberpoint.prototype.Registries.ThemeRegistry;
import com.jabberpoint.style.FontName;
import com.jabberpoint.style.Style;
import com.jabberpoint.style.Theme;

import java.awt.Color;

public class PrototypeRegistryTest {
    private PrototypeRegistry<Style> styleRegistry;
    private PrototypeRegistry<Theme> themeRegistry;

    @BeforeEach
    public void setUp() {
        // Initialize registries
        styleRegistry = new PrototypeRegistry<>();
        themeRegistry = new PrototypeRegistry<>();

        // Initialize and register a sample style
        Style headerStyle = new Style(0, Color.BLUE, 28, 12, FontName.ARIAL);
        styleRegistry.addPrototype("header", headerStyle);

        // Initialize and register a sample theme
        Theme darkTheme = new Theme("Dark");
        darkTheme.addStyle(new Style(0, Color.WHITE, 32, 15, FontName.ARIAL));
        darkTheme.addStyle(new Style(20, Color.LIGHT_GRAY, 24, 10, FontName.TIMES_NEW_ROMAN));
        themeRegistry.addPrototype("dark", darkTheme);
    }

    @Test
    public void clone_StyleObject_ReturnsNewInstanceWithSameProperties() {
        // Get the prototype
        Style originalStyle = styleRegistry.getPrototype("header");

        // Check if clone is not null
        assertNotNull(originalStyle);

        // Check if it's a new instance but with same properties
        Style clonedStyle = originalStyle.clone();
        assertNotSame(originalStyle, clonedStyle);
        assertEquals(originalStyle.getIndent(), clonedStyle.getIndent());
        assertEquals(originalStyle.getColor(), clonedStyle.getColor());
        assertEquals(originalStyle.getFontSize(), clonedStyle.getFontSize());
        assertEquals(originalStyle.getLeading(), clonedStyle.getLeading());
        assertEquals(originalStyle.getFontName(), clonedStyle.getFontName());
    }

    @Test
    public void clone_ThemeObject_ReturnsNewInstanceWithDeepCopiedStyles() {
        // Get the prototype
        Theme originalTheme = themeRegistry.getPrototype("dark");

        // Check if clone is not null
        assertNotNull(originalTheme);

        // Check if it's a new instance but with same properties
        Theme clonedTheme = originalTheme.clone();
        assertNotSame(originalTheme, clonedTheme);
        assertEquals(originalTheme.getThemeName(), clonedTheme.getThemeName());

        // Check if styles list has same size
        assertEquals(originalTheme.getStyles().size(), clonedTheme.getStyles().size());

        // Check if styles are cloned (not same instances)
        assertNotSame(originalTheme.getStyles().get(0), clonedTheme.getStyles().get(0));

        // Check if style properties are the same
        Style originalStyle = originalTheme.getStyles().get(0);
        Style clonedStyle = clonedTheme.getStyles().get(0);
        assertEquals(originalStyle.getColor(), clonedStyle.getColor());
        assertEquals(originalStyle.getFontSize(), clonedStyle.getFontSize());
    }

    @Test
    public void getPrototype_ExistingAndNonExistingIds_ReturnsCloneOrNull() {
        // Test adding and retrieving prototypes
        Style bodyStyle = new Style(20, Color.BLACK, 16, 8, FontName.TIMES_NEW_ROMAN);
        styleRegistry.addPrototype("body", bodyStyle);

        Style retrievedStyle = styleRegistry.getPrototype("body");
        assertNotNull(retrievedStyle);
        assertNotSame(bodyStyle, retrievedStyle); // Should get a clone, not the original

        // Test getting non-existent prototype
        Style nonExistentStyle = styleRegistry.getPrototype("nonexistent");
        assertNull(nonExistentStyle);
    }

    @Test
    public void getDefaultStyles_StyleRegistry_ReturnsUniqueClonedInstances() {
        // Test the specialized StyleRegistry
        StyleRegistry specializedStyleRegistry = new StyleRegistry();

        // Test retrieving default styles
        Style titleStyle = specializedStyleRegistry.getTitleStyle();
        assertNotNull(titleStyle);

        Style headingStyle = specializedStyleRegistry.getHeadingStyle();
        assertNotNull(headingStyle);

        // Test that clones are returned
        Style firstHeading = specializedStyleRegistry.getHeadingStyle();
        Style secondHeading = specializedStyleRegistry.getHeadingStyle();
        assertNotSame(firstHeading, secondHeading);
    }

    @Test
    public void addDefaultThemes_ThemeRegistry_CreatesAndStoresThemes() {
        // Create a theme registry with default themes
        ThemeRegistry specializedThemeRegistry = new ThemeRegistry();
        specializedThemeRegistry.addDefaultThemes();

        // Test retrieving default themes
        Theme darkTheme = specializedThemeRegistry.getPrototype("dark");
        assertNotNull(darkTheme);
        assertEquals("Dark", darkTheme.getThemeName());

        Theme lightTheme = specializedThemeRegistry.getPrototype("light");
        assertNotNull(lightTheme);
        assertEquals("Light", lightTheme.getThemeName());
    }

    @Test
    public void modifyClonedStyle_ChangeProperties_OriginalRemainsUnchanged() {
        // Test that modifying a cloned style doesn't affect the original
        Style originalStyle = styleRegistry.getPrototype("header");
        Style clonedStyle = originalStyle.clone();

        // Modify the cloned style
        Color newColor = new Color(255, 0, 0); // Red
        clonedStyle.setColor(newColor);
        clonedStyle.setFontSize(36);

        // Check that original wasn't affected
        assertNotEquals(newColor, originalStyle.getColor());
        assertNotEquals(36, originalStyle.getFontSize());

        // Check that clone was modified
        assertEquals(newColor, clonedStyle.getColor());
        assertEquals(36, clonedStyle.getFontSize());
    }

    @Test
    public void modifyClonedTheme_ChangeNameAndAddStyle_OriginalRemainsUnchanged() {
        // Test that modifying a cloned theme doesn't affect the original
        Theme originalTheme = themeRegistry.getPrototype("dark");
        Theme clonedTheme = originalTheme.clone();

        // Modify the cloned theme
        clonedTheme.setThemeName("Modified Dark");

        // Add a new style to the cloned theme
        Style newStyle = new Style(60, Color.RED, 14, 5, FontName.COURIER_NEW);
        clonedTheme.addStyle(newStyle);

        // Check that original wasn't affected
        assertEquals("Dark", originalTheme.getThemeName());
        assertNotEquals(originalTheme.getStyles().size(), clonedTheme.getStyles().size());
    }

    @Test
    public void removePrototype_ExistingId_RemovesAndReturnsPrototype() {
        // Add a prototype to remove
        Style footerStyle = new Style(10, Color.GRAY, 12, 5, FontName.ARIAL);
        styleRegistry.addPrototype("footer", footerStyle);

        // Remove the prototype
        Style removedStyle = styleRegistry.removePrototype("footer");

        // Check that the correct prototype was returned
        assertNotNull(removedStyle);
        assertSame(footerStyle, removedStyle);

        // Check that it was actually removed from the registry
        assertNull(styleRegistry.getPrototype("footer"));
    }

    @Test
    public void removePrototype_NonExistingId_ReturnsNull() {
        // Try to remove a non-existent prototype
        Style removedStyle = styleRegistry.removePrototype("nonexistent");

        // Check that null was returned
        assertNull(removedStyle);
    }
}