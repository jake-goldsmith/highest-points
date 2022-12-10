package highest.points.io;

import highest.points.io.ShapeFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeFileReaderTest {
    private ShapeFileReader underTest;

    @BeforeEach
    public void setUp() throws Exception {
        File file = new File("test-data" + File.separator + "doc.shp");
        underTest = new ShapeFileReader(file);
    }

    @Test
    public void shouldLoadDataFromFile() throws IOException {
        List<SimpleFeature> features = underTest.readFeaturesFromFile();
        assertFalse(features.isEmpty());
        assertNotNull(features.get(0));
    }
}