package highest.points;

import org.geotools.data.DataUtilities;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeature;

import java.awt.*;
import java.util.List;

public class MapBuilder {

    public static Style DEFAULT_POLYGON_STYLE = SLD.createPolygonStyle(Color.green, Color.CYAN, 1.0f);

    public JMapFrame build(List<SimpleFeature> source) {
        Layer layer = new FeatureLayer(DataUtilities.collection(source), DEFAULT_POLYGON_STYLE);

        MapContent mapContent = new MapContent();
        mapContent.addLayer(layer);

        return new JMapFrame(mapContent);
    }
}
