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
      addWidgets( submin )
      val subminBut = new JButton( "Button" )
      subminBut.setUI( new ButtonUI )
      submin.add( subminBut )
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
