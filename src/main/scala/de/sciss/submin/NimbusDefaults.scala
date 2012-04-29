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
   private val defaultDisabledTextColor         = new Color( 142, 143, 145, 255 )
   private val defaultControlHighlightColor     = new Color( 233, 236, 242, 255 )
   private val defaultSelectionBackgroundColor  = new Color(  57, 105, 138, 255 )

   private val defaultBackgroundColor           = defaultControlColor

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

   def selectedTextColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "selectedText" )
      if( c == null ) defaultSelectedTextColor else c
   }

   def controlHighlightColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "controlHighlight" )
      if( c == null ) defaultControlHighlightColor else c
   }

   def selectionBackgroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusSelectionBackground" )
      if( c == null ) defaultSelectionBackgroundColor else c
   }

   def disabledTextColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusDisabledText" )
      if( c == null ) defaultDisabledTextColor else c
   }

   def getBlueGreyColor( base: Color ) : Color = {
       val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusBlueGrey" )
       if( c == null ) getDefaultBlueGreyColor( base ) else c
   }

   private def getDefaultBlueGreyColor( base: Color ) : Color =
      ColorUtil.adjustColor( base, 0.032459438f, -0.52518797f, 0.19607842f, 0 )
}