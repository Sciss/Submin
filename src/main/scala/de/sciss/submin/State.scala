package de.sciss.submin

object State {
   val ENABLED    = 0x01
   val MOUSE_OVER = 0x02
   val PRESSED    = 0x04
   val FOCUSED    = 0x08
   val SELECTED   = 0x10
   val DEFAULT    = 0x20

   val disabled : State       = new Impl( 0 )
//   val enabled : State        = new Impl( ENABLED )
//   val pressed : State        = new Impl( ENABLED | PRESSED )
//   val focused : State        = new Impl( ENABLED | FOCUSED )
//   val focusedPressed : State = new Impl( ENABLED | FOCUSED | PRESSED )
//   val over: State            = new Impl( ENABLED | OVER )
//   val focusedOver: State     = new Impl( ENABLED | FOCUSED | OVER )

   def apply( mask: Int ) : State = new Impl( mask )

   private final class Impl( mask: Int ) extends State {
      def isEnabled  : Boolean = (mask & ENABLED)      != 0
      def isFocused  : Boolean = (mask & FOCUSED)      != 0
      def isPressed  : Boolean = (mask & PRESSED)      != 0
      def isMouseOver     : Boolean = (mask & MOUSE_OVER)   != 0
      def isSelected : Boolean = (mask & SELECTED)     != 0
      def isDefault  : Boolean = (mask & DEFAULT)      != 0

      override def toString = {
         val s = if( isEnabled ) {
            if( isPressed ) {
               if( isFocused ) "focused+pressed" else "pressed"
            } else if( isMouseOver ) {
               if( isFocused ) "focused+over" else "over"
            } else if( isSelected ) {
               if( isFocused ) "focused+selected" else "selected"
            } else {
               if( isFocused ) "focused" else "enabled"
            }
         } else "disabled"
         if( isDefault ) s + "+default" else s
      }
   }
}
sealed trait State {
   def isEnabled     : Boolean
   def isFocused     : Boolean
   def isPressed     : Boolean
   def isMouseOver   : Boolean
   def isSelected    : Boolean
   def isDefault     : Boolean
}
