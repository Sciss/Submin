package de.sciss.submin;

import com.alee.extended.style.StyleEditor;
import com.alee.laf.label.WebLabel;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.text.WebEditorPane;
import com.alee.managers.style.Skin;
import com.alee.utils.xml.ResourceFile;
import com.alee.utils.xml.ResourceLocation;

import javax.swing.*;

public class SubminStyleEditor {
    public static void main(String[] args) {
        final boolean isDark = args.length > 0 && args[0].equals("--dark");

        final Class<? extends Skin> skinClass = isDark ? SubminDarkSkin.class : SubminLightSkin.class;

        Submin.install(isDark);

        final String prefix = isDark ? "dark" : "light";

        // Edited skin file
        final ResourceFile skin = new ResourceFile(ResourceLocation.nearClass, prefix + "/skin.xml", skinClass);

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
            }
        };
        final WebMenuBar mb = new WebMenuBar();
        final WebMenu m1 = new WebMenu("File");
        final WebMenu m3 = new WebMenu("Open Recent");
        final WebMenuItem mi1 = new WebMenuItem("Open...");
        final WebMenuItem mi2 = new WebMenuItem("Save");
        final WebMenuItem mi5 = new WebMenuItem("Document.txt");
        mi2.setEnabled(false);
        m1.add(mi1);
        m1.add(m3);
        m1.addSeparator();
        m1.add(mi2);
        m3.add(mi5);
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
        styleEditor.setJMenuBar(mb);
        styleEditor.setVisible(true);
    }
}
