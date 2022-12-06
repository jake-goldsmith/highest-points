package highest.points;

import lombok.AllArgsConstructor;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeature;

import javax.swing.*;
import java.util.List;

@AllArgsConstructor
public class MapDisplay {

    private MapBuilder mapBuilder;
    private List<SimpleFeature> features;

    public JMapFrame render() {
        JMapFrame mapFrame = mapBuilder.build(features);

        mapFrame.enableToolBar(true);
        mapFrame.enableStatusBar(true);

        JToolBar toolbar = mapFrame.getToolBar();
        toolbar.addSeparator();

        mapFrame.setVisible(true);

        return mapFrame;
    }
}
