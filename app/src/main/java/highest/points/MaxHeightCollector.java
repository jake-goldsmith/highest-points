package highest.points;

import java.util.HashMap;
import java.util.Map;

public class MaxHeightCollector {

    private final Map<OSCounty, Integer> heights = new HashMap<>();
    private final Map<OSCounty, OSPointWithHeight> points = new HashMap<>();

    public void addPoint(OSCounty county, OSPointWithHeight point) {
        if (couldPointBeHigher(point, county)) {
            heights.put(county, point.getHeight());
            points.put(county, point);
        }
    }

    public Integer getMaxHeight(OSCounty county) {
        return heights.get(county);
    }

    public boolean couldPointBeHigher(OSPointWithHeight point, OSCounty county) {
        return !heights.containsKey(county) || heights.get(county) < point.getHeight();
    }

    public OSPointWithHeight getMaxHeightPoint(OSCounty county) {
        OSPointWithHeight maxHeightPoint = points.get(county);
        if (maxHeightPoint != null) {
            // Setting the label - use the FEAT_TYPE attribute, so we don't need to retype the SimpleFeature adding and additional attribute.
            maxHeightPoint.getFeature().setAttribute("FEAT_TYPE", String.format("%s %sm", county.getName(), maxHeightPoint.getHeight()));
        }
        return maxHeightPoint;
    }
}
