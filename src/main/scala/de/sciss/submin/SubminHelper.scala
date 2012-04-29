/*
 *  SubminHelper.scala
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

import javax.swing.plaf.ColorUIResource
import javax.swing.{UnsupportedLookAndFeelException, UIManager, JComponent}
import annotation.tailrec
import java.awt.{Container, Color}

object SubminHelper {
   def getBoolean( c: JComponent, property: String, default: Boolean = false ) : Boolean =
      c.getClientProperty( property ) match {
         case java.lang.Boolean.TRUE   => true
         case java.lang.Boolean.FALSE  => false
         case _                        => default
      }

   @tailrec private def findJComponentAncestor( child: Container ) : JComponent = {
      if( child == null ) return null
      child.getParent match {
         case jp: JComponent => jp
         case p => findJComponentAncestor( p )
      }
   }

   @tailrec def getClosestBoolean( c: JComponent, property: String, default: Boolean = false ) : Boolean =
      c.getClientProperty( property ) match {
         case java.lang.Boolean.TRUE   => true
         case java.lang.Boolean.FALSE  => false
         case _ =>
            val p = findJComponentAncestor( c )
            if( p == null ) default else getClosestBoolean( p, property, default )
      }

   def setLookAndFeel() {
      try {
         val current = UIManager.getLookAndFeel
         if( current.getName.toLowerCase != "nimbus" ) {
            val infos = UIManager.getInstalledLookAndFeels
            for( i <- 0 until infos.length ) {
               if( infos( i ).getName.toLowerCase == "nimbus" ) {
                  UIManager.setLookAndFeel( infos( i ).getClassName )
               }
            }
         }
      } catch {
         case e1: UnsupportedLookAndFeelException  => e1.printStackTrace()
         case e1: ClassNotFoundException           => e1.printStackTrace()
         case e1: InstantiationException           => e1.printStackTrace()
         case e1: IllegalAccessException           => e1.printStackTrace()
      }

      val defaults = UIManager.getDefaults
      defaults.put( "Panel[submin].background",  new ColorUIResource( SubminDefaults.panelBackgroundColor ))
      defaults.put( "Panel[submin].foreground",  new ColorUIResource( SubminDefaults.panelForegroundColor ))
      defaults.put( "Button[submin].background", new ColorUIResource( SubminDefaults.buttonBackgroundColor ))
      defaults.put( "Button[submin].foreground", new ColorUIResource( SubminDefaults.buttonForegroundColor ))
   }
}
