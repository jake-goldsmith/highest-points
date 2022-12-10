package highest.points;

import org.geotools.coverage.grid.GridCoverage2D;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestDataUtil {
    public static List<SimpleFeature> testShapeDataUtil() throws IOException {
        return new ShapeFileReader(new File("test-data" + File.separator + "doc.shp")).readFeaturesFromFile();
    }

    public static GridCoverage2D testGridCoverageDataUtil() throws IOException {
        return new CoverageFileReader(new File("test-data" + File.separator + "example.gpkg")).readCoverageFromFile();
    }
}
