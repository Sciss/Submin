/*
 *  SubminLookAndFeel.scala
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

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.Skin;
import com.alee.managers.style.StyleManager;
import com.alee.utils.LafUtils;

import javax.swing.*;

/**
 * Extends <code>WebLookAndFeel</code> with a few
 * customized UI components, such as the file-chooser.
 * Otherwise, changes id and name.
 */
public class SubminLookAndFeel extends WebLookAndFeel {
    public static String fileChooserUI = SubminFileChooserUI.class.getCanonicalName ();
    // public static String scrollPaneUI  = SubminScrollPaneUI .class.getCanonicalName ();

    @Override
    public String getName() {
        return "Submin";
    }

    @Override
    public String getID() {
        return "submin";
    }

    @Override
    protected void initClassDefaults(final UIDefaults table) {
        super.initClassDefaults(table);
        table.put("FileChooserUI", SubminLookAndFeel.fileChooserUI);
        // table.put("ScrollPaneUI" , SubminLookAndFeel.scrollPaneUI );
    }

    @Override
    public String getDescription() {
        return "WebLaF based cross-platform look and feel with light and dark skin";
    }

    public static boolean install(final Class<? extends Skin> skin) {
        return install(skin, false);
    }

    /**
     * Installs look and feel in one simple call.
     *
     * @param skin     initially installed skin class
     * @param updateUI whether should update visual representation of all existing components or not
     * @return true if look and feel was successfully installed, false otherwise
     */
    public static boolean install(final Class<? extends Skin> skin, final boolean updateUI) {
        // Preparing initial skin
        StyleManager.setDefaultSkin(skin);

        // Installing LookAndFeel
        if (LafUtils.setupLookAndFeelSafely(SubminLookAndFeel.class)) {
            // Updating already created components tree
            if (updateUI) {
                updateAllComponentUIs();
            }

            // Installed successfully
            return true;
        } else {
            // Installation failed
            return false;
        }
    }
}