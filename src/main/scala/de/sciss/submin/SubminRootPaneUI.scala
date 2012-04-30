package de.sciss.submin

import javax.swing.plaf.basic.BasicRootPaneUI
import javax.swing.JComponent
import javax.swing.plaf.ComponentUI

object SubminRootPaneUI {
   private val instance = new SubminRootPaneUI
   def createUI( c: JComponent ) : ComponentUI = instance
}
final class SubminRootPaneUI extends BasicRootPaneUI {

}
