package de.sciss.submin;

import com.alee.painter.decoration.IDecoration;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.swing.AbstractButton;
import java.awt.Color;

/**
 * A variant of ButtonText that uses our own text-content renderer.
 * Note that we do not alter the <code>id</code> of the decoration,
 * therefore <code>SubminButtonText</code> can override <code>ButtonText</code>
 * from an inherited style.
 */
@XStreamAlias ( "SubminButtonText" )
public class SubminButtonText<C extends AbstractButton, D extends IDecoration<C, D>, I extends SubminButtonText<C, D, I>>
        extends SubminTextContent<C, D, I>
{
    private static final Color defaultShadowColor = Color.BLACK;

    @Override
    public float getShadowOpacity(C c, D d) {
        return shadowOpacity != null ? shadowOpacity : 0.7f;
    }

    @Override
    protected Color getShadowColor(C c, D d) {
        return (shadowColor != null) ? shadowColor : defaultShadowColor;
    }

    @Override
    protected boolean isShadow(C c, D d) {
        return (shadow != null) ? shadow : true;
    }

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