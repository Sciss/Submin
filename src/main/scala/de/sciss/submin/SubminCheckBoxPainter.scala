/*
 *  SubminCheckBoxPainter.scala
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

object SubminCheckBoxPainter extends CheckBoxPainter {
   protected def defaults : Defaults = SubminDefaults

   protected def enabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.05356429f,  -0.12549019f, 0 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.015789472f, -0.37254903f, 0 )
      res
   }

   protected def disabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.06766917f, 0.07843137f,  0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.06484103f, 0.027450979f, 0 )
      res
   }

   protected def disabledSelectedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, -0.01111114f, -0.03771078f,  0.062745094f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, -0.02222222f, -0.032806106f, 0.011764705f, 0 )
      res
   }

   protected def pressedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.055555582f, 0.8894737f,    -0.7176471f, 0 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f,         0.0016232133f, -0.3254902f, 0 )
      res
   }

   protected def pressedSelectedGrad1colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( base, -0.57865167f,  -0.6357143f,   -0.54901963f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, -3.528595e-5f,  0.026785731f, -0.23529413f, 0 )
      res
   }

   protected def overGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f,        -0.020974077f, -0.21960783f, 0 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.01010108f,  0.08947369f,  -0.5294118f,  0 )
      res
   }

   protected def overSelectedGrad1colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 0.0013483167f, -0.1769987f, -0.12156865f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 0.05468172f,    0.3642857f, -0.43137258f, 0 )
      res
   }

   protected def selectedGrad1colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 5.1498413e-4f, -0.34585923f, -0.007843137f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 5.1498413e-4f, -0.10238093f, -0.25490198f,  0 )
      res
   }

   protected def enabledGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      // brightness = nimbusBrightness.linlin( 0.32549018f, 0.43921566f, -0.43921566f, -0.32549018f )
//      res( 0 ) = ColorUtil.adjustColor( base, 0.08801502f, -0.63174605f, -0.32549018f, 0 )
//      res( 1 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.5953556f, -0.43921566f, 0 )
//      res( 2 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.59942394f, -0.34117645f, 0 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.08801502f, -0.63174605f, -0.32549018f * 1.75f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.5953556f, -0.43921566f * 1.75f, 0 )
      res( 2 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.59942394f, -0.34117645f * 1.75f, 0 )
      res
   }

   protected def disabledGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 0.032459438f, -0.60996324f, 0.36470586f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 0.02551502f,  -0.5996783f,  0.3215686f,  0 )
      res( 2 ) = ColorUtil.subAdjustColor( base, 0.032459438f, -0.59624064f, 0.34509802f, 0 )
      res
   }

   protected def disabledSelectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 0.021348298f, -0.59223604f, 0.35294116f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 0.021348298f, -0.56722116f, 0.3098039f,  0 )
      res( 2 ) = ColorUtil.subAdjustColor( base, 0.021348298f, -0.56875f,    0.32941175f, 0 )
      res
   }

   protected def pressedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 0.027408898f, -0.5847884f,  0.2980392f,  0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 0.029681683f, -0.52701867f, 0.17254901f, 0 )
      res( 2 ) = ColorUtil.subAdjustColor( base, 0.029681683f, -0.5376751f,  0.25098038f, 0 )
      res
   }

   protected def pressedSelectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 4 )
      res( 0 ) = ColorUtil.subAdjustColor( base, -4.2033195e-4f, -0.38050595f,   0.20392156f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, -0.0021489263f, -0.2891234f,    0.14117646f, 0 )
      res( 2 ) = ColorUtil.subAdjustColor( base, -0.006362498f,  -0.016311288f, -0.02352941f, 0 )
      res( 3 ) = ColorUtil.subAdjustColor( base,  0.0f,          -0.17930403f,   0.21568626f, 0 )
      res
   }

   // XXX TODO
   protected def overGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 0.08801502f, -0.6317773f, 0.4470588f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 0.032459438f, -0.5985242f, 0.39999998f, 0 )
      res( 2 ) = ColorUtil.subAdjustColor( base, 0.0f, -0.6357143f, 0.45098037f, 0 )
      res
   }

   protected def overSelectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 0.004681647f,  -0.6198413f,  0.43921566f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 5.1498413e-4f, -0.4555341f,  0.3215686f,  0 )
      res( 2 ) = ColorUtil.subAdjustColor( base, 5.1498413e-4f, -0.47377098f, 0.41960782f, 0 )
      res
   }

   protected def selectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.subAdjustColor( base, 0.004681647f,  -0.6197143f,  0.43137252f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( base, 5.1498413e-4f, -0.44153953f, 0.2588235f,  0 )
      res( 2 ) = ColorUtil.subAdjustColor( base, 5.1498413e-4f, -0.4602757f,  0.34509802f, 0 )
      res
   }

   protected def enabledBackColor( blueGrey: Color ) : Color =
      ColorUtil.subAdjustColor( blueGrey, 0.0f, 0.0f, 0.0f, -89 )

   protected def selectedCheckColor( base: Color ) : Color =
      ColorUtil.subAdjustColor( base, -0.57865167f, -0.6357143f, -0.54901963f, 0 )

   protected def disabledSelectedCheckColor( base: Color ) : Color =
      ColorUtil.subAdjustColor( base, 0.027408898f, -0.5735674f, 0.14509803f, 0 )

   protected def pressedSelectedBackColor( blueGrey: Color ) : Color =
      ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, -89 )
}