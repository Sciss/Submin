/*
 *  SubminSliderPainter.scala
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

import com.alee.laf.slider.SliderPainter;
import com.alee.laf.slider.WebSliderUI;
import com.alee.utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;

public class SubminSliderPainter<E extends JSlider, U extends WebSliderUI> extends SliderPainter<E, U> {
    /**
     * Style settings.
     */
    protected Color focusColor;
    protected Color tickColor;
    protected Color shadeColor;
    protected Color borderColor;
    protected Color darkBorderColor;
    protected Color disabledBorderColor;

    public Color getFocusColor() {
        return focusColor;
    }

    public void setFocusColor(Color focusColor) {
        this.focusColor = focusColor;
    }

    public Color getTickColor() {
        return tickColor;
    }

    public void setTickColor(Color tickColor) {
        this.tickColor = tickColor;
    }

    public Color getShadeColor() {
        return shadeColor;
    }

    public void setShadeColor(Color shadeColor) {
        this.shadeColor = shadeColor;
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getDarkBorderColor() {
        return darkBorderColor;
    }

    public void setDarkBorderColor(Color darkBorderColor) {
        this.darkBorderColor = darkBorderColor;
    }

    public Color getDisabledBorderColor() {
        return disabledBorderColor;
    }

    public void setDisabledBorderColor(Color disabledBorderColor) {
        this.disabledBorderColor = disabledBorderColor;
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

    @Override
    public void paintTrack(final Graphics2D g2d) {
        final Object aa = GraphicsUtils.setupAntialias(g2d);

        // Track shape
        final Shape ss = getTrackShape();

        // Track background & shade
        {
            // Track shade
            if (component.isEnabled()) {
                GraphicsUtils.drawShade(g2d, ss, component.isFocusOwner() ? focusColor : shadeColor,
                        trackShadeWidth);
            }

            // Track background
            if (component.getOrientation() == JSlider.HORIZONTAL) {
                g2d.setPaint(new GradientPaint(0, trackRect.y, trackBgTop, 0, trackRect.y + trackRect.height, trackBgBottom));
            } else {
                g2d.setPaint(new GradientPaint(trackRect.x, 0, trackBgTop, trackRect.x + trackRect.width, 0, trackBgBottom));
            }
            g2d.fill(ss);
        }

        // Inner progress line
        if (drawProgress) {
            // Progress shape
            final Shape ps = getProgressShape();

            // Progress shade
            if (component.isEnabled()) {
                GraphicsUtils.drawShade(g2d, ps, shadeColor, progressShadeWidth);
            }

            // Progress background
            final Rectangle bounds = ss.getBounds();
            if (component.getOrientation() == JSlider.HORIZONTAL) {
                g2d.setPaint(new GradientPaint(0, bounds.y + progressShadeWidth, progressTrackBgTop, 0,
                        bounds.y + bounds.height - progressShadeWidth, progressTrackBgBottom));
            } else {
                g2d.setPaint(new GradientPaint(bounds.x + progressShadeWidth, 0, progressTrackBgTop,
                        bounds.x + bounds.width - progressShadeWidth, 0, progressTrackBgBottom));
            }
            g2d.fill(ps);

            // Progress border
            g2d.setPaint(component.isEnabled() ? progressBorderColor : disabledBorderColor);
            g2d.draw(ps);
        }

        // Track border & focus
        {
            // Track border
            g2d.setPaint(
                    component.isEnabled() ? rolloverDarkBorderOnly && !dragging ? getBorderColor() : darkBorderColor :
                            disabledBorderColor);
            g2d.draw(ss);
        }

        GraphicsUtils.restoreAntialias(g2d, aa);
    }

    @Override
    public void paintThumb(final Graphics2D g2d) {
        if (drawThumb) {
            final Object aa = GraphicsUtils.setupAntialias(g2d);

            // Thumb shape
            final Shape ts = getThumbShape();

            // Thumb background
            if (component.getOrientation() == JSlider.HORIZONTAL) {
                g2d.setPaint(new GradientPaint(0, thumbRect.y, thumbBgTop, 0, thumbRect.y + thumbRect.height, thumbBgBottom));
            } else {
                g2d.setPaint(new GradientPaint(thumbRect.x, 0, thumbBgTop, thumbRect.x + thumbRect.width, 0, thumbBgBottom));
            }
            g2d.fill(ts);

            // Thumb border
            g2d.setPaint(component.isEnabled() ? darkBorderColor : disabledBorderColor);
            g2d.draw(ts);

            GraphicsUtils.restoreAntialias(g2d, aa);
        }
    }
}
