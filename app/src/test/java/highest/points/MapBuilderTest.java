package highest.points;

import org.geotools.swing.JMapFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opengis.feature.simple.SimpleFeature;

import java.io.IOException;
import java.util.List;

import static highest.points.TestDataUtil.testShapeDataUtil;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapBuilderTest {

    private List<SimpleFeature> simpleFeature;

    private SimpleFeature first;

    @BeforeEach
    public void setUp() throws IOException {
        simpleFeature = testShapeDataUtil();
        first = testShapeDataUtil().get(0);
    }

    @Test
    public void testBuild() throws IOException {
        JMapFrame map = new MapBuilder().build(simpleFeature);

        assertNotNull(map.getMapContent());
        assertEquals(first, map.getMapContent().layers().get(0).getFeatureSource().getFeatures().toArray()[0]);
    }
}