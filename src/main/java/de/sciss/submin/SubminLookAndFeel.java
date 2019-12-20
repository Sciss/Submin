/*
 *  SubminLookAndFeel.scala
 *  (Submin)
 *
 *  Copyright (c) 2012-2018 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v3+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.submin;

import com.alee.api.annotations.NotNull;
import com.alee.laf.LookAndFeelException;
import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.Skin;
import com.alee.managers.style.StyleManager;
import com.alee.utils.LafUtils;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 * Extends <code>WebLookAndFeel</code> with a few
 * customized UI components, such as the file-chooser.
 * Otherwise, changes id and name.
 */
public class SubminLookAndFeel extends WebLookAndFeel {
    public static String fileChooserUI  = SubminFileChooserUI.class.getCanonicalName ();

    @Override
    public String getName() {
        return "Submin";
    }

    @Override
    public String getID() {
        return "submin";
    }

    @Override
    protected void initClassDefaults(final UIDefaults table) {
        super.initClassDefaults(table);
        table.put("FileChooserUI", SubminLookAndFeel.fileChooserUI);
    }

    @Override
    public String getDescription() {
        return "WebLaF based cross-platform look and feel with light and dark skin";
    }

    public static void install (@NotNull final Class<? extends Skin> skin, @NotNull final Object... arguments ) throws LookAndFeelException
    {
        // Event Dispatch Thread check
        checkEventDispatchThread ();

        // Saving previous installed LaF class
        previousLookAndFeelClass = UIManager.getLookAndFeel ().getClass ();

        // Preparing initial skin
        StyleManager.setDefaultSkin ( skin );

        // Installing LookAndFeel
        LafUtils.setupLookAndFeel ( SubminLookAndFeel.class );
    }
}