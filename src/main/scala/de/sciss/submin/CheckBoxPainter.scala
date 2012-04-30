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

   protected def enabledGrad1colr(           blueGrey: Color ) : Array[ Color ]
   protected def disabledGrad1colr(          blueGrey: Color ) : Array[ Color ]
   protected def disabledSelectedGrad1colr(  blueGrey: Color ) : Array[ Color ]
   protected def pressedGrad1colr(           blueGrey: Color ) : Array[ Color ]
   protected def overGrad1colr(              blueGrey: Color ) : Array[ Color ]
   protected def pressedSelectedGrad1colr(   base: Color )     : Array[ Color ]
   protected def overSelectedGrad1colr(      base: Color )     : Array[ Color ]
   protected def selectedGrad1colr(          base: Color )     : Array[ Color ]
   protected def enabledGrad2colr(           base: Color )     : Array[ Color ]
   protected def disabledGrad2colr(          base: Color )     : Array[ Color ]
   protected def disabledSelectedGrad2colr(  base: Color )     : Array[ Color ]
   protected def pressedGrad2colr(           base: Color )     : Array[ Color ]
   protected def pressedSelectedGrad2colr(   base: Color )     : Array[ Color ]
   protected def overGrad2colr(              base: Color )     : Array[ Color ]
   protected def overSelectedGrad2colr(      base: Color )     : Array[ Color ]
   protected def selectedGrad2colr(          base: Color )     : Array[ Color ]

   protected def enabledBackColor(           blueGrey: Color ) : Color
   protected def selectedCheckColor(         base: Color )     : Color
   protected def disabledSelectedCheckColor( base: Color )     : Color
   protected def pressedSelectedBackColor(   blueGrey: Color ) : Color

   private val rrect = new RoundRectangle2D.Float()
   private val path  = new GeneralPath()

   private val grad1frac = Array[ Float ]( 0.0f, 1.0f )
   private val grad2frac = Array[ Float ]( 0.0f, 0.64457834f, 1.0f )
   private val pressedSelectedGrad2frac = Array[ Float ]( 0.0f, 0.11550152f, 0.64457834f, 1.0f )

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
      g.setColor( enabledBackColor( blueGrey ))
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
      g.setColor( selectedCheckColor( base ))
      paintSelectedCheckShape( g, x, y, width, height )
   }

   private def paintDisabledSelectedCheck( g: Graphics2D, base: Color, x: Int, y: Int, width: Int, height: Int ) {
      g.setColor( disabledSelectedCheckColor( base ))
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
      g.setColor( pressedSelectedBackColor( blueGrey ))
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