package de.sciss.submin

import org.jdesktop.laffy.Laffy
import java.awt.EventQueue
import javax.swing.UIManager

object LaffyDemo extends Runnable {
   def main( args: Array[ String ]) {
      EventQueue.invokeLater( this )
   }

   def run() {
      Submin.init()
      UIManager.installLookAndFeel( SubminDarkLookAndFeel.name, SubminDarkLookAndFeel.className )

      // "invokeAndWait cannot be called from event thread"...
      new Thread( new Runnable { def run() { Laffy.main( Array.empty[ String ])}}).start()
   }
}
