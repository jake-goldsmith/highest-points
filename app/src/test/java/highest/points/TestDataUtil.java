package highest.points;

import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestDataUtil {
    public static List<SimpleFeature> testShapeDataUtil() throws IOException {
        return new ShapeFileReader(new File("test-data" + File.separator + "doc.shp")).readFeaturesFromFile();
    }
}
