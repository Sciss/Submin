///*
// *  AbstractButtonPainter.scala
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
//import com.alee.laf.button.IAbstractButtonPainter;
//import com.alee.painter.decoration.IDecoration;
//import com.alee.utils.GraphicsUtils;
//import com.alee.utils.SwingUtils;
//
//import javax.swing.*;
//import javax.swing.plaf.basic.BasicButtonUI;
//import javax.swing.plaf.basic.BasicHTML;
//import javax.swing.text.View;
//import java.awt.*;
//import java.util.Map;
//
//public abstract class AbstractButtonPainter<E extends AbstractButton, U extends BasicButtonUI, D extends IDecoration<E, D>>
//        extends com.alee.laf.button.AbstractButtonPainter<E, U, D>
//        implements IAbstractButtonPainter<E, U> {
//    /**
//     * Style settings.
//     */
//    protected boolean drawShade;
//    protected Color shadeColor;
//    protected int shadeSize;
//    protected boolean shadeShadow;
//    protected int shadeShiftY;
//    protected Color disabledForeground;
//    protected int pressedShiftY;
//
//    /**
//     * Returns whether text shade is displayed or not.
//     *
//     * @return true if text shade is displayed, false otherwise
//     */
//    public boolean isDrawShade() {
//        return drawShade;
//    }
//
//    /**
//     * Sets whether text shade is displayed or not.
//     *
//     * @param drawShade whether text shade is displayed or not
//     */
//    public void setDrawShade(final boolean drawShade) {
//        this.drawShade = drawShade;
//    }
//
//    /**
//     * Returns text shade color.
//     *
//     * @return text shade color
//     */
//    public Color getShadeColor() {
//        return shadeColor;
//    }
//
//    /**
//     * Sets text shade color.
//     *
//     * @param shadeColor text shade color
//     */
//    public void setShadeColor(final Color shadeColor) {
//        this.shadeColor = shadeColor;
//    }
//
//    /**
//     * Returns whether text shade is displayed as a shadow (painted softer).
//     *
//     * @return true if text shade is painted softer
//     */
//    public boolean isShadeShadow() {
//        return shadeShadow;
//    }
//
//    /**
//     * Sets whether text shade is displayed as a shadow (painted softer).
//     *
//     * @param value whether text shade is painted softer
//     */
//    public void setShadeShadow(final boolean value) {
//        shadeShadow = value;
//    }
//
//    /**
//     * Returns text shade vertical shift.
//     *
//     * @return vertical shift in pixels
//     */
//    public int getShadeShiftY() {
//        return shadeShiftY;
//    }
//
//    /**
//     * Sets text shade vertical shift.
//     *
//     * @param value text shade vertical shift in pixels
//     */
//    public void setShadeShiftY(final int value) {
//        shadeShiftY = value;
//    }
//
//    /**
//     * Returns text disabled color.
//     *
//     * @return text shade color
//     */
//    public Color getDisabledForeground() {
//        return disabledForeground;
//    }
//
//    /**
//     * Sets text disabled color.
//     *
//     * @param disabledForeground text shade color
//     */
//    public void setDisabledForeground(final Color disabledForeground) {
//        this.disabledForeground = disabledForeground;
//    }
//
//    /**
//     * Returns text pressed vertical shift.
//     *
//     * @return vertical shift in pixels
//     */
//    public int getPressedShiftY() {
//        return pressedShiftY;
//    }
//
//    /**
//     * Sets text pressed vertical shift.
//     *
//     * @param value text pressed vertical shift in pixels
//     */
//    public void setPressedShiftY(final int value) {
//        pressedShiftY = value;
//    }
//
//    @Override
//    protected void paintText(final Graphics2D g2d) {
//        final String text = component.getText();
//        if (text != null && !text.equals("")) {
//            final Map map = SwingUtils.setupTextAntialias(g2d);
//            final View v = (View) component.getClientProperty(BasicHTML.propertyKey);
//            if (v != null) {
//                v.paint(g2d, textRect);
//            } else {
//                // Drawing text
//                paintText(g2d, component, text);
//            }
//            SwingUtils.restoreTextAntialias(g2d, map);
//        }
//    }
//
//    /**
//     * Paints plain text view.
//     *
//     * @param g2d   graphics context
//     * @param label painted component
//     * @param text  label text
//     */
//    protected void paintText(final Graphics2D g2d, final E label, final String text) {
//        final FontMetrics fm = SwingUtils.getFontMetrics(label, g2d);
//        final ButtonModel model = label.getModel();
//        final boolean enabled = label.isEnabled();
//        final boolean sel = enabled && (model.isPressed() || model.isSelected());
//        final int textX = textRect.x;
//        final int textY = textRect.y + fm.getAscent() + (sel ? pressedShiftY : 0);
//        g2d.setColor(enabled ? (sel ? selectedForeground : label.getForeground()) : disabledForeground);
//        final int mnemonicIndex = enabled ? label.getDisplayedMnemonicIndex() : -1;
//        if (enabled && drawShade) {
//            paintShadowText(g2d, text, textX, textY);
//
//            if (mnemonicIndex >= 0 && mnemonicIndex < text.length()) {
//                g2d.fillRect(textX + fm.stringWidth(text.substring(0, mnemonicIndex)),
//                        textY + fm.getDescent() - 1,
//                        fm.charWidth(text.charAt(mnemonicIndex)), 1);
//            }
//        } else {
//            SwingUtils.drawStringUnderlineCharAt(g2d, text, mnemonicIndex, textX, textY);
//        }
//    }
//
//    /**
//     * Paints custom text shade.
//     *
//     * @param g2d   graphics context
//     * @param text  text
//     * @param textX text X coordinate
//     * @param textY text Y coordinate
//     */
//    protected void paintShadowText(final Graphics2D g2d, final String text, final int textX, final int textY) {
//        g2d.translate(textX, textY);
//        GraphicsUtils.paintTextEffect(g2d, text, shadeColor, shadeSize, -shadeSize, shadeShiftY - shadeSize, shadeShadow);
//        g2d.translate(-textX, -textY);
//    }
//}