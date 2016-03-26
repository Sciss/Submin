package de.sciss.submin;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.CustomSkin;
import com.alee.utils.XmlUtils;

import javax.swing.*;

public class SubminDarkSkin extends CustomSkin {
    public SubminDarkSkin() {
        super("dark/skin.xml");
    }

    /*
     * Manager initialization mark.
     */
    private static boolean initialized = false;

    /**
     * Initializes StyleManager settings.
     */
    public static synchronized void initialize() {
        if (!initialized) {
            initialized = true;

            // Class aliases
            XmlUtils.processAnnotations(SubminFocusBorder.class);
        }
    }

    /**
     * Initializes the skin and sets the L&amp;F to <tt>WebLookAndFeel</tt> using this skin.
     * Sets UI default <tt>"dark-skin"</tt> to <tt>true"</tt>.
     */
    public static void install() {
        initialize();
        WebLookAndFeel.install(SubminDarkSkin.class);
        UIManager.put("dark-skin", true);
    }
}