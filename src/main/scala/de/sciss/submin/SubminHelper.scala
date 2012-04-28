package de.sciss.submin

import javax.swing.{UnsupportedLookAndFeelException, UIManager, JComponent}
import javax.swing.plaf.ColorUIResource
import java.awt.Color


object SubminHelper {
   def getBoolean( c: JComponent, property: String, default: Boolean = false ) : Boolean =
      c.getClientProperty( property ) match {
         case java.lang.Boolean.TRUE   => true
         case java.lang.Boolean.FALSE  => false
         case _                        => default
      }

   def setLookAndFeel() {
      try {
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
      defaults.put( "Panel[submin].background", new ColorUIResource( SubminDefaults.backgroundColor ))
      defaults.put( "Panel[submin].foreground", new ColorUIResource( Color.yellow ))
   }
}
