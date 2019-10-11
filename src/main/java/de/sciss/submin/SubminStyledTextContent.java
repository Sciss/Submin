package de.sciss.submin;

import com.alee.extended.label.AbstractStyledTextContent;
import com.alee.painter.decoration.IDecoration;
import com.alee.utils.ColorUtils;

import javax.swing.JComponent;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/** A slight variant of `AbstractStyledTextContent` that doesn't shift the shadow position */
public abstract class SubminStyledTextContent<C extends JComponent, D extends IDecoration<C, D>, I extends AbstractStyledTextContent<C, D, I>>
        extends AbstractStyledTextContent<C, D, I> {

    // XXX TODO: complete DRY with SubminTextContent
    @Override
    protected void paintTextShadow (final C c, final D d, final Graphics2D g2d, final String text, final int textX, final int textY )
    {
        if ( isShadow ( c, d ) )
        {
            // This is required to properly render sub-pixel text antialias
            final RenderingHints rh = g2d.getRenderingHints ();

            // Shadow settings
            final float opacity = getShadowOpacity ( c, d );
            final int size = getShadowSize ( c, d );
            final Color color = getShadowColor ( c, d );
            // --- begin modifications HHR ---
            final double sizeH  = size * 0.5;
            final double tx     = -sizeH - 1;
            final double ty     = -sizeH - 1;
            // --- end modifications HHR ---
            final boolean isShadow = true;

            // Configuring graphics
            final Composite oldComposite = g2d.getComposite ();
            final Color oldColor = g2d.getColor ();
            g2d.translate ( textX + tx, textY + ty );

            // Use a alpha blend smaller than 1 to prevent the effect from becoming too dark when multiple paints occur on top of each other
            float preAlpha = 0.4f;
            if ( oldComposite instanceof AlphaComposite)
            {
                final AlphaComposite alphaComposite = ( AlphaComposite ) oldComposite;
                if ( alphaComposite.getRule () == AlphaComposite.SRC_OVER )
                {
                    // Make sure alpha blend is adjusted by composite passed from above
                    preAlpha = alphaComposite.getAlpha () * preAlpha;
                }
            }
            g2d.setPaint ( ColorUtils.opaque ( color ) );

            // If the effect is a shadow it looks better to stop painting a bit earlier - shadow will look softer
            final int maxSize = isShadow ? size - 1 : size;
            for ( int i = -size; i <= maxSize; i++ )
            {
                for ( int j = -size; j <= maxSize; j++ )
                {
                    final double distance = i * i + j * j;
                    float alpha;
                    if ( distance > 0.0d )
                    {
                        alpha = ( float ) ( 1.0f / ( distance * size * opacity ) );
                    }
                    else
                    {
                        alpha = opacity;
                    }
                    alpha *= preAlpha;
                    if ( alpha > 1.0f )
                    {
                        alpha = 1.0f;
                    }
                    g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, alpha ) );
                    g2d.drawString ( text, i + size, j + size );
                }
            }

            // Restore graphics
            g2d.translate ( -textX - tx, -textY - ty );
            g2d.setComposite ( oldComposite );
            g2d.setPaint ( oldColor );

            // This is required to properly render sub-pixel text antialias
            g2d.setRenderingHints ( rh );
        }
    }
}
