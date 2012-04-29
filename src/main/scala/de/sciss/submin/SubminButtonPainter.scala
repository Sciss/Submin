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

import java.awt.geom.RoundRectangle2D
import java.awt.{Color, Graphics2D, MultipleGradientPaint, LinearGradientPaint, Paint}
import scala.Int

object SubminButtonPainter extends ButtonPainter {
   private val rrect    = new RoundRectangle2D.Float()

   private val grad1frac = Array[ Float ]( 0.09f, 0.95f )

   private val pressedGrad1frac = Array[ Float ]( 0.05f, 0.95f )

   private def enabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, -0.055555522f, -0.05356429f, -0.12549019f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.0147816315f, -0.3764706f, 0 )
      res
   }

   private def disabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.06766917f, 0.07843137f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.06484103f, 0.027450979f, 0 )
      res
   }

   private def overGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.020974077f, -0.21960783f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, 0.11169591f, -0.53333336f, 0 )
      res
   }

   private def pressedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.8894737f * 0.5f, 0.7176471f * 0.5f, 0 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -5.847961e-4f, 0.32156864f, 0 )
      res
   }

//   private val pressedBackColr = ColorUtil.subminify( new Color( 245, 250, 255, 160 ))
   private val pressedBackColr = new Color( 245, 250, 255, 160 )

   private def focusedPressedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, 0.8894737f, -0.7176471f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, 5.847961e-4f, -0.32156864f, 0 )
      res
   }

   private val grad2frac = Array[ Float ]( 0.0f, 0.06f, 0.6f, 0.7f, 0.95f, 1.0f )

//   private def enabledGrad2colr( blueGrey: Color ) : Array[ Color ] = {
//      val res = new Array[ Color ]( 6 )
//      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.055555582f, -0.10655806f, 0.24313724f, 0 )
//      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.09823123f,  0.2117647f,  0 )
//      res( 2 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07016757f,  0.12941176f, 0 )
//      res( 3 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07016757f,  0.12941176f, 0 )
//      res( 4 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.0749532f,   0.24705881f, 0 )
//      res( 5 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )
//      res
//   }

   private def enabledGrad2colr( blueGrey: Color ) : Array[ Color ] = {
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

   private def disabledGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.08477524f, 0.16862744f, 0 )
      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, -0.015872955f, -0.080091536f, 0.15686274f, 0 )
      res( 2 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07016757f, 0.12941176f, 0 )
      res( 3 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07016757f, 0.12941176f, 0 )
      res( 4 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07052632f, 0.1372549f, 0 )
      res( 5 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.070878744f, 0.14509803f, 0 )
      res
   }

//   private def overGrad2colr( blueGrey: Color ) : Array[ Color ] = {
//      val res = new Array[ Color ]( 6 )
//      res( 0 ) = ColorUtil.subAdjustColor( blueGrey, 0.055555582f, -0.10658931f, 0.25098038f, 0 )
//      res( 1 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.098526314f, 0.2352941f, 0 )
//      res( 2 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07333623f, 0.20392156f, 0 )
//      res( 3 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.07333623f, 0.20392156f, 0 )
//      res( 4 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )
//      res( 5 ) = ColorUtil.subAdjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )
//      res
//   }

//   private def overGrad2colr( blueGrey: Color ) : Array[ Color ] = {
//      val res = new Array[ Color ]( 6 )
//      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10658931f, -0.24313724f + 0.25098038f, 0 )
//      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.098526314f, -0.2117647f * 2 + 0.2352941f, 0 )
//      res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, -0.12941176f * 2 + 0.20392156f, 0 )
//      res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, -0.12941176f * 2 + 0.20392156f, 0 )
//      res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.24705881f * 2 + 0.25490195f, 0 )
//      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.25490195f * 2 + 0.25490195f, 0 )
//      res
//   }

   // submin enabled
//   res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10655806f, -0.14117646f, 0 )
//   res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.09823123f,  -0.17254901f, 0 )
//   res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  -0.25490195f, 0 )
//   res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  -0.25490195f, 0 )
//   res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.0749532f,   -0.1372549f,  0 )
//   res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.12941176f, 0 )

   // nimbus over
//   res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10658931f, 0.25098038f, 0 )
//   res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.098526314f, 0.2352941f, 0 )
//   res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, 0.20392156f, 0 )
//   res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, 0.20392156f, 0 )
//   res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )
//   res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )

   // nimbus enabled
//   res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10655806f, 0.24313724f, 0 )
//   res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.09823123f,  0.2117647f,  0 )
//   res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  0.12941176f, 0 )
//   res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  0.12941176f, 0 )
//   res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.0749532f,   0.24705881f, 0 )
//   res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )

   private def overGrad2colr( blueGrey: Color ) : Array[ Color ] = {
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

//      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10658931f, -0.031372547f, 0 )
//      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.098526314f, -0.109803915f, 0 )
//      res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, -0.30588233f, 0 )
//      res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, -0.30588233f, 0 )
//      res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.019607842f, 0 )
//      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, -0.0039215684f, 0 )
      res
   }

   private def pressedGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, -0.00505054f, -0.05960039f, 0.10196078f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, -0.008547008f, -0.04772438f, 0.06666666f, 0 )
      res( 2 ) = ColorUtil.adjustColor( blueGrey, -0.0027777553f, -0.0018306673f, -0.02352941f, 0 )
      res( 3 ) = ColorUtil.adjustColor( blueGrey, -0.0027777553f, -0.0018306673f, -0.02352941f, 0 )
      res( 4 ) = ColorUtil.adjustColor( blueGrey, -0.0027777553f, -0.0212406f, 0.13333333f, 0 )
      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0055555105f, -0.030845039f, 0.23921567f, 0 )
      res
   }

   def paint( state: State, c: Color, g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
//      val nimBase = SubminDefaults.baseColor
      if( state.isEnabled ) {
         val blueGrey = ColorUtil.mixColorWithAlpha( SubminDefaults.blueGreyColor, c )
         if( state.isPressed ) {
            if( state.isFocused ) {
               paintFocusedPressed( g, blueGrey, x, y, width, height )
            } else {
               paintPressed( g, blueGrey, x, y, width, height )
            }
         } else if( state.isMouseOver ) {
            if( state.isFocused ) {
               paintFocusedOver( g, blueGrey, x, y, width, height )
            } else {
               paintOver( g, blueGrey, x, y, width, height )
            }
         } else {
            if( state.isFocused ) {
               paintFocused( g, blueGrey, x, y, width, height )
            } else {
               paintEnabled( g, blueGrey, x, y, width, height )
            }
         }
       } else {
          val c2          = if( c == null ) c else ColorUtil.subAdjustColor( c, 0f, 0f, 0f, -112 )
          val blueGrey    = ColorUtil.mixColorWithAlpha( SubminDefaults.blueGreyColor, c2 )
          paintDisabled( g, blueGrey, x, y, width, height )
       }
   }

   private def createEnabledGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, enabledGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createDisabledGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, disabledGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createOverGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad1frac, overGrad1colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createPressedGradient1( colors: Array[ Color ], shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, pressedGrad1frac, colors,
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createPressedGradient2( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, pressedGrad2colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createEnabledGradient2( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, enabledGrad2colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createDisabledGradient2( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, disabledGrad2colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def createOverGradient2( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, grad2frac, overGrad2colr( blueGrey ),
                                MultipleGradientPaint.CycleMethod.NO_CYCLE )
   }

   private def paintFocusedPressed( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g, x, y, width, height )
      paintPressedTop(  g, blueGrey, focusedPressedGrad1colr( blueGrey ), x, y, width, height )
   }

   private def paintPressed( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintPressedBack( g, /* blueGrey, */ x, y, width, height )
      paintPressedTop(  g, blueGrey, pressedGrad1colr( blueGrey ), x, y, width, height )
   }

   private def paintPressedBack( g: Graphics2D, /* blueGrey: Color, */ x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + 3f
      val e1w = width - 4f
      val e1h = height - 4f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 12f, 12f )
      g.setColor( pressedBackColr )
      g.fill( rrect )
   }

   private def paintPressedTop( g: Graphics2D, blueGrey: Color, grad1Colors: Array[ Color ],
                                x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 9f, 9f )
      g.setPaint( createPressedGradient1( grad1Colors, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 7f, 7f )
      g.setPaint( createPressedGradient2( blueGrey, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintFocusedOver( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g,           x, y, width, height )
      paintOverTop(     g, blueGrey, x, y, width, height )
   }

   private def paintOver( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack( g, blueGrey, x, y, width, height )
      paintOverTop(     g, blueGrey, x, y, width, height )
   }

   private def paintFocused( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g,           x, y, width, height )
      paintEnabledTop(  g, blueGrey, x, y, width, height )
   }

   private def paintFocusedBack( g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 0.6f
      val e1y = y + 0.6f
      val e1w = width - 1.2f
      val e1h = height - 1.2f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 11f, 11f )
      g.setColor( SubminDefaults.focusColor )
      g.fill( rrect )
   }

   // outer rect width = 104, height = 33
   private def paintEnabled( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack( g, blueGrey, x, y, width, height )
      paintEnabledTop( g, blueGrey, x, y, width, height )
   }

   private def paintEnabledBack( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + 3f
      val e1w = width - 4f
      val e1h = height - 4f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 12f, 12f )
      g.setColor( ColorUtil.subAdjustColor( blueGrey, -0.027777791f, -0.06885965f, -0.36862746f, -190 ))
      g.fill( rrect )
   }

   private def paintEnabledTop( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 9f, 9f )
      g.setPaint( createEnabledGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 7f, 7f )
      g.setPaint( createEnabledGradient2( blueGrey, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintDisabled( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + 3f
      val e1w = width - 4f
      val e1h = height - 4f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 12f, 12f )
      g.setColor( ColorUtil.subAdjustColor( blueGrey, -0.027777791f, -0.06885965f, -0.36862746f, -232 ))
      g.fill( rrect )

      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 9f, 9f )
      g.setPaint( createDisabledGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 7f, 7f )
      g.setPaint( createDisabledGradient2( blueGrey, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }

   private def paintOverTop( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 9f, 9f )
      g.setPaint( createOverGradient1( blueGrey, e2x, e2y, e2w, e2h ))
      g.fill( rrect )

      val e3x = x + 3f
      val e3y = y + 3f
      val e3w = width - 6f
      val e3h = height - 6f
      rrect.setRoundRect( e3x, e3y, e3w, e3h, 7f, 7f )
      g.setPaint( createOverGradient2( blueGrey, e3x, e3y, e3w, e3h ))
      g.fill( rrect )
   }
}
