/*
 *  SubminFocusBorder.scala
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

import com.alee.painter.decoration.IDecoration;
import com.alee.painter.decoration.border.AbstractBorder;
import com.alee.painter.decoration.border.BorderWidth;
import com.alee.utils.GraphicsUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Area;

@XStreamAlias("SubminFocusBorder")
public class SubminFocusBorder<C extends JComponent, D extends IDecoration<C, D>, I extends SubminFocusBorder<C, D, I>>
        extends AbstractBorder<C, D, I> {
    /**
     * Border stroke.
     */
    @XStreamAsAttribute
    protected Stroke stroke;

    /**
     * Border color.
     */
    @XStreamAsAttribute
    protected Color color;

    /**
     * Shade color.
     */
    @XStreamAsAttribute
    protected Color focusColor;

    /**
     * Default border color.
     */
    private static final Color defaultFocusColor = new Color(48,77,130);

    // @Override
    public Color getFocusColor() {
        return focusColor != null ? focusColor : defaultFocusColor;
    }

    /**
     * Returns {@link Stroke} used for this border.
     *
     * @return {@link Stroke} used for this border
     */
    public Stroke getStroke ()
    {
        return stroke;
    }

    /**
     * Returns {@link Color} used for this border.
     *
     * @return {@link Color} used for this border
     */
    public Color getColor ()
    {
        return color != null ? color : new Color ( 210, 210, 210 );
    }

    @Override
    public BorderWidth getWidth ()
    {
        final float opacity = getOpacity ();
        final Stroke stroke = getStroke ();
        final float lineWidth = opacity > 0 ? stroke instanceof BasicStroke ? ( ( BasicStroke ) stroke ).getLineWidth () : 1 : 0;
        final int width = Math.round ( lineWidth );
        return new BorderWidth ( width, width, width, width );
    }

    @Override
    public void paint(final Graphics2D g2d, final Rectangle bounds, final C c, final D d, final Shape shape) {
        final float opacity = getOpacity();
        if ( opacity > 0 && !getWidth ().isEmpty () ) {
            final Stroke stroke = getStroke();
            final Color     color       = getColor();
            final Color     focusColor  = getFocusColor();
            final boolean   hasColor    = focusColor != null || color != null;

            final Composite oc = GraphicsUtils.setupAlphaComposite  (g2d, opacity, opacity < 1f);
            final Stroke    os = GraphicsUtils.setupStroke          (g2d, stroke, stroke != null);
            final Paint     op = GraphicsUtils.setupPaint           (g2d, focusColor, hasColor);

            final Stroke    s1 = new BasicStroke(2f);
            final Area      a1 = new Area(s1.createStrokedShape(shape));
            final Area      a2 = new Area(shape);
            a1.intersect(a2);

            g2d.draw(a1);
            g2d.setColor(color);
            g2d.draw(shape);

            GraphicsUtils.restorePaint      (g2d, op, hasColor);
            GraphicsUtils.restoreStroke     (g2d, os, stroke != null);
            GraphicsUtils.restoreComposite  (g2d, oc, opacity < 1f);
        }
    }
}