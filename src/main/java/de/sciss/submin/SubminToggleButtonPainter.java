/*
 *  SubminToggleButtonPainter.scala
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

import com.alee.laf.button.IToggleButtonPainter;
import com.alee.laf.button.WebToggleButtonUI;
import com.alee.painter.decoration.IDecoration;

import javax.swing.*;

public class SubminToggleButtonPainter<E extends JToggleButton, U extends WebToggleButtonUI, D extends IDecoration<E, D>>
        extends AbstractButtonPainter<E, U, D> implements IToggleButtonPainter<E, U> {
}