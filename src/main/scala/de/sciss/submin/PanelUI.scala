package de.sciss.submin

import javax.swing.{JPanel, LookAndFeel}
import javax.swing.plaf.basic.BasicPanelUI
import SubminHelper._

class PanelUI extends BasicPanelUI {
   override protected def installDefaults( p: JPanel ) {
      val submin = getBoolean( p, "submin" )
      val propBg = if( submin ) "Panel[submin].background" else "Panel.background"
      val propFg = if( submin ) "Panel[submin].foreground" else "Panel.foreground"
      LookAndFeel.installColorsAndFont( p, propBg, propFg, "Panel.font" )
      LookAndFeel.installBorder( p, "Panel.border" )
      LookAndFeel.installProperty( p, "opaque", true )
   }
}
