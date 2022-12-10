package highest.points;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opengis.coverage.grid.GridCoverage;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class CoverageFileReaderTest {

    private CoverageFileReader underTest;

    @BeforeEach
    void setUp() {
        File file = new File("test-data" + File.separator + "example.gpkg");

        underTest = new CoverageFileReader(file);
    }

    @Test
    public void shouldReturnGridCoverage() throws IOException {
        GridCoverage coverage = underTest.readCoverageFromFile();

        assertNotNull(coverage.getGridGeometry());
    }
}