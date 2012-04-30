/*
 *  SubminButtonPainter.scala
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

import java.awt.{Graphics2D, MultipleGradientPaint, LinearGradientPaint, Paint, Color}

object SubminButtonPainter extends ButtonPainter {
   protected val grad1frac = Array[ Float ]( 0.05f, 0.91f )
   protected def defaults : Defaults = SubminDefaults

   protected def enabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, -0.055555522f, -0.05356429f, -0.12549019f, 0 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.0147816315f, -0.3764706f, 0 )
      res
   }

   protected def disabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.06766917f, 0.07843137f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.06484103f, 0.027450979f, 0 )
      res
   }

   protected def overGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.020974077f, -0.21960783f, 0 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, 0.11169591f, -0.53333336f, 0 )
      res
   }

   protected def pressedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.8894737f * 0.5f, 0.7176471f * 0.5f, 0 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -5.847961e-4f, 0.32156864f, 0 )
      res
   }

   protected def enabledGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      // brightness = nimbusBrightness.linlin(0.12941176f,0.25490195f,-0.25490195f,-0.12941176f)
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10655806f, -0.14117646f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.09823123f,  -0.17254901f, 0 )
      res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  -0.25490195f, 0 )
      res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  -0.25490195f, 0 )
      res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.0749532f,   -0.1372549f,  0 )
      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.12941176f, 0 )
      res
   }

   protected def disabledGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.08477524f, 0.16862744f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, -0.015872955f, -0.080091536f, 0.15686274f, 0 )
      res( 2 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07016757f, 0.12941176f, 0 )
      res( 3 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07016757f, 0.12941176f, 0 )
      res( 4 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07052632f, 0.1372549f, 0 )
      res( 5 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.070878744f, 0.14509803f, 0 )
      res
   }

   protected def overGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      // brightness = same offset from enabled brightness as in nimbus equivalent
      // e.g. nimbus enabled 0.24313724f / over 0.25098038f = offset 0.007843137f
      // with submin enabled = -0.14117646f thus yields submin over -0.13333333f
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10658931f, -0.13333333f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.098526314f, -0.1490196f, 0 )
      res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, -0.18039215f, 0 )
      res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, -0.18039215f, 0 )
      res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.12941176f, 0 )
      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.12941176f, 0 )
      res
   }

   protected def enabledBackColor( blueGrey: Color ) : Color =
      ColorUtil.subAdjustColor( blueGrey, -0.027777791f, -0.06885965f, -0.36862746f, -190 )

   protected def disabledBackColor( blueGrey: Color ) : Color =
      ColorUtil.subAdjustColor( blueGrey, -0.027777791f, -0.06885965f, -0.36862746f, -232 )

   protected val pressedBackColor = ColorUtil.subminify( new Color( 245, 250, 255, 160 ))

   protected val enabledBackYOffset = 1

   protected val pressedBackYOffset = 1
}