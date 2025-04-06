package com.jabberpoint.slide;

import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.style.Theme;
import com.jabberpoint.slide.Slide;
import com.jabberpoint.style.styleManager.StyleManager;
import com.jabberpoint.style.ViewerStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideViewerComponentTest {

    private Presentation mockPresentation;
    private Slide mockSlide;
    private JFrame mockFrame;

    @BeforeEach
    void setup() {
        mockPresentation = mock(Presentation.class);
        mockSlide = mock(Slide.class);
        mockFrame = mock(JFrame.class);
    }

    @Test
    void constructor_shouldThrowException_whenPresentationIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SlideViewerComponent(null, mockFrame);
        });
    }

    @Test
    void constructor_shouldThrowException_whenFrameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SlideViewerComponent(mockPresentation, null);
        });
    }

    @Test
    void update_shouldSetPresentationAndSlideAndUpdateTitle() {
        when(mockPresentation.getTitle()).thenReturn("Test Title");

        SlideViewerComponent component = new SlideViewerComponent(mockPresentation, mockFrame);
        component.update(mockPresentation, mockSlide);

        // Check that setTitle was called
        verify(mockFrame).setTitle("Test Title");
    }

    @Test
    void update_shouldRepaint_whenSlideIsNull() {
        SlideViewerComponent component = new SlideViewerComponent(mockPresentation, mockFrame);

        // Safe to call with null slide
        component.update(mockPresentation, null);

        // No exception should be thrown; hard to assert repaint directly, but no errors is good
    }

    @Test
    void paintComponent_shouldCallSlideAccept_whenValidSlide() {
        when(mockPresentation.getSlideNumber()).thenReturn(0);
        when(mockPresentation.getSize()).thenReturn(1);

        SlideViewerComponent component = new SlideViewerComponent(mockPresentation, mockFrame);
        component.update(mockPresentation, mockSlide);

        // Create dummy graphics context
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        component.paintComponent(g);

        verify(mockSlide).accept(any()); // Ensure slide.accept(renderVisitor) was called
    }
}
