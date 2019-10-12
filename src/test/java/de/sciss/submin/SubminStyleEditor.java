package de.sciss.submin;

import com.alee.extended.style.StyleEditor;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.label.WebLabel;
import com.alee.laf.menu.WebCheckBoxMenuItem;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.menu.WebRadioButtonMenuItem;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.text.WebEditorPane;
import com.alee.managers.style.Skin;
import com.alee.utils.xml.Resource;

import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;

public class SubminStyleEditor {
    public static void main(String[] args) {
        final boolean isDark = args.length > 0 && args[0].equals("--dark");

        final Class<? extends Skin> skinClass = isDark ? SubminDarkSkin.class : SubminLightSkin.class;
//        final Class<? extends Skin> skinClass = com.alee.skin.web.WebSkin.class;

        // trick to set global font size:
//        WebLookAndFeel.globalControlFont = WebLookAndFeel.globalControlFont.deriveFont ( 15f );

        // XXX TODO
        Submin.install(isDark);
//        WebLookAndFeel.install(skinClass);

        final String prefix = isDark ? "dark" : "light";

        // Edited skin file
        // XXX TODO
        final Resource skin = new Resource(skinClass, prefix + "/skin.xml");
//        final Resource skin = new Resource(skinClass, "resources/skin.xml");

        // Displaying StyleEditor
        final StyleEditor styleEditor = new StyleEditor(skin) {
            @Override
            protected void createPreviewPanel() {
                super.createPreviewPanel();

                final WebProgressBar progress = new WebProgressBar();
//                progress.setValue ( 0 );
                progress.setIndeterminate(true);
                progress.setString("Label");
                progress.setStringPainted(true);
                addViewComponent("Progress (indeterminate)", progress, progress, true);

                final WebLabel label = new WebLabel("<html><body>An <i>HTML</i> <b>label</b>.<br><A HREF=\"foo\">link</A></body>");
                addViewComponent("Label with HTML", label, label, true);

                final String editorHTML = "<html><body><h1>Heading</h1><p>Paragraph</p><ul><li>List 1</li><li>List 2</li></ul></body>";
                final WebEditorPane editor = new WebEditorPane("text/html", editorHTML);
                editor.putClientProperty("styleId", "decorated");
                addViewComponent("Editor pane with HTML", editor, editor, true);

//                final WebSplitPane split = new WebSplitPane();
//                editor.putClientProperty("styleId", "decorated");

            }
        };
        final WebMenuBar mb = new WebMenuBar();
        final WebMenu m1 = new WebMenu("File");
        final WebMenu m3 = new WebMenu("Open Recent");
        final WebMenu m4 = new WebMenu("Foo Bar");
        final WebMenuItem mi1 = new WebMenuItem("Open...");
        final WebMenuItem mi2 = new WebMenuItem("Save");
        final WebMenuItem mi5 = new WebMenuItem("Document.txt");
        final WebCheckBoxMenuItem mi6 = new WebCheckBoxMenuItem("Checkbox");
        final WebCheckBoxMenuItem mi6d = new WebCheckBoxMenuItem("Checkbox");
        final WebRadioButtonMenuItem mi7 = new WebRadioButtonMenuItem("Radio 1");
        final WebRadioButtonMenuItem mi7d = new WebRadioButtonMenuItem("Radio 1");
        final WebRadioButtonMenuItem mi8 = new WebRadioButtonMenuItem("Radio 2");
        final WebRadioButtonMenuItem mi8d = new WebRadioButtonMenuItem("Radio 2");
        final ButtonGroup bg1 = new ButtonGroup();
        final ButtonGroup bg1d = new ButtonGroup();
        mi2.setEnabled(false);
        m1.add(mi1);
        m1.add(m3);
        m1.addSeparator();
        m1.add(mi2);
        m3.add(mi5);
        bg1.add(mi7);
        bg1.add(mi8);
        bg1d.add(mi7d);
        bg1d.add(mi8d);
        bg1.setSelected(mi7.getModel(), true);
        bg1d.setSelected(mi7d.getModel(), true);
        mi6d.setEnabled(false);
        mi7d.setEnabled(false);
        mi8d.setEnabled(false);
        m4.add(mi6);
        m4.add(mi7);
        m4.add(mi8);
        m4.add(mi6d);
        m4.add(mi7d);
        m4.add(mi8d);

        final WebMenu m2 = new WebMenu("Edit");
        final WebMenuItem mi3 = new WebMenuItem("Cut");
        mi3.setAccelerator(KeyStroke.getKeyStroke("control X"));
        final WebMenuItem mi4 = new WebMenuItem("Paste");
        mi4.setAccelerator(KeyStroke.getKeyStroke("control V"));
        mi4.setEnabled(false);
        m2.add(mi3);
        m2.add(mi4);
        mb.add(m1);
        mb.add(m2);
        mb.add(m4);
        styleEditor.setJMenuBar(mb);
        styleEditor.setVisible(true);
    }
}
