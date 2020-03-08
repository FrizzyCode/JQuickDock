package com.frizz.dock.Display;

import com.alee.laf.tooltip.WebToolTip;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.utils.FileUtils;
import com.alee.utils.ImageUtils;
import com.frizz.dock.UI.Components.Dock.*;
import com.frizz.dock.UI.Viewers.SourceViewer;
import com.frizz.dock.Utilities.SafeIcon;
import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.NativeMonitorInfo;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Steven Frizell
 * @version 1.0.0a
 */
public class DockHandler implements MouseListener {

    /**
     *
     */
    private final CursorDock cursorDock;

    /**
     * The list of DockShortcuts and DockSeparators currently contained
     * within the dock.
     */
    private final Map<Clickable, SourceViewer> dockClickables;


    /**
     *
     */
    private final DockPopupHandler popupHandler;

    /**
     *
     */
    private boolean maskPressed;

    /**
     *
     */
    private boolean secMaskPressed;

    /**
     *
     */
    private boolean actButtonPressed;

    /**
     *
     */
    protected DockHandler() {
        cursorDock = new CursorDock();

        dockClickables = new HashMap<Clickable, SourceViewer>();
        popupHandler = new DockPopupHandler(this);

        GlobalHelper helper = new GlobalHelper( cursorDock );

        populateForTest();

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(helper);

        helper.showDock();
    }

    /**
     *
     * @param sourcePath
     */
    public DockShortcut addShortcut(String sourcePath) {
        SourceViewer viewer = createSourceViewerFor(sourcePath);
        DockShortcut shortcut = new DockShortcut(sourcePath);
        dockClickables.put(shortcut, viewer);

        if (cursorDock.isInUse()) {

        }

        return shortcut;
    }

    /**
     * Removes a shortcut from the dock(s).
     * @param shortcut
     */
    public void deleteShortcut(DockShortcut shortcut) {
        if (cursorDock.isInUse()) {

        }
    }

    /**
     *
     * @param sourcePath
     * @return
     */
    private SourceViewer createSourceViewerFor(String sourcePath) {
        SourceViewer viewer = new SourceViewer(sourcePath);

        return viewer;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            if (e.getSource() instanceof DockShortcut) {
                if (!popupHandler.isShortcutPopupShowing()) {
                    DockShortcut shortcut = (DockShortcut) e.getSource();

                    popupHandler.show(shortcut, e.getPoint());
                } else {
                    popupHandler.hide();
                }
            } else if (e.getSource() instanceof DockSeparator) {
                if (!popupHandler.isDockPopupShowing()) {
                    DockSeparator separator = (DockSeparator) e.getSource();
                    popupHandler.show(separator, e.getPoint());
                } else {
                    popupHandler.hide();
                }
            }
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            if (e.getSource() instanceof DockShortcut) {
                DockShortcut shortcut = (DockShortcut) e.getSource();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void populateForTest() {
        String s = File.separator;

        File desktop = new File(System.getProperty("user.home") + s + "Desktop");

       if ( desktop.isDirectory()) {
           File[] files = desktop.listFiles();

            List<DockShortcut> shortcuts = new ArrayList<>(  );

           for (File f : files) {
               ImageIcon icon = ImageUtils.toImageIcon(FileUtils.getFileIcon( f, true ));

               DockShortcut shortcut = new DockShortcut(f.getAbsolutePath(), f.getName(), new Dimension(56, 56),
                                                        icon);
               shortcut.addMouseListener(this);

               SourceViewer viewer = new SourceViewer(f.getAbsolutePath());

               dockClickables.put(shortcut, viewer);
               shortcuts.add( shortcut );

               cursorDock.addItem(shortcut);

               if (cursorDock.getComponentCount() == cursorDock.getMaxShortcuts()) {
                   break;
               }
           }

           int length = shortcuts.size();
           int half = length / 2;
           int i = 0;

           TooltipWay way = TooltipWay.down;

           for (DockShortcut ds : shortcuts) {
               switch (i) {
                   case 0:

                           break;
               }
           }
        }
    }
}
