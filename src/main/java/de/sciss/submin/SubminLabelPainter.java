package de.sciss.submin;

import com.alee.laf.label.ILabelPainter;
import com.alee.laf.label.WebLabelUI;
import com.alee.painter.decoration.IDecoration;

import javax.swing.*;

public class SubminLabelPainter<E extends JLabel, U extends WebLabelUI, D extends IDecoration<E, D>>
        extends AbstractLabelPainter<E, U, D>
        implements ILabelPainter<E, U>
{
}
