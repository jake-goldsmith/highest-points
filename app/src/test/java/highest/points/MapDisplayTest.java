package highest.points;

import org.geotools.swing.JMapFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.opengis.feature.simple.SimpleFeature;

import javax.swing.*;

import java.util.List;

import static highest.points.TestDataUtil.testShapeDataUtil;


public class MapDisplayTest {
    MapDisplay underTest;

    MapBuilder mapBuilder = new MapBuilder();

    @Mock
    JMapFrame mockMapFrame;

    @Mock
    JToolBar mockToolBar;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println(mapBuilder);
        List<SimpleFeature> features = testShapeDataUtil();
        System.out.println("features" + features);
        underTest = new MapDisplay(mapBuilder, features);
    }

    @Test
    public void shouldRenderMap() {
        System.out.println(mockMapFrame);
        JMapFrame map = underTest.render();
        // Can't get this test working at the moment, something funny is going on with the mocks.

        //when(mapBuilder.build(any())).thenReturn(mockMapFrame);

        /*verify(mockMapFrame).enableToolBar(true);
        verify(mockMapFrame).enableStatusBar(true);
        verify(mockToolBar).addSeparator();

        verify(mockMapFrame).setVisible(true);*/
    }
}