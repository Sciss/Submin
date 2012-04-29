package de.sciss.submin

import javax.swing.text.View
import javax.swing.plaf.basic.{BasicHTML, BasicButtonUI}
import sun.swing.SwingUtilities2
import java.awt.geom.RoundRectangle2D
import java.awt.{Paint, LinearGradientPaint, MultipleGradientPaint, RenderingHints, Dimension, Graphics2D, Color, FontMetrics, Rectangle, Graphics}
import javax.swing.{JButton, SwingUtilities, JComponent, AbstractButton}

/**
 * todo: doesn't paint default dialog button distinguished
 */
object ButtonUI {
   private val viewRect = new Rectangle()
   private val textRect = new Rectangle()
   private val iconRect = new Rectangle()

   private val rrect    = new RoundRectangle2D.Float()

   private val grad1frac = Array[ Float ]( 0.09f, 0.95f )

   private val pressedGrad1frac = Array[ Float ]( 0.05f, 0.95f )

   private def enabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, -0.055555522f, -0.05356429f, -0.12549019f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.0147816315f, -0.3764706f, 0 )
      res
   }

   private def disabledGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.06766917f, 0.07843137f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.06484103f, 0.027450979f, 0 )
      res
   }

   private def overGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.020974077f, -0.21960783f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, 0.11169591f, -0.53333336f, 0 )
      res
   }

   private def pressedGrad1colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 2 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, 0.8894737f, -0.7176471f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, 5.847961e-4f, -0.32156864f, 0 )
      res
   }

   private val grad2frac = Array[ Float ]( 0.0f, 0.06f, 0.6f, 0.7f, 0.95f, 1.0f )

   private def enabledGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10655806f, 0.24313724f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.09823123f,  0.2117647f,  0 )
      res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  0.12941176f, 0 )
      res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f,  0.12941176f, 0 )
      res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.0749532f,   0.24705881f, 0 )
      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )
      res
   }

   private def disabledGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.08477524f, 0.16862744f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, -0.015872955f, -0.080091536f, 0.15686274f, 0 )
      res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f, 0.12941176f, 0 )
      res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07016757f, 0.12941176f, 0 )
      res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07052632f, 0.1372549f, 0 )
      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.070878744f, 0.14509803f, 0 )
      res
   }

   private def overGrad2colr( blueGrey: Color ) : Array[ Color ] = {
      val res = new Array[ Color ]( 6 )
      res( 0 ) = ColorUtil.adjustColor( blueGrey, 0.055555582f, -0.10658931f, 0.25098038f, 0 )
      res( 1 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.098526314f, 0.2352941f, 0 )
      res( 2 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, 0.20392156f, 0 )
      res( 3 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.07333623f, 0.20392156f, 0 )
      res( 4 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )
      res( 5 ) = ColorUtil.adjustColor( blueGrey, 0.0f, -0.110526316f, 0.25490195f, 0 )
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

   private val pressedBackColr = new Color( 245, 250, 255, 160 )
}
class ButtonUI extends BasicButtonUI {
   import ButtonUI._

//   override def update( g: Graphics, c: JComponent ) {
////       paintBackground( g, c.asInstanceOf[ AbstractButton ])
//       paint( g, c )
//   }

   private def getComponentState( b: AbstractButton ) : State = {
      if( !b.isEnabled ) return State.disabled

      var state   = State.ENABLED
      val model   = b.getModel

      if( model.isPressed ) {
         if( model.isArmed ) {
            state |= State.PRESSED
         } else {
            state |= State.MOUSE_OVER
         }
      }
      if( model.isRollover ) state |= State.MOUSE_OVER
      if( model.isSelected ) state |= State.SELECTED
      if( b.isFocusOwner && b.isFocusPainted ) state |= State.FOCUSED

      b match {
         case b2: JButton if b2.isDefaultButton => state |= State.DEFAULT
         case _ =>
      }

      State( state )
   }

   override def paint( g: Graphics, c: JComponent ) {
      val b       = c.asInstanceOf[ AbstractButton ]
      val text    = layout( b, SwingUtilities2.getFontMetrics( c, g ), c.getWidth, c.getHeight )

      clearTextShiftOffset()

      val state   = getComponentState( b )
      val g2      = g.asInstanceOf[ Graphics2D ]
      g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON )
      paint( state, null, g2, viewRect.x, viewRect.y, viewRect.width, viewRect.height )

      if( b.getIcon != null ) paintIcon( g, c, iconRect )

      if( text != null && text != "" ) {
         val v = c.getClientProperty( BasicHTML.propertyKey ).asInstanceOf[ View ]
         if( v != null ) {
	         v.paint( g, textRect )
         } else {
	         paintText( g, b, textRect, text )
         }
      }
   }

   override protected def paintText( g: Graphics, b: AbstractButton, textRect: Rectangle, text: String ) {
      val model  = b.getModel
      val fm     = SwingUtilities2.getFontMetrics( b, g )
      val mnIdx  = b.getDisplayedMnemonicIndex

      g.setColor( if( model.isEnabled ) b.getForeground else NimbusDefaults.disabledTextColor )
      SwingUtilities2.drawStringUnderlineCharAt( b, g, text, mnIdx,
         textRect.x + getTextShiftOffset, textRect.y + fm.getAscent + getTextShiftOffset )
   }

   private def paint( state: State, c: Color, g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) {
      val nimBase = NimbusDefaults.baseColor
      if( state.isEnabled ) {
         val blueGrey = ColorUtil.mixColorWithAlpha( NimbusDefaults.getBlueGreyColor( nimBase ), c )
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
          val blueGrey    = ColorUtil.mixColorWithAlpha( NimbusDefaults.getBlueGreyColor( nimBase ), c2 )
          paintDisabled( g, blueGrey, x, y, width, height )
       }
   }

//   private def paintBackground( g: Graphics, c: AbstractButton ) {
//      if( c.isContentAreaFilled ) {
//         // XXX
//      }
//   }

   override def getPreferredSize( c: JComponent ) : Dimension = {
      val res = super.getPreferredSize( c )
      // XXX
      res.width  += 28
      res.height += 12
      res
   }

   private def layout( b: AbstractButton, fm: FontMetrics, width: Int, height: Int ) : String = {
      val i             = b.getInsets
      viewRect.x        = i.left
      viewRect.y        = i.top
      viewRect.width    = width - (i.right + viewRect.x)
      viewRect.height   = height - (i.bottom + viewRect.y)

      textRect.x        = 0
      textRect.y        = 0
      textRect.width    = 0
      textRect.height   = 0
      iconRect.x        = 0
      iconRect.y        = 0
      iconRect.width    = 0
      iconRect.height   = 0

      // layout the text and icon
      SwingUtilities.layoutCompoundLabel(
         b, fm, b.getText, b.getIcon, b.getVerticalAlignment, b.getHorizontalAlignment,
         b.getVerticalTextPosition, b.getHorizontalTextPosition, viewRect, iconRect, textRect,
         if( b.getText == null ) 0 else b.getIconTextGap )
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

   private def createPressedGradient1( blueGrey: Color, shpX1: Float, shpY1: Float, shpW: Float, shpH: Float ) : Paint = {
       val startX = shpX1 + 0.5f * shpW
       val startY = shpY1
       val endX   = shpX1 + 0.5f * shpW
       val endY   = shpY1 + shpH
       new LinearGradientPaint( startX, startY, endX, endY, pressedGrad1frac, pressedGrad1colr( blueGrey ),
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
      paintFocusedBack( g,           x, y, width, height )
      paintPressedTop(  g, blueGrey, x, y, width, height )
   }

   private def paintPressed( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      paintPressedBack( g, /* blueGrey, */ x, y, width, height )
      paintPressedTop(  g, blueGrey, x, y, width, height )
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

   private def paintPressedTop( g: Graphics2D, blueGrey: Color, x: Int, y: Int, width: Int, height: Int ) {
      val e2x = x + 2f
      val e2y = y + 2f
      val e2w = width - 4f
      val e2h = height - 4f
      rrect.setRoundRect( e2x, e2y, e2w, e2h, 9f, 9f )
      g.setPaint( createPressedGradient1( blueGrey, e2x, e2y, e2w, e2h ))
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
      g.setColor( NimbusDefaults.focusColor )
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
      g.setColor( ColorUtil.adjustColor( blueGrey, -0.027777791f, -0.06885965f, -0.36862746f, -190 ))
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
      g.setColor( ColorUtil.adjustColor( blueGrey, -0.027777791f, -0.06885965f, -0.36862746f, -232 ))
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