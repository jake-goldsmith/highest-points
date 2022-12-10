package highest.points;

import lombok.RequiredArgsConstructor;
import org.eclipse.xsd.ecore.MapBuilder;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeature;

import javax.swing.*;
import java.util.List;

@RequiredArgsConstructor
public class MapDisplay {
    public JMapFrame render(Map map) {
        return displayMap(map.getMapFrame());
    }

    private static JMapFrame displayMap(JMapFrame mapFrame) {
        mapFrame.enableToolBar(true);
        mapFrame.enableStatusBar(true);

        JToolBar toolbar = mapFrame.getToolBar();
        toolbar.addSeparator();

        mapFrame.setSize(960, 540);
        mapFrame.setVisible(true);

        return mapFrame;
    }
}
