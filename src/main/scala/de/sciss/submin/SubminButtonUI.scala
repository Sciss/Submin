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
import java.beans.{PropertyChangeEvent, PropertyChangeListener}

/**
 * todo:
 *  - doesn't check the `DEFAULT` state flag
 *  - haven't checked proper icon painting
 *  - should recognise custom button color (currently `null` is passed into `NimbusButtonPainter`)
 */
object SubminButtonUI {
   private val viewRect = new Rectangle()
   private val textRect = new Rectangle()
   private val iconRect = new Rectangle()

   private val instance = new SubminButtonUI
   def createUI( c: JComponent ) : ComponentUI = instance
}
class SubminButtonUI extends BasicButtonUI {
   ui =>

   import SubminButtonUI._

   private def getSubmin( c: JComponent ) : Boolean = SubminUtil.getClosestBoolean( c, "submin" )

   private def getSubminPropertyPrefix( c: JComponent ) : String = {
      val pp = getPropertyPrefix
      if( getSubmin( c )) pp.substring( 0, pp.length - 1 ) + "[submin]." else pp
   }

   override protected def installDefaults( b: AbstractButton ) {
      val pp = getPropertyPrefix

      defaultTextShiftOffset = UIManager.getInt( pp + "textShiftOffset" )

//      LookAndFeel.installProperty( b, "opaque", b.isContentAreaFilled )

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

   private def updateSizeVariant( b: AbstractButton ) {
      val pp = getPropertyPrefix

      val m = b.getMargin
      if( m == null || m.isInstanceOf[ UIResource ]) {
         b.setMargin( SubminUtil.getInsets( b, null, pp + "contentMargins" ))
      }

      val f = b.getFont
      if( f == null || f.isInstanceOf[ UIResource ]) {
         b.setFont( SubminUtil.getDefaultFont( b, pp + "font" ))
      }
   }

   private def updateColors( b: AbstractButton ) {
      val pps = getSubminPropertyPrefix( b )
      LookAndFeel.installColors( b, pps + "background", pps + "foreground" )
//      if( pps.contains( "submin" )) {
//         println( "foreground (" + pps + ") now " + b.getForeground )
//      }
   }

   private val prop = new PropertyChangeListener {
      def propertyChange( e: PropertyChangeEvent ) {
//         println( "PCE " + e.getPropertyName + " : " + e.getOldValue + " -> " + e.getNewValue )
         e.getPropertyName match {
            case "JComponent.sizeVariant" =>
               val b = e.getSource.asInstanceOf[ AbstractButton ]
//               val oldSz = b.getPreferredSize
//               println( "BEFORE " + oldSz )
               updateSizeVariant( b )
//               val newSz = b.getPreferredSize
//               println( "NOW " + newSz )
//               b.invalidate()
//               b.setPreferredSize( getPreferredSize( b ))
//               b.validate()
            case "submin" =>
               updateColors( e.getSource.asInstanceOf[ AbstractButton ])
            case _ =>
         }
      }
   }

   override protected def installListeners( b: AbstractButton ) {
      super.installListeners( b )
      b.addPropertyChangeListener( prop )
   }

   override protected def uninstallListeners( b: AbstractButton ) {
      super.uninstallListeners( b )
      b.removePropertyChangeListener( prop )
   }

   private def getComponentState( b: AbstractButton ) : State = {
      if( !b.isEnabled ) return State.disabled

      var state   = State.ENABLED
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

   override def paint( g: Graphics, c: JComponent ) {
      val b       = c.asInstanceOf[ AbstractButton ]
      val text    = layout( b, SwingUtilities2.getFontMetrics( c, g ), c.getWidth, c.getHeight )

      clearTextShiftOffset()

      val state   = getComponentState( b )
      val g2      = g.asInstanceOf[ Graphics2D ]
      g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON )
      val butPtr  = if( SubminUtil.getBoolean( c, "submin" )) SubminButtonPainter else NimbusButtonPainter
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

   override protected def paintText( g: Graphics, b: AbstractButton, textRect: Rectangle, text: String ) {
      val model  = b.getModel
      val fm     = SwingUtilities2.getFontMetrics( b, g )
      val mnIdx  = b.getDisplayedMnemonicIndex

      g.setColor( if( model.isEnabled ) b.getForeground else NimbusDefaults.disabledTextColor )
      SwingUtilities2.drawStringUnderlineCharAt( b, g, text, mnIdx,
         textRect.x + getTextShiftOffset, textRect.y + fm.getAscent + getTextShiftOffset )
   }

   override def getPreferredSize( c: JComponent ) : Dimension = {
      val res = super.getPreferredSize( c )
      val b = c.asInstanceOf[ AbstractButton ]
      val in = b.getMargin
//      println( "GETPREF " + in )
      res.width  += in.left + in.right // 28
      res.height += in.top + in.bottom // 12
      res
   }

   private def layout( b: AbstractButton, fm: FontMetrics, width: Int, height: Int ) : String = {
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
}