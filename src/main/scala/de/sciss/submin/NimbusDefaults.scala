/*
 *  NimbusDefaults.scala
 *  (Submin)
 *
 *  Copyright (c) 2012 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.submin

import javax.swing.text.DefaultEditorKit
import java.awt.{Font, Color}
import javax.swing.{JTextField, UIDefaults, UIManager}
import javax.swing.plaf.{IconUIResource, FontUIResource, InsetsUIResource, ColorUIResource}

object NimbusDefaults {
   private val defaultControlColor              = new Color( 214, 217, 223, 255 )
   private val defaultFocusColor                = new Color( 115, 164, 209, 255 )
   private val defaultBaseColor                 = new Color(  51,  98, 140, 255 )
   private val defaultTextColor                 = Color.black
   private val defaultSelectedTextColor         = Color.white
   private val defaultDisabledTextColor         = new Color( 142, 143, 145, 255 )
   private val defaultControlHighlightColor     = new Color( 233, 236, 242, 255 )
   private val defaultSelectionBackgroundColor  = new Color(  57, 105, 138, 255 )

   private val defaultBackgroundColor           = defaultControlColor
   private val defaultButtonForegroundColor     = defaultTextColor
   private val defaultPanelForegroundColor      = defaultTextColor

   private val defaultButtonBackgroundColor     = defaultBackgroundColor
   private val defaultPanelBackgroundColor      = defaultBackgroundColor

   private val nimbusDefaults : UIDefaults = {
      val current = UIManager.getLookAndFeel
      if( current.getName.toLowerCase == "nimbus" ) current.getDefaults else null
   }

   private[submin] def map : UIDefaults = {
      val m = new UIDefaults()

      val zeroInsets = new InsetsUIResource( 0, 0, 0, 0 )
      val fntSans12  = new FontUIResource( "SansSerif", Font.PLAIN, 12 )

      m.put( "control",                   new ColorUIResource( controlColor ))
      m.put( "nimbusBase",                new ColorUIResource( baseColor ))
      m.put( "nimbusDisabledText",        new ColorUIResource( disabledTextColor ))
      m.put( "nimbusFocus",               new ColorUIResource( focusColor ))
      m.put( "nimbusSelectedText",        new ColorUIResource( selectedTextColor ))
      m.put( "nimbusSelectionBackground", new ColorUIResource( selectionBackgroundColor ))
      m.put( "text",                      new ColorUIResource( textColor ))

      m.put( "background",                new ColorUIResource( backgroundColor ))
      m.put( "controlHighlight",          new ColorUIResource( controlHighlightColor ))

      m.put( "Button.background",         new ColorUIResource( buttonBackgroundColor ))
      m.put( "Button.contentMargins",     new InsetsUIResource( 6, 14, 6, 14 ))
      m.put( "Button.font",               fntSans12 )
      m.put( "Button.foreground",         new ColorUIResource( buttonForegroundColor ))

      m.put( "CheckBox.contentMargins",   zeroInsets )
      m.put( "CheckBox.font",             fntSans12 )
      m.put( "CheckBox.icon",             new IconUIResource( SubminCheckBoxIcon ))

      m.put( "Label.contentMargins",      zeroInsets )
      m.put( "Label.font",                fntSans12 )

      m.put( "Panel.background",          new ColorUIResource( panelBackgroundColor ))
      m.put( "Panel.contentMargins",      zeroInsets )
      m.put( "Panel.font",                fntSans12 )
      m.put( "Panel.opaque",              true )

      // ---- keyboard actions ----
      val isMac   = sys.props( "os.name" ).toLowerCase.contains( "mac" )
      val mod1    = if( isMac ) "meta " else "control "
      val mod2    = if( isMac ) "alt "  else "control "
      val mod3    = if( isMac ) "" else "control "

      // like synth look and feel, but mac aware modifiers
      val itTextShare0 = IndexedSeq[ AnyRef ](
         // cut and paste
         mod1 + "X",          DefaultEditorKit.cutAction,
         mod1 + "C",          DefaultEditorKit.copyAction,
         mod1 + "V",          DefaultEditorKit.pasteAction,
         "COPY",              DefaultEditorKit.copyAction,
         "CUT",               DefaultEditorKit.cutAction,
         "PASTE",             DefaultEditorKit.pasteAction,
         "control INSERT",    DefaultEditorKit.copyAction,
         "shift INSERT",      DefaultEditorKit.pasteAction,
         "shift DELETE",      DefaultEditorKit.cutAction,

         // cursor motion
         "LEFT",              DefaultEditorKit.backwardAction,
         "RIGHT",             DefaultEditorKit.forwardAction,
         "KP_LEFT",           DefaultEditorKit.backwardAction,
         "KP_RIGHT",          DefaultEditorKit.forwardAction,
         mod2 + "LEFT",       DefaultEditorKit.previousWordAction,
         mod2 + "RIGHT",      DefaultEditorKit.nextWordAction,
         (if( isMac ) "meta LEFT" else "HOME"), DefaultEditorKit.beginLineAction,
         (if( isMac ) "meta RIGHT" else "END"), DefaultEditorKit.endLineAction,
         "control B",         DefaultEditorKit.backwardAction,
         "control F",         DefaultEditorKit.forwardAction,

         // selections
         "shift LEFT",        DefaultEditorKit.selectionBackwardAction,
         "shift RIGHT",       DefaultEditorKit.selectionForwardAction,
         mod2 + "shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
         mod2 + "shift RIGHT", DefaultEditorKit.selectionNextWordAction,
         mod1 + " A",         DefaultEditorKit.selectAllAction,
         (if( isMac ) "meta shift LEFT" else "shift HOME"), DefaultEditorKit.selectionBeginLineAction,
         (if( isMac ) "meta shift RIGHT" else "shift END"), DefaultEditorKit.selectionEndLineAction,
         "control BACK_SLASH", "unselect" /* DefaultEditorKit.unselectAction */,
         "control shift B",   DefaultEditorKit.selectionBackwardAction,
         "control shift F",   DefaultEditorKit.selectionForwardAction,

         // deletions
         "BACK_SPACE",        DefaultEditorKit.deletePrevCharAction,
         "shift BACK_SPACE",  DefaultEditorKit.deletePrevCharAction,
         "control H",         DefaultEditorKit.deletePrevCharAction,
         "control D",         DefaultEditorKit.deleteNextCharAction,
         "DELETE",            DefaultEditorKit.deleteNextCharAction,
         "control DELETE",    DefaultEditorKit.deleteNextWordAction,
         "control BACK_SPACE",DefaultEditorKit.deletePrevWordAction,

         // misc
         "control shift O",   "toggle-componentOrientation" /* DefaultEditorKit.toggleComponentOrientation */
      )

      // todo: mac: meta DELETE = deleteToBeginLine, ctrl K = deleteToEndLine,
      // ctrl O = line break but no cursor motion, ctrl T = take character to the right,
      // ctrl Y = some alternative paste??
      val itTextShare = if( isMac ) itTextShare0 ++ IndexedSeq(
         "control A",         DefaultEditorKit.beginLineAction,
         "control E",         DefaultEditorKit.endLineAction,
         "control shift E",   DefaultEditorKit.selectionEndLineAction
      ) else itTextShare0

      val itSingleShare = itTextShare ++ IndexedSeq(
         // cursor motion
         "HOME",              DefaultEditorKit.beginLineAction,
         "END",               DefaultEditorKit.endLineAction,

         // selections
         "shift UP",          DefaultEditorKit.selectionBeginLineAction,
         "shift DOWN",        DefaultEditorKit.selectionEndLineAction,
         mod3 + "shift HOME", DefaultEditorKit.selectionBeginLineAction,
         mod3 + "shift END",  DefaultEditorKit.selectionEndLineAction,

         // misc
         "ENTER",             JTextField.notifyAction
      )

      val itSingleLine = itSingleShare ++ IndexedSeq(
         // cursor motion
         "UP",                DefaultEditorKit.beginLineAction,
         "DOWN",              DefaultEditorKit.endLineAction
      )

      val mSingleLine = new UIDefaults.LazyInputMap( itSingleLine.toArray )
      m.put( "TextField.focusInputMap",     mSingleLine )
      m.put( "PasswordField.focusInputMap", mSingleLine )

      val itFormatted = itSingleShare ++ IndexedSeq(
         "ESCAPE",            "reset-field-edit",
         "UP",                "increment",
         "KP_UP",             "increment",
         "DOWN",              "decrement",
         "KP_DOWN",           "decrement"
      )

      val mFormatted = new UIDefaults.LazyInputMap( itFormatted.toArray )
      m.put( "FormattedTextField.focusInputMap", mFormatted )

      val itMultiLine0 = itTextShare ++ IndexedSeq(
         // cursor motion
         "UP",                DefaultEditorKit.upAction,
         "DOWN",              DefaultEditorKit.downAction,
         mod3 + "HOME",       DefaultEditorKit.beginAction,
         mod3 + "END",        DefaultEditorKit.endAction,
         "PAGE_UP",           DefaultEditorKit.pageUpAction,
         "PAGE_DOWN",         DefaultEditorKit.pageDownAction,
         "control P",         DefaultEditorKit.upAction,
         "control N",         DefaultEditorKit.downAction,

         // selections
         "shift UP",          DefaultEditorKit.selectionUpAction,
         "shift DOWN",        DefaultEditorKit.selectionDownAction,
         mod3 + "shift HOME", DefaultEditorKit.selectionBeginAction,
         mod3 + "shift END",  DefaultEditorKit.selectionEndAction,
         "shift PAGE_UP",     "selection-page-up",
         "shift PAGE_DOWN",   "selection-page-down",
         "control shift PAGE_UP", "selection-page-left",
         "control shift PAGE_DOWN", "selection-page-right",
         "control shift P",   DefaultEditorKit.selectionUpAction,
         "control shift N",   DefaultEditorKit.selectionDownAction,

         // insertions
         "ENTER",             DefaultEditorKit.insertBreakAction,
         "TAB",               DefaultEditorKit.insertTabAction,

         // misc
         "control T",         "next-link-action",
         "control shift T",   "previous-link-action",
         "control SPACE",     "activate-link-action"
      )

      val itMultiLine = if( isMac ) itMultiLine0 ++ IndexedSeq(
         "control shift V",   DefaultEditorKit.selectionEndAction
      ) else itMultiLine0

      val mMultiLine = new UIDefaults.LazyInputMap( itMultiLine.toArray )
      m.put( "TextArea.focusInputMap",   mMultiLine )
      m.put( "TextPane.focusInputMap",   mMultiLine )
      m.put( "EditorPane.focusInputMap", mMultiLine )

      m
   }

   def controlColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "control" )
      if( c == null ) defaultControlColor else c
   }

   def backgroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "background" )
      if( c == null ) defaultBackgroundColor else c
   }

   def focusColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusFocus" )
      if( c == null ) defaultFocusColor else c
   }

   def baseColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusBase" )
      if( c == null ) defaultBaseColor else c
   }

   def textColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "text" )
      if( c == null ) defaultTextColor else c
   }

   def selectedTextColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "selectedText" )
      if( c == null ) defaultSelectedTextColor else c
   }

   def controlHighlightColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "controlHighlight" )
      if( c == null ) defaultControlHighlightColor else c
   }

   def selectionBackgroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusSelectionBackground" )
      if( c == null ) defaultSelectionBackgroundColor else c
   }

   def disabledTextColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusDisabledText" )
      if( c == null ) defaultDisabledTextColor else c
   }

   def buttonBackgroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "Button.background" )
      if( c == null ) defaultButtonBackgroundColor else c
   }

   def buttonForegroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "Button.foreground" )
      if( c == null ) defaultButtonForegroundColor else c
   }

   def panelBackgroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "Panel.background" )
      if( c == null ) defaultPanelBackgroundColor else c
   }

   def panelForegroundColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "Panel.foreground" )
      if( c == null ) defaultPanelForegroundColor else c
   }

//   def blueGreyColor : Color = getBlueGreyColor( baseColor )
//
//   def getBlueGreyColor( base: Color ) : Color = {
//       val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusBlueGrey" )
//       if( c == null ) getDefaultBlueGreyColor( base ) else c
//   }

   def blueGreyColor : Color = {
      val c = if( nimbusDefaults == null ) null else nimbusDefaults.getColor( "nimbusBlueGrey" )
      if( c == null ) getDefaultBlueGreyColor( baseColor ) else c
   }

   private def getDefaultBlueGreyColor( base: Color ) : Color =
      ColorUtil.adjustColor( base, 0.032459438f, -0.52518797f, 0.19607842f, 0 )
}