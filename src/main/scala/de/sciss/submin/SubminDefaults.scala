package de.sciss.submin

import java.awt.Color

object SubminDefaults {
   private val hsbArr   = new Array[ Float ]( 3 )

   def backgroundColor : Color = {
      val nimbus = NimbusDefaults.backgroundColor
      subminify( nimbus )
   }

   private def subminify( c: Color ) : Color = {
      val r    = 0xFF - c.getRed
      val g    = 0xFF - c.getGreen
      val b    = 0xFF - c.getBlue
      val a    = c.getAlpha
      Color.RGBtoHSB( r, g, b, hsbArr )
      val hue  = hsbArr( 0 ) + 0.5f
      val sat  = hsbArr( 1 )
      val bri  = hsbArr( 2 )
      val rgb  = Color.HSBtoRGB( hue, sat, bri )
      val rgba = (rgb & 0xFFFFFF) | (a << 24)
      new Color( rgba, true )
   }
}
