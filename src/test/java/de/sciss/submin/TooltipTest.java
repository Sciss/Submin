package de.sciss.submin;

import com.alee.laf.WebLookAndFeel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.EventQueue;

public class TooltipTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            WebLookAndFeel.install();
            final JFrame f = new JFrame("Test");

            final JMenuBar  mb      = new JMenuBar();
            final JMenu     mFile   = new JMenu("File");
            final JMenuItem mNew    = new JMenu("New");
            mFile.add(mNew);
            mb.add(mFile);
            f.setJMenuBar(mb);

            final JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
            p.add(Box.createHorizontalStrut(50));
            final JLabel lbNumUGens  = new JLabel("tool-tip");
            lbNumUGens.setToolTipText("UGens");
            p.add(lbNumUGens);
            p.add(Box.createHorizontalStrut(50));

            f.setResizable(false);
            f.getContentPane().add(p);
            f.pack();
            f.setVisible(true);
        });
    }
}
