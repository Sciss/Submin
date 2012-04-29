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
   private val hsbArr   = new Array[ Float ]( 3 )

   def backgroundColor : Color = {
      val nimbus = NimbusDefaults.backgroundColor
      subminify( nimbus )
   }

   private def subminify( c: Color ) : Color = {
      val r    = 0xFF - c.getRed
      val g    = 0xFF - c.getGreen
      val b    = 0xFF - c.getBlue
      val a    = c.getAlpha
      Color.RGBtoHSB( r, g, b, hsbArr )
      val hue  = hsbArr( 0 ) + 0.5f
      val sat  = hsbArr( 1 )
      val bri  = hsbArr( 2 )
      val rgb  = Color.HSBtoRGB( hue, sat, bri )
      val rgba = (rgb & 0xFFFFFF) | (a << 24)
      new Color( rgba, true )
   }
}
