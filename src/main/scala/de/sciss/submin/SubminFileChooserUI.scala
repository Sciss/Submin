package de.sciss.submin

import javax.swing.plaf.basic.BasicFileChooserUI
import javax.swing.plaf.ComponentUI
import javax.swing.{JFileChooser, JComponent}

object SubminFileChooserUI {
   def createUI( c: JComponent ) : ComponentUI = new SubminFileChooserUI( c.asInstanceOf[ JFileChooser ])
}
final class SubminFileChooserUI( c: JFileChooser ) extends BasicFileChooserUI( c ) {

}
