package de.sciss.submin;

import com.alee.laf.scroll.WebScrollPaneUI;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

// XXX TODO temporary work-around for NPE issue with ScalaCollider-Swing (DockingFrames)
public class SubminScrollPaneUI extends WebScrollPaneUI {
    @Override
    public void installUI(JComponent c) {
        c.setLayout(new SubminScrollPaneLayout());
        super.installUI(c);
    }

    public static ComponentUI createUI(final JComponent c) {
        return new SubminScrollPaneUI();
    }
}
