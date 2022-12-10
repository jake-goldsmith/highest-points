package highest.points;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opengis.feature.simple.SimpleFeature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaxHeightCollectorTest {

    private MaxHeightCollector underTest;

    @Mock
    private OSCounty countyA;

    @Mock
    private OSCounty countyB;

    @Mock
    private OSPointWithHeight lowPoint;
    @Mock
    private OSPointWithHeight mediumPoint;
    @Mock
    private OSPointWithHeight highPoint;

    @Mock
    private SimpleFeature mockFeature;

    @BeforeEach
    public void setUp() {
        underTest = new MaxHeightCollector();
    }

    @Test
    public void shouldRecordPoint() {
        when(mediumPoint.getHeight()).thenReturn(10);
        when(mediumPoint.getFeature()).thenReturn(mockFeature);
        when(countyA.getName()).thenReturn("County A");

        underTest.addPoint(countyA, mediumPoint);
        assertEquals(Integer.valueOf(10), underTest.getMaxHeight(countyA));
        assertEquals(mediumPoint, underTest.getMaxHeightPoint(countyA));

        verify(mockFeature).setAttribute("FEAT_TYPE", "County A 10m");
    }

    @Test
    public void shouldOverrideWithHigherPoint() {
        when(mediumPoint.getHeight()).thenReturn(10);
        when(highPoint.getHeight()).thenReturn(20);
        when(highPoint.getFeature()).thenReturn(mockFeature);

        underTest.addPoint(countyA, mediumPoint);
        underTest.addPoint(countyA, highPoint);
        assertEquals(Integer.valueOf(20), underTest.getMaxHeight(countyA));
        assertEquals(highPoint, underTest.getMaxHeightPoint(countyA));
    }

    @Test
    public void shouldNotOverrideWithLowerPoint() {
        when(mediumPoint.getHeight()).thenReturn(10);
        when(lowPoint.getHeight()).thenReturn(5);
        when(mediumPoint.getFeature()).thenReturn(mockFeature);

        underTest.addPoint(countyA, mediumPoint);
        underTest.addPoint(countyA, lowPoint);
        assertEquals(Integer.valueOf(10), underTest.getMaxHeight(countyA));
        assertEquals(mediumPoint, underTest.getMaxHeightPoint(countyA));
    }

    @Test
    public void shouldStoreCountyHeightsSeparately() {
        when(mediumPoint.getHeight()).thenReturn(10);
        when(highPoint.getHeight()).thenReturn(20);
        when(mediumPoint.getFeature()).thenReturn(mockFeature);
        when(highPoint.getFeature()).thenReturn(mockFeature);

        underTest.addPoint(countyB, highPoint);
        underTest.addPoint(countyA, mediumPoint);

        assertEquals(Integer.valueOf(10), underTest.getMaxHeight(countyA));
        assertEquals(mediumPoint, underTest.getMaxHeightPoint(countyA));
        assertEquals(Integer.valueOf(20), underTest.getMaxHeight(countyB));
        assertEquals(highPoint, underTest.getMaxHeightPoint(countyB));
    }

    @Test
    public void shouldCheckCountyIfItHasNoRecordedHeight() {
        assertTrue(underTest.couldPointBeHigher(mediumPoint, countyA));
    }

    @Test
    public void shouldCheckCountyIfItHasLowerPoint() {
        when(mediumPoint.getHeight()).thenReturn(10);
        underTest.addPoint(countyA, mediumPoint);

        when(highPoint.getHeight()).thenReturn(20);
        assertTrue(underTest.couldPointBeHigher(highPoint, countyA));
    }

    @Test
    public void shouldNotCheckCountyIfItHasHigherPoint() {
        when(mediumPoint.getHeight()).thenReturn(10);
        underTest.addPoint(countyA, mediumPoint);

        when(lowPoint.getHeight()).thenReturn(5);
        assertFalse(underTest.couldPointBeHigher(lowPoint, countyA));
    }

}