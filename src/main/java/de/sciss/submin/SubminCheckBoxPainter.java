package de.sciss.submin;

import com.alee.laf.checkbox.ICheckBoxPainter;
import com.alee.laf.checkbox.WebCheckBoxUI;
import com.alee.painter.decoration.DecorationState;
import com.alee.painter.decoration.IDecoration;

import javax.swing.*;
import java.util.List;

/**
 * The Submin version simply inherits from a different `AbstractButtonPainter` trait.
 * With Scala we could have better composition, I guess...
 *
 * @author Alexandr Zernov
 */

public class SubminCheckBoxPainter<E extends JCheckBox, U extends WebCheckBoxUI, D extends IDecoration<E, D>>
        extends AbstractStateButtonPainter<E, U, D> implements ICheckBoxPainter<E, U>
{
    @Override
    protected List<String> getDecorationStates ()
    {
        final List<String> states = super.getDecorationStates ();
        if ( component.isSelected () )
        {
            states.add ( DecorationState.checked );
        }
        return states;
    }
}