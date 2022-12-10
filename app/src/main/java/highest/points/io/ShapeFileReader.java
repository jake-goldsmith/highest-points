package highest.points.io;

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
import java.util.*;
import java.util.Map;

public class ShapeFileReader implements AutoCloseable {

    private final File file;
    private DataStore dataStore;

    public ShapeFileReader(File file) {
        this.file = file;
    }

    private Map<String, Object> buildDataStoreMap() throws MalformedURLException {
        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());
        return map;
    }

    private FeatureCollection<SimpleFeatureType, SimpleFeature> readFile() throws IOException {
        // Following tutorial from https://docs.geotools.org/stable/userguide/library/data/shape.html
        dataStore = DataStoreFinder.getDataStore(buildDataStoreMap());

        String typeName = dataStore.getTypeNames()[0];

        FeatureSource<SimpleFeatureType, SimpleFeature> source =
                dataStore.getFeatureSource(typeName);

        return source.getFeatures();
    }

    public List<SimpleFeature> readFeaturesFromFile() throws IOException {
        List<SimpleFeature> featureList = new ArrayList<>();
        // TODO - Steam the data in rather than collecting a list
        try (FeatureIterator<SimpleFeature> features = readFile().features()) {
            while (features.hasNext()) {
                featureList.add(features.next());
            }
        }
        return featureList;
    }

    @Override
    public void close() {
        if (Objects.nonNull(dataStore)) {
            dataStore.dispose();
        }
    }
}
