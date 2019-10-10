package de.sciss.submin;

import com.alee.painter.decoration.IDecoration;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.swing.JLabel;

/**
 * A variant of <code>LabelText</code> that uses our custom text content renderer.
 */
@XStreamAlias ( "SubminLabelText" )
public class SubminLabelText<C extends JLabel, D extends IDecoration<C, D>, I extends SubminLabelText<C, D, I>> extends SubminTextContent<C, D, I>
{
    @Override
    protected String getText ( final C c, final D d )
    {
        return c.getText ();
    }

    @Override
    protected int getMnemonicIndex ( final C c, final D d )
    {
        return c.getDisplayedMnemonicIndex ();
    }
}