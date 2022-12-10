package highest.points.io;

import lombok.AllArgsConstructor;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridEnvelope2D;
import org.geotools.coverage.grid.GridGeometry2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.geopkg.GeoPackage;
import org.geotools.geopkg.TileEntry;
import org.geotools.geopkg.mosaic.GeoPackageReader;
import org.geotools.parameter.Parameter;
import org.opengis.parameter.GeneralParameterValue;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class CoverageFileReader {

    private File source;

    public GridCoverage2D readCoverageFromFile() throws IOException {
        // https://gis.stackexchange.com/questions/242910/how-to-read-geopackage-tiles-using-geotools

        TileEntry tileEntry;
        GeoPackageReader reader;
        GeneralParameterValue[] parameters;

        try (GeoPackage geoPackage = new GeoPackage(source)) {
            reader = new GeoPackageReader(source, null);
            parameters = new GeneralParameterValue[1];
            tileEntry = geoPackage.tiles().get(0);
        }
        ReferencedEnvelope referencedEnvelope = tileEntry.getBounds();
        Rectangle rectangle = new Rectangle((int) referencedEnvelope.getWidth(), (int) referencedEnvelope.getHeight());
        GridEnvelope2D gridEnvelope = new GridEnvelope2D(rectangle);
        GridGeometry2D gridGeometry = new GridGeometry2D(gridEnvelope, referencedEnvelope);
        parameters[0] = new Parameter<GridGeometry2D>(AbstractGridFormat.READ_GRIDGEOMETRY2D, gridGeometry);
        String tableName = tileEntry.getTableName(); // "rivers_tiles"

        System.out.println("2 dimensions of gridEnvelope - width: " + gridEnvelope.getWidth() + " | height:" + gridEnvelope.getHeight());

        return reader.read(tableName, parameters);
    }
}
