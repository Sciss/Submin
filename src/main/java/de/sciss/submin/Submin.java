package de.sciss.submin;

public class Submin {
    public static void install(boolean isDark) {
        if (isDark) SubminDarkSkin .install();
        else        SubminLightSkin.install();
    }
}
