package highest.points.map;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.swing.JMapFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opengis.feature.simple.SimpleFeature;

import java.io.IOException;
import java.util.List;

import static highest.points.io.TestDataUtil.testGridCoverageDataUtil;
import static highest.points.io.TestDataUtil.testShapeDataUtil;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    private List<SimpleFeature> simpleFeature;

    private GridCoverage2D gridCoverage;

    private SimpleFeature first;

    @BeforeEach
    public void setUp() throws IOException {
        simpleFeature = testShapeDataUtil();
        gridCoverage = testGridCoverageDataUtil();
        first = testShapeDataUtil().get(0);
    }

    @Test
    public void testBuild() throws IOException {
        JMapFrame map = Map.builder().features(simpleFeature).build().getMapFrame();

        assertNotNull(map.getMapContent());
        assertEquals(first, map.getMapContent().layers().get(0).getFeatureSource().getFeatures().toArray()[0]);
    }

    @Test
    public void testBuildGridCoverage() throws IOException {
        JMapFrame map = Map.builder().coverage(gridCoverage).build().getMapFrame();

        assertNotNull(map.getMapContent());
        assertNotNull(map.getMapContent().layers().get(0).getFeatureSource().getFeatures().toArray()[0]);
    }

    @Test
    public void testBuildEmpty() throws IOException {
        JMapFrame map = Map.builder().build().getMapFrame();

        assertNotNull(map.getMapContent());
        assertTrue(map.getMapContent().layers().isEmpty());
    }
}