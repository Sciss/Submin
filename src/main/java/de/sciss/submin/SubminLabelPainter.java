/*
 *  SubminLabelPainter.scala
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

import com.alee.laf.label.ILabelPainter;
import com.alee.laf.label.WebLabelUI;
import com.alee.painter.decoration.IDecoration;

import javax.swing.*;

public class SubminLabelPainter<E extends JLabel, U extends WebLabelUI, D extends IDecoration<E, D>>
        extends AbstractLabelPainter<E, U, D>
        implements ILabelPainter<E, U> {
}
