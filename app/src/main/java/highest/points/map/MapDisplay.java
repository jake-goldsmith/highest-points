package highest.points.map;

import lombok.RequiredArgsConstructor;
import org.geotools.swing.JMapFrame;

import javax.swing.*;

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
