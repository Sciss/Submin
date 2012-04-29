/*
 *  ColorUtil.scala
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

object ColorUtil {
   private val hsbArr = new Array[ Float ]( 3 )

   def subminify( c: Color ) : Color = {
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

   def subAdjustColor( c: Color, hueOffset: Float, satOffset: Float, briOffset: Float, alphaOffset: Int ) : Color =
      adjustColor( c, hueOffset, satOffset, -briOffset, alphaOffset )

   def adjustColor( c: Color, hueOffset: Float, satOffset: Float, briOffset: Float, alphaOffset: Int ) : Color = {
      // statistically, brightness offset is varied more often than hue offset
      val sameColor = briOffset == 0f && satOffset == 0f && hueOffset == 0f
      val sameAlpha = alphaOffset == 0
      if( sameColor ) {
         if( sameAlpha ) return c
         // don't know what's going on here. nimbus defaults ColorUIResources have alpha values of zero sometimes
         val cAlpha = c.getAlpha
         return new Color( c.getRed, c.getGreen, c.getBlue, math.max( 0, math.min( 0xFF, cAlpha + alphaOffset )))
      }

      Color.RGBtoHSB( c.getRed, c.getGreen, c.getBlue, hsbArr )
      val hue     = hsbArr( 0 ) + hueOffset
      val sat     = math.max( 0f, math.min( 1f, hsbArr( 1 ) + satOffset ))
      val bri     = math.max( 0f, math.min( 1f, hsbArr( 2 ) + briOffset ))
      val rgb     = Color.HSBtoRGB( hue, sat, bri )
      val cAlpha  = c.getAlpha
      val a       = if( sameAlpha ) cAlpha else math.max( 0, math.min( 0xFF, cAlpha + alphaOffset ))
      val rgba    = (rgb & 0xFFFFFF) | (a << 24)
      new Color( rgba, true )
   }

   def mixColorWithAlpha( base: Color, mix: Color ) : Color = {
      if( mix == null ) return base
      val a0 = mix.getAlpha
      if( a0 == 0 ) { return base } else if( a0 == 0xFF ) return mix

      val wm   = a0.toFloat / 0xFF
      val wb   = 1f - wm
      val r    = (base.getRed   * wb + mix.getRed   * wm + 0.5f).toInt
      val g    = (base.getGreen * wb + mix.getGreen * wm + 0.5f).toInt
      val b    = (base.getBlue  * wb + mix.getBlue  * wm + 0.5f).toInt
      new Color( r, g, b )
   }
}
