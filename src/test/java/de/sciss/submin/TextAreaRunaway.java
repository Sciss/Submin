package de.sciss.submin;

import com.alee.laf.WebLookAndFeel;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class TextAreaRunaway implements Runnable {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.runtime.version"));

        EventQueue.invokeLater(new TextAreaRunaway());
    }

    private JTextArea textArea(int rows) {
        final JTextArea res = new JTextArea(rows, 12);
        res.setEditable(false);
        res.setWrapStyleWord(true);
        res.setLineWrap(true);
        return res;
    }

    @Override
    public void run() {
        WebLookAndFeel.install();
        final JComponent ggTags         = textArea(2);
        final JComponent ggDescription  = textArea(4);
        final JComponent lbTags         = new JLabel("Tags"       );
        final JComponent lbDescription  = new JLabel("Description");
        final JPanel p = new JPanel();
        final GroupLayout lay = new GroupLayout(p);
        lay.setAutoCreateGaps(true);
        p.setLayout(lay);
        GroupLayout.SequentialGroup hGroup = lay.createSequentialGroup();
        hGroup.addGroup(lay.createParallelGroup().
                addComponent(lbTags).addComponent(lbDescription));
        hGroup.addGroup(lay.createParallelGroup().
                addComponent(ggTags).addComponent(ggDescription));
        lay.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = lay.createSequentialGroup();
        vGroup.addGroup(lay.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(lbTags).addComponent(ggTags));
        vGroup.addGroup(lay.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(lbDescription).addComponent(ggDescription));
        lay.setVerticalGroup(vGroup);

//        p.setLayout(new GridLayout(2, 2));
//        final GridBagConstraints con = new GridBagConstraints();
//        final GridBagLayout lay = new GridBagLayout();
//        p.setLayout(lay);
//        con.gridwidth   = 1;
//        con.weightx     = 0.0;
//        con.fill        = GridBagConstraints.NONE;
//        lay.setConstraints(lbTags, con);
//        con.gridwidth   = GridBagConstraints.REMAINDER;
//        con.weightx     = 1.0;
//        con.fill        = GridBagConstraints.BOTH;
//        lay.setConstraints(ggTags, con);
//        con.gridwidth   = 1;
//        con.weightx     = 0.0;
//        con.fill        = GridBagConstraints.NONE;
//        lay.setConstraints(lbDescription, con);
//        con.gridwidth   = GridBagConstraints.REMAINDER;
//        con.weightx     = 1.0;
//        con.fill        = GridBagConstraints.BOTH;
//        lay.setConstraints(ggDescription, con);
//        p.add(lbTags);
//        p.add(ggTags);
//        p.add(lbDescription);
//        p.add(ggDescription);

        final JScrollPane scroll = new JScrollPane(p);
        final JViewport vp = scroll.getViewport();
//        vp.setPreferredSize(vp.getPreferredSize());
        vp.setMaximumSize(vp.getPreferredSize());

        final JFrame f = new JFrame();
        f.getContentPane().add(scroll);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
