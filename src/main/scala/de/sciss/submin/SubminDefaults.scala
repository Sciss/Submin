/*
 *  SubminDefaults.scala
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

import java.awt.Color

object SubminDefaults {
//   private val hsbArr   = new Array[ Float ]( 3 )

   def backgroundColor : Color = {
      val nimbus = NimbusDefaults.backgroundColor
      ColorUtil.subminify( nimbus )
   }

//   def focusColor : Color = NimbusDefaults.focusColor

   def focusColor : Color = {
      val nimbus = NimbusDefaults.focusColor
      ColorUtil.subminify( nimbus )
   }

   def buttonBackgroundColor : Color = {
      val nimbus = NimbusDefaults.buttonBackgroundColor
      ColorUtil.subminify( nimbus )
   }

   def buttonForegroundColor : Color = {
      val nimbus = NimbusDefaults.buttonForegroundColor
      ColorUtil.subminify( nimbus )
   }

   def panelBackgroundColor : Color = {
      val nimbus = NimbusDefaults.panelBackgroundColor
      ColorUtil.subminify( nimbus )
   }

   def panelForegroundColor : Color = {
      val nimbus = NimbusDefaults.panelForegroundColor
      ColorUtil.subminify( nimbus )
   }

   def blueGreyColor : Color = {
      val nimbus = NimbusDefaults.blueGreyColor
      ColorUtil.subminify( nimbus )
   }

//   def getBlueGreyColor( base: Color ) : Color = {
//       val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusBlueGrey" )
//       if( c == null ) getDefaultBlueGreyColor( base ) else c
//   }

//   private def getDefaultBlueGreyColor( base: Color ) : Color =
//      ColorUtil.adjustColor( base, 0.032459438f, -0.52518797f, -0.19607842f, 0 )
}
