/*
 *  SubminSliderPainter.scala
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

import com.alee.laf.slider.SliderPainter;
import com.alee.laf.slider.WebSliderUI;

import javax.swing.*;
import java.awt.*;

public class SubminSliderPainter<E extends JSlider, U extends WebSliderUI> extends SliderPainter<E, U> {
    /**
     * Style settings.
     */
    protected Color tickColor;

    public Color getTickColor() {
        return tickColor;
    }

    public void setTickColor(Color tickColor) {
        this.tickColor = tickColor;
    }

    @Override
    public void paintTicks(Graphics g) {
        final Rectangle tickBounds = tickRect;

        g.setColor(tickColor);

        if (component.getOrientation() == JSlider.HORIZONTAL) {
            g.translate(0, tickBounds.y);

            int value = component.getMinimum();
            int xPos;

            if (component.getMinorTickSpacing() > 0) {
                while (value <= component.getMaximum()) {
                    xPos = xPositionForValue(value);
                    paintMinorTickForHorizontalSlider(g, tickBounds, xPos);
                    value += component.getMinorTickSpacing();
                }
            }

            if (component.getMajorTickSpacing() > 0) {
                value = component.getMinimum();

                while (value <= component.getMaximum()) {
                    xPos = xPositionForValue(value);
                    paintMajorTickForHorizontalSlider(g, tickBounds, xPos);
                    value += component.getMajorTickSpacing();
                }
            }

            g.translate(0, -tickBounds.y);
        } else {
            g.translate(tickBounds.x, 0);

            int value = component.getMinimum();
            int yPos;

            if (component.getMinorTickSpacing() > 0) {
                int offset = 0;
                if (!ltr) {
                    offset = tickBounds.width - tickBounds.width / 2;
                    g.translate(offset, 0);
                }

                while (value <= component.getMaximum()) {
                    yPos = yPositionForValue(value);
                    paintMinorTickForVerticalSlider(g, tickBounds, yPos);
                    value += component.getMinorTickSpacing();
                }

                if (!ltr) {
                    g.translate(-offset, 0);
                }
            }

            if (component.getMajorTickSpacing() > 0) {
                value = component.getMinimum();
                if (!ltr) {
                    g.translate(2, 0);
                }

                while (value <= component.getMaximum()) {
                    yPos = yPositionForValue(value);
                    paintMajorTickForVerticalSlider(g, tickBounds, yPos);
                    value += component.getMajorTickSpacing();
                }

                if (!ltr) {
                    g.translate(-2, 0);
                }
            }
            g.translate(-tickBounds.x, 0);
        }
    }
}
