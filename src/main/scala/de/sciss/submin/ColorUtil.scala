package de.sciss.submin

import java.awt.Color

object ColorUtil {
   private val hsbArr = new Array[ Float ]( 3 )

   def adjustColor( c: Color, hueOffset: Float, satOffset: Float, briOffset: Float, alphaOffset: Int ) : Color = {
      val sameColor = hueOffset == 0f && satOffset == 0f && briOffset == 0f
      val sameAlpha = alphaOffset == 0
      if( sameColor ) {
         if( sameAlpha ) return c
         // don't know what's going on here. nimbus defaults ColorUIResources have alpha values of zero sometimes
         val cAlpha = c.getAlpha
         return new Color( c.getRed, c.getGreen, c.getBlue, math.max( 0, math.min( 0xFF, cAlpha + alphaOffset )))
      }

      Color.RGBtoHSB( c.getRed, c.getGreen, c.getBlue, hsbArr )
      val hue     = hsbArr( 0 ) + hueOffset
      val sat     = math.max( 0f, math.min( 1f, hsbArr( 1 ) + satOffset ))
      val bri     = math.max( 0f, math.min( 1f, hsbArr( 2 ) + briOffset ))
      val rgb     = Color.HSBtoRGB( hue, sat, bri )
      val cAlpha  = c.getAlpha
      val a       = if( sameAlpha ) cAlpha else math.max( 0, math.min( 0xFF, cAlpha + alphaOffset ))
      val rgba    = (rgb & 0xFFFFFF) | (a << 24)
      new Color( rgba, true )
   }

   def mixColorWithAlpha( base: Color, mix: Color ) : Color = {
      if( mix == null ) return base
      val a0 = mix.getAlpha
      if( a0 == 0 ) { return base } else if( a0 == 0xFF ) return mix

      val wm   = a0.toFloat / 0xFF
      val wb   = 1f - wm
      val r    = (base.getRed   * wb + mix.getRed   * wm + 0.5f).toInt
      val g    = (base.getGreen * wb + mix.getGreen * wm + 0.5f).toInt
      val b    = (base.getBlue  * wb + mix.getBlue  * wm + 0.5f).toInt
      new Color( r, g, b )
   }
}
