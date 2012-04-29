package de.sciss.submin

import javax.swing.plaf.basic.BasicTextAreaUI
import javax.swing.plaf.ComponentUI
import java.awt.{RenderingHints, Graphics2D, Graphics}
import javax.swing.{JTextArea, JComponent}

object SubminTextAreaUI {
   private val instance = new SubminTextAreaUI
   def createUI( c: JComponent ) : ComponentUI = instance
}
final class SubminTextAreaUI extends BasicTextAreaUI with SubminUI[ JTextArea ] {
   protected def propertyPrefix = "Label."

   override protected def paintSafely( g: Graphics ) {
      val g2 = g.asInstanceOf[ Graphics2D ]
      g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON )
      super.paintSafely( g )
   }

   override protected def installListeners() {
      super.installListeners()
      installPropertyListener( getComponent )
   }

   override protected def uninstallListeners() {
      super.uninstallListeners()
      uninstallPropertyListener( getComponent )
   }
}