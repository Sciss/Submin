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

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.CustomSkin;

import javax.swing.*;

public class SubminLightSkin extends CustomSkin {
    public SubminLightSkin() {
        super("light/skin.xml");
    }

    /**
     * Initializes the skin and sets the L&amp;F to <tt>WebLookAndFeel</tt> using this skin.
     * Sets UI default <tt>"dark-skin"</tt> to <tt>false</tt>.
     */
    public static void install() {
        SubminLookAndFeel.install(SubminLightSkin.class);
        UIManager.put("dark-skin", false);
    }
}