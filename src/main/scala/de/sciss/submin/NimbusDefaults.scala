package de.sciss.submin

import javax.swing.{UIDefaults, UIManager}
import java.awt.Color

object NimbusDefaults {
   val STATE_ENABLED   = 0x01
   val STATE_OVER      = 0x02
   val STATE_FOCUSED   = 0x04
   val STATE_PRESSED   = 0x08

   private val defaultControlColor              = new Color( 214, 217, 223, 255 )
   private val defaultFocusColor                = new Color( 115, 164, 209, 255 )
   private val defaultBaseColor                 = new Color(  51,  98, 140, 255 )
   private val defaultTextColor                 = Color.black
   private val defaultSelectedTextColor         = Color.white
   private val defaultControlHighlightColor     = new Color( 233, 236, 242, 255 )
   private val defaultSelectionBackgroundColor  = new Color(  57, 105, 138, 255 )

   private val defaultBackgroundColor           = defaultControlColor

   private val hsbArr                           = new Array[ Float ]( 3 )

   private val nimbusDefaults : UIDefaults = {
      val current = UIManager.getLookAndFeel
      if( current.getName.toLowerCase == "nimbus" ) current.getDefaults else null
   }

   def controlColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "control" )
      if( c == null ) defaultControlColor else c
   }

   def backgroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "background" )
      if( c == null ) defaultBackgroundColor else c
   }

   def focusColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusFocus" )
      if( c == null ) defaultFocusColor else c
   }

   def baseColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusBase" )
      if( c == null ) defaultBaseColor else c
   }

   def textColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "text" )
      if( c == null ) defaultTextColor else c
   }

   def sSelectedTextColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "selectedText" )
      if( c == null ) defaultSelectedTextColor else c
   }

   def controlHighlighColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "controlHighlight" )
      if( c == null ) defaultControlHighlightColor else c
   }

   def selectionBackgroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusSelectionBackground" )
      if( c == null ) defaultSelectionBackgroundColor else c
   }

   def getBlueGreyColor( base: Color ) : Color = {
       val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusBlueGrey" )
       if( c == null ) getDefaultBlueGreyColor( base ) else c
   }

   private def getDefaultBlueGreyColor( base: Color ) : Color =
      adjustColor( base, 0.032459438f, -0.52518797f, 0.19607842f, 0 )

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