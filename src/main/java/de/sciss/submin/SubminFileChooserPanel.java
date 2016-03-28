/*
 *  SubminFileChooserPanel.scala
 *  (Submin)
 *
 *  Copyright (c) 2012-2016 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v3+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.submin;

import com.alee.extended.tree.FileTreeNode;
import com.alee.extended.tree.FileTreeRootType;
import com.alee.extended.tree.WebFileTree;
import com.alee.laf.filechooser.FileChooserType;
import com.alee.laf.filechooser.FileChooserViewType;
import com.alee.laf.filechooser.WebFileChooserPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.managers.style.StyleId;
import com.alee.utils.FileUtils;
import com.alee.utils.SystemUtils;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Extends WebFileChooserPanel by using user favourites
 * as roots on Linux/Gnome.
 *
 * Uses tables view as default (currently not customizable via XML).
 */
public class SubminFileChooserPanel extends WebFileChooserPanel {
    public SubminFileChooserPanel(final FileChooserType chooserType, final boolean showControlButtons) {
        super(chooserType, showControlButtons);
        setViewType(FileChooserViewType.table);
    }

    protected java.util.List<File> getFavourites() {
        final ArrayList<File> list = new ArrayList<File>(16);
        if (SystemUtils.isWindows()) {
            // todo: find appropriate bookmarks
        } else if (SystemUtils.isMac()) {
            // todo: find appropriate bookmarks
        } else if (SystemUtils.isUnix()) {
            final File userHome = FileUtils.getUserHome();
            // cf. http://askubuntu.com/questions/192747/where-does-nautilus-stores-bookmark-names
            final File fGtkBookMarks = new File(new File(new File(userHome, ".config"), "gtk-3.0"), "bookmarks");
            if (fGtkBookMarks.isFile()) {
                list.add(userHome);
                // these are always added by Nautilus
                final String[] children = new String[]{"Documents", "Downloads", "Music", "Pictures", "Videos"};
                for (String aChildren : children) {
                    final File f = new File(userHome, aChildren);
                    if (f.isDirectory()) list.add(f);
                }
                try {
                    final InputStream fis = new FileInputStream(fGtkBookMarks);
                    try {
                        final InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                        final BufferedReader r = new BufferedReader(isr);
                        while (r.ready()) {
                            final String line = r.readLine();
                            if (line.startsWith("file://")) {
                                final int stop = line.indexOf(' ');
                                if (stop > 0) {
                                    final URI uri = new URI(line.substring(0, stop));
                                    final File f = new File(uri);
                                    if (!list.contains(f)) list.add(f);
                                }
                            }
                        }
                    } finally {
                        fis.close();
                    }
                } catch (Exception e) {
                    // ignore
                }
            }
        }
        return list;
    }

    @Override
    protected void createFileTree() {
        treeScroll = new WebScrollPane(StyleId.filechooserNavScroll.at(centralSplit));
        treeScroll.setPreferredSize(new Dimension(dividerLocation, 1));

        final java.util.List<File> rootsFav = getFavourites();
        final boolean hasFav = !rootsFav.isEmpty();
        final java.util.List<File> roots = hasFav ? rootsFav : FileTreeRootType.drives.getRoots();
        fileTree = new WebFileTree(StyleId.filechooserFileTree.at(treeScroll), roots);
        fileTree.setAutoExpandSelectedNode(!hasFav);

        if (hasFav) {
            // disable automatic sorting
            fileTree.setComparator(new Comparator<FileTreeNode>() {
                @Override
                public int compare(FileTreeNode n1, FileTreeNode n2) {
                    final int idx1 = rootsFav.indexOf(n1.getFile());
                    final int idx2 = rootsFav.indexOf(n2.getFile());
                    return idx1 < idx2 ? -1 : (idx1 > idx2 ? 1 : 0);
                }
            });
            // todo: un-expand root; doesn't work
        }

        fileTree.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeScroll.setViewportView(fileTree);

        fileTreeListener = new TreeSelectionListener() {
            @Override
            public void valueChanged(final TreeSelectionEvent e) {
                if (fileTree.getSelectionCount() > 0) {
                    updateCurrentFolder(fileTree.getSelectedFile(), UpdateSource.tree);
                }
            }
        };
        fileTree.addTreeSelectionListener(fileTreeListener);
    }
}
