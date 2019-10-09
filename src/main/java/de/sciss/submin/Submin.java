/*
 *  Submin.scala
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

import com.alee.utils.XmlUtils;
import com.thoughtworks.xstream.XStream;

/**
 * Submin is an extension of the WebLookAndFeel. This class contains
 * a simple utility function to install the look-and-feel with either
 * the light or dark skin.
 */
public class Submin {
    private Submin() {}

    public static void install(boolean isDark) {
        // cf. https://stackoverflow.com/questions/44698296/security-framework-of-xstream-not-initialized-xstream-is-probably-vulnerable
        final XStream xs = XmlUtils.getXStream();
//        XStream.setupDefaultSecurity(xs);
        xs.allowTypesByWildcard(new String[] { "com.alee.**" });

        if (isDark) SubminDarkSkin  .installSkin();
        else        SubminLightSkin .installSkin();
    }
}
