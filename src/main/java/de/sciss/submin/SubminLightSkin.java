/*
 *  SubminLightSkin.scala
 *  (Submin)
 *
 *  Copyright (c) 2012-2018 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v3+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.submin;

import com.alee.api.resource.ClassResource;
import com.alee.managers.style.XmlSkin;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class SubminLightSkin extends XmlSkin {
    public SubminLightSkin() {
        super(new ClassResource(SubminLightSkin.class, "light/skin.xml"));
    }

    /*
     * Manager initialization mark.
     */
    private static boolean initialized = false;

    /**
     * Initializes StyleManager settings.
     */
    public static synchronized void initialize() {
        if (!initialized) {
            initialized = true;
        }
    }

    /**
     * Initializes the skin and sets the L&amp;F to <tt>WebLookAndFeel</tt> using this skin.
     * Sets UI default <tt>"dark-skin"</tt> to <tt>false</tt>.
     */
    public static void installSkin() {
        initialize();
        SubminLookAndFeel.install(SubminLightSkin.class);
        UIManager.put("dark-skin", false);
        UIManager.put("Label.foreground", new ColorUIResource(0, 0, 0));
    }
}