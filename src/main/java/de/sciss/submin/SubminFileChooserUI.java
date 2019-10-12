package de.sciss.submin;

import com.alee.laf.filechooser.WebFileChooserPanel;
import com.alee.laf.filechooser.WebFileChooserUI;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.plaf.ComponentUI;

/**
 * Extends <code>WebFileChooserUI</code> by providing
 * a refined web-file-chooser-panel.
 */
public class SubminFileChooserUI extends WebFileChooserUI {
    @Override
    protected WebFileChooserPanel createPanel(final JFileChooser fileChooser) {
        return new SubminFileChooserPanel(getFileChooserType(), fileChooser.getControlButtonsAreShown());
    }

    public static ComponentUI createUI(final JComponent c) {
        return new SubminFileChooserUI();
    }
}
