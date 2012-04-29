/*
 *  SubminPanelUI.scala
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

import javax.swing.plaf.basic.BasicPanelUI
import javax.swing.plaf.ComponentUI
import javax.swing.{JComponent, JPanel, LookAndFeel}

object SubminPanelUI {
   private val instance = new SubminPanelUI
   def createUI( c: JComponent ) : ComponentUI = instance // new SubminPanelUI
}
final class SubminPanelUI extends BasicPanelUI with SubminUI[ JPanel ] {
   protected val propertyPrefix = "Panel."

   override protected def installDefaults( p: JPanel ) {
      updateColors( p )
      updateSizeVariant( p )

      LookAndFeel.installBorder( p, "Panel.border" )
      LookAndFeel.installProperty( p, "opaque", true )
   }

   override def installUI( c: JComponent ) {
      super.installUI( c )
      installPropertyListener( c )
   }

   override def uninstallUI( c: JComponent ) {
      super.uninstallUI( c )
      uninstallPropertyListener( c )
   }
}