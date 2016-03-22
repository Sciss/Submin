package de.sciss.submin;

import com.alee.laf.radiobutton.IRadioButtonPainter;
import com.alee.laf.radiobutton.WebRadioButtonUI;
import com.alee.painter.decoration.IDecoration;

import javax.swing.*;

/**
 * The Submin version simply inherits from a different `AbstractButtonPainter` trait.
 * With Scala we could have better composition, I guess...
 *
 * @author Alexandr Zernov
 */

public class SubminRadioButtonPainter<E extends JRadioButton, U extends WebRadioButtonUI, D extends IDecoration<E, D>>
        extends AbstractStateButtonPainter<E, U, D> implements IRadioButtonPainter<E, U>
{
}