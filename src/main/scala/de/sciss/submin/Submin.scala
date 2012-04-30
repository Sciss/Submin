/*
 *  Submin.scala
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

import javax.swing.UIManager

object Submin {
   val name          = "Submin"
   val version       = 0.10
   val copyright     = "(C)opyright 2012 Hanns Holger Rutz"
   val isSnapshot    = true

   def versionString = {
      val s = (version + 0.001).toString.substring( 0, 4 )
      if( isSnapshot ) s + "-SNAPSHOT" else s
   }

   private val sync = new AnyRef
   private var initialized = false

   def init( dark: Boolean = false ) {
      sync.synchronized {
         if( !initialized ) {
            val (name, className) = if( dark ) {
               SubminDarkLookAndFeel.name -> SubminDarkLookAndFeel.className
            } else {
               SubminLookAndFeel.name -> SubminLookAndFeel.className
            }
            UIManager.installLookAndFeel( name, className )
            UIManager.setLookAndFeel( className )
            initialized = true
         }
      }
   }
}
