package de.sciss.submin

import java.awt.{FlowLayout, BorderLayout, EventQueue}
import javax.swing.{JTextArea, JTextField, JCheckBox, JSlider, JButton, JLabel, JPanel, WindowConstants, JSplitPane, JFrame}

object PanelTest extends Runnable {
   def main( args: Array[ String ]) {
      EventQueue.invokeLater( this )
   }

   val setUI = false

   def run() {
//      SubminUtil.init()
      Submin.init()
      val f       = new JFrame( "Submin" )
      val cp      = f.getContentPane
      val split   = new JSplitPane()
      val nimbus  = new JPanel()
      val submin  = new JPanel()
      submin.putClientProperty( "submin", true )
//         override def getClientProperty( key: Any ) : Any = key match {
//            case "submin"  => true
//            case _ => super.getClientProperty( key )
//         }
//println( "AAAA" )
      if( setUI ) submin.setUI( new SubminPanelUI )
      addWidgets( nimbus )
      nimbus.add( new JButton( "Button" ))
      val testBut = new JButton( "Button" )
      if( setUI ) testBut.setUI( new SubminButtonUI )
      nimbus.add( testBut )
      val disabledNimbus = new JButton( "Disabled" )
      disabledNimbus.setEnabled( false )
      nimbus.add( disabledNimbus )
      val disabledSubmin = new JButton( "Disabled" )
      if( setUI ) disabledSubmin.setUI( new SubminButtonUI )
      disabledSubmin.setEnabled( false )
      nimbus.add( disabledSubmin )
      val focusNimbus = new JButton( "No Focus" )
      focusNimbus.setFocusable( false )
      nimbus.add( focusNimbus )
      val focusSubmin = new JButton( "No Focus" )
      if( setUI ) focusSubmin.setUI( new SubminButtonUI )
      focusSubmin.setFocusable( false )
      nimbus.add( focusSubmin )
//      val defaultNimbus = new JButton( "Default" )
//      defaultNimbus.setDefaultCapable( true )
//      nimbus.add( defaultNimbus )
//      val defaultSubmin = new JButton( "Default" )
//      if( setUI ) defaultSubmin.setUI( new SubminSubminButtonUI )
//      defaultSubmin.setDefaultCapable( true )
//      nimbus.add( defaultSubmin )
      val smallNimbus = new JButton( "Small" )
      smallNimbus.putClientProperty( "JComponent.sizeVariant", "small" )
      nimbus.add( smallNimbus )
      val smallSubmin = new JButton( "Small" )
      smallSubmin.putClientProperty( "JComponent.sizeVariant", "small" )
      if( setUI ) smallSubmin.setUI( new SubminButtonUI )
      nimbus.add( smallSubmin )
      addWidgets( submin )
      val subminBut = new JButton( "Button" )
//      subminBut.putClientProperty( "submin", true )
      if( setUI ) subminBut.setUI( new SubminButtonUI )
      submin.add( subminBut )
      val disabledSubmin2 = new JButton( "Disabled" )
//      disabledSubmin2.putClientProperty( "submin", true )
      if( setUI ) disabledSubmin2.setUI( new SubminButtonUI )
      disabledSubmin2.setEnabled( false )
      submin.add( disabledSubmin2 )
      val focusSubmin2 = new JButton( "No Focus" )
//      focusSubmin2.putClientProperty( "submin", true )
      if( setUI ) focusSubmin2.setUI( new SubminButtonUI )
      focusSubmin2.setFocusable( false )
      submin.add( focusSubmin2 )
      val smallSubmin2 = new JButton( "Boot" )
      smallSubmin2.putClientProperty( "JComponent.sizeVariant", "small" )
//      smallSubmin2.putClientProperty( "submin", true )
      if( setUI ) smallSubmin2.setUI( new SubminButtonUI )
      submin.add( smallSubmin2 )

      nimbus.add( new JTextArea( 40, 20 ))

      split.setLeftComponent( nimbus )
      split.setRightComponent( submin )
      split.setResizeWeight( 0.5 )
      split.setDividerLocation( 190 )
      cp.add( split, BorderLayout.CENTER )
      f.setSize( 400, 400 )
      f.setLocationRelativeTo( null )
      f.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE )
      f.setVisible( true )
   }

   private def addWidgets( p: JPanel ) {
      p.setLayout( new FlowLayout() )
      p.add( new JLabel( "Label" ))
      val lbSmall = new JLabel( "Small" )
      lbSmall.putClientProperty( "JComponent.sizeVariant", "small" )
      p.add( lbSmall )
      p.add( new JSlider() )
      p.add( new JCheckBox( "Check Box" ))
      val cbNoFocus = new JCheckBox( "No Focus" )
      cbNoFocus.setFocusable( false )
      p.add( cbNoFocus )
      val cbDisabled = new JCheckBox( "Disabled" )
      cbDisabled.setEnabled( false )
      p.add( cbDisabled )
      p.add( new JTextField( "Text Field" ))
   }
}
