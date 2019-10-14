package de.sciss.submin;

import com.alee.laf.WebLookAndFeel;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class MenuTest implements Runnable {
    public static void main(String[] args) {
        EventQueue.invokeLater(new MenuTest());
    }

    @Override
    public void run() {
        WebLookAndFeel.install();
        final JMenuBar          mb  = new JMenuBar();
        final JMenu             m   = new JMenu("Menu");
        final JCheckBoxMenuItem ci1 = new JCheckBoxMenuItem("Text-based");
        final Action a1 = new AbstractAction("Action-based") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action");
            }
        };
        final JCheckBoxMenuItem ci2 = new JCheckBoxMenuItem(a1);
        final JRadioButtonMenuItem ri1 = new JRadioButtonMenuItem("Text-based");
        final Action a2 = new AbstractAction("Action-based") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action");
            }
        };
        final JRadioButtonMenuItem ri2 = new JRadioButtonMenuItem(a2);

        m.add(ci1);
        m.add(ci2);
        m.add(ri1);
        m.add(ri2);
        mb.add(m);

        final JFrame f = new JFrame();
        f.setJMenuBar(mb);
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
