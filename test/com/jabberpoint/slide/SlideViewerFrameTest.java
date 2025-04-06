package com.jabberpoint.slide;

import com.jabberpoint.presentation.Presentation;
import com.jabberpoint.slide.metadata.Resolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideViewerFrameTest {

    private Presentation mockPresentation;

    @BeforeEach
    void setup() {
        mockPresentation = mock(Presentation.class);
    }

    @Test
    void getResolution_shouldReturnDefaultResolution() {
        SlideViewerFrame frame = new SlideViewerFrame("Test", mockPresentation);
        assertEquals(Resolution.STANDARD_DISPLAY, frame.getResolution());
    }

    @Test
    void setResolution_shouldUpdateResolution() {
        SlideViewerFrame frame = new SlideViewerFrame("Test", mockPresentation);
        frame.setResolution(Resolution.HD_TV);
        assertEquals(Resolution.HD_TV, frame.getResolution());
    }

    @Test
    void constructor_shouldSetPresentationView() {
        SlideViewerFrame frame = new SlideViewerFrame("Test", mockPresentation);
        verify(mockPresentation).setShowView(any(SlideViewerComponent.class));
    }
}
