///*
// *  SubminFocusBorder.scala
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
//import com.alee.painter.decoration.IDecoration;
//import com.alee.painter.decoration.border.AbstractBorder;
//import com.alee.utils.GraphicsUtils;
//import com.thoughtworks.xstream.annotations.XStreamAlias;
//import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Area;
//
//@XStreamAlias("SubminFocusBorder")
//public class SubminFocusBorder<E extends JComponent, D extends IDecoration<E, D>, I extends SubminFocusBorder<E, D, I>>
//        extends AbstractBorder<E, D, I> {
//    /**
//     * Shade color.
//     */
//    @XStreamAsAttribute
//    protected Color focusColor;
//
//    /**
//     * Default border color.
//     */
//    private static final Color defaultFocusColor = new Color(48, 77, 130);
//
//    // @Override
//    public Color getFocusColor() {
//        return focusColor != null ? focusColor : defaultFocusColor;
//    }
//
//    @Override
//    public void paint(final Graphics2D g2d, final Rectangle bounds, final E c, final D d, final Shape shape) {
//        final float opacity = getOpacity();
//        if (opacity > 0 && getWidth() > 0) {
//            final Stroke stroke = getStroke();
//            final Color color = getColor();
//            final Color focusColor = getFocusColor();
//            final boolean hasColor = focusColor != null || color != null;
//
//            final Composite oc = GraphicsUtils.setupAlphaComposite(g2d, opacity, opacity < 1f);
//            final Stroke os = GraphicsUtils.setupStroke(g2d, stroke, stroke != null);
//            final Paint op = GraphicsUtils.setupPaint(g2d, focusColor, hasColor);
//
//            final Stroke s1 = new BasicStroke(2f);
////            final Area a1 = new Area(s1.createStrokedShape(shape));
////            final Area a2 = new Area(shape);
////            a1.subtract(a2);
//            final Area a1 = new Area(s1.createStrokedShape(shape));
//            final Area a2 = new Area(shape);
//            a1.intersect(a2);
//
//            // g2d.draw ( shape );
//            // g2d.fill( a1);
////            g2d.draw(a1);
////            g2d.setColor(getFocusColor());
////            g2d.draw(shape);
//            g2d.draw(a1);
//            g2d.setColor(color);
//            g2d.draw(shape);
//
//            GraphicsUtils.restorePaint(g2d, op, hasColor);
//            GraphicsUtils.restoreStroke(g2d, os, stroke != null);
//            GraphicsUtils.restoreComposite(g2d, oc, opacity < 1f);
//        }
//    }
//}