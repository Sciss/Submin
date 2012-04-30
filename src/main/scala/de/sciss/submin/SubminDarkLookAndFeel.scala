package de.sciss.submin

object SubminDarkLookAndFeel {
   val name                = "SubminDark"
   val className           = classOf[ SubminDarkLookAndFeel ].getName
}
class SubminDarkLookAndFeel extends SubminLookAndFeel {
   import SubminDarkLookAndFeel._
   override def getName                = name
   override def getID                  = name
   override def getDescription         = "Submin Dark Look and Feel"

   override protected def rootPaneUI : String   = "SubminDarkRootPaneUI"
}
