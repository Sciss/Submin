/*
 *  SubminMenuPainter.scala
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

import com.alee.laf.menu.MenuPainter;
import com.alee.laf.menu.WebMenuUI;
import com.alee.utils.GraphicsUtils;
import com.alee.utils.LafUtils;

import javax.swing.*;
import java.awt.*;

public class SubminMenuPainter<E extends JMenu, U extends WebMenuUI>
        extends MenuPainter<E, U> {
    /**
     * Style settings.
     */
    protected Color shadeColor;
    protected Float hoverOpacity;
    protected Color borderColor;

    /**
     * Returns text shade color.
     *
     * @return text shade color
     */
    public Color getShadeColor() {
        return shadeColor;
    }

    /**
     * Sets text shade color.
     *
     * @param shadeColor text shade color
     */
    public void setShadeColor(final Color shadeColor) {
        this.shadeColor = shadeColor;
    }

    /**
     * Returns text border color.
     *
     * @return text border color
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets text border color.
     *
     * @param borderColor text border color
     */
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
    }

    public float getHoverOpacity() {
        return hoverOpacity != null ? hoverOpacity : 1f;
    }

    public void setHoverOpacity(float opacity) {
        this.hoverOpacity = opacity;
    }

    @Override
    protected void paintBackground(Graphics2D g2d, boolean selected) {
        if (component.getParent() instanceof JPopupMenu) {
//            super.paintBackground ( g2d, selected );
            if (selected) {
                g2d.setPaint(new GradientPaint(0, 0, selectedTopBg, 0, component.getHeight(), selectedBottomBg));
                g2d.fillRect(0, 0, component.getWidth(), component.getHeight());
            }
        } else {
            if (component.isEnabled() && (selected || mouseover)) {
//                LafUtils.drawWebStyle ( g2d, component, shadeColor, shadeWidth, round, component.isEnabled (),
//                        !selected && mouseover, selected ? StyleConstants.averageBorderColor : StyleConstants.borderColor );
                // final Color borderColor = selected ? StyleConstants.averageBorderColor : StyleConstants.borderColor;
//                final float opacity = mouseover ? getHoverOpacity () : 1f;
                final float opacity = selected ? 1f : getHoverOpacity();
                final Paint bgPaint = new GradientPaint(0, 0, selectedTopBg, 0, component.getHeight(), selectedBottomBg);
                drawShape(g2d, component, shadeColor, shadeWidth, round, component.isEnabled(),
                        borderColor, borderColor, bgPaint, opacity);
            }
        }
    }

    private Shape drawShape(final Graphics2D g2d, final JComponent component, final Color shadeColor, final int shadeWidth,
                            final int round, final boolean fillBackground, final Color border,
                            final Color disabledBorder, final Paint background, final float opacity) {
        // todo Use simple drawRoundRect e.t.c. methods
        // todo Add new class "ShapeInfo" that will contain a shape data and pass it instead of shapes

        // Ignore incorrect or zero hoverOpacity
        if (opacity <= 0f || opacity > 1f) {
            return null;
        }

        // State settings
        final Object aa = GraphicsUtils.setupAntialias(g2d);
        final Composite oc = GraphicsUtils.setupAlphaComposite(g2d, opacity, opacity < 1f);

        // Shapes
        final Shape borderShape = LafUtils.getWebBorderShape(component, shadeWidth, round);

        // Outer shadow
        if (component.isEnabled() && shadeColor != null) {
            GraphicsUtils.drawShade(g2d, borderShape, shadeColor, shadeWidth);
        }

        // Background
        if (fillBackground) {
            // Setup either cached gradient paint or single color paint
            // g2d.setPaint ( webColored ? LafUtils.getWebGradientPaint ( 0, shadeWidth, 0, component.getHeight () - shadeWidth ) : background );
            g2d.setPaint(background);

            // Fill background shape
            if (round > 0) {
                g2d.fillRoundRect(shadeWidth, shadeWidth, component.getWidth() - shadeWidth * 2, component.getHeight() - shadeWidth * 2,
                        round * 2 + 2, round * 2 + 2);
            } else {
                g2d.fillRect(shadeWidth, shadeWidth, component.getWidth() - shadeWidth * 2, component.getHeight() - shadeWidth * 2);
            }
        }

        // Border
        if (border != null) {
            g2d.setPaint(component.isEnabled() ? border : disabledBorder);
            g2d.draw(borderShape);
        }

        // Restoring old values
        GraphicsUtils.restoreComposite(g2d, oc, opacity < 1f);
        GraphicsUtils.restoreAntialias(g2d, aa);

        return borderShape;
    }
}