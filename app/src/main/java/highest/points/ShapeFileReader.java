package highest.points;

import lombok.AllArgsConstructor;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ShapeFileReader {

    private File file;

    private Map<String, Object> buildDataStoreMap() throws MalformedURLException {
        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());
        return map;
    }

    private FeatureCollection<SimpleFeatureType, SimpleFeature> readFile() throws IOException {
        // Following tutorial from https://docs.geotools.org/stable/userguide/library/data/shape.html
        DataStore dataStore = DataStoreFinder.getDataStore(buildDataStoreMap());

        String typeName = dataStore.getTypeNames()[0];

        FeatureSource<SimpleFeatureType, SimpleFeature> source =
                dataStore.getFeatureSource(typeName);

        return source.getFeatures();
    }

    public List<SimpleFeature> readFeaturesFromFile() throws IOException {
        List<SimpleFeature> featureList = new ArrayList<>();
        // TODO - Steam the data in rather than collecting a list
        FeatureIterator<SimpleFeature> features = readFile().features();
            while (features.hasNext()) {
                featureList.add(features.next());
            }


        return featureList;
    }
}
