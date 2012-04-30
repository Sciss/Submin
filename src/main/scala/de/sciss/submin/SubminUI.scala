/*
 *  SubminUI.scala
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

import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import javax.swing.{LookAndFeel, JComponent}
import javax.swing.plaf.UIResource

trait SubminUI[ C <: JComponent ] {
   private val prop : PropertyChangeListener = new PropertyChangeListener {
      def propertyChange( e: PropertyChangeEvent ) {
         e.getPropertyName match {
            case "JComponent.sizeVariant" =>
               val b = e.getSource.asInstanceOf[ C ]
               updateSizeVariant( b )
            case "submin" =>
               val c = e.getSource.asInstanceOf[ JComponent ]
               updateColors( c )
//               propagateUpdateColors( c )
            case "ancestor" =>
               val a = e.getNewValue
               if( a != null ) { // the component has been added to a parent which is made visible
                  val c = e.getSource.asInstanceOf[ JComponent ]
                  // therefore, scan again for the `submin` property. if it is true
                  // for any parent which is not `c`, we need to update the colors
                  SubminUtil.getClosestBooleanSource( c, "submin" ) match {
                     case Some( (a2, true) ) if a2 != c => updateColors( c )
                     case _ =>
                  }
               }
            case _ =>
         }
      }
   }

//   private val anc : AncestorListener = new AncestorListener {
//      def ancestorAdded( e: AncestorEvent ) {
//      }
//
//      def ancestorRemoved( e: AncestorEvent ) {
//      }
//
//      def ancestorMoved( e: AncestorEvent ) {
//      }
//   }

//   private def propagateUpdateColors( c: JComponent ) {
//      val num = c.getComponentCount
//println( "has " + num + " children" )
//      var i = 0; while( i < num ) {
//         c.getComponent( i ) match {
//            case jc: JComponent =>
//println( "found child " + jc )
//               try {
//                  val m = jc.getClass.getMethod( "getUI" )
//println( "found method" )
//                  m.invoke( jc ) match {
//                     case sui: SubminUI[ _ ] =>
//                        sui.updateColors( jc )
//                        propagateUpdateColors( jc )
//                  }
//               } catch {
//                  case _: NoSuchMethodException =>
//                  case _: SecurityException =>
//               }
//            case _ =>
//         }
//      i += 1 }
//   }

//   final protected def getSubmin( c: JComponent ) : Boolean = SubminUtil.getClosestBoolean( c, "submin" )

   protected def propertyPrefix : String

   final protected def getSubminPropertyPrefix( c: JComponent ) : String = {
      val pp = propertyPrefix
      if( SubminUtil.getSubmin( c )) pp.substring( 0, pp.length - 1 ) + "[submin]." else pp
   }

   final protected def updateColors( c: JComponent ) {
      val pps = getSubminPropertyPrefix( c )
      LookAndFeel.installColors( c, pps + "background", pps + "foreground" )
   }

   protected def updateSizeVariant( c: C ) {
      val f = c.getFont
      if( f == null || f.isInstanceOf[ UIResource ]) {
         c.setFont( SubminUtil.getDefaultFont( c, propertyPrefix + "font" ))
      }
   }

   final protected def installPropertyListener( c: JComponent ) {
      c.addPropertyChangeListener( prop )
   }

   final protected def uninstallPropertyListener( c: JComponent ) {
      c.removePropertyChangeListener( prop )
   }
}