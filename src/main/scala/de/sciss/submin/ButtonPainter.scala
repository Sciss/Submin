package de.sciss.submin

import java.awt.{Color, Graphics2D}

trait ButtonPainter {
   def paint( state: State, c: Color, g: Graphics2D, x: Int, y: Int, width: Int, height: Int ) : Unit
}