/*
 *  Submin.scala
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

/**
 * Submin is an extension of the WebLookAndFeel. This class contains
 * a simple utility function to install the look-and-feel with either
 * the light or dark skin.
 */
public class Submin {
    private Submin() {}

    public static void install(boolean isDark) {
        if (isDark) SubminDarkSkin .install();
        else        SubminLightSkin.install();
    }
}
