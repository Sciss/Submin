/*
 *  SubminLookAndFeel.scala
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

import javax.swing.plaf.basic.BasicLookAndFeel
import javax.swing.UIDefaults

object SubminLookAndFeel {
   private val packageName = "de.sciss.submin."
   val name                = "Submin"
   val className           = classOf[ SubminLookAndFeel ].getName
}
class SubminLookAndFeel extends BasicLookAndFeel {
   import SubminLookAndFeel._

   def getName                = name
   def getID                  = name
   def getDescription         = "Submin Look and Feel"
   def isNativeLookAndFeel    = false
   def isSupportedLookAndFeel = true

   override protected def initClassDefaults( table: UIDefaults ) {
      super.initClassDefaults( table )

      val uiDefaults = Array[ AnyRef ](
         "ButtonUI",       packageName + "SubminButtonUI",
         "FileChooserUI",  packageName + "SubminFileChooserUI",    // laffy needs it...
         "LabelUI",        packageName + "SubminLabelUI",
         "PanelUI",        packageName + "SubminPanelUI",
         "TextAreaUI",     packageName + "SubminTextAreaUI"
//         "ToggleButtonUI", packageName + "ButtonUI",
//         "CheckBoxUI",     packageName + "CheckBoxUI",
//         "SliderUI",       packageName + "SliderUI",
//         "ListUI",         packageName + "ListUI",
//         "ProgressBarUI",  packageName + "ProgressBarUI"
      )
      table.putDefaults( uiDefaults )
   }

   override protected def initComponentDefaults( table: UIDefaults ) {
      super.initComponentDefaults( table )
      table.remove( "Button.border" )
      table.putAll( NimbusDefaults.map )
      table.putAll( SubminDefaults.map )
   }
}
