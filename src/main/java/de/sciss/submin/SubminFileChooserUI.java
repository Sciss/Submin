/*
 *  SubminFileChooserPanel.scala
 *  (Submin)
 *
 *  Copyright (c) 2012-2016 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v3+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.submin;

import com.alee.laf.filechooser.WebFileChooserPanel;
import com.alee.laf.filechooser.WebFileChooserUI;

import javax.swing.*;
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
