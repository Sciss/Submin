/*
 *  SubminLabelUI.scala
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

import javax.swing.plaf.basic.BasicLabelUI
import javax.swing.{JComponent, JLabel}
import javax.swing.plaf.ComponentUI
import java.awt.{RenderingHints, Graphics2D, Graphics}

object SubminLabelUI {
   private val instance = new SubminLabelUI
   def createUI( c: JComponent ) : ComponentUI = instance
}
final class SubminLabelUI extends BasicLabelUI with SubminUI[ JLabel ] {
   protected def propertyPrefix = "Label."

   override def paint( g: Graphics, c: JComponent ) {
      val g2 = g.asInstanceOf[ Graphics2D ]
      g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON )
      super.paint( g, c )
   }

   override protected def installListeners( c: JLabel ) {
      super.installListeners( c)
      installPropertyListener( c )
   }

   override protected def uninstallListeners( c: JLabel ) {
      super.uninstallListeners( c )
      uninstallPropertyListener( c )
   }
}
