package de.sciss.submin

import org.jdesktop.laffy.Laffy
import java.awt.EventQueue

object LaffyDemo extends Runnable {
   def main( args: Array[ String ]) {
      EventQueue.invokeLater( this )
   }

   def run() {
      Submin.init()
//      UIManager.installLookAndFeel( "Dorian", "de.sciss.dorianlaf.DorianLookAndFeel" )
//      Laffy.getInstance().load( Array.empty[ AnyRef ])

      // "invokeAndWait cannot be called from event thread"... sucky bastards
      new Thread( new Runnable { def run() { Laffy.main( Array.empty[ String ])}}).start()
   }
}
