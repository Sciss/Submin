package de.sciss.submin

import javax.swing.{AbstractButton, Icon}
import java.awt.{Graphics2D, Graphics, Component}

object SubminCheckBoxIcon extends Icon {
   def getIconWidth  = 18
   def getIconHeight = 18

   def paintIcon( c: Component, g: Graphics, x: Int, y: Int ) {
      val b       = c.asInstanceOf[ AbstractButton ]
      val state   = SubminButtonUILike.getComponentState( b )
      // XXX TODO
      val butPtr  = if( SubminUtil.getClosestBoolean( b, "submin" )) NimbusCheckBoxPainter else NimbusCheckBoxPainter
      val g2      = g.asInstanceOf[ Graphics2D ]
      butPtr.paint( state, null, g2, x, y, getIconWidth, getIconHeight )
   }
}
