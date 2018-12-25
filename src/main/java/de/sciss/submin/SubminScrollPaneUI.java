///*
// *  SubminScrollPaneUI.scala
// *  (Submin)
// *
// *  Copyright (c) 2012-2018 Hanns Holger Rutz. All rights reserved.
// *
// *  This software is published under the GNU General Public License v3+
// *
// *
// *  For further information, please contact Hanns Holger Rutz at
// *  contact@sciss.de
// */
//
//package de.sciss.submin;
//
//import com.alee.laf.scroll.ScrollCornerProvider;
//import com.alee.laf.scroll.WebScrollPaneUI;
//
//import javax.swing.*;
//import javax.swing.plaf.ComponentUI;
//import java.awt.*;
//
//public class SubminScrollPaneUI extends WebScrollPaneUI {
//    public static ComponentUI createUI (final JComponent c )
//    {
//        return new SubminScrollPaneUI ();
//    }
//
//    /**
//     * Style settings.
//     */
//    protected Color cornerBackground;
//    protected Color cornerLineColor;
//
//    public Color getCornerBackground() {
//        return cornerBackground;
//    }
//
//    public void setCornerBackground(Color cornerBackground) {
//        this.cornerBackground = cornerBackground;
//    }
//
//    public Color getCornerLineColor() {
//        return cornerLineColor;
//    }
//
//    public void setCornerLineColor(Color cornerLineColor) {
//        this.cornerLineColor = cornerLineColor;
//    }
//
//    @Override
//    public void installUI(JComponent c) {
//        super.installUI(c);
//    }
//
//    @Override
//    protected void updateCorner(String key, ScrollCornerProvider provider) {
//        JComponent corner = cornersCache.get(key);
//        if (corner == null) {
//            if (provider != null) {
//                corner = provider.getCorner(key);
//            }
//            if (corner == null) {
//                corner = new SubminScrollPaneCorner(key);
//            }
//            cornersCache.put(key, corner);
//        }
//
//        scrollpane.setCorner(key, corner);
//    }
//}
