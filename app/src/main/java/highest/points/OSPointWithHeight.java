package highest.points;

import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;

public class OSPointWithHeight {

    private final SimpleFeature point;
    private final int height;

    public OSPointWithHeight(SimpleFeature point) throws ClassCastException {
        this.point = point;
        height = (int) point.getAttribute("PROP_VALUE");
    }

    public int getHeight() {
        return height;
    }

    public SimpleFeature getFeature() {
        return point;
    }

    public Geometry getGeometry() {
        return (Geometry) point.getDefaultGeometry();
    }
}
