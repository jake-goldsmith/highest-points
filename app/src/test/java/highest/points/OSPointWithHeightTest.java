package highest.points;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opengis.feature.simple.SimpleFeature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OSPointWithHeightTest {
    public static final int HEIGHT = 10;
    private OSPointWithHeight underTest;

    @Mock
    SimpleFeature point;

    @Mock
    SimpleFeature invalidPoint;

    @BeforeEach
    void setUp() {
        when(point.getAttribute("PROP_VALUE")).thenReturn(HEIGHT);
        underTest = new OSPointWithHeight(point);
    }

    @Test
    public void getHeight() {
        assertEquals(HEIGHT, underTest.getHeight());
    }

    @Test
    public void getFeature() {
        assertEquals(point, underTest.getFeature());
    }

    @Test
    public void shouldThrowExceptionForNonIntegerHeight() {
        when(invalidPoint.getAttribute("PROP_VALUE")).thenReturn("10");
        assertThrows(ClassCastException.class, () -> new OSPointWithHeight(invalidPoint));
    }
}