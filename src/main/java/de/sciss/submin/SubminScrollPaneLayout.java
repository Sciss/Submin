package de.sciss.submin;

import com.alee.api.merge.Mergeable;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.swing.ScrollPaneLayout;
import java.awt.Component;

// XXX TODO temporary work-around for NPE issue with ScalaCollider-Swing (DockingFrames)
@XStreamAlias( "SubminScrollPaneLayout" )
public class SubminScrollPaneLayout extends ScrollPaneLayout implements Mergeable, Cloneable {
    public SubminScrollPaneLayout ()
    {
        super ();
    }

    @Override
    protected Component addSingletonComponent(Component oldC, Component newC)
    {
        if ((oldC != null) && (oldC.getParent() != null) && (oldC != newC)) {
            oldC.getParent().remove(oldC);
        }
        return newC;
    }

    @XStreamAlias ( "SubminScrollPaneLayout$UIResource" )
    public static final class UIResource extends SubminScrollPaneLayout implements javax.swing.plaf.UIResource
    {
    }
}
