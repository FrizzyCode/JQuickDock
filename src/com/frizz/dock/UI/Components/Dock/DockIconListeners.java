package com.frizz.dock.UI.Components.Dock;

import com.alee.extended.window.PopOverDirection;
import com.alee.extended.window.WebPopOver;
import com.frizz.dock.UI.Drag.ComponentMover;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DockIconListeners extends MouseAdapter {

    /**
     * The Clickable that represents a file or web on the dock.
     */
    final DockShortcut dockShortcut;


    /**
     * The custom tooltip that displays when the user places the cursor above the DockIcon.
     */
    final WebPopOver toolTip;


    /**
     * Constructs a new DockShortcutListeners instance to listen for mouse events on the
     * DockShortcut.
     */
    DockIconListeners(DockShortcut dockable, WebPopOver tooltip) {
        this.dockShortcut = dockable;
        this.toolTip = tooltip;

    }


    /**
     * If the tooltip is currently visible.
     * @return
     */
    boolean isTooltipVisible() {
        return toolTip.isVisible();
    }



    /**
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (!toolTip.isShowing()) {
            toolTip.show(dockShortcut, PopOverDirection.up);
        }
    }

    /**
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (toolTip.isShowing()) {
            toolTip.setVisible(false);
        }
    }
}

