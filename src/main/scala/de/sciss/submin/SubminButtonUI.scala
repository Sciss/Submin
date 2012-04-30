/*
 *  SubminButtonUI.scala
 *  (Submin)
 *
 *  Copyright (c) 2012 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.submin

import javax.swing.text.View
import javax.swing.plaf.basic.{BasicHTML, BasicButtonUI}
import sun.swing.SwingUtilities2
import java.awt.{RenderingHints, Dimension, Graphics2D, FontMetrics, Rectangle, Graphics}
import javax.swing.{LookAndFeel, UIManager, JButton, SwingUtilities, JComponent, AbstractButton}
import javax.swing.plaf.{ComponentUI, UIResource}

/**
 * todo:
 *  - doesn't check the `DEFAULT` state flag
 *  - should recognise custom button color (currently `null` is passed into `NimbusButtonPainter`)
 */
object SubminButtonUI {
//   private val viewRect = new Rectangle()
//   private val textRect = new Rectangle()
//   private val iconRect = new Rectangle()

   private val instance = new SubminButtonUI
   def createUI( c: JComponent ) : ComponentUI = instance
}
final class SubminButtonUI extends BasicButtonUI with SubminButtonUILike {
   ui =>

   import SubminButtonUILike._

   protected def propertyPrefix = getPropertyPrefix

   override protected def paintText( g: Graphics, b: AbstractButton, textRect: Rectangle, text: String ) {
      val model  = b.getModel
      val fm     = SwingUtilities2.getFontMetrics( b, g )
      val mnIdx  = b.getDisplayedMnemonicIndex

      g.setColor( if( model.isEnabled ) b.getForeground else {
         val defaults = if( SubminUtil.getClosestBoolean( b, "submin" )) SubminDefaults else NimbusDefaults
         defaults.disabledTextColor
      })
      SwingUtilities2.drawStringUnderlineCharAt( b, g, text, mnIdx,
         textRect.x + getTextShiftOffset, textRect.y + fm.getAscent + getTextShiftOffset )
   }

   override protected def installDefaults( b: AbstractButton ) {
      val pp = propertyPrefix

      defaultTextShiftOffset = UIManager.getInt( pp + "textShiftOffset" )

      updateSizeVariant( b )
      updateColors( b )

      LookAndFeel.installBorder( b, pp + "border" )

      val rollover = UIManager.get( pp + "rollover" )
      if( rollover != null ) {
         LookAndFeel.installProperty( b, "rolloverEnabled", if( rollover != null ) rollover else true )
      }

      val gap = UIManager.get( pp + "iconTextGap" )
      if( gap != null ) LookAndFeel.installProperty( b, "iconTextGap", gap )
   }

   override def getPreferredSize( c: JComponent ) : Dimension = {
      val res  = super.getPreferredSize( c )
      val b    = c.asInstanceOf[ AbstractButton ]
      val in   = b.getMargin
      res.width  += in.left + in.right
      res.height += in.top + in.bottom
      res
   }

   override protected def installListeners( b: AbstractButton ) {
      super.installListeners( b )
      installPropertyListener( b )
   }

   override protected def uninstallListeners( b: AbstractButton ) {
      super.uninstallListeners( b )
      uninstallPropertyListener( b )
   }

   override def paint( g: Graphics, c: JComponent ) {
      val b       = c.asInstanceOf[ AbstractButton ]
      val text    = layout( b, SwingUtilities2.getFontMetrics( c, g ), c.getWidth, c.getHeight )

      clearTextShiftOffset()

      val state   = getComponentState( b )
      val g2      = g.asInstanceOf[ Graphics2D ]
      g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON )
      val butPtr  = if( SubminUtil.getClosestBoolean( c, "submin" )) SubminButtonPainter else NimbusButtonPainter
      butPtr.paint( state, null, g2, viewRect.x, viewRect.y, viewRect.width, viewRect.height )

      if( b.getIcon != null ) paintIcon( g, c, iconRect )

      if( text != null && text != "" ) {
         val v = c.getClientProperty( BasicHTML.propertyKey ).asInstanceOf[ View ]
         if( v != null ) {
	         v.paint( g, textRect )
         } else {
	         paintText( g, b, textRect, text )
         }
      }
   }
}