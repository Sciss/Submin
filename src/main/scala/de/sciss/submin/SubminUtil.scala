/*
 *  SubminHelper.scala
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

import javax.swing.{UnsupportedLookAndFeelException, UIManager, JComponent}
import annotation.tailrec
import java.awt.{Insets, Font, Container}
import javax.swing.plaf.InsetsUIResource

object SubminUtil {
   private val LARGE_SCALE = 1.15
   private val SMALL_SCALE = 0.857
   private val MINI_SCALE  = 0.714

   def getBoolean( c: JComponent, property: String, default: Boolean = false ) : Boolean =
      c.getClientProperty( property ) match {
         case java.lang.Boolean.TRUE   => true
         case java.lang.Boolean.FALSE  => false
         case _                        => default
      }

   @tailrec private def findJComponentAncestor( child: Container ) : JComponent = {
      if( child == null ) return null
      child.getParent match {
         case jp: JComponent => jp
         case p => findJComponentAncestor( p )
      }
   }

   @tailrec def getClosestBoolean( c: JComponent, property: String, default: Boolean = false ) : Boolean =
      c.getClientProperty( property ) match {
         case java.lang.Boolean.TRUE   => true
         case java.lang.Boolean.FALSE  => false
         case _ =>
            val p = findJComponentAncestor( c )
            if( p == null ) default else getClosestBoolean( p, property, default )
      }

   def getDefaultFont( c: JComponent, defaultFontName: String ) : Font = {
      val f = UIManager.getFont( defaultFontName ) // "defaultFont"

      c.getClientProperty( "JComponent.sizeVariant" ) match {
         case "large" => f.deriveFont( math.round( f.getSize2D * LARGE_SCALE ))
         case "small" => f.deriveFont( math.round( f.getSize2D * SMALL_SCALE ))
         case "mini"  => f.deriveFont( math.round( f.getSize2D * MINI_SCALE  ))
         case _       => f
      }
   }

   def getInsets( c: JComponent, in: Insets, defaultMarginsName: String ) : Insets = {
      val in1 = if( in != null && in.isInstanceOf[ InsetsUIResource ]) in else new InsetsUIResource( 0, 0, 0, 0 )

      val m = UIManager.getInsets( defaultMarginsName ) // pp + "contentMargins"
      if( m == null ) {
         in1.top     = 0
         in1.left    = 0
         in1.bottom  = 0
         in1.right   = 0
      } else {
         c.getClientProperty( "JComponent.sizeVariant" ) match {
            case "large" =>
               in1.top     = (m.top    * LARGE_SCALE).toInt
               in1.left    = (m.left   * LARGE_SCALE).toInt
               in1.bottom  = (m.bottom * LARGE_SCALE).toInt
               in1.right   = (m.right  * LARGE_SCALE).toInt
            case "small" =>
               in1.top     = (m.top    * SMALL_SCALE).toInt
               in1.left    = (m.left   * SMALL_SCALE).toInt
               in1.bottom  = (m.bottom * SMALL_SCALE).toInt
               in1.right   = (m.right  * SMALL_SCALE).toInt
            case "mini"  =>
               in1.top     = (m.top    * MINI_SCALE).toInt
               in1.left    = (m.left   * MINI_SCALE).toInt
               in1.bottom  = (m.bottom * MINI_SCALE).toInt
               in1.right   = (m.right  * MINI_SCALE).toInt
            case _ =>
               in1.top     = m.top
               in1.left    = m.left
               in1.bottom  = m.bottom
               in1.right   = m.right
         }
      }
      in1
   }

   def initx( force: Boolean = false ) {
      if( force ) try {
println( "AQUI" )
         val current = UIManager.getLookAndFeel
         if( current.getName.toLowerCase != "nimbus" ) {
            val infos = UIManager.getInstalledLookAndFeels
            for( i <- 0 until infos.length ) {
               if( infos( i ).getName.toLowerCase == "nimbus" ) {
                  UIManager.setLookAndFeel( infos( i ).getClassName )
               }
            }
         }
      } catch {
         case e1: UnsupportedLookAndFeelException  => e1.printStackTrace()
         case e1: ClassNotFoundException           => e1.printStackTrace()
         case e1: InstantiationException           => e1.printStackTrace()
         case e1: IllegalAccessException           => e1.printStackTrace()
      }

      val defaults = UIManager.getDefaults
      defaults.putAll( SubminDefaults.map )
   }
}
