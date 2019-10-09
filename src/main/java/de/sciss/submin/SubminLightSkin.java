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

import com.alee.managers.style.XmlSkin;

import javax.swing.UIManager;

public class SubminLightSkin extends XmlSkin {
    public SubminLightSkin() {
        super(SubminLightSkin.class, "light/skin.xml");
    }

    /**
     * Initializes the skin and sets the L&amp;F to <tt>WebLookAndFeel</tt> using this skin.
     * Sets UI default <tt>"dark-skin"</tt> to <tt>false</tt>.
     */
    public static void installSkin() {
        SubminLookAndFeel.install(SubminLightSkin.class);
        UIManager.put("dark-skin", false);
    }
}