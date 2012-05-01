package de.sciss.submin

import javax.swing.plaf.basic.BasicProgressBarUI
import javax.swing.plaf.ComponentUI
import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import javax.swing.{SwingUtilities, SwingConstants, JComponent, JProgressBar}
import sun.swing.SwingUtilities2
import java.awt.geom.AffineTransform
import java.awt.{Color, Graphics2D, Rectangle, Graphics}

object SubminProgressBarUI {
   private val instance = new SubminProgressBarUI
   def createUI( c: JComponent ) : ComponentUI = instance

   private val rotLTR   = AffineTransform.getRotateInstance( -math.Pi / 2 )
   private val rotRTL   = AffineTransform.getRotateInstance(  math.Pi / 2 )
}
final class SubminProgressBarUI extends BasicProgressBarUI with SubminUI[ JProgressBar ] {
   import SubminProgressBarUI._

   protected val propertyPrefix = "ProgressBar."

//   private val prop : PropertyChangeListener = new PropertyChangeListener {
//      def propertyChange( e: PropertyChangeEvent ) {
//
//      }
//   }
//
//   override protected def installListeners() {
//      super.installListeners()
//      progressBar.addPropertyChangeListener( "indeterminate", prop )
//   }
//
//   override protected def uninstallListeners() {
//      super.uninstallListeners()
//      progressBar.removePropertyChangeListener( "indeterminate", prop )
//   }

   override def paint( g: Graphics, c: JComponent ) {
      val b       = c.asInstanceOf[ JProgressBar ]
      val g2      = g.asInstanceOf[ Graphics2D ]

      var x       = 0
      var y       = 0
      var width   = 0
      var height  = 0

      val orient  = b.getOrientation
      val horiz   = orient == SwingConstants.HORIZONTAL

      if( b.isIndeterminate ) {
         boxRect  = getBox( boxRect )
         x        = boxRect.x + 3
         y        = boxRect.y + 3
         width    = boxRect.width - 6
         height   = boxRect.height - 6

         val tileWidth  = 27
         val phase      = getAnimationIndex.toDouble / getFrameCount
         val offset     = (tileWidth * phase).toInt
         val clipOrig   = g.getClip
         g.clipRect( x, y, width, height )
         if( horiz ) {
            var xi = x - tileWidth + offset
            while( xi <= width ) {
               paintProgressBarForeground( g2, xi, y, tileWidth, height, orient )
               xi += tileWidth
            }
         } else {
            var yi   = y - offset
            val h    = height + tileWidth
            while( yi <= h ) {
               paintProgressBarForeground( g2, x, yi, width, tileWidth, orient )
               yi += tileWidth
             }
         }
         g.setClip( clipOrig )

      } else {
         val in   = b.getInsets
         val perc = b.getPercentComplete
         if( perc != 0.0 ) {
            if( horiz ) {
               y        = in.top
               val w0   = b.getWidth
               width    = (w0 * perc).toInt - (in.left + in.right)
               height   = b.getHeight - (in.top + in.bottom)
               x        = if( b.getComponentOrientation.isLeftToRight ) w0 - (in.right + width) else in.left
            } else {
               x        = in.left
               width    = b.getWidth - (in.left + in.right)
               val h0   = b.getHeight
               height   = (h0 * perc).toInt - (in.top + in.bottom)
               y        = h0 - (in.bottom + height)
            }

            paintProgressBarForeground( g2, x, y, width, height, orient )
         }
      }

      if( b.isStringPainted ) {
         val str = b.getString
         if( str != null && str != "" ) paintText( c, g2, str, horiz )
      }
   }

   override protected def getBox( r: Rectangle ) : Rectangle = SwingUtilities.calculateInnerArea( progressBar, r )

   override protected def setAnimationIndex( newValue: Int ) {
      if( getAnimationIndex != newValue ) {
        super.setAnimationIndex( newValue )
        progressBar.repaint()
      }
   }

   override def update( g: Graphics, c: JComponent ) {
      paintProgressBarBackground( g.asInstanceOf[ Graphics2D ], 0, 0, c.getWidth, c.getHeight, progressBar.getOrientation )
      paint( g, c )
   }

   private def paintText( c: JComponent, g: Graphics2D, text: String, horiz: Boolean ) {
      var font    = c.getFont
      val fm      = SwingUtilities2.getFontMetrics( c, g, font )
      val strLen  = SwingUtilities2.stringWidth( c, fm, text )
      var x       = 0
      var y       = 0
      val width   = c.getWidth
      val height  = c.getHeight

      if( horiz ) {
         y = (height - (fm.getAscent + fm.getDescent)) >> 1
         // if bar is not tall enough for the font, do not paint string
         if( y < 0 ) return
         x = (width - strLen) >> 1

      } else {
         if( progressBar.getComponentOrientation.isLeftToRight ) {
            font     = font.deriveFont( rotLTR )
            x        = (width + fm.getAscent - fm.getDescent) >> 1
            y        = (height + strLen) >> 1

         } else {
            font     = font.deriveFont( rotRTL )
            x        = (width - fm.getAscent + fm.getDescent ) >> 1
            y        = (height - strLen) >> 1
         }

         // if bar is not wide enough for the font, do not paint string
         if( x < 0 ) return
      }

      val colr = c.getForeground // XXX TODO account for disabled
      g.setColor( colr )
      g.setFont( font )
      SwingUtilities2.drawStringUnderlineCharAt( c, g, text, -1, x, y + fm.getAscent )
   }

   private def paintProgressBarBackground( g: Graphics2D, x: Int, y: Int, width: Int, height: Int, orient: Int ) {
      g.setColor( Color.blue )
      g.fillRect( x, y, width, height )
   }

   private def paintProgressBarForeground( g: Graphics2D, x: Int, y: Int, width: Int, height: Int, orient: Int ) {
      g.setColor( Color.yellow )
      g.fillRect( x, y, width, height )
   }
}