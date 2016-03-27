/*
 *  AbstractLabelPainter.scala
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

import com.alee.painter.decoration.IDecoration;
import com.alee.utils.GraphicsUtils;
import com.alee.utils.SwingUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

/**
 * Extends <tt>AbstractLabelPainter</tt> with support for vertical shade shift
 * (defaults to <tt>0</tt>) and shadow-mode (defaults to <tt>false</tt>).
 */
public abstract class AbstractLabelPainter<E extends JLabel, U extends BasicLabelUI, D extends IDecoration<E, D>>
        extends com.alee.laf.label.AbstractLabelPainter<E, U, D> {
    /**
     * Style settings.
     */
    protected int shadeShiftY;
    protected boolean shadeShadow;
    protected Color disabledForeground;

    /**
     * Returns whether text shade is displayed as a shadow (painted softer).
     *
     * @return true if text shade is painted softer
     */
    public boolean isShadeShadow() {
        return shadeShadow;
    }

    /**
     * Sets whether text shade is displayed as a shadow (painted softer).
     *
     * @param value whether text shade is painted softer
     */
    public void setShadeShadow(final boolean value) {
        shadeShadow = value;
    }

    /**
     * Returns text shade vertical shift.
     *
     * @return vertical shift in pixels
     */
    public int getShadeShiftY() {
        return shadeShiftY;
    }

    /**
     * Sets text shade vertical shift.
     *
     * @param value text shade vertical shift in pixels
     */
    public void setShadeShiftY(final int value) {
        shadeShiftY = value;
    }

    /**
     * Returns text disabled color.
     *
     * @return text shade color
     */
    public Color getDisabledForeground() {
        return disabledForeground;
    }

    /**
     * Sets text disabled color.
     *
     * @param disabledForeground text shade color
     */
    public void setDisabledForeground(final Color disabledForeground) {
        this.disabledForeground = disabledForeground;
    }

    @Override
    protected void paintShadowText(Graphics2D g2d, String text, int textX, int textY) {
        g2d.translate(textX, textY);
        GraphicsUtils.paintTextEffect(g2d, text, shadeColor, shadeSize, -shadeSize, shadeShiftY - shadeSize, shadeShadow);
        g2d.translate(-textX, -textY);
    }

    @Override
    protected void paintDisabledText(final E label, final Graphics2D g2d, final String text, final int textX, final int textY) {
        final int accChar = label.getDisplayedMnemonicIndex();
        g2d.setPaint(disabledForeground);
        SwingUtils.drawStringUnderlineCharAt(g2d, text, accChar, textX, textY);
    }
}
