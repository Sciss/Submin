package de.sciss.submin

import java.awt.{FlowLayout, BorderLayout, EventQueue}
import javax.swing.{JTextField, JCheckBox, JSlider, JButton, JLabel, JPanel, WindowConstants, JSplitPane, JFrame}

object PanelTest extends Runnable {
   def main( args: Array[ String ]) {
      EventQueue.invokeLater( this )
   }

   def run() {
      SubminHelper.setLookAndFeel()
      val f       = new JFrame( "Submin" )
      val cp      = f.getContentPane
      val split   = new JSplitPane()
      val nimbus  = new JPanel()
      val submin  = new JPanel()
      submin.putClientProperty( "submin", true )
      submin.setUI( new PanelUI )
      addWidgets( nimbus )
      nimbus.add( new JButton( "Button" ))
      val testBut = new JButton( "Button" )
      testBut.setUI( new ButtonUI )
      nimbus.add( testBut )
      val disabledNimbus = new JButton( "Disabled" )
      disabledNimbus.setEnabled( false )
      nimbus.add( disabledNimbus )
      val disabledSubmin = new JButton( "Disabled" )
      disabledSubmin.setUI( new ButtonUI )
      disabledSubmin.setEnabled( false )
      nimbus.add( disabledSubmin )
      val focusNimbus = new JButton( "No Focus" )
      focusNimbus.setFocusable( false )
      nimbus.add( focusNimbus )
      val focusSubmin = new JButton( "No Focus" )
      focusSubmin.setUI( new ButtonUI )
      focusSubmin.setFocusable( false )
      nimbus.add( focusSubmin )
//      val defaultNimbus = new JButton( "Default" )
//      defaultNimbus.setDefaultCapable( true )
//      nimbus.add( defaultNimbus )
//      val defaultSubmin = new JButton( "Default" )
//      defaultSubmin.setUI( new ButtonUI )
//      defaultSubmin.setDefaultCapable( true )
//      nimbus.add( defaultSubmin )
      addWidgets( submin )
      val subminBut = new JButton( "Button" )
      subminBut.putClientProperty( "submin", true )
      subminBut.setUI( new ButtonUI )
      submin.add( subminBut )
      val disabledSubmin2 = new JButton( "Disabled" )
      disabledSubmin2.putClientProperty( "submin", true )
      disabledSubmin2.setUI( new ButtonUI )
      disabledSubmin2.setEnabled( false )
      submin.add( disabledSubmin2 )
      val focusSubmin2 = new JButton( "No Focus" )
      focusSubmin2.putClientProperty( "submin", true )
      focusSubmin2.setUI( new ButtonUI )
      focusSubmin2.setFocusable( false )
      submin.add( focusSubmin2 )

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
      p.add( new JSlider() )
      p.add( new JCheckBox( "Check Box" ))
      p.add( new JTextField( "Text Field" ))
   }
}
