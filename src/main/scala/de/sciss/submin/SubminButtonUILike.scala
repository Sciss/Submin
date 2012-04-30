/*
 *  SubminButtonUILike.scala
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

import javax.swing.plaf.basic.BasicButtonUI
import javax.swing.plaf.UIResource
import java.awt.{FontMetrics, Rectangle}
import javax.swing.{SwingUtilities, JButton, AbstractButton}

object SubminButtonUILike {
   private[submin] val viewRect = new Rectangle()
   private[submin] val textRect = new Rectangle()
   private[submin] val iconRect = new Rectangle()

   def getComponentState( b: AbstractButton ) : State = {
//      if( !b.isEnabled ) return State.disabled

      var state   = if( b.isEnabled ) State.ENABLED else State.DISABLED
      val model   = b.getModel

      if( model.isPressed ) {
         if( model.isArmed ) {
            state |= State.PRESSED
         } else {
            state |= State.MOUSE_OVER
         }
      }
      if( model.isRollover ) state |= State.MOUSE_OVER
      if( model.isSelected ) state |= State.SELECTED
      if( b.isFocusOwner && b.isFocusPainted ) state |= State.FOCUSED

      b match {
         case b2: JButton if b2.isDefaultButton => state |= State.DEFAULT
         case _ =>
      }

      State( state )
   }
}
trait SubminButtonUILike extends SubminUI[ AbstractButton ] {
   ui: BasicButtonUI =>

   import SubminButtonUILike._

// scalac error!
//   final protected def propertyPrefix = getPropertyPrefix

//   override protected def paintText( g: Graphics, b: AbstractButton, textRect: Rectangle, text: String ) {
//      val model  = b.getModel
//      val fm     = SwingUtilities2.getFontMetrics( b, g )
//      val mnIdx  = b.getDisplayedMnemonicIndex
//
//      g.setColor( if( model.isEnabled ) b.getForeground else NimbusDefaults.disabledTextColor )
//// scalac error!
//      SwingUtilities2.drawStringUnderlineCharAt( b, g, text, mnIdx,
//         textRect.x + getTextShiftOffset, textRect.y + fm.getAscent + getTextShiftOffset )
//   }

   final protected def layout( b: AbstractButton, fm: FontMetrics, width: Int, height: Int ) : String = {
      val i             = b.getInsets
      viewRect.x        = i.left
      viewRect.y        = i.top
      viewRect.width    = width - (i.right + viewRect.x)
      viewRect.height   = height - (i.bottom + viewRect.y)

      textRect.x        = 0
      textRect.y        = 0
      textRect.width    = 0
      textRect.height   = 0
      iconRect.x        = 0
      iconRect.y        = 0
      iconRect.width    = 0
      iconRect.height   = 0

      // layout the text and icon
      SwingUtilities.layoutCompoundLabel(
         b, fm, b.getText, b.getIcon, b.getVerticalAlignment, b.getHorizontalAlignment,
         b.getVerticalTextPosition, b.getHorizontalTextPosition, viewRect, iconRect, textRect,
         if( b.getText == null ) 0 else b.getIconTextGap )
   }

//   override protected def installDefaults( b: AbstractButton ) {
//      val pp = propertyPrefix
//
//      // scalac error
//      defaultTextShiftOffset = UIManager.getInt( pp + "textShiftOffset" )
//
//      updateSizeVariant( b )
//      updateColors( b )
//
//      LookAndFeel.installBorder( b, pp + "border" )
//
//      val rollover = UIManager.get( pp + "rollover" )
//      if( rollover != null ) {
//         LookAndFeel.installProperty( b, "rolloverEnabled", if( rollover != null ) rollover else true )
//      }
//
//      val gap = UIManager.get( pp + "iconTextGap" )
//      if( gap != null ) LookAndFeel.installProperty( b, "iconTextGap", gap )
//   }

   override protected def updateSizeVariant( c: AbstractButton ) {
      super.updateSizeVariant( c )

      val m = c.getMargin
      if( m == null || m.isInstanceOf[ UIResource ]) {
         c.setMargin( SubminUtil.getInsets( c, null, propertyPrefix + "contentMargins" ))
      }
   }
}
