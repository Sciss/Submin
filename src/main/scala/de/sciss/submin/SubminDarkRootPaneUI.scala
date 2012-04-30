package de.sciss.submin

import javax.swing.plaf.basic.BasicRootPaneUI
import javax.swing.plaf.ComponentUI
import javax.swing.{JRootPane, JComponent}

object SubminDarkRootPaneUI {
   private val instance = new SubminDarkRootPaneUI
   def createUI( c: JComponent ) : ComponentUI = instance
}
final class SubminDarkRootPaneUI extends BasicRootPaneUI {
   override protected def installDefaults( rp: JRootPane ) {
      super.installDefaults( rp )
      rp.putClientProperty( "submin", true )
   }
}