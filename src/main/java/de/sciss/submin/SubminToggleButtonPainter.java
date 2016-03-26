package de.sciss.submin;

import com.alee.laf.button.IToggleButtonPainter;
import com.alee.laf.button.WebToggleButtonUI;
import com.alee.painter.decoration.IDecoration;
import de.sciss.submin.AbstractButtonPainter;

import javax.swing.*;

public class SubminToggleButtonPainter<E extends JToggleButton, U extends WebToggleButtonUI, D extends IDecoration<E, D>>
        extends AbstractButtonPainter<E, U, D> implements IToggleButtonPainter<E, U>
{
}