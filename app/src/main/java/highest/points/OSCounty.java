package highest.points;

import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;

public class OSCounty {
    private final SimpleFeature county;
    private final String name;

    public OSCounty(SimpleFeature county) {
        this.county = county;
        name = (String) county.getAttribute("NAME");
    }

    public String getName() {
        return name;
    }

    public SimpleFeature getFeature() {
        return county;
    }

    public Geometry getGeometry() {
        return (Geometry) county.getDefaultGeometry();
    }
}
