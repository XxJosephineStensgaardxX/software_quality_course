package com.jabberpoint.style;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StyleTest {

    @Test
    void constructorWithParameters_shouldReturnTheParametersOfConstructor() {
        Style style = new Style(10, Color.blue, 32, 15, FontName.HELVETICA);

        assertEquals(10, style.getIndent());
        assertEquals(Color.blue, style.getColor());
        assertEquals(32, style.getFontSize());
        assertEquals(15, style.getLeading());
        assertEquals(FontName.HELVETICA, style.getFontName());
        assertNotNull(style.getFont());
        assertEquals(FontName.HELVETICA.getName(), style.getFont().getName());
        assertEquals(Font.BOLD, style.getFont().getStyle());
        assertEquals(32, style.getFont().getSize());
    }

    @Test
    void defaultConstructor_shouldReturnDefaultStyle() {
        Style style = new Style();

        assertEquals(0, style.getIndent());
        assertEquals(Color.black, style.getColor());
        assertEquals(Style.DEFAULT_FONT_SIZE, style.getFontSize());
        assertEquals(Style.DEFAULT_LEADING, style.getLeading());
        assertEquals(FontName.ARIAL, style.getFontName());
    }

    @Test
    void setIndentWithValidValue_shouldReturn25() {
        Style style = new Style();
        style.setIndent(25);
        assertEquals(25, style.getIndent());
    }

    @Test
    void setIndentWithNegativeValue_shouldThrowException() {
        Style style = new Style();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            style.setIndent(-5);
        });
        assertEquals("indent cannot be less than zero", exception.getMessage());
    }

    @Test
    void setFontSizeWithValidValue_shouldReturn72FontSize() {
        Style style = new Style();
        style.setFontSize(72);
        assertEquals(72, style.getFontSize());
    }

    @Test
    void setFontSizeWithNegativeValue_shouldThrowException() {
        Style style = new Style();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            style.setFontSize(-10);
        });
        assertEquals("font size must be greater than zero", exception.getMessage());
    }

    @Test
    void setLeadingWithValidValue_shouldReturn30() {
        Style style = new Style();
        style.setLeading(30);
        assertEquals(30, style.getLeading());
    }

    @Test
    void setLeadingWithNegativeValue_shouldThrowException() {
        Style style = new Style();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            style.setLeading(-2);
        });
        assertEquals("leading must at least be zero", exception.getMessage());
    }

    @Test
    void setColor_verifiesTheColor() {
        Style style = new Style();
        style.setColor(Color.magenta);
        assertEquals(Color.magenta, style.getColor());
    }

    @Test
    void setFontName_shouldReturnTimesNewRoman() {
        Style style = new Style();
        style.setFontName(FontName.TIMES_NEW_ROMAN);
        assertEquals(FontName.TIMES_NEW_ROMAN, style.getFontName());
    }

    @Test
    void setFont_shouldReturnTheNewFont() {
        Style style = new Style();
        Font newFont = new Font("Dialog", Font.ITALIC, 16);
        style.setFont(newFont);
        assertEquals(newFont, style.getFont());
    }

    @Test
    void getFontWithScale_shouldScalFontUpAndDown() {
        Style style = new Style(0, Color.black, 20, 10, FontName.ARIAL);

        // Test scaling up
        Font scaledUp = style.getFont(1.5f);
        assertEquals(30, scaledUp.getSize()); // 20 * 1.5 = 30

        // Test scaling down
        Font scaledDown = style.getFont(0.5f);
        assertEquals(10, scaledDown.getSize()); // 20 * 0.5 = 10
    }

    @Test
    void toString_shouldReturnAString_verifiesTheAttributesOfTheString() {
        Style style = new Style(15, Color.red, 36, 12, FontName.HELVETICA);
        String representation = style.toString();

        // Verify the string contains the main attributes
        assertTrue(representation.contains("15"));
        assertTrue(representation.contains("36"));
        assertTrue(representation.contains("12"));
        assertTrue(representation.contains("java.awt.Color"));
    }
}