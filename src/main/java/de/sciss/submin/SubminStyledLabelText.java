package de.sciss.submin;

import com.alee.api.clone.behavior.OmitOnClone;
import com.alee.api.merge.behavior.OmitOnMerge;
import com.alee.extended.label.StyleRange;
import com.alee.extended.label.StyledLabelText;
import com.alee.extended.label.TextWrap;
import com.alee.extended.label.WebStyledLabel;
import com.alee.painter.decoration.IDecoration;
import com.alee.painter.decoration.content.ContentPropertyListener;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/** A variant of StyledLabelText that mixes in <code>SubminStyledTextContent</code> */
@XStreamAlias ( "SubminStyledLabelText" )
public class SubminStyledLabelText<C extends WebStyledLabel, D extends IDecoration<C, D>, I extends StyledLabelText<C, D, I>>
        extends SubminStyledTextContent<C, D, I>
{
    /**
     * Component property change listener.
     */
    @OmitOnClone
    @OmitOnMerge
    protected transient ContentPropertyListener<C, D> listener;

    @Override
    public void activate ( final C c, final D d )
    {
        // Performing default actions
        super.activate ( c, d );

        // Adding style ranges change listener
        listener = new ContentPropertyListener<C, D> ( c, d )
        {
            @Override
            public void propertyChange ( final C c, final D d, final String property, final Object oldValue, final Object newValue )
            {
                buildTextRanges ( c, d );
                c.repaint ();
            }
        };
        c.addPropertyChangeListener ( WebStyledLabel.STYLE_RANGES_PROPERTY, listener );
    }

    @Override
    public void deactivate ( final C c, final D d )
    {
        // Removing style ranges change listener
        c.removePropertyChangeListener ( WebStyledLabel.STYLE_RANGES_PROPERTY, listener );
        listener = null;

        // Performing default actions
        super.deactivate ( c, d );
    }

    @Override
    protected List<StyleRange> getStyleRanges (final C c, final D d )
    {
        return c.getStyleRanges ();
    }

    @Override
    protected TextWrap getWrapType (final C c, final D d )
    {
        return c.getWrap ();
    }

    @Override
    protected int getMaximumRows ( final C c, final D d )
    {
        return c.getMaximumRows ();
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

    @Override
    protected int getHorizontalAlignment ( final C c, final D d )
    {
        return halign != null ? halign : c.getHorizontalTextAlignment ();
    }

    @Override
    protected int getVerticalAlignment ( final C c, final D d )
    {
        return valign != null ? valign : c.getVerticalTextAlignment ();
    }
}