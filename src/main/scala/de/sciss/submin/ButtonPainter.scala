package de.sciss.submin

import java.awt.geom.RoundRectangle2D
import java.awt.{MultipleGradientPaint, LinearGradientPaint, Paint, Color, Graphics2D}

trait ButtonPainter extends ButtonPainterLike {
   protected def grad1frac : Array[ Float ]

   protected def enabledGrad1colr(  blueGrey: Color ) : Array[ Color ]
   protected def disabledGrad1colr( blueGrey: Color ) : Array[ Color ]
   protected def overGrad1colr(     blueGrey: Color ) : Array[ Color ]
   protected def enabledGrad2colr(  blueGrey: Color ) : Array[ Color ]
   protected def disabledGrad2colr( blueGrey: Color ) : Array[ Color ]
   protected def overGrad2colr(     blueGrey: Color ) : Array[ Color ]
   protected def pressedGrad1colr(  blueGrey: Color ) : Array[ Color ]
   protected def disabledBackColor( blueGrey: Color ) : Color
   protected def enabledBackColor(  blueGrey: Color ) : Color
   protected def pressedBackColor : Color

   protected def enabledBackYOffset : Int
   protected def pressedBackYOffset : Int

   protected def defaults: Defaults

   private val rrect = new RoundRectangle2D.Float()

   private val pressedGrad1frac = Array[ Float ]( 0.05f, 0.95f )

   private val grad2frac = Array[ Float ]( 0.0f, 0.06f, 0.6f, 0.7f, 0.95f, 1.0f )

   private def pressedGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, -0.00505054f,   -0.05960039f,    0.10196078f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, -0.008547008f,  -0.04772438f,    0.06666666f, 0 )
      res( 2 ) = ColorUtil.adjustColor( blueGrey, -0.0027777553f, -0.0018306673f, -0.02352941f, 0 )
      res( 3 ) = ColorUtil.adjustColor( blueGrey, -0.0027777553f, -0.0018306673f, -0.02352941f, 0 )
      res( 4 ) = ColorUtil.adjustColor( blueGrey, -0.0027777553f, -0.0212406f,     0.13333333f, 0 )
      res( 5 ) = ColorUtil.adjustColor( blueGrey,  0.0055555105f, -0.030845039f,   0.23921567f, 0 )
      res
   }

   final protected def focusedPressedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, 0.8894737f, -0.7176471f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, 5.847961e-4f, -0.32156864f, 0 )
      res
   }

   final def paint( state: State, c: Color, g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
      if( state.isEnabled ) {
         val blueGrey = ColorUtil.mixColorWithAlpha( defaults.blueGreyColor, c )
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
          val c2          = if( c == null ) c else ColorUtil.adjustColor( c, 0f, 0f, 0f, -112 )
          val blueGrey    = ColorUtil.mixColorWithAlpha( defaults.blueGreyColor, c2 )
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

   private def paintEnabled( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack( g, blueGrey, x, y, width, height )
      paintEnabledTop( g, blueGrey, x, y, width, height )
   }

   private def paintEnabledBack( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + enabledBackYOffset
      val e1w = width - 4f
      val e1h = height - 4f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 12f, 12f )
      g.setColor( enabledBackColor( blueGrey ))
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
      g.setColor( defaults.focusColor )
      g.fill( rrect )
   }

   private def paintOver( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintEnabledBack( g, blueGrey, x, y, width, height )
      paintOverTop(     g, blueGrey, x, y, width, height )
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

   private def paintFocusedOver( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g,           x, y, width, height )
      paintOverTop(     g, blueGrey, x, y, width, height )
   }

   private def paintPressed( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintPressedBack( g,                                         x, y, width, height )
      paintPressedTop(  g, blueGrey, pressedGrad1colr( blueGrey ), x, y, width, height )
   }

   private def paintPressedBack( g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + pressedBackYOffset
      val e1w = width - 4f
      val e1h = height - 4f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 12f, 12f )
      g.setColor( pressedBackColor )
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

   private def paintFocusedPressed( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintFocusedBack( g, x, y, width, height )
      paintPressedTop(  g, blueGrey, focusedPressedGrad1colr( blueGrey ), x, y, width, height )
   }

   private def paintDisabled( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e1x = x + 2f
      val e1y = y + 3f
      val e1w = width - 4f
      val e1h = height - 4f
      rrect.setRoundRect( e1x, e1y, e1w, e1h, 12f, 12f )
      g.setColor( disabledBackColor( blueGrey ))
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
}