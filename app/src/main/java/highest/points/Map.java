package highest.points;

import lombok.Builder;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.DataUtilities;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridCoverageLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.styling.StyleBuilder;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeature;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Builder
public class Map {

    @Builder.Default
    private List<SimpleFeature> features = null;
    @Builder.Default
    private GridCoverage2D coverage = null;
    @Builder.Default
    private List<SimpleFeature> points = null;

    public static Style DEFAULT_POLYGON_STYLE = SLD.createPolygonStyle(Color.green, Color.CYAN, 1.0f);
    public static Style DEFAULT_COVERAGE_STYLE = SLD.wrapSymbolizers(CommonFactoryFinder.getStyleFactory().getDefaultRasterSymbolizer());
    public static Style DEFAULT_POINT_STYLE = SLD.createPointStyle("circle", Color.RED, Color.RED, 1.0f, 5.0f, "FEAT_TYPE", new StyleBuilder().createFont("sans-serif", 15.0f));

    public JMapFrame getMapFrame() {
        List<Layer> layers = new ArrayList<>();

        if (features != null) {
            Layer layer = new FeatureLayer(DataUtilities.collection(features), DEFAULT_POLYGON_STYLE);
            layers.add(layer);
        }

        if (coverage != null) {
            Layer layer = new GridCoverageLayer(coverage, DEFAULT_COVERAGE_STYLE);
            layers.add(layer);
        }

        if (points != null) {
            Layer layer = new FeatureLayer(DataUtilities.collection(points), DEFAULT_POINT_STYLE);
            layers.add(layer);
        }

        MapContent mapContent = new MapContent();
        mapContent.addLayers(layers);

        return new JMapFrame(mapContent);
    }
}
