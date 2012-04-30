/*
 *  SubminCheckBoxUI.scala
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

import javax.swing.plaf.ComponentUI
import sun.swing.SwingUtilities2
import de.sciss.submin.SubminButtonUILike._
import javax.swing.plaf.basic.{BasicHTML, BasicCheckBoxUI}
import javax.swing.text.View
import javax.swing.{LookAndFeel, UIManager, SwingUtilities, JComponent, AbstractButton}
import java.awt.{Rectangle, RenderingHints, Graphics2D, Graphics, Dimension}

object SubminCheckBoxUI {
   private val instance = new SubminCheckBoxUI
   def createUI( c: JComponent ) : ComponentUI = instance
}
final class SubminCheckBoxUI extends BasicCheckBoxUI with SubminButtonUILike {
//   import SubminCheckBoxUI._

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
//      if( rollover != null ) {
         LookAndFeel.installProperty( b, "rolloverEnabled", if( rollover != null ) rollover else true )
//      }

      val gap = UIManager.get( pp + "iconTextGap" )
      if( gap != null ) LookAndFeel.installProperty( b, "iconTextGap", gap )

      icon = UIManager.getIcon( pp + "icon" )
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
      val b             = c.asInstanceOf[ AbstractButton ]
      val i             = c.getInsets
      val width         = b.getWidth
      val height        = b.getHeight
      viewRect.x        = i.left
      viewRect.y        = i.top
      viewRect.width    = width  - (i.left + i.right)
      viewRect.height   = height - (i.top + i.bottom)
      iconRect.x        = 0
      iconRect.y        = 0
      iconRect.width    = 0
      iconRect.height   = 0
      textRect.x        = 0
      textRect.y        = 0
      textRect.width    = 0
      textRect.height   = 0

      val userIcon      = b.getIcon
      val icon          = if( userIcon == null ) {
         getDefaultIcon
      } else {
         val state = getComponentState( b )
         val res2 = if( state.isEnabled ) {
            if( state.isSelected ) {
               b.getDisabledSelectedIcon
            } else {
               b.getDisabledIcon
            }
         } else if( state.isPressed  ) {
            val res = b.getPressedIcon
            if( res != null ) res else b.getSelectedIcon
         } else if( state.isSelected ) {
            if( b.isRolloverEnabled && state.isMouseOver ) {
               val res = b.getRolloverSelectedIcon
               if( res != null ) res else b.getSelectedIcon
            } else {
               b.getSelectedIcon
            }
         } else if( b.isRolloverEnabled && state.isMouseOver ) {
            b.getRolloverIcon
         } else userIcon

         if( res2 != null ) res2 else userIcon
      }

      val str           = b.getText
      val f             = c.getFont
      g.setFont( f )
      val fm            = SwingUtilities2.getFontMetrics( c, g, f )
      val text          = SwingUtilities.layoutCompoundLabel(
          c, fm, str, icon,
          b.getVerticalAlignment, b.getHorizontalAlignment,
          b.getVerticalTextPosition, b.getHorizontalTextPosition,
          viewRect, iconRect, textRect, if( str == null ) 0 else b.getIconTextGap )

      clearTextShiftOffset()

      val g2      = g.asInstanceOf[ Graphics2D ]
      g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON )

      icon.paintIcon( c, g, iconRect.x, iconRect.y )

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