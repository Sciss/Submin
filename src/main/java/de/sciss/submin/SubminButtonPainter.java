package de.sciss.submin;

import com.alee.laf.button.IButtonPainter;
import com.alee.laf.button.WebButtonUI;
import com.alee.painter.decoration.IDecoration;

import javax.swing.*;

public class SubminButtonPainter<E extends JButton, U extends WebButtonUI, D extends IDecoration<E, D>>
        extends AbstractButtonPainter<E, U, D> implements IButtonPainter<E, U>
{
}