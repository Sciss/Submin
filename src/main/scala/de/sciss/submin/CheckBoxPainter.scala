/*
 *  CheckBoxPainter.scala
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

import java.awt.geom.{GeneralPath, RoundRectangle2D}
import scala.{Array, Int}
import java.awt.{Graphics2D, MultipleGradientPaint, LinearGradientPaint, Paint, Color}

trait CheckBoxPainter extends ButtonPainterLike {
   protected def defaults : Defaults

   private val rrect = new RoundRectangle2D.Float()
   private val path  = new GeneralPath()

   private val grad1frac = Array[ Float ]( 0.0f, 1.0f )
   private val grad2frac = Array[ Float ]( 0.0f, 0.64457834f, 1.0f )
   private val pressedSelectedGrad2frac = Array[ Float ]( 0.0f, 0.11550152f, 0.64457834f, 1.0f )

   private def enabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.05356429f,  -0.12549019f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.015789472f, -0.37254903f, 0 )
      res
   }

   private def disabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.06766917f, 0.07843137f,  0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.06484103f, 0.027450979f, 0 )
      res
   }

   private def disabledSelectedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, -0.01111114f, -0.03771078f,  0.062745094f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, -0.02222222f, -0.032806106f, 0.011764705f, 0 )
      res
   }

   private def pressedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, 0.8894737f,    -0.7176471f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f,         0.0016232133f, -0.3254902f, 0 )
      res
   }

   private def pressedSelectedGrad1colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( base, -0.57865167f,  -0.6357143f,   -0.54901963f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, -3.528595e-5f,  0.026785731f, -0.23529413f, 0 )
      res
   }

   private def overGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f,        -0.020974077f, -0.21960783f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.01010108f,  0.08947369f,  -0.5294118f,  0 )
      res
   }

   private def overSelectedGrad1colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.0013483167f, -0.1769987f, -0.12156865f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 0.05468172f,    0.3642857f, -0.43137258f, 0 )
      res
   }

   private def selectedGrad1colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( base, 5.1498413e-4f, -0.34585923f, -0.007843137f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 5.1498413e-4f, -0.10238093f, -0.25490198f,  0 )
      res
   }

   private def enabledGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.08801502f, -0.63174605f, 0.43921566f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.5953556f, 0.32549018f, 0 )
      res( 2 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.59942394f, 0.4235294f, 0 )
      res
   }

   private def disabledGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.60996324f, 0.36470586f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 0.02551502f,  -0.5996783f,  0.3215686f,  0 )
      res( 2 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.59624064f, 0.34509802f, 0 )
      res
   }

   private def disabledSelectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.021348298f, -0.59223604f, 0.35294116f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 0.021348298f, -0.56722116f, 0.3098039f,  0 )
      res( 2 ) = ColorUtil.adjustColor( base, 0.021348298f, -0.56875f,    0.32941175f, 0 )
      res
   }

   private def pressedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.027408898f, -0.5847884f,  0.2980392f,  0 )
      res( 1 ) = ColorUtil.adjustColor( base, 0.029681683f, -0.52701867f, 0.17254901f, 0 )
      res( 2 ) = ColorUtil.adjustColor( base, 0.029681683f, -0.5376751f,  0.25098038f, 0 )
      res
   }

   private def pressedSelectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 4 )
      res( 0 ) = ColorUtil.adjustColor( base, -4.2033195e-4f, -0.38050595f,   0.20392156f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, -0.0021489263f, -0.2891234f,    0.14117646f, 0 )
      res( 2 ) = ColorUtil.adjustColor( base, -0.006362498f,  -0.016311288f, -0.02352941f, 0 )
      res( 3 ) = ColorUtil.adjustColor( base,  0.0f,          -0.17930403f,   0.21568626f, 0 )
      res
   }

   private def overGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.08801502f, -0.6317773f, 0.4470588f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 0.032459438f, -0.5985242f, 0.39999998f, 0 )
      res( 2 ) = ColorUtil.adjustColor( base, 0.0f, -0.6357143f, 0.45098037f, 0 )
      res
   }

   private def overSelectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.004681647f,  -0.6198413f,  0.43921566f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 5.1498413e-4f, -0.4555341f,  0.3215686f,  0 )
      res( 2 ) = ColorUtil.adjustColor( base, 5.1498413e-4f, -0.47377098f, 0.41960782f, 0 )
      res
   }

   private def selectedGrad2colr( base: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 3 )
      res( 0 ) = ColorUtil.adjustColor( base, 0.004681647f,  -0.6197143f,  0.43137252f, 0 )
      res( 1 ) = ColorUtil.adjustColor( base, 5.1498413e-4f, -0.44153953f, 0.2588235f,  0 )
      res( 2 ) = ColorUtil.adjustColor( base, 5.1498413e-4f, -0.4602757f,  0.34509802f, 0 )
      res
   }

   def paint( state: State, c: Color, g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
      if( state.isEnabled ) {
         val base       = ColorUtil.mixColorWithAlpha( defaults.baseColor, c )
         val blueGrey   = defaults.blueGreyColor
         if( state.isPressed ) {
            if( state.isFocused ) {
               if( state.isSelected ) {
                  paintFocusedPressedSelected( g, base, x, y, width, height )
               } else {
                  paintFocusedPressed( g, base, blueGrey, x, y, width, height )
               }
            } else {
               if( state.isSelected ) {
                  paintPressedSelected( g, base, blueGrey, x, y, width, height )
               } else {
                  paintPressed( g, base, blueGrey, x, y, width, height )
               }
            }
         } else if( state.isMouseOver ) {
            if( state.isFocused ) {
               if( state.isSelected ) {
                  paintFocusedOverSelected( g, base, x, y, width, height )
               } else {
                  paintFocusedOver( g, base, blueGrey, x, y, width, height )
               }
            } else {
               if( state.isSelected ) {
                  paintSelectedOver( g, base, blueGrey, x, y, width, height )
               } else {
                  paintOver( g, base, blueGrey, x, y, width, height )
               }
            }
         } else {
            if( state.isFocused ) {
               if( state.isSelected ) {
                  paintFocusedSelected( g, base, x, y, width, height )
               } else {
                  paintFocused( g, base, blueGrey, x, y, width, height )
               }
            } else {
               if( state.isSelected ) {
                  paintSelected( g, base, blueGrey, x, y, width, height )
               } else {
                  paintEnabled( g, base, blueGrey, x, y, width, height )
               }
            }
         }
      } else {
         val c2          = if( c == null ) c else ColorUtil.adjustColor( c, 0f, 0f, 0f, -112 )
         val base        = ColorUtil.mixColorWithAlpha( defaults.baseColor, c2 )
         val blueGrey    = defaults.blueGreyColor
         if( state.isSelected ) {
            paintDisabledSelected( g, base, blueGrey, x, y, width, height )
         } else {
            paintDisabled( g, base, blueGrey, x, y, width, height )
         }
      }
   }

   private def createEnabledGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, enabledGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createDisabledGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, disabledGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createDisabledSelectedGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, disabledSelectedGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createPressedGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, pressedGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createPressedSelectedGradient1( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, pressedSelectedGrad1colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createOverGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, overGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createOverSelectedGradient1( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, overSelectedGrad1colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createSelectedGradient1( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25210084033613445f * shpW
       val endY   = shpY1 + 0.9957983193277311f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, selectedGrad1colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createEnabledGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, enabledGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createDisabledGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, disabledGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createDisabledSelectedGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, disabledSelectedGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createPressedGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, pressedGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createPressedSelectedGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, pressedSelectedGrad2frac, pressedSelectedGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createOverGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, overGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createOverSelectedGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, overSelectedGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createSelectedGradient2( base: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.25f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.25f* shpW
       val endY   = shpY1 + 0.9975490196078431f * shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, selectedGrad2colr( base ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def paintEnabled( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack( g,       blueGrey, x, y, width, height )
      paintEnabledTop(  g, base, blueGrey, x, y, width, height )
   }

   private def paintEnabledBack( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + height - 7f
      val e1w = width - 4f
      val e1h = 6f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 5.176f, 5.176f )
      g.setColor( ColorUtil.adjustColor( blueGrey, 0.0f, 0.0f, 0.0f, -89 ))
      g.fill( rrect )
   }

   private def paintEnabledTop( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createEnabledGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createEnabledGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintSelected( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack(   g,       blueGrey, x, y, width, height )
      paintSelectedTop(   g, base,           x, y, width, height )
      paintSelectedCheck( g, base,           x, y, width, height )
   }

   private def paintSelectedTop( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createSelectedGradient1( base, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createSelectedGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintSelectedCheck( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      g.setColor( ColorUtil.adjustColor( base, -0.57865167f, -0.6357143f, -0.54901963f, 0 ))
      paintSelectedCheckShape( g, x, y, width, height )
   }

   private def paintDisabledSelectedCheck( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      g.setColor( ColorUtil.adjustColor( base, 0.027408898f, -0.5735674f, 0.14509803f, 0 ))
      paintSelectedCheckShape( g, x, y, width, height )
   }

   private def paintSelectedCheckShape( g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
      val sx = 18f / width
      val sy = 18f / height
      path.reset()
      path.moveTo( x +  5.029f * sx, y +  8.059f * sy )
      path.lineTo( x +  7.029f * sx, y +  8.059f * sy )
      path.lineTo( x +  8.441f * sx, y + 11.058f * sy )
      path.lineTo( x + 11.588f * sx, y +  3.118f * sy )
      path.lineTo( x + 14.000f * sx, y +  3.088f * sy )
      path.lineTo( x +  8.941f * sx, y + 13.029f * sy )
      path.lineTo( x +  8.059f * sx, y + 13.029f * sy )
      path.closePath()
      g.fill( path )
   }

   private def paintOver( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack( g,       blueGrey, x, y, width, height )
      paintOverTop(     g, base, blueGrey, x, y, width, height )
   }

   private def paintOverTop( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createOverGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createOverGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintSelectedOver( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack(     g,       blueGrey, x, y, width, height )
      paintOverSelectedTop( g, base,           x, y, width, height )
      paintSelectedCheck(   g, base,           x, y, width, height )
   }

   private def paintOverSelectedTop( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createOverSelectedGradient1( base, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createOverSelectedGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintPressed( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack( g,       blueGrey, x, y, width, height )
      paintPressedTop(  g, base, blueGrey, x, y, width, height )
   }

   private def paintPressedSelected( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + height - 7f
      val e1w = width - 4f
      val e1h = 6f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 5.176f, 5.176f )
      g.setColor( ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, -89 ))
      g.fill( rrect )

      paintPressedSelectedTop( g, base, x, y, width, height )
      paintSelectedCheck(      g, base, x, y, width, height )
   }

   private def paintPressedSelectedTop( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createPressedSelectedGradient1( base, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createPressedSelectedGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintFocused( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g,                 x, y, width, height )
      paintEnabledTop(  g, base, blueGrey, x, y, width, height )
   }

   private def paintFocusedBack( g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 0.6f
      val e1y = y + 0.6f
      val e1w = width - 1.2f
      val e1h = height - 1.2f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 8f, 8f )
      g.setColor( defaults.focusColor )
      g.fill( rrect )
   }

   private def paintFocusedSelected( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack(   g,       x, y, width, height )
      paintSelectedTop(   g, base, x, y, width, height )
      paintSelectedCheck( g, base, x, y, width, height )
   }

   private def paintFocusedOver( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g,                 x, y, width, height )
      paintOverTop(     g, base, blueGrey, x, y, width, height )
   }

   private def paintFocusedOverSelected( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack(     g,       x, y, width, height )
      paintOverSelectedTop( g, base, x, y, width, height )
      paintSelectedCheck(   g, base, x, y, width, height )
   }

   private def paintFocusedPressed( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g,                 x, y, width, height )
      paintPressedTop(  g, base, blueGrey, x, y, width, height )
   }

   private def paintPressedTop( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createPressedGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createPressedGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintFocusedPressedSelected( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack(        g,       x, y, width, height )
      paintPressedSelectedTop( g, base, x, y, width, height )
      paintSelectedCheck(      g, base, x, y, width, height )
   }

   private def paintDisabled( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createDisabledGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createDisabledGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintDisabledSelected( g: Graphics2D, base: Color, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 3.706f, 3.706f )
      g.setPaint( createDisabledSelectedGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 3.765f, 3.765f )
      g.setPaint( createDisabledSelectedGradient2( base, e3x, e3y, e3w, e3h ))
      g.fill( rrect )

      paintDisabledSelectedCheck( g, base, x, y, width, height )
   }
}