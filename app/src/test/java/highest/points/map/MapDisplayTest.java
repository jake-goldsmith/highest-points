package highest.points.map;

import highest.points.map.Map;
import highest.points.map.MapDisplay;
import org.geotools.swing.JMapFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapDisplayTest {
    MapDisplay underTest;

    @Mock
    Map map;

    @Mock
    JMapFrame mockMapFrame;

    @Mock
    JToolBar mockToolBar;

    @BeforeEach
    public void setUp() {
        underTest = new MapDisplay();
    }

    @Test
    public void shouldRenderMap() {
        when(map.getMapFrame()).thenReturn(mockMapFrame);
        when(mockMapFrame.getToolBar()).thenReturn(mockToolBar);

        JMapFrame mapFrame = underTest.render(map);

        verify(mockMapFrame).enableToolBar(true);
        verify(mockMapFrame).enableStatusBar(true);
        verify(mockToolBar).addSeparator();

        verify(mockMapFrame).setVisible(true);

        assertEquals(mockMapFrame, mapFrame);
    }
}