package highest.points.model;

import highest.points.model.OSCounty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opengis.feature.simple.SimpleFeature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OSCountyTest {

    public static final String NAME = "Test Name";
    private OSCounty underTest;

    @Mock
    private SimpleFeature county;

    @BeforeEach
    public void setUp() {
        when(county.getAttribute("NAME")).thenReturn(NAME);
        underTest = new OSCounty(county);
    }

    @Test
    public void shouldReturnName() {
        assertEquals(NAME, underTest.getName());
    }

    @Test
    public void shouldReturnFeature() {
        assertEquals(county, underTest.getFeature());
    }
}